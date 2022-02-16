package junit;

import junit.framework.TestCase;
import pass.BitwiseAnd;

public class BitwiseAndTest extends TestCase {
    private BitwiseAnd bitwiseAnd;

    protected void setUp() throws Exception {
        super.setUp();
        bitwiseAnd = new BitwiseAnd();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testBitwiseAnd() {
        this.assertEquals(8, bitwiseAnd.and(8, 8));
        this.assertEquals(0, bitwiseAnd.and(0, 4));
        this.assertEquals(0, bitwiseAnd.and(5, 0));
        this.assertEquals(0, bitwiseAnd.and(2, 1));
        this.assertEquals(1, bitwiseAnd.and(9, 5));
    }
}