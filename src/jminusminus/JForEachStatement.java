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
    private JArrayInitializer arrayInitializer;

    /** Test expression. */
    private JExpression condition;

    /** Update the variable. */
    // We need to create this ourselves
    private ArrayList<JStatement> forUpdate;

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

        /**
         * A certain amount of AST tree rewriting, usually to make implicit constructs more
         * explicit.
         * TODO are we supposed to the stuff below in this step?
         */
        JExpression theArray = array.analyze(localContext);

        String indexName = "index";
        JVariable indexVariable = new JVariable(line(), indexName);
        JArrayExpression arrayExpression = new JArrayExpression(line(), theArray, indexVariable);

        ArrayList<JVariableDeclarator> decls = new ArrayList<>();
        JVariableDeclarator index = new JVariableDeclarator(line(), indexName, Type.INT, new JLiteralInt(line(), "0"));
        decls.add(index);
        this.declarator.setInitializer(arrayExpression);
        decls.add(this.declarator);

        JVariableDeclaration declaration = new JVariableDeclaration(line(),
                new ArrayList<>(), decls);
        this.declaration = declaration.analyze(localContext);


        //TODO Create a condition to check when the increment of the index should stop
        /** Condition should be index < array.length
         * Not sure how to find array length from the array name
         *
         */

        var a = (JArrayExpression) arrayExpression;
        var b = a.analyzeLhs(localContext);
        this.condition = new JLessEqualOp(line(), indexVariable, b);


        //TODO Create the expression for updating the variable
        JVariable itemVariable =  new JVariable(line(), declarator.name());
        JPlusAssignOp updateIndex = new JPlusAssignOp(line(), indexVariable,new JLiteralInt(line(), "1"));
        JAssignOp updateItem = new JAssignOp(line(), itemVariable, arrayExpression);
        forUpdate.add(updateIndex);
        forUpdate.add(updateItem);

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
        p.printf("<ForInit>\n");
        p.indentRight();
        declaration.writeToStdOut(p);
        p.indentLeft();
        p.printf("</ForInit>\n");

        p.printf("<Condition>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Condition>\n");
//        p.printf("<Array>\n");
//        p.indentRight();
//        array.writeToStdOut(p);
//        p.indentLeft();
//        p.printf("</Array>\n");
        p.printf("<ForUpdate>\n");
        p.indentRight();
        for (JStatement statement : forUpdate) {
            statement.writeToStdOut(p);
        }
        p.indentLeft();
        p.printf("</ForUpdate>\n");
        p.printf("<Body>\n");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Body>\n");
        p.indentLeft();
        p.printf("</ForStatementEnumerable>\n");
    }

}
