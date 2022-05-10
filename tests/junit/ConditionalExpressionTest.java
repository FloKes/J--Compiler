package junit;

import junit.framework.TestCase;
import pass.ConditionalExpression;

public class ConditionalExpressionTest extends TestCase {

    public void testConditionalExpressions() {
        this.assertEquals(ConditionalExpression.conditional(true, 1, false, 2, 3), 1);
        this.assertEquals(ConditionalExpression.conditional(false, 1, true, 2, 3), 2);
        this.assertEquals(ConditionalExpression.conditional(false, 1, false, 2, 3), 3);
    }

}
