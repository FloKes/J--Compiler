package junit;

import junit.framework.TestCase;
import pass.UnsignedShiftRight;
import pass.UnsignedShiftRightAssign;
public class UnsignedShiftRightAssignTest  extends TestCase {;

    private UnsignedShiftRightAssign unsignedShiftRightAssign;

    protected void setUp() throws Exception{
        super.setUp();
        unsignedShiftRightAssign = new UnsignedShiftRightAssign();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testShiftLeft() {
        this.assertEquals(unsignedShiftRightAssign.unsignedShiftRightAssign(2, 2), 0);
        this.assertEquals(unsignedShiftRightAssign.unsignedShiftRightAssign(2, 1), 1);
        this.assertEquals(unsignedShiftRightAssign.unsignedShiftRightAssign(2, 0), 2);
        this.assertEquals(unsignedShiftRightAssign.unsignedShiftRightAssign(2, -1), 0);
        this.assertEquals(unsignedShiftRightAssign.unsignedShiftRightAssign(-2, 1), 2147483647);
    }
}
