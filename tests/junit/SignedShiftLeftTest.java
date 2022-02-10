package junit;

import junit.framework.TestCase;
import pass.SignedShiftLeft;
public class SignedShiftLeftTest  extends TestCase {;

    private SignedShiftLeft signedShiftLeft;

    protected void setUp() throws Exception{
        super.setUp();
        signedShiftLeft = new SignedShiftLeft();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testShiftLeft() {
        this.assertEquals(signedShiftLeft.shiftLeft(2, 2), 8);
        this.assertEquals(signedShiftLeft.shiftLeft(2, 1), 4);
        this.assertEquals(signedShiftLeft.shiftLeft(2, 0), 2);
        this.assertEquals(signedShiftLeft.shiftLeft(2, -1), 0);
        this.assertEquals(signedShiftLeft.shiftLeft(3, 1), 6);
    }
}
