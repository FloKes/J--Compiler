package jminusminus;

public class JInstanceInitBlock implements JMember {

    private JBlock blockBody;

    public JInstanceInitBlock(JBlock blockBody) {
        this.blockBody = blockBody;
    }

    @Override
    public void preAnalyze(Context context, CLEmitter partial) {

    }

}
