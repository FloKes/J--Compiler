package pass;

import java.lang.Exception;
import java.lang.System;

public class ThrowTryCatch {
    public int _throw () {
        try
        {
            throw new Exception("Testing exceptions, yay!");
            return 5;
        }
        catch(Exception e)
        {
            return 10;
        }

        return 15;
    }
}