// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.GOTO;

/**
 * The AST node for an if-statement.
 */

class JTryStatement extends JStatement {

    /** Try statement. */
    private JStatement tryStatement;

    /** Expression type and identifier */
    private JFormalParameter exception;
    /** Catch statement. */
    private JStatement catchStatement;

    /** Finally statement. */
    private JStatement finallyStatement;

    /**
     * Constructs an AST node for a try-catch-finally-statement given its line number, the
     * try statement, the exception type and identifier, the catch statement and the
     * finally statement.
     *
     * @param line
     *            line in which the if-statement occurs in the source file.
     * @param tryStatement
     *            test expression.
     * @param exception
     *            the type of exception
     * @param catchStatement
     *            then clause.
     * @param finallyStatement
     *            else clause.
     */

    public JTryStatement(int line,
                         JStatement tryStatement,
                         JFormalParameter exception,
                         JStatement catchStatement,
                         JStatement finallyStatement) {
        super(line);
        this.tryStatement = tryStatement;
        this.exception = exception;
        this.catchStatement = catchStatement;
        this.finallyStatement = finallyStatement;
    }

    /**
     * Analyzing the try-statement means analyzing its components and checking
     * that the Exception that has been given to the catch is a valid exception
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JStatement analyze(Context context) {
        exception = (JFormalParameter) exception.analyze(context);
        exception.type().mustMatchExpected(line(), Type.EXCEPTION);
        tryStatement = (JStatement) tryStatement.analyze(context);
        catchStatement = (JStatement) catchStatement.analyze(context);
        if (finallyStatement != null) {
            finallyStatement = (JStatement) finallyStatement.analyze(context);
        }
        return this;
    }

    /**
     * Code generation for an if-statement. We generate code to branch over the
     * consequent if !test; the consequent is followed by an unconditonal branch
     * over (any) alternate.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
    }

    /**
     * {@inheritDoc}
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JTryStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<TryStatement>\n");
        p.indentRight();
        tryStatement.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TryStatement>\n");
        p.printf("<CatchStatement>\n");
        p.indentRight();
        p.printf("<Exception>\n");
        //p.indentRight();
        //exception.writeToStdOut(p);
        //p.indentLeft();
        p.printf("</Exception>\n");
        catchStatement.writeToStdOut(p);
        p.indentLeft();
        p.printf("</CatchStatement>\n");
        if (finallyStatement != null) {
            p.printf("<FinallyStatement>\n");
            p.indentRight();
            finallyStatement.writeToStdOut(p);
            p.indentLeft();
            p.printf("</FinallyStatement>\n");
        }
        p.indentLeft();
        p.printf("</JTryStatement>\n");
    }

}
