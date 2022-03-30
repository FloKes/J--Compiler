public class ForStatementEnumerable {
    public int forStatement(int i, int n, int x, int[] numbers){
        for(int item: numbers){
            n = n -1;
        }
        return n;
    }
}
