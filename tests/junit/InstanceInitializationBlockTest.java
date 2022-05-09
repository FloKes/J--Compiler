package junit;

import junit.framework.TestCase;
import pass.InstanceInitBlock;

public class InstanceInitializationBlockTest extends TestCase {

    private InstanceInitBlock instanceInitBlock;

    protected void setUp() throws Exception {
        super.setUp();
        instanceInitBlock = new InstanceInitBlock();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testStaticInitBlock () {
        this.assertEquals(42, instanceInitBlock.x);
    }
}