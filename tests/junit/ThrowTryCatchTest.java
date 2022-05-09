package junit;

import junit.framework.TestCase;
import pass.ThrowTryCatch;

public class ThrowTryCatchTest extends TestCase {
    private ThrowTryCatch _throw;

    protected void setUp() throws Exception {
        super.setUp();
        _throw = new ThrowTryCatch();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testThrowTryCatch() {
        this.assertEquals(10, _throw._throw());
    }
}