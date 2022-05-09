package pass;

import java.lang.Exception;

public class Throw {
    public void _throw () {
        try
        {
            throw new Exception("Testing exceptions, yay!");
        }
        catch(Exception e)
        {
            return;
        }
    }
}