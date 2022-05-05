package junit;

import junit.framework.TestCase;
import pass.AssignAdd;

public class AssignAddTest extends TestCase {;

    private AssignAdd assignAdd;

    protected void setUp() throws Exception{
        super.setUp();
        assignAdd = new AssignAdd();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testAssignAdd() {
        this.assertEquals(10 , assignAdd.assignadd(5, 5));
    }
}
