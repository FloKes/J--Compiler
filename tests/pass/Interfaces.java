package pass;

import java.lang.System;

interface IA {
    String identity(String a);
}

class CA implements IA {
    public String identity(String a) {
        return a + "CA";
    }
}

public class Interfaces {

    public static String message(String a) {
        CA cls = new CA();
        return ((IA)cls).identity(a);
    }

    public static void main(String[] args) {
        CA cls = new CA();
        System.out.println(cls.identity("cls"));
        System.out.println(((IA)cls).identity("ia"));
    }

}
