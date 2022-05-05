package junit;

import junit.framework.TestCase;
import pass.AssignRem;

public class AssignRemTest extends TestCase {;

    private AssignRem assignRem;

    protected void setUp() throws Exception{
        super.setUp();
        assignRem = new AssignRem();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testAssignRem() {
        this.assertEquals(0 , assignRem.assignRem(5, 5));
    }
}