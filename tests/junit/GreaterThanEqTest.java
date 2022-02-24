package junit;

import junit.framework.TestCase;
import pass.GreaterThanEq;
import pass.SignedShiftRight;

public class GreaterThanEqTest extends TestCase {
    private GreaterThanEq greaterThanEq;

    protected void setUp() throws Exception {
        super.setUp();
        greaterThanEq = new GreaterThanEq();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSignedShiftRight() {
        this.assertEquals(true, greaterThanEq.greaterThanEq(16, 2));
        this.assertEquals(true, greaterThanEq.greaterThanEq(3, 3));
        this.assertEquals(false, greaterThanEq.greaterThanEq(2, 3));
    }
}