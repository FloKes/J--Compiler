package junit;

import junit.framework.TestCase;
import pass.AssignMinus;

public class AssignMinusTest extends TestCase {;

    private AssignMinus assignMinus;

    protected void setUp() throws Exception{
        super.setUp();
        assignMinus = new AssignMinus();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testAssignMinus() {
        this.assertEquals(0 , assignMinus.assignminus(5, 5));
    }
}