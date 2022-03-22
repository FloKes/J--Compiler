package pass;

import java.lang.System;

interface AnInterfaceA {
    int a = 0;
}

interface AnInterfaceB {
    int b = 0;
}

interface AnInterfaceC extends AnInterfaceA, AnInterfaceB {
    int c = 0;
}

public class Interfaces implements AnInterfaceA, AnInterfaceB, AnInterfaceC {

    public static String message() {
        return A.a + ", " + (new B()).b;
    }

    public static void main(String[] args) {
        System.out.println(Classes.message());
    }

}

class Aa {

    public static String a = "Hello";

}

class Bb extends Aa {

    public String b = "World!";

}
