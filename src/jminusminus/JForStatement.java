// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import java.util.ArrayList;

import static jminusminus.CLConstants.GOTO;

/**
 * The AST node for a for-statement.
 */

class JForStatement extends JStatement {

    /** Declare the variable. */
    private ArrayList<JVariableDeclarator> declarators;

    private JVariableDeclaration declaration;

    /** Test expression. */
    private JExpression condition;

    /** Update the variable. */
    private ArrayList<JStatement> forUpdate;

    /** The body. */
    private JStatement body;

    private LocalContext localContext;

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

    public JForStatement(int line, ArrayList<JVariableDeclarator> declarators, JExpression condition,
                         ArrayList<JStatement> forUpdate, JStatement body) {
        super(line);
        this.declarators = declarators;
        this.condition = condition;
        this.forUpdate = forUpdate;
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
        // Create new local context for the for statement
        localContext = new LocalContext(context);
        // Offset 0 is used to address "this".
        localContext.nextOffset();


        ArrayList<String> mods = new ArrayList<>();
        declaration = new JVariableDeclaration(line(), mods, declarators);

        declaration.analyze(localContext);

        // second expression must be boolean
        condition = condition.analyze(localContext);
        condition.type().mustMatchExpected(line(), Type.BOOLEAN);

        // Must be statement with side effect
        for (JStatement statement : forUpdate) {
            statement.analyze(localContext);
        }

        //body
        body = (JStatement) body.analyze(localContext);
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
//        p.printf("<DeclaratorExpression>\n");
        p.indentRight();
//        for (JVariableDeclarator declarator : declarators) {
//            declarator.writeToStdOut(p);
//        }


        if (declaration != null) {
            declaration.writeToStdOut(p);
        }
        p.indentLeft();
//        p.printf("</DeclaratorExpression>\n");
        p.printf("<TestExpression>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TestExpression>\n");
        p.printf("<StatementExpression>\n");
        p.indentRight();
        for (JStatement statement : forUpdate) {
            statement.writeToStdOut(p);
        }
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
