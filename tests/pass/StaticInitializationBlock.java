public class TestStaticInitBlock {
    public static int x;

    static {
        x = 42;
    }

    public static void main(String[] args) {
        TestStaticInitBlock statInit = new TestStaticInitBlock();

        System.out.println("x = " + statInit.x);
    }
}