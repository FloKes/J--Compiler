package pass;

public class Ternary {
    public int ternaryOp(int a, int b, int c, int d, int e) {
        // should be handled as a right-associative expression like this: a ? b : (c ? d : e)
        return a ? b : c ? d : e;
    }
}