package pass;

public class ForEachStatement {
    public int forEachStatement(int n, int[] numbers){
        for(int item: numbers){
            n = n + item;
        }
        return n;
    }
    public int twoForEachStatement(int n, int[] numbers){
        for(int item: numbers){
            n = n + item;
        }
        for(int item: numbers){
            n = n + item;
        }
        return n;
    }
}

