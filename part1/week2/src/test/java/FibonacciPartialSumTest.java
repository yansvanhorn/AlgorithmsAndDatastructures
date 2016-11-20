import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcin_Bazarnik on 2016-09-29.
 */
public class FibonacciPartialSumTest {

    @Test
    public void testGetFibonacciPartialSum() throws Exception {

        for (int i = 0; i < 30; i++) {
            for (int j = i; j < 30; j++) {
                assertEquals("Assert fail for " + i + " " + j,
                        FibonacciPartialSum.getFibonacciPartialSumNaive(i, j),
                        FibonacciPartialSum.getFibonacciPartialSum(i, j));
            }
        }
    }

    @Test
    public void test0_2() throws Exception {
//        testI_J(0, 2);
        assertEquals(2, FibonacciPartialSum.getFibonacciPartialSumNaive(0, 2));
        assertEquals(2, FibonacciPartialSum.getFibonacciPartialSum(0, 2));
    }

    @Test
    public void test3_7() throws Exception {
//        testI_J(3, 7);
        assertEquals(1, FibonacciPartialSum.getFibonacciPartialSum(3, 7));
    }

    @Test
    public void test10_200() throws Exception {
        testI_J(10, 200);
    }

    public void testI_J(int i, int j) throws Exception {

        assertEquals("Assert fail for " + i + " " + j,
                FibonacciPartialSum.getFibonacciPartialSumNaive(i, j),
                FibonacciPartialSum.getFibonacciPartialSum(i, j));

    }

    // Not really a test
    public void testForSequence() throws Exception {

        for (int i = 0; i < 5; i++) {
            for (int j = i; j < 5; j++) {
                System.out.printf("lasti-1=%d, lasti=%d, lastj=%d, expected=%d\n",
                        FibonacciPartialSum.getSumOfFibonacciLastDigit(i - 1),
                        FibonacciPartialSum.getSumOfFibonacciLastDigit(i),
                        FibonacciPartialSum.getSumOfFibonacciLastDigit(j),
                        FibonacciPartialSum.getFibonacciPartialSumNaive(i, j) );
            }
        }

    }
}