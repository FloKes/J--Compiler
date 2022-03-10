// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for a conditional (ternary) expression. The ?: operator has three
 * operands: a condition, lhs, rhs.
 */

class JTernaryExpression extends JExpression {

    
    /** The conditiona operand. */
    protected JExpression condition;
    
    /** The lhs operand. */
    protected JExpression lhs;
    
    /** The rhs operand. */
    protected JExpression rhs;

    /**
     * Constructs the AST node for a conditional (ternary) expression given the condition, lhs, and
     * rhs operands.
     * 
     * @param line
     *            line in which the assignment expression occurs in the source
     *            file.
     * @param condition
     *            condition operand.
     * @param lhs
     *            lhs operand.
     * @param rhs
     *            rhs operand.
     */

    public JTernaryExpression(int line, JExpression condition, JExpression lhs, JExpression rhs) {
        super(line);
        this.condition = condition;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    // TO BE IMPLEMENTED
    public JExpression analyze(Context context) {
        return this;
    }

    // TO BE IMPLEMENTED
    public void codegen(CLEmitter output) {
    }


    /**
     * {@inheritDoc}
     */
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JTernaryExpression line=\"%d\" type=\"%s\" \n", line(), ((type == null) ? "" : type.toString()));
        p.indentRight();

        p.printf("<Cond>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Cond>\n");

        p.printf("<Lhs>\n");
        p.indentRight();
        lhs.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Lhs>\n");

        p.printf("<Rhs>\n");
        p.indentRight();
        rhs.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Rhs>\n");

        p.indentLeft();
        p.printf("</JTernaryExpression>\n");
    }

}
