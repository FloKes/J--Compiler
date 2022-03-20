// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.GOTO;

/**
 * The AST node for a for-statement.
 */

class JForStatement extends JStatement {

    /** Declare the variable. */
    private JVariableDeclarator declarator;

    /** Test expression. */
    private JExpression condition;

    /** Update the variable. */
    private JStatement statementExpression;

    /** The body. */
    private JStatement body;

    /**
     * Constructs an AST node for a for-statement given its line number, the
     * test expression, and the body.
     *
     * @param line
     *            line in which the for-statement occurs in the source file.
     * @param condition
     *            test expression.
     * @param body
     *            the body.
     */

    public JForStatement(int line, JVariableDeclarator declarator, JExpression condition,
                         JStatement statementExpression, JStatement body) {
        super(line);
        this.declarator = declarator;
        this.condition = condition;
        this.statementExpression = statementExpression;
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

    public JForStatement analyze(Context context) {
        declarator = declarator.analyze(context);
        condition = condition.analyze(context);
        condition.type().mustMatchExpected(line(), Type.BOOLEAN);
        statementExpression = (JStatement) statementExpression.analyze(context);
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
        output.addLabel(test);
        condition.codegen(output, out, false);

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
        p.printf("<ForStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<DeclaratorExpression>\n");
        p.indentRight();
        declarator.writeToStdOut(p);
        p.indentLeft();
        p.printf("</DeclaratorExpression>\n");
        p.printf("<TestExpression>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TestExpression>\n");
        p.printf("<StatementExpression>\n");
        p.indentRight();
        statementExpression.writeToStdOut(p);
        p.indentLeft();
        p.printf("</StatementExpression>\n");
        p.indentLeft();
        p.printf("<Body>\n");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Body>\n");
        p.indentLeft();
        p.printf("</ForStatement>\n");
    }

}
