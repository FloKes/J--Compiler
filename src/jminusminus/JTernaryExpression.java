// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for a conditional (ternary) expression. The ?: operator has three
 * operands: a condition, thenPart, elsePart.
 */

class JTernaryExpression extends JExpression {

    
    /** The conditiona operand. */
    protected JExpression condition;
    
    /** The thenPart operand. */
    protected JExpression thenPart;
    
    /** The elsePart operand. */
    protected JExpression elsePart;

    /**
     * Constructs the AST node for a conditional (ternary) expression given the condition, thenPart, and
     * elsePart operands.
     * 
     * @param line
     *            line in which the assignment expression occurs in the source
     *            file.
     * @param condition
     *            condition operand.
     * @param thenPart
     *            thenPart operand.
     * @param elsePart
     *            elsePart operand.
     */

    public JTernaryExpression(int line, JExpression condition, JExpression thenPart, JExpression elsePart) {
        super(line);
        this.condition = condition;
        this.thenPart = thenPart;
        this.elsePart = elsePart;
    }

    public JExpression analyze(Context context) {
        condition = (JExpression) condition.analyze(context);
        condition.type().mustMatchExpected(line(), Type.BOOLEAN);
        thenPart = (JStatement) thenPart.analyze(context);
        elsePart = (JStatement) elsePart.analyze(context);
        if (thenPart.type() == elsePart.type()) {
            type = thenPart.type();
        } else {
            type = Type.ANY;
            JAST.compilationUnit.reportSemanticError(line(), "ThenPart and ElsePart operand types must match in ternary expression");
        }
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

        p.printf("<ThenPart>\n");
        p.indentRight();
        thenPart.writeToStdOut(p);
        p.indentLeft();
        p.printf("</ThenPart>\n");

        p.printf("<ElsePart>\n");
        p.indentRight();
        elsePart.writeToStdOut(p);
        p.indentLeft();
        p.printf("</ElsePart>\n");

        p.indentLeft();
        p.printf("</JTernaryExpression>\n");
    }

}
