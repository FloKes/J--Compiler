package pass;

public class ForStatement {
    public int forStatement(){
        for(int n = 0, i=5; i > 0; --i, n++){
            n = n + 1;
            i++;
        }
//        for(n = 0, i=5; i > 0; --i, n++){
//            n = n + 1;
//            i++;
//        }
        return i;
    }
}
