// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import java.util.ArrayList;
import static jminusminus.CLConstants.*;

/**
 * An interface declaration has a list of modifiers, a name, a list of extended interfaces and an
 * interface block;
 *  This class is not final and in progress.
 */

class JInterfaceDeclaration extends JAST implements JTypeDecl {

    /** interface modifiers. */
    private ArrayList<String> mods;

    /** interface name. */
    private String name;

    /** interface block. */
    private ArrayList<JMember> interfaceBlock;

    /** Extended interface types. */
    private ArrayList<Type> superInterfaces;

    /** This interface type. */
    private Type thisType;

    /** Context for this interface. */
    private ClassContext context;

    /** Static (class) fields of this class. */
    private ArrayList<JFieldDeclaration> staticFieldInitializations;

    /** Names of the super interfaces */
    private ArrayList<String> superInterfaceNames;

    /**
     * Constructs an AST node for an interface declaration given the line number, list
     * of interface modifiers, name of the interface, super interfaces, and the
     * interface block.
     * 
     * @param line
     *            line in which the interface declaration occurs in the source file.
     * @param mods
     *            interface modifiers.
     * @param name
     *            interface name.
     * @param superInterfaces
     *             interfaces extended by this interface.
     * @param interfaceBlock
     *            interface block.
     */

    public JInterfaceDeclaration(int line, ArrayList<String> mods, String name, ArrayList<Type> superInterfaces, ArrayList<JMember> interfaceBlock) {
        super(line);
        this.mods = mods;
        this.name = name;
        this.superInterfaces = superInterfaces;
        this.interfaceBlock = interfaceBlock;
        staticFieldInitializations = new ArrayList<JFieldDeclaration>();
        superInterfaceNames = new ArrayList<String>();
    }

    public String name() {
        return name;
    }

    public Type superType() {
        return Type.OBJECT;
    }

    public Type thisType() {
        return thisType;
    }

    /**
     * Declares this interface in the parent (compilation unit) context.
     * 
     * @param context
     *            the parent (compilation unit) context.
     */

    public void declareThisType(Context context) {
        String qualifiedName = JAST.compilationUnit.packageName() == "" ? name : JAST.compilationUnit.packageName() + "/" + name;
        CLEmitter partial = new CLEmitter(false);

        for (String mod: mods) {
          if (mod != "public" && mod != "abstract")
            JAST.compilationUnit.reportSemanticError(line, "Non-nested interfaces cannot have %s modifier", mod);
        }
        if (!mods.contains("abstract")) mods.add("abstract");
        mods.add("interface");

        partial.addClass(mods, qualifiedName, Type.OBJECT.jvmName(), null, false);
        thisType = Type.typeFor(partial.toClass());
        context.addType(line, thisType);
    }

    /**
     * Pre-analyzes the members of this declaration in the parent context.
     * Pre-analysis extends to the member headers (including method headers) but
     * not into the bodies.
     * 
     * @param context
     *            the parent (compilation unit) context.
     */

    public void preAnalyze(Context context) {
        // Construct a class context
        this.context = new ClassContext(this, context);

        for (int i = 0; i < superInterfaces.size(); ++i) {
          superInterfaces.set(i, superInterfaces.get(i).resolve(this.context));
          thisType.checkAccess(line, superInterfaces.get(i));
          superInterfaceNames.add(superInterfaces.get(i).jvmName());
        }

        // Create the (partial) class
        CLEmitter partial = new CLEmitter(false);

        // Add the class header to the partial class
        String qualifiedName = JAST.compilationUnit.packageName() == "" ? name : JAST.compilationUnit.packageName() + "/" + name;
        partial.addClass(mods, qualifiedName, Type.OBJECT.jvmName(), superInterfaceNames, false);

        // Pre-analyze the members and add default implicit modifiers if not present
        for (JMember member: interfaceBlock) {
          if (member instanceof JFieldDeclaration) {
            for (String mod: ((JFieldDeclaration) member).mods()) {
              if (mod != "public" && mod != "static" && mod != "final")
                JAST.compilationUnit.reportSemanticError(line, "Interface fields cannot have %s modifier", mod);
            }
            ((JFieldDeclaration) member).maybeAddMod("public");
            ((JFieldDeclaration) member).maybeAddMod("static");
            ((JFieldDeclaration) member).maybeAddMod("final");
          } else if (member instanceof JMethodDeclaration) {
            for (String mod: ((JMethodDeclaration) member).mods()) {
              if (mod != "public" && mod != "abstract")
                JAST.compilationUnit.reportSemanticError(line, "Interface methods cannot have %s modifier", mod);
            }
            ((JMethodDeclaration) member).maybeAddMod("public");
            ((JMethodDeclaration) member).maybeAddMod("abstract");
          }
          member.preAnalyze(this.context, partial);
        }

        // Get the Class rep for the (partial) class and make it
        // the
        // representation for this type
        Type id = this.context.lookupType(name);
        if (id != null && !JAST.compilationUnit.errorHasOccurred()) {
            id.setClassRep(partial.toClass());
        }
    }

    public JAST analyze(Context context) {
      // Analyze all members
      for (JMember member : interfaceBlock) {
          ((JAST) member).analyze(this.context);
      }

      // Copy declared fields for purposes of initialization.
      for (JMember member : interfaceBlock) {
        if (member instanceof JFieldDeclaration) {
          for (JVariableDeclarator decl: ((JFieldDeclaration) member).decls()) {
            if (decl.initializer() == null)
              JAST.compilationUnit.reportSemanticError(line, "Interface fields must be initialized");
          }
          staticFieldInitializations.add((JFieldDeclaration) member);
        }
      }

      thisType.checkedAbstractMethods(line);

      return this;
    }

    /**
     * Generates code for the interface declaration.
     *
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        // The class header
        String qualifiedName = JAST.compilationUnit.packageName() == "" ? name : JAST.compilationUnit.packageName() + "/" + name;
        output.addClass(mods, qualifiedName, Type.OBJECT.jvmName(), superInterfaceNames, false);

        // The members
        for (JMember member: interfaceBlock) {
            ((JAST) member).codegen(output);
        }

        // Generate code for static fields on class initialization
        if (staticFieldInitializations.size() > 0) {
            codegenClassInit(output);
        }
    }

    /**
     * Generates code for interface initialization, in j-- this means static field
     * initializations.
     *
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    private void codegenClassInit(CLEmitter output) {
      ArrayList<String> mods = new ArrayList<String>();
      mods.add("public");
      mods.add("static");
      output.addMethod(mods, "<clinit>", "()V", null, false);

      // If there are instance initializations, generate code
      // for them
      for (JFieldDeclaration staticField : staticFieldInitializations) {
          staticField.codegenInitializations(output);
      }

      // Return
      output.addNoArgInstruction(RETURN);
    }

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JInterfaceDeclaration line=\"%d\" name=\"%s\""
                + " superInterfaces=%s>\n", line(), name, superInterfaces.toString());
        p.indentRight();
        if (context != null) {
            context.writeToStdOut(p);
        }
        if (mods != null) {
            p.println("<Modifiers>");
            p.indentRight();
            for (String mod : mods) {
                p.printf("<Modifier name=\"%s\"/>\n", mod);
            }
            p.indentLeft();
            p.println("</Modifiers>");
        }
        if (interfaceBlock != null) {
            p.println("<InterfaceBlock>");
            p.indentRight();
            for (JMember member : interfaceBlock) {
                ((JAST) member).writeToStdOut(p);
            }
            p.indentLeft();
            p.println("</InterfaceBlock>");
        }
        p.indentLeft();
        p.println("</JInterfaceDeclaration>");
    }
}
