package junit;

import junit.framework.TestCase;
import pass.IncrementSuffix;

public class IncrementSuffixTest extends TestCase {
    private IncrementSuffix incrementSuffix;

    protected void setUp() throws Exception{
        super.setUp();
        incrementSuffix = new IncrementSuffix();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testIncrementSuffix() {
        this.assertEquals(6, incrementSuffix.incrementSuffix(5));
        this.assertEquals(5, incrementSuffix.incrementSuffix(4));
        this.assertEquals(-2, incrementSuffix.incrementSuffix(-3));
        this.assertEquals(5, incrementSuffix.returnBeforeIncrement(5));
    }
}