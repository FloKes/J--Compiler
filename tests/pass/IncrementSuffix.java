package pass;

public class IncrementSuffix {
    public int incrementSuffix(int x){
        x++;
        return x;
    }

    public int returnBeforeIncrement(int x){
        return x++;
    }
}