package pass;

import java.lang.System;

interface AnInterfaceA {
    String a = "a";
    void x();
}

interface AnInterfaceB {
    ;
    ;
    String b = "b";
    String c = AnInterfaceA.a + "c";
    void x();
}

interface AnInterfaceC extends AnInterfaceA, AnInterfaceB {
}

public class Interfaces implements AnInterfaceA, AnInterfaceB, AnInterfaceC {

    public static String message() {
        return AnInterfaceA.a + ", " + b;
    }

    public void x() {}

}
