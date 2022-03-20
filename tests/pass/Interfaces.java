// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package pass;

import java.lang.System;

public interface A {
    int a = 0;
}

public interface B {
    int b = 0;
}

public interface extends A,B {
    int c = 0;
}

public class Classes implements A, B, C {

    public static String message() {
        return A.a + ", " + (new B()).b;
    }

    public static void main(String[] args) {
        System.out.println(Classes.message());
    }

}

class A {

    public static String a = "Hello";

}

class B extends A {

    public String b = "World!";

}
