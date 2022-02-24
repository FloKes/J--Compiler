package junit;

import junit.framework.TestCase;
import pass.SignedShiftRight;
import pass.SignedShiftRightAssign;

public class SignedShiftRightAssignTest extends TestCase {
    private SignedShiftRightAssign signedShiftRightAssign;

    protected void setUp() throws Exception {
        super.setUp();
        signedShiftRightAssign = new SignedShiftRightAssign();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSignedShiftRight() {
        this.assertEquals(4, signedShiftRightAssign.shiftRightAssign(16, 2));
        this.assertEquals(0, signedShiftRightAssign.shiftRightAssign(5, 10));
        this.assertEquals(0, signedShiftRightAssign.shiftRightAssign(0, 10));
        this.assertEquals(10, signedShiftRightAssign.shiftRightAssign(10, 0));
        this.assertEquals(0, signedShiftRightAssign.shiftRightAssign(10, 5));
        this.assertEquals(-4, signedShiftRightAssign.shiftRightAssign(-16, 2));
        this.assertEquals(0, signedShiftRightAssign.shiftRightAssign(16, -2));
        this.assertEquals(-1, signedShiftRightAssign.shiftRightAssign(-16, -2));
    }
}