// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import java.util.ArrayList;

import static jminusminus.CLConstants.GOTO;

/**
 * The AST node for an if-statement.
 */

class JTryStatement extends JStatement {

    /** Try block. */
    private JStatement tryBlock;

    /** Array of all catch clauses (exception type and block) */
    private ArrayList<JCatchClause> catchClauses;

    /** Finally block. */
    private JBlock finallyBlock;

    /**
     * Constructs an AST node for a try-catch-finally-statement given its line number, the
     * try statement, the exception type and identifier, the catch statement and the
     * finally statement.
     *
     * @param line
     *
     * @param tryBlock
     *
     * @param catchClauses
     *
     * @param finallyBlock
     *
     */

    public JTryStatement(int line,
                         JBlock tryBlock,
                         ArrayList<JCatchClause> catchClauses,
                         JBlock finallyBlock)
    {
        super(line);
        this.tryBlock = tryBlock;
        this.catchClauses = catchClauses;
        this.finallyBlock = finallyBlock;
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
        for (int i = 0; i < this.catchClauses.size(); i++) {
            this.catchClauses.get(i).analyze(context);
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
        /**
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
        p.indentRight();
        exception.writeToStdOut(p);
        p.indentLeft();
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
    */
    }
}
