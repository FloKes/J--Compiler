package junit;

import junit.framework.TestCase;
import pass.SignedShiftRight;

public class SignedShiftRightTest extends TestCase {
    private SignedShiftRight signedShiftRight;

    protected void setUp() throws Exception {
        super.setUp();
        signedShiftRight = new SignedShiftRight();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testRemainder() {
        this.assertEquals(4, signedShiftRight.shiftRight(16, 2));
        this.assertEquals(0, signedShiftRight.shiftRight(5, 10));
        this.assertEquals(0, signedShiftRight.shiftRight(0, 10));
        this.assertEquals(10, signedShiftRight.shiftRight(10, 0));
        this.assertEquals(0, signedShiftRight.shiftRight(10, 5));
        this.assertEquals(-4, signedShiftRight.shiftRight(-16, 2));
        this.assertEquals(0, signedShiftRight.shiftRight(16, -2));
        this.assertEquals(-1, signedShiftRight.shiftRight(-16, -2));
    }
}