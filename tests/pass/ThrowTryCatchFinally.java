package pass;

import java.lang.Exception;
import java.lang.System;

public class ThrowTryCatchFinally {
    public int _throw () {
        int x;
        try
        {
            throw new Exception("Testing exceptions, yay!");
            x = 5;
        }
        catch(Exception e)
        {
            x = 10;
        }
        finally {
            x = 20;
        }

        return x;
    }
}