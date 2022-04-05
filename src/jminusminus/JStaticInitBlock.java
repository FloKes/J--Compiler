package jminusminus;

public class JStaticInitBlock implements JMember {
    private JBlock blockBody;

    public JStaticInitBlock(JBlock blockBody) {
        this.blockBody = blockBody;
    }

    @Override
    public void preAnalyze(Context context, CLEmitter partial) {

    }
}
