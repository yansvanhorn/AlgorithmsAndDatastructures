import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marcin_Bazarnik on 2016-09-29.
 */
public class FractionalKnapsackTest {

    @Test
    public void testGetOptimalValue() throws Exception {
        assertEquals(180.0000, FractionalKnapsack.getOptimalValue(
                50,
                new int[]{60, 100, 120},
                new int[]{20, 50, 30})
                , 0.0001);
        assertEquals(166.6667, FractionalKnapsack.getOptimalValue(
                10,
                new int[]{500},
                new int[]{30})
                , 0.0001);
        assertEquals(100, FractionalKnapsack.getOptimalValue(
                100,
                new int[]{Integer.MAX_VALUE},
                new int[]{Integer.MAX_VALUE})
                , 0.0001);
        assertEquals(100f / Integer.MAX_VALUE, FractionalKnapsack.getOptimalValue(
                100,
                new int[]{1},
                new int[]{Integer.MAX_VALUE})
                , 0.0001);
        assertEquals(Integer.MAX_VALUE, FractionalKnapsack.getOptimalValue(
                100,
                new int[]{Integer.MAX_VALUE},
                new int[]{1})
                , 0.0001);
        assertEquals(100, FractionalKnapsack.getOptimalValue(
                100,
                new int[]{100, 0},
                new int[]{50, 30})
                , 0.0001);
        assertEquals(7000, FractionalKnapsack.getOptimalValue(
                1500,
                new int[]{1000, 2000, 4000},
                new int[]{3, 3, 3})
                , 0.0001);
        assertEquals(150.26, FractionalKnapsack.getOptimalValue(
                15,
                new int[] {1, 100, 1, 50},
                new int[] {100, 1, 50, 1}),
                0.0001);
        assertEquals(8000, FractionalKnapsack.getOptimalValue(
                2000000 - 2,
                new int[]{1000, 2000, 8000},
                new int[]{ 2000000, 2000000 - 1, 2000000 - 2})
                , 0.0001);

    }
}