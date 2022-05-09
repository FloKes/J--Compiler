package junit;

import junit.framework.TestCase;
import pass.AssignStar;

public class AssignStarTest extends TestCase {;

    private AssignStar assignStar;

    protected void setUp() throws Exception{
        super.setUp();
        assignStar = new AssignStar();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testAssignStar() {
        this.assertEquals(25 , assignStar.assignstar(5, 5));
    }
}