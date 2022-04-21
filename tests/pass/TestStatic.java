public class TestStatic {

    static{
        System.out.println("Static");
    }

    {
        System.out.println("Non-static block");
    }

    public static void main(String[] args) {
        TestStatic t = new Test();
        TestStatic t2 = new Test();
    }
}