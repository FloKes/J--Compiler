package pass;

import java.lang.System;


public class InstanceInitBlock {
    public int x;

    {
        x = 42;
    }

    public static void main(String[] args) {
        InstanceInitBlock insInit = new InstanceInitBlock();

        System.out.println("x = " + insInit.x);
    }

}
