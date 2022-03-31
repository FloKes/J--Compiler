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

    private JStatement declaration;

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

        ArrayList<JVariableDeclarator> decls = new ArrayList<>();
        decls.add(declarators);
        JVariableDeclaration declaration = new JVariableDeclaration(line(),
                new ArrayList<>(), decls);
        this.declaration = declaration.analyze(localContext);

        // second expression must be boolean
        condition = condition.analyze(localContext);
        condition.type().mustMatchExpected(line(), Type.BOOLEAN);

        // Must be statement with side effect
        // TODO Ask: if this isn't a statement expression, the code is not analyzed any further
        // TODO: Local context has no entries for body, but analyzes correctly
        // TODO: Ask TA to go over this
        forUpdate = (JStatement) forUpdate.analyze(localContext);

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
        p.printf("<DeclaratorExpression>\n");
        p.indentRight();
        declarators.writeToStdOut(p);
        p.indentLeft();
        p.printf("</DeclaratorExpression>\n");
        p.printf("<TestExpression>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TestExpression>\n");
        p.printf("<StatementExpression>\n");
        p.indentRight();
        forUpdate.writeToStdOut(p);
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
