// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import java.util.ArrayList;

import static jminusminus.CLConstants.GOTO;

/**
 * The AST node for a for-statement.
 */

class JForEachStatement extends JStatement {

    /** Declarator for hidden array. */
    private JStatement hiddenArrayDeclaration;

    /** The for loop declarations. Contains #index */
    private JStatement declaration;

    /** The formal parameter of the for statement*/
    private JFormalParameter formalParameter;

    /** The array. */
    private JVariable array;

    /** Test expression. */
    private JExpression condition;

    /** Update the variable. */
    // We need to create this ourselves
    private ArrayList<JStatement> forUpdate;

    /** The body. */
    private JStatement body;

    /** The stateement as a For statement */
    private JForStatement forStatement;

    /** The Local context. */
    private LocalContext localContext;

    /**
     * Constructs an AST node for a for-statement given its line number, the
     * test expression, and the body.
     *
     * @param line
     *            line in which the for-statement occurs in the source file.
     *            test expression.
     * @param body
     *            the body.
     */

    public JForEachStatement(int line, JFormalParameter formalParameter, JVariable array, JStatement body) {
        super(line);
        this.formalParameter = formalParameter;
        this.array = array;
        this.body = body;
        this.forUpdate = new ArrayList<>();
    }

    /**
     * Analysis involves analyzing the test, checking its type and analyzing the
     * body statement.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JAST analyze(Context context) {
        // Create new local context for the for statement
        // Offset 0 is used to address "this".
        localContext = new LocalContext(context);
        localContext.nextOffset();

        // TODO Ask TA or teacher when we should do the #array = array part as in javaspec link
        // TODO Find a hidden array name that is not already in the scope. If we have two
        // two ForEach statements we will get a shadowing error on #array
        // Add the declaration of the hidden array before the for loop
        JExpression arrayExpression = array.analyze(localContext);
        String hiddenArrayName = "#array";
        JVariableDeclarator hiddenArray = new JVariableDeclarator(line(), hiddenArrayName, array.type(), arrayExpression);
        ArrayList<JVariableDeclarator> prevDecls = new ArrayList<>();
        prevDecls.add(hiddenArray);
        JVariableDeclaration prevDecl = new JVariableDeclaration(line(), new ArrayList<>(), prevDecls);
        hiddenArrayDeclaration = prevDecl.analyze(localContext);


        /** must be an array, and variable must be of the type of the array */
        array = (JVariable) array.analyze(localContext);
        if (!array.type().isArray()) {
            JAST.compilationUnit.reportSemanticError(line,
                    "for-each not applicable to expression type " + array.type().toString());
        }
        formalParameter.type().mustMatchExpected(line(), array.type().componentType());


        /**
         * A certain amount of AST tree rewriting, usually to make implicit constructs more
         * explicit.
         * TODO are we supposed to the stuff below in this step?
         */
        String indexName = "#index";
        JVariableDeclarator index = new JVariableDeclarator(line(), indexName, Type.INT, new JLiteralInt(line(), "0"));
        ArrayList<JVariableDeclarator> decls = new ArrayList<>();
        decls.add(index);
//        JVariableDeclaration declaration = new JVariableDeclaration(line(),
//                new ArrayList<>(), decls);
//        this.declaration = declaration.analyze(localContext);


        //Condition which checks when we should stop incrementing
        JVariable hiddenArrayVariable = new JVariable(line(), hiddenArrayName);
        JExpression hiddenArrayExpression = hiddenArrayVariable.analyze(localContext);
        var lengthField = new JFieldSelection(line(), hiddenArrayExpression, "length");
        // TODO change to less than when it is implemented
        JVariable indexVariable = new JVariable(line(), indexName);
        condition = new JLessThanOp(line(), indexVariable, lengthField);
//        condition = condition.analyze(localContext);

        // Updating the #index variable
        JExpression updateIndex = new JPostIncrementOp(line(), indexVariable);
        // So as not to save to stack
        updateIndex.isStatementExpression = true;
        forUpdate.add(updateIndex);

        // The body of the statement
        JArrayExpression hiddenArrayAccessExpression = new JArrayExpression(line(), hiddenArrayExpression, indexVariable);
        JVariableDeclarator declarator = new JVariableDeclarator(line(), formalParameter.name(), formalParameter.type(), hiddenArrayAccessExpression);
        ArrayList<JVariableDeclarator> vdecls = new ArrayList<>();
        vdecls.add(declarator);
        JVariableDeclaration newBodyVarDeclaration = new JVariableDeclaration(line(), new ArrayList<>(), vdecls);

        JBlock bodyBlock = (JBlock) body;
        bodyBlock.statements().add(0, newBodyVarDeclaration);
//        body = bodyBlock.analyze(localContext);


        // Rewriting to normal For statement
        forStatement = new JForStatement(line, decls, condition, forUpdate, bodyBlock);
        forStatement = forStatement.analyze(localContext);

        return this;
    }

    /**
     * Generates code for the while loop.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        //        T[] #a = Expression;
//        L1: L2: ... Lm:
//        for (int #i = 0; #i < #a.length; #i++) {
//            VariableModifiersopt TargetType Identifier = #a[#i];
//            Statement
//        }
//
        hiddenArrayDeclaration.codegen(output);
        forStatement.codegen(output);
////        T[] #a = Expression;
////        L1: L2: ... Lm:
////        for (int #i = 0; #i < #a.length; #i++) {
////            VariableModifiersopt TargetType Identifier = #a[#i];
////            Statement
////        }
////
//        hiddenArrayDeclaration.codegen(output);
//        declaration.codegen(output);
//
//        // Need two labels
//        String test = output.createLabel();
//        String out = output.createLabel();
//
//        // Branch out of the loop on the test condition
//        // being false
//        output.addLabel(test);
//        condition.codegen(output, out, false);
//
//        // Codegen body
//        body.codegen(output);
//
//        for (JStatement statement : forUpdate) {
//            statement.codegen(output);
//        }
//
////         Unconditional jump back up to test
//        output.addBranchInstruction(GOTO, test);
//
//        // The label below and outside the loop
//        output.addLabel(out);
    }

    /**
     * {@inheritDoc}
     */

    public void writeToStdOut(PrettyPrinter p) {
        if (forStatement == null) {
            p.printf("<ForEachStatement line=\"%d\">\n", line());
            p.indentRight();
            formalParameter.writeToStdOut(p);
            array.writeToStdOut(p);
            p.indentLeft();
            p.printf("</ForEachStatement>\n");
        } else {
            p.printf("<HiddenArray>\n");
            p.indentRight();
            hiddenArrayDeclaration.writeToStdOut(p);
            p.indentLeft();
            p.printf("</HiddenArray>\n");
            forStatement.writeToStdOut(p);
        }
//        p.printf("<HiddenArray>\n");
//        p.indentRight();
//        hiddenArrayDeclaration.writeToStdOut(p);
//        p.indentLeft();
//        p.printf("</HiddenArray>\n");
//        p.indentLeft();
//        p.printf("<ForEachStatement line=\"%d\">\n", line());
//        p.indentRight();
//        p.printf("<ForInit>\n");
//        p.indentRight();
//        declaration.writeToStdOut(p);
//        p.indentLeft();
//        p.printf("</ForInit>\n");
//
//        p.printf("<Condition>\n");
//        p.indentRight();
//        condition.writeToStdOut(p);
//        p.indentLeft();
//        p.printf("</Condition>\n");
//        p.printf("<ForUpdate>\n");
//        p.indentRight();
//        for (JStatement statement : forUpdate) {
//            statement.writeToStdOut(p);
//        }
//        p.indentLeft();
//        p.printf("</ForUpdate>\n");
//        p.printf("<Body>\n");
//        p.indentRight();
//        body.writeToStdOut(p);
//        p.indentLeft();
//        p.printf("</Body>\n");
//        p.indentLeft();
//        p.printf("</ForEachStatement>\n");
    }
}
