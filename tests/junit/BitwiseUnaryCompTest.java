package junit;

import junit.framework.TestCase;
import pass.BitwiseUnaryComp;

public class BitwiseUnaryCompTest extends TestCase {
    private BitwiseUnaryComp bitwiseUnaryComp;

    protected void setUp() throws Exception {
        super.setUp();
        bitwiseUnaryComp = new BitwiseUnaryComp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testBitwiseUnaryComp() {
        this.assertEquals(-1, bitwiseUnaryComp.complement(0));
        this.assertEquals(-3, bitwiseUnaryComp.complement(2));
    }
}