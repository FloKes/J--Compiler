// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import java.util.ArrayList;

import static jminusminus.CLConstants.GOTO;

/**
 * The AST node for an if-statement.
 */

class JTryStatement extends JStatement {

    /** Try block. */
    private JBlock tryBlock;

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
        boolean hasCatchClause = false;
        tryBlock.analyze(context);
        if (!catchClauses.isEmpty()){
            hasCatchClause = true;
            for (int i = 0; i < this.catchClauses.size(); i++) {
                this.catchClauses.get(i).analyze(context);
            }
        }
        if (finallyBlock != null) {
            finallyBlock.analyze(context);
        } else if (hasCatchClause == false) {
            JAST.compilationUnit.reportSemanticError(line, "finally clause is required in try-catch-finally statement, if no catch clauses are present");
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
            p.printf("<TryBlock>\n");
            p.indentRight();
                tryBlock.writeToStdOut(p);
                p.indentLeft();
            p.printf("</TryBlock>\n");
            p.printf("<CatchStatements>\n");
            if (catchClauses != null) {
                for (int i = 0; i < catchClauses.size(); i++) {
                    p.indentRight();
                    catchClauses.get(i).writeToStdOut(p);
                    p.indentLeft();
                }
            }
            p.printf("</CatchStatements>\n");
            if (finallyBlock != null) {
                p.printf("<FinallyStatement>\n");
                p.indentRight();
                finallyBlock.writeToStdOut(p);
                p.indentLeft();
                p.printf("</FinallyStatement>\n");
            }
            p.indentLeft();
        p.printf("</JTryStatement>\n");
    }
}
