package pass;

import java.lang.Exception;
import java.lang.Throwable;
import java.lang.Error;

public class TryCatchFinally {
    public void tryCatchFinally () {
        try {
            int x = 5;
        } catch (Exception e) {
            int x = 10;
        } catch (Throwable e) {
            int x = 10;
        } catch (Error e) {
            int x = 10;
        } 
    }
}