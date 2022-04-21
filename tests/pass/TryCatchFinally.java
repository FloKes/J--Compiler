package pass;

public class TryCatchFinally {
    public void tryCatchFinally () {
        try {
            int x = 5;
            try{
                int z = 0;
            } catch{
                int z = 1;
            }
        } catch (Exception e) {
            int x = 10;
        } finally {
            int y = 20;
        }
    }

    public void tryCatch () {
        try {
            int x = 5;
        } catch (Exception e) {
            int x = 10;
        }
    }
}