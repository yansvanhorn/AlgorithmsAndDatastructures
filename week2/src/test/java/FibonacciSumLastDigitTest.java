import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marcin_Bazarnik on 2016-09-28.
 */
public class FibonacciSumLastDigitTest {

    @Test
    public void testGetSumOfFibonacciLastDigit() throws Exception {

        for (int i = 0; i <= 50; i++) {
            System.out.println(i);
            assertEquals("i = " + i, FibonacciSumLastDigit.getFibonacciSumNaive(i), FibonacciSumLastDigit.getSumOfFibonacciLastDigit(i));
        }
    }

    @Test
    public void testGetSumOfFibonacciLastDigit_1000000() throws Exception {
        assertEquals(10, FibonacciSumLastDigit.getSumOfFibonacciLastDigit(1000000));
    }
}