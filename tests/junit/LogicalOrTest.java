package junit;

import junit.framework.TestCase;
import pass.Lor;

public class LogicalOrTest extends TestCase {
    private Lor logicalOr;

    protected void setUp() throws Exception {
        super.setUp();
        logicalOr = new Lor();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testLogicalOr() {
        this.assertEquals(true, logicalOr.lOr(true, true));
        this.assertEquals(true, logicalOr.lOr(true, false));
        this.assertEquals(true, logicalOr.lOr(false, true));
        this.assertEquals(false, logicalOr.lOr(false , false));
    }
}