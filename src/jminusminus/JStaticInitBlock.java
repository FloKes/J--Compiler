package jminusminus;

public class JStaticInitBlock extends JAST implements JMember {
    private JBlock blockBody;

    public JStaticInitBlock(int line, JBlock blockBody) {
        super(line);
        this.blockBody = blockBody;
    }

    @Override
    public void preAnalyze(Context context, CLEmitter partial) {

    }

    @Override
    public JAST analyze(Context context) {
        return this.blockBody.analyze(context);
    }

    @Override
    public void codegen(CLEmitter output) {
        this.blockBody.codegen(output);

    }

    @Override
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JStaticInitBlock line=\"%d\">\n", line());
        blockBody.writeToStdOut(p);
        p.printf("</JStaticInitBlock>\n");

    }
}
