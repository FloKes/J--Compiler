package pass;

public class ConditionalExpression {
    public int conditionalOp(boolean a, int b, boolean c, int d, int e) {
        // should be handled as a right-associative expression like this: a ? b : (c ? d : e)
        return a ? b : c ? d : e;
    }
}