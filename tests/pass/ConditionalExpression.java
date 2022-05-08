package pass;
import java.lang.System;

public class ConditionalExpression {
    public static int conditional (boolean a, int x, boolean b, int y, int z) {
        return a ? x : b ? y : z; // conditional expressions are right associative, thus this should be processed as a ? x : (b ? y : z)
    }

    public static void main(String[] args) {
        System.out.println(this.conditional(false, 1, false, 2, 3)); // should return 3
    }
}