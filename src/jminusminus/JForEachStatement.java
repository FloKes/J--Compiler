// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import java.util.ArrayList;

import static jminusminus.CLConstants.GOTO;

/**
 * The AST node for a for-statement.
 */

class JForEachStatement extends JStatement {

    /** Declarator for  the variable. */
    private JVariableDeclarator declarator;

    /** The initialized variable from the arr. */
    private JStatement declaration;

    /** The array. */
    private JVariable array;

    /** Test expression. */
    private JExpression condition;

    /** Update the variable. */
    // We need to create this ourselves
    private JStatement updateStatement;

    /** The body. */
    private JStatement body;

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

    public JForEachStatement(int line, JVariableDeclarator declarator, JVariable array, JStatement body) {
        super(line);
        this.declarator = declarator;
        this.array = array;
        this.body = body;
    }

    /**
     * Analysis involves analyzing the test, checking its type and analyzing the
     * body statement.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JForEachStatement analyze(Context context) {
        // Create new local context for the for statement
        // Offset 0 is used to address "this".
        localContext = new LocalContext(context);
        localContext.nextOffset();

        /** must be an array, and variable must be of the type of the array */
        array = (JVariable) array.analyze(localContext);
        if (!array.type().isArray()) {
            JAST.compilationUnit.reportSemanticError(line,
                    "for-each not applicable to expression type " + array.type().toString());
        }
        declarator.type().mustMatchExpected(line(), array.type().componentType());

        // Retrieve the first element of the array and set the initializer for the variable

        /**
         for(int item: numbers)
         for(int index = 0, item = numbers[index]; index < numbers.length; index++, item = numbers[index])
         */

        JExpression theArray = array.analyze(localContext);


        // Analyze the declaration and add it to the local context
        ArrayList<JVariableDeclarator> decls = new ArrayList<>();
        JVariableDeclarator index = new JVariableDeclarator(line(), "index", Type.INT, new JLiteralInt(line(), "0"));
        decls.add(index);

        JVariable indexVariable = new JVariable(line(), "index");
        //JExpression indexExpr = indexVariable.analyze(localContext);

        JArrayExpression arrayExpression = new JArrayExpression(line(), theArray, indexVariable);
        declarator.setInitializer(arrayExpression);
        decls.add(declarator);

        JVariableDeclaration declaration = new JVariableDeclaration(line(),
                new ArrayList<>(), decls);

        this.declaration = declaration.analyze(localContext);




        //TODO Create a condition to check when the increment of the index should stop

        // Create the expression for updating the variable
        JPlusAssignOp updateExpression = new JPlusAssignOp(line(), null,new JLiteralInt(line(), "1"));
        this.updateStatement = updateExpression;


        // The body of the statement
        body = (JStatement) body.analyze(localContext);
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
        // Need two labels
        String test = output.createLabel();
        String out = output.createLabel();

        // Branch out of the loop on the test condition
        // being false

        // Codegen body
        body.codegen(output);

        // Unconditional jump back up to test
        output.addBranchInstruction(GOTO, test);

        // The label below and outside the loop
        output.addLabel(out);
    }

    /**
     * {@inheritDoc}
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<ForStatementEnumerable line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<DeclaratorExpression>\n");
        p.indentRight();
        declaration.writeToStdOut(p);
        p.indentLeft();
        p.printf("</DeclaratorExpression>\n");
        p.printf("<Enumerable>\n");
        p.indentRight();
        array.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Enumerable>\n");
        p.printf("<Body>\n");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Body>\n");
        p.indentLeft();
        p.printf("</ForStatementEnumerable>\n");
    }

}
