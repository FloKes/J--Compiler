import junit.framework.TestCase;
import pass.DecrementPrefix;

public class DecrementPrefixTest extends TestCase {
    private DecrementPrefix decrementPrefix;

    protected void setUp() throws Exception{
        super.setUp();
        decrementPrefix = new DecrementPrefix();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testMinusMinusPrefix() {
        this.assertEquals(4, decrementPrefix.decrementPrefix(5));
        this.assertEquals(3, decrementPrefix.decrementPrefix(4));
        this.assertEquals(-4, decrementPrefix.decrementPrefix(-3));
    }
}
