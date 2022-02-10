package junit;

import junit.framework.TestCase;
import pass.Remainder;

public class RemainderTest extends TestCase {
    private Remainder remainder;

    protected void setUp() throws Exception {
        super.setUp();
        remainder = new Remainder();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testRemainder() {
        this.assertEquals(8, remainder.remainder(8, 10));
        this.assertEquals(0, remainder.remainder(8, 4));
        this.assertEquals(3, remainder.remainder(8, 5));
    }
}