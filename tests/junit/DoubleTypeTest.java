package junit;

import junit.framework.TestCase;
import pass.DoubleType;

public class DoubleTypeTest extends TestCase {

    private DoubleType doubleType;

    protected void setUp() throws Exception {
        super.setUp();
        doubleType = new DoubleType();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }


    public void testDoubleType() {
        this.assertEquals(1, doubleType.testDouble());
    }
    
}
