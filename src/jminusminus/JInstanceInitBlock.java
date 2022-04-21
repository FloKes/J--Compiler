package jminusminus;

public class JInstanceInitBlock extends JAST implements JMember {

    private JBlock blockBody;

    public JInstanceInitBlock(int line, JBlock blockBody) {
        super(line);
        this.blockBody = blockBody;
    }

    @Override
    public void preAnalyze(Context context, CLEmitter partial) {

    }

    @Override
    public JAST analyze(Context context) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void codegen(CLEmitter output) {
        // TODO Auto-generated method stub

    }

    @Override
    public void writeToStdOut(PrettyPrinter p) {
        // TODO Auto-generated method stub

    }

}
