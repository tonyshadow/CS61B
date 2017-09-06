import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {
    /** When the input number is great than 127, this method will be broken. */
    @Test
    public void testFlik() {
        assertTrue(Flik.isSameNumber(127, 127));
        assertTrue(Flik.isSameNumber(128, 128));
    }
}
