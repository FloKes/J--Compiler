// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import java.util.ArrayList;
import static jminusminus.CLConstants.*;

/**
 * An interface declaration has a list of modifiers, a name, a list of extended interfaces and an
 * interface block; it distinguishes between interface fields and static (class)
 * fields for initialization, and it defines a type. It also introduces its own
 * (interface) context.
 *  This class is not final and in progress.
 */

class JInterfaceDeclaration extends JAST {

    /** interface modifiers. */
    private ArrayList<String> mods;

    /** interface name. */
    private String name;

    /** interface block. */
    private ArrayList<JMember> interfaceBlock;

    /** Extended interface types. */
    private ArrayList<Type> extendedInterfaces;

    /** This interface type. */
    private Type thisType;

    /** Context for this interface. */
    private ClassContext context;

    /** Interface fields of this interface. */
    private ArrayList<JFieldDeclaration> interfaceFieldInitializations;

    /** Static fields of this interface. */
    private ArrayList<JFieldDeclaration> staticFieldInitializations;

    /**
     * Constructs an AST node for an interface declaration given the line number, list
     * of interface modifiers, name of the interface, extended interfaces, and the
     * interface block.
     * 
     * @param line
     *            line in which the interface declaration occurs in the source file.
     * @param mods
     *            interface modifiers.
     * @param name
     *            interface name.
     * @param extendedInterfaces
     *             interfaces extended by this interface.
     * @param interfaceBlock
     *            interface block.
     */

    public JInterfaceDeclaration(int line, ArrayList<String> mods, String name, ArrayList<Type> extendedInterfaces, ArrayList<JMember> interfaceBlock) {
        super(line);
        this.mods = mods;
        this.name = name;
        this.extendedInterfaces = extendedInterfaces;
        this.interfaceBlock = interfaceBlock;
        interfaceFieldInitializations = new ArrayList<JFieldDeclaration>();
        staticFieldInitializations = new ArrayList<JFieldDeclaration>();
    }

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JInterfaceDeclaration line=\"%d\" name=\"%s\""
                + " extendedInterfaces=%s>\n", line(), name, extendedInterfaces.toString());
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

    // TO BE IMPLEMENTED
    public void codegen(CLEmitter output) {}

    // TO BE IMPLEMENTED
    public JAST analyze(Context context) {
        return this;
    }
}
