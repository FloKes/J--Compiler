package pass;

import java.lang.System;


public class StaticInitializationBlock {
    public static int x;

    static {
        x = 42;
    }

    public static void main(String[] args) {
        StaticInitializationBlock statInit = new StaticInitializationBlock();

        System.out.println("x = " + StaticInitializationBlock.x);
    }
}