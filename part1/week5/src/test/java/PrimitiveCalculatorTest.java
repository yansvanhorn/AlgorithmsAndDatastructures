import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcin_Bazarnik on 2016-10-19.
 */
public class PrimitiveCalculatorTest {

    @Test
    public void testOptimal_dp() throws Exception {

        assertArrayEquals(new Integer[] { 1, 3, 9, 10 }, PrimitiveCalculator.optimal_dp(10).toArray());

        assertArrayEquals(new Integer[] { 1, 3, 9, 10, 11, 22, 66, 198, 594, 1782, 5346, 16038, 16039, 32078, 96234 }, PrimitiveCalculator.optimal_dp(96234).toArray());
        assertArrayEquals(new Integer[] { 1 }, PrimitiveCalculator.optimal_dp(1).toArray());
        assertArrayEquals(new Integer[] { 1, 2 },  PrimitiveCalculator.optimal_dp(2).toArray());
        assertArrayEquals(new Integer[] { 1, 3}, PrimitiveCalculator.optimal_dp(3).toArray());
        assertArrayEquals(new Integer[] { 1, 2, 4 }, PrimitiveCalculator.optimal_dp(4).toArray());
        assertArrayEquals(new Integer[] { 1, 2, 4, 5 }, PrimitiveCalculator.optimal_dp(5).toArray());
        assertArrayEquals(new Integer[] { 1, 2, 6 }, PrimitiveCalculator.optimal_dp(6).toArray());
        assertArrayEquals(new Integer[] { 1, 2, 6, 7 }, PrimitiveCalculator.optimal_dp(7).toArray());
        assertArrayEquals(new Integer[] { 1, 2, 4, 8 }, PrimitiveCalculator.optimal_dp(8).toArray());
        assertArrayEquals(new Integer[] { 1, 3, 9 }, PrimitiveCalculator.optimal_dp(9).toArray());
    }
}