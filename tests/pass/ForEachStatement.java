package pass;

public class ForEachStatement {
    public int forEachStatement(int n, int[] numbers){
        for(int item: numbers){
            n = n + item;
        }
        return n;
    }
}

