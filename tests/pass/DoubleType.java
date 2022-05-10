package pass;

public class DoubleType {
    public int testDouble () {
        double op1 = 3.14;
        double op2 = 2.7;
        double op3 = op1 + op2;
        op3 += 3.0;
        if(op3>5.0)
            return 1;
        return 0;
    }
}
