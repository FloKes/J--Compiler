package junit;

import junit.framework.TestCase;
import pass.StaticInitializationBlock;

public class StaticInitializationBlockTest extends TestCase {

    private StaticInitializationBlock staticInitBlock;

    protected void setUp() throws Exception {
        super.setUp();
        staticInitBlock = new StaticInitializationBlock();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testStaticInitBlock () {
        this.assertEquals(42, StaticInitializationBlock.x);
    }
}
