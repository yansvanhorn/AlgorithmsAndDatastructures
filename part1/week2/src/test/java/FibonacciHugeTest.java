import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marcin_Bazarnik on 2016-09-28.
 */
public class FibonacciHugeTest {

    @Test
    public void testGetFibonacciModuloM() throws Exception {
        assertEquals(75, FibonacciHuge.getFibonacciModuloM(1000, 100));
    }

    @Test(timeout = 1500)
    public void testGetFibonacciModulo_100_100000() throws Exception {
        assertEquals(15075, FibonacciHuge.getFibonacciModuloM(100, 100000));
    }

    @Test(timeout = 1500)
    public void testGetFibonacciModulo_99999999999999999_100000() throws Exception {
        assertEquals(90626, FibonacciHuge.getFibonacciModuloM(99999999999999999L, 100000));
    }
}