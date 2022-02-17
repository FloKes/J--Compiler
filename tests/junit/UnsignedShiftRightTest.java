package junit;

import junit.framework.TestCase;
import pass.UnsignedShiftRight;
public class UnsignedShiftRightTest  extends TestCase {;

    private UnsignedShiftRight unsignedShiftRight;

    protected void setUp() throws Exception{
        super.setUp();
        unsignedShiftRight = new UnsignedShiftRight();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testShiftLeft() {
        this.assertEquals(unsignedShiftRight.unsignedShiftRight(2, 2), 0);
        this.assertEquals(unsignedShiftRight.unsignedShiftRight(2, 1), 1);
        this.assertEquals(unsignedShiftRight.unsignedShiftRight(2, 0), 2);
        this.assertEquals(unsignedShiftRight.unsignedShiftRight(2, -1), 0);
        this.assertEquals(unsignedShiftRight.unsignedShiftRight(-2, 1), 2147483647);
    }
}
