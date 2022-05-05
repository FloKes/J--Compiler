package pass;

public class ForStatement {
    public int forStatement(int x){
        int n = 0;
        for(int i= x; i > 0; i--){
            n = n + 1;
        }
        return n;
    }
    public int forStatementMultInit(int x, int y){
        int n = 0;
        for(int i= x, j = y; i > 0; i--, j--){
            n = n + x + y;
        }
        return n;
    }
    public int forStatementExp(int x){
        int n = 0;
        int i = 0;
        for(i= x; i > 0; i--){
            n = n + 1;
        }
        return n;
    }
    public int forStatementMultExp(int x, int y){
        int n = 0;
        int i = 0, j = 0;
        for(i= x, j = y; i > 0; i--, j--){
            n = n + x + y;
        }
        return n;
    }
}
