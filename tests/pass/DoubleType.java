package pass;

public class DoubleType {
    public int testDouble () {
        double op1 = 8.0;
        double op2 = 1.0;
        double op3 = op1 + op2;
        double divide = 5.0;
        op3 %= divide;
        if(op3>3.99)
            return 1;
        return 0;
    }
}
