package pass;

public class WhileStatement {
    public int whileStatement(int x){
        int n = 0;
        while(x > 0){
            n = n + x;
            x--;
        }
        return n;
    }
}
