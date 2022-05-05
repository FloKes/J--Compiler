package junit;

import junit.framework.TestCase;
import pass.WhileStatement;

public class WhileStatementTest extends TestCase {;

    private WhileStatement whileStatement;

    protected void setUp() throws Exception{
        super.setUp();
        whileStatement = new WhileStatement();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testWhileStatement() {
        this.assertEquals(3 , whileStatement.whileStatement(2));
    }
}
