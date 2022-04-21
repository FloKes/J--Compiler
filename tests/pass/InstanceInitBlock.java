public class TestInstanceInitBlock {
    public int x;

    {
        x = 42;
    }

    public static void main(String[] args) {
        TestInstanceInitBlock insInit = new TestInstanceInitBlock();

        System.out.println("x = " + insInit.x);
    }

}
