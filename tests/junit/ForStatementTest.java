package junit;

import junit.framework.TestCase;
import pass.ForStatement;

public class ForStatementTest extends TestCase {;

    private ForStatement forStatement;

    protected void setUp() throws Exception{
        super.setUp();
        forStatement = new ForStatement();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testForStatement() {
        //this.assertEquals(2 ,forStatement.forStatement(2));
        //this.assertEquals(10 ,forStatement.forStatementMultInit(2, 3));
        //this.assertEquals(2 ,forStatement.forStatementExp(2));
        //this.assertEquals(10 ,forStatement.forStatementMultExp(2, 3));
    }
}
