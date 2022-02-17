package junit;

import junit.framework.TestCase;
import pass.BitwiseXor;

public class BitwiseXorTest extends TestCase {
    private BitwiseXor bitwiseXor;

    protected void setUp() throws Exception {
        super.setUp();
        bitwiseXor = new BitwiseXor();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testBitwiseXor() {
        this.assertEquals(0, bitwiseXor.xor(8, 8));
        this.assertEquals(4, bitwiseXor.xor(0, 4));
        this.assertEquals(5, bitwiseXor.xor(5, 0));
        this.assertEquals(2, bitwiseXor.xor(3, 1));
    }
}