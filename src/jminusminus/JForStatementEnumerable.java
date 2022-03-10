// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.GOTO;

/**
 * The AST node for a for-statement.
 */

class JForStatementEnumerable extends JStatement {

    /** Declare the variable. */
    private JVariableDeclarator declarator;

    private JVariable enumerable;

    /** The body. */
    private JStatement body;

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

    public JForStatementEnumerable(int line, JVariableDeclarator declarator, JVariable enumerable, JStatement body) {
        super(line);
        this.declarator = declarator;
        this.enumerable = enumerable;
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

    public JForStatementEnumerable analyze(Context context) {
        declarator = declarator.analyze(context);
        enumerable = (JVariable) enumerable.analyze(context);
        body = (JStatement) body.analyze(context);
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
        declarator.writeToStdOut(p);
        p.indentLeft();
        p.printf("</DeclaratorExpression>\n");
        p.printf("<Enumerable>\n");
        p.indentRight();
        enumerable.writeToStdOut(p);
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
