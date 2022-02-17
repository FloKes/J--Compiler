package junit;

import junit.framework.TestCase;
import pass.UnaryPlusOp;

public class UnaryPlusOpTest extends TestCase {
    private UnaryPlusOp unaryPlusOp;

    protected void setUp() throws Exception {
        super.setUp();
        unaryPlusOp = new UnaryPlusOp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testUnaryPlusOp() {
        this.assertEquals(42, unaryPlusOp.unaryPlus(42));
        this.assertEquals(97, unaryPlusOp.unaryPlus('a'));
    }
}