public class ForStatementEnumerable {
    public int forStatement(int n, int[] numbers){
        for(int item: numbers){
            n = n + item;
        }
        return n;

    }
}
