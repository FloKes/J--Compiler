// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

/**
 * The AST node for a catch-statement.
 */

class JCatchClause extends JStatement {

    /** Catch exception */
    private JFormalParameter exception;

    /** The block. */
    private JBlock block;

    /**
     * Constructs an AST node for a for-statement given its line number, the
     * test expression, and the body.
     *
     * @param line
     *          line in which the catch-statement occurs in the source file.
     *
     * @param exception
     *          The exception to catch.
     * @param block
     *          The block to execute if exception is caught.
     *
     */

    public JCatchClause(int line, JFormalParameter exception, JBlock block) {
        super(line);
        this.exception = exception;
        this.block = block;
    }

    /**
     * Analysis involves analyzing the test, checking its type and analyzing the
     * body statement.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JCatchClause analyze(Context context) {
        /*
        block = block.analyze(context);
        exception = (JFormalParameter) exception.analyze(context);
        if (!Throwable.class.isAssignableFrom(exception.type().classRep())) {
            JAST.compilationUnit.reportSemanticError(line, String.format("%s not sublcass of throwable", exception.type().getClass().getName()), exception.type().getClass().getName());
        }
        */
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
    }

    /**
     * {@inheritDoc}
     */

    public void writeToStdOut(PrettyPrinter p) {
    }

}
