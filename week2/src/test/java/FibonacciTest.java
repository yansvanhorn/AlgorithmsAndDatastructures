/**
 * Created by Marcin_Bazarnik on 2016-09-28.
 */
public class FibonacciTest {

    @org.junit.Test
    public void testFibonacci() throws Exception {

        for(int n = 0; n < 30; n++) {
            System.out.println("n = " + n);
            assertEquals(Fibonacci.slow_fibonacci(n), Fibonacci.fibonacci(n));
        }
    }
}