package junit;

import junit.framework.TestCase;
import pass.BitwiseOr;

public class BitwiseOrTest extends TestCase {
    private BitwiseOr bitwiseOr;

    protected void setUp() throws Exception {
        super.setUp();
        bitwiseOr = new BitwiseOr();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testBitwiseOr() {
        this.assertEquals(8, bitwiseOr.or(8, 8));
        this.assertEquals(4, bitwiseOr.or(0, 4));
        this.assertEquals(5, bitwiseOr.or(5, 0));
        this.assertEquals(3, bitwiseOr.or(2, 1));
    }
}