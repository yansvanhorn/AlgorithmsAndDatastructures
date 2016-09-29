import org.junit.Test;

/**
 * Created by Marcin_Bazarnik on 2016-09-29.
 */
public class FibonacciLastDigitTest {

    @Test
    public void stressTest() {
        for (int i = 0; i < 50; i++) {
            assertEquals(FibonacciLastDigit.getFibonacciLastDigitNaive(i), FibonacciLastDigit.fibonacciLastDigit(i));
        }
    }
}