package junit;

import junit.framework.TestCase;
import pass.ForEachStatement;

public class ForEachStatementTest extends TestCase {;

    private ForEachStatement forEachStatement;

    protected void setUp() throws Exception{
        super.setUp();
        forEachStatement = new ForEachStatement();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testForEachStatement() {
        int[] array = {1,2,3,4};
        int[] emptyArray = {};
        this.assertEquals(10 , forEachStatement.forEachStatement(0, array));
        this.assertEquals(15 , forEachStatement.forEachStatement(5, array));
        this.assertEquals(0 , forEachStatement.forEachStatement(0, emptyArray));
        this.assertEquals(20 , forEachStatement.twoForEachStatement(0, array));
        this.assertEquals(25 , forEachStatement.twoForEachStatement(5, array));
        this.assertEquals(0 , forEachStatement.twoForEachStatement(0, emptyArray));
    }
}
