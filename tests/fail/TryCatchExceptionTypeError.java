// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package fail;

import java.lang.Integer;
import java.lang.System;

// This program has type errors and shouldn't compile.

public class TryCatchExceptionTypeError {

    public static void main(String[] args) {
        try {
            int x = 5;
        } catch (int e) {
            int x = 2;
        }
    }
}
