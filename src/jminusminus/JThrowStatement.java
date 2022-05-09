package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for a throw-statement.
 */

class JThrowStatement extends JStatement {

    /** The expression that is thrown. */
    private JExpression expr;

    /**
     * Constructs an AST node for a throw-statement given its
     * line number, and the expression that is thrown.
     * 
     * @param line
     *            line in which the throw-statement appears
     *            in the source file.
     * @param expr
     *            the thrown expression.
     */

    public JThrowStatement(int line, JExpression expr) {
        super(line);
        this.expr = expr;
    }

    /**
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JStatement analyze(Context context) {
        
        return this;
    }

    /**
     * Generates code for the return statement. In the case of
     * void method types, generate a simple (void) return. In the
     * case of a return expression, generate code to load that
     * onto the stack and then generate the appropriate return
     * instruction.
     * 
     * @param output
     *            the code emitter (basically an abstraction
     *            for producing the .class file).
     */

    public void codegen(CLEmitter output) {
        /**
        if (expr == null) {
            output.addNoArgInstruction(RETURN);
        } else {
            expr.codegen(output);
            if (expr.type() == Type.INT
                || expr.type() == Type.BOOLEAN
                || expr.type() == Type.CHAR) {
                output.addNoArgInstruction(IRETURN);
            } else {
                output.addNoArgInstruction(ARETURN);
            }
        }
        */
    }

    /**
     * {@inheritDoc}
     */

    public void writeToStdOut(PrettyPrinter p) {
        /**
        if (expr != null) {
            p.printf("<JReturnStatement line=\"%d\">\n", line());
            p.indentRight();
            expr.writeToStdOut(p);
            p.indentLeft();
            p.printf("</JReturnStatement>\n");
        } else {
            p.printf("<JReturnStatement line=\"%d\"/>\n", line());
        }
        */
    }
}
