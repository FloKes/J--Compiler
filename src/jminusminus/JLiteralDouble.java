// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for an {@code double} literal.
 */

class JLiteralDouble extends JExpression {

    /** String representation of the double. */
    private String text;

    /**
     * Constructs an AST node for an {@code double} literal given its line number 
     * and string representation.
     * 
     * @param line
     *            line in which the literal occurs in the source file.
     * @param text
     *            string representation of the literal.
     */

    public JLiteralDouble(int line, String text) {
        super(line);
        this.text = text;
    }

    /**
     * Analyzing a double literal is trivial.
     * 
     * @param context
     *            context in which names are resolved (ignored here).
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JExpression analyze(Context context) {
        type = Type.DOUBLE;
        return this;
    }

    /**
     * Generating code for a double literal means generating code to push it onto
     * the stack.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        // FIXME: We cannot switch on a double. Casting to int, does a floor on the number, so not reliable.
        // Only 1.0 => 1, whereas right now (int) 1.6 => 1
        double i = Double.parseDouble(text);
        switch ((int) i) {
        case 0:
            output.addNoArgInstruction(DCONST_0);
            break;
        case 1:
            output.addNoArgInstruction(DCONST_1);
            break;
        default:
            output.addLDCInstruction(i);
        }
    }

    /**
     * {@inheritDoc}
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JLiteralDouble line=\"%d\" type=\"%s\" " + "value=\"%s\"/>\n",
                line(), ((type == null) ? "" : type.toString()), text);
    }
}
