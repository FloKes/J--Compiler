package junit;

import junit.framework.TestCase;
import pass.AssignDiv;

public class AssignDivTest extends TestCase {;

    private AssignDiv assignDiv;

    protected void setUp() throws Exception{
        super.setUp();
        assignDiv = new AssignDiv();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testAssignDiv() {
        this.assertEquals(1 , assignDiv.assignDiv(5, 5));
    }
}