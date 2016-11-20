import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcin_Bazarnik on 2016-10-19.
 */
public class KnapsackTest {

    @Test
    public void testOptimalWeightDP() throws Exception {

        assertEquals(1, Knapsack.optimalWeightDP(1, new int[] { 1, 2, 4, 6, 8 }));
        assertEquals(2, Knapsack.optimalWeightDP(2, new int[] { 1, 2, 4, 6, 8 }));
        assertEquals(3, Knapsack.optimalWeightDP(3, new int[] { 1, 2, 4, 6, 8 }));
        assertEquals(5, Knapsack.optimalWeightDP(5, new int[] { 1, 2, 4, 6, 8 }));
        assertEquals(10, Knapsack.optimalWeightDP(10, new int[] { 1, 2, 4, 6, 8 }));
        assertEquals(11, Knapsack.optimalWeightDP(11, new int[] { 1, 2, 4, 6, 8 }));
        assertEquals(12, Knapsack.optimalWeightDP(12, new int[] { 1, 2, 4, 6, 8 }));
        assertEquals(13, Knapsack.optimalWeightDP(13, new int[] { 1, 2, 4, 6, 8 }));
        assertEquals(14, Knapsack.optimalWeightDP(14, new int[] { 1, 2, 4, 6, 8 }));
        assertEquals(15, Knapsack.optimalWeightDP(15, new int[] { 1, 2, 4, 6, 8 }));
        assertEquals(16, Knapsack.optimalWeightDP(16, new int[] { 1, 2, 4, 6, 8 }));
        assertEquals(17, Knapsack.optimalWeightDP(17, new int[] { 1, 2, 4, 6, 8 }));
        assertEquals(18, Knapsack.optimalWeightDP(18, new int[] { 1, 2, 4, 6, 8 }));
        assertEquals(19, Knapsack.optimalWeightDP(19, new int[] { 1, 2, 4, 6, 8 }));

        assertEquals(7, Knapsack.optimalWeightDP(9, new int[] { 5, 6, 7, 10 }));
        assertEquals(18, Knapsack.optimalWeightDP(20, new int[] { 5, 6, 7, 10 }));
        assertEquals(21, Knapsack.optimalWeightDP(21, new int[] { 5, 6, 7, 10 }));

    }
}