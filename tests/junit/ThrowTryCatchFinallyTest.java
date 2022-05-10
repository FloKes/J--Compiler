package junit;

import junit.framework.TestCase;
import pass.ThrowTryCatchFinally;

public class ThrowTryCatchFinallyTest extends TestCase {
    private ThrowTryCatchFinally _throw;

    protected void setUp() throws Exception {
        super.setUp();
        _throw = new ThrowTryCatchFinally();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testThrowTryCatchFinally() {
        this.assertEquals(20, _throw._throw());
    }
}