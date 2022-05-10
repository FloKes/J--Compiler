package junit;

import junit.framework.TestCase;
import pass.Interfaces;

public class InterfacesTest extends TestCase {

    public void testInterfaces() {
        this.assertEquals(Interfaces.message("ABC"), "ABCCA");
    }

}
