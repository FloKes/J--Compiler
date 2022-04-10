// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import java.util.ArrayList;

import static jminusminus.CLConstants.GOTO;
import static jminusminus.TokenKind.SEMI;

/**
 * The AST node for a for-statement.
 */

class JForEachStatement extends JStatement {

    /** Declarator for  the variable. */
//    private JVariableDeclarator declarator;

    /** The initialized variable from the arr. */
    private JStatement declaration;

    private JFormalParameter formalParameter;

    /** The array. */
    private JVariable array;
    private JArrayInitializer arrayInitializer;

    /** Test expression. */
    private JExpression condition;

    /** Update the variable. */
    // We need to create this ourselves
    private ArrayList<JStatement> forUpdate;

    /** The body. */
    private JStatement body;

    /** The Local context. */
    private LocalContext localContext;

    /**
     * Constructs an AST node for a for-statement given its line number, the
     * test expression, and the body.
     *
     * @param line
     *            line in which the for-statement occurs in the source file.
     *            test expression.
     * @param body
     *            the body.
     */

    public JForEachStatement(int line, JFormalParameter formalParameter, JVariable array, JStatement body) {
        super(line);
        this.formalParameter = formalParameter;
        this.array = array;
        this.body = body;
        this.forUpdate = new ArrayList<>();
    }

    /**
     * Analysis involves analyzing the test, checking its type and analyzing the
     * body statement.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JForEachStatement analyze(Context context) {

        // TODO consult JAVA spec to confirm its good
        // Create new local context for the for statement
        // Offset 0 is used to address "this".
        localContext = new LocalContext(context);
        localContext.nextOffset();

        /** must be an array, and variable must be of the type of the array */
        array = (JVariable) array.analyze(localContext);
        if (!array.type().isArray()) {
            JAST.compilationUnit.reportSemanticError(line,
                    "for-each not applicable to expression type " + array.type().toString());
        }
        formalParameter.type().mustMatchExpected(line(), array.type().componentType());


        /**
         * A certain amount of AST tree rewriting, usually to make implicit constructs more
         * explicit.
         * TODO are we supposed to the stuff below in this step?
         */
        JExpression arrayExpression = array.analyze(localContext);
        String indexName = "#index";
        String hiddenArrayName = "#array";
        ArrayList<JVariableDeclarator> decls = new ArrayList<>();
        JVariableDeclarator index = new JVariableDeclarator(line(), indexName, Type.INT, new JLiteralInt(line(), "0"));
        JVariableDeclarator hiddenArray = new JVariableDeclarator(line(), hiddenArrayName, array.type(), arrayExpression);

        decls.add(index);
        decls.add(hiddenArray);

        JVariableDeclaration declaration = new JVariableDeclaration(line(),
                new ArrayList<>(), decls);
        this.declaration = declaration.analyze(localContext);


        //Condition which checks when we should stop incrementing
        JVariable hiddenArrayVariable = new JVariable(line(), hiddenArrayName);
        JExpression hiddenArrayExpression = hiddenArrayVariable.analyze(localContext);
        var lengthField = new JFieldSelection(line(), hiddenArrayExpression, "length");
        // TODO change to less than when it is implemented
        JVariable indexVariable = new JVariable(line(), indexName);
        this.condition = new JLessEqualOp(line(), indexVariable, lengthField);

        // Updating the #index variable
        JPostIncrementOp updateIndex = new JPostIncrementOp(line(), indexVariable);
        forUpdate.add(updateIndex);

        // The body of the statement
        JArrayExpression hiddenArrayAccessExpression = new JArrayExpression(line(), hiddenArrayExpression, indexVariable);
        JVariableDeclarator declarator = new JVariableDeclarator(line(), formalParameter.name(), formalParameter.type(), hiddenArrayAccessExpression);
        ArrayList<JVariableDeclarator> vdecls = new ArrayList<>();
        vdecls.add(declarator);
        JVariableDeclaration newBodyVarDeclaration = new JVariableDeclaration(line(), new ArrayList<>(), vdecls);

        ArrayList<JStatement> newBodyStatements = new ArrayList<>();
        newBodyStatements.add(newBodyVarDeclaration);
        newBodyStatements.add(body);
        JStatement newBody = new JBlock(line(),newBodyStatements);
        this.body = (JStatement) newBody.analyze(localContext);
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
        p.printf("<ForEachStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<ForInit>\n");
        p.indentRight();
        declaration.writeToStdOut(p);
        p.indentLeft();
        p.printf("</ForInit>\n");

        p.printf("<Condition>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Condition>\n");
        p.printf("<ForUpdate>\n");
        p.indentRight();
        for (JStatement statement : forUpdate) {
            statement.writeToStdOut(p);
        }
        p.indentLeft();
        p.printf("</ForUpdate>\n");
        p.printf("<Body>\n");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Body>\n");
        p.indentLeft();
        p.printf("</ForEachStatement>\n");
    }

}
