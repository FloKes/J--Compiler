package pass;

public class ForStatement {
    public int forStatement(int n, int i){
        for(n = 0, i=5; i > 0; --i, n++){
            n = n + 1;
            i++;
        }
        return i;
    }
}
