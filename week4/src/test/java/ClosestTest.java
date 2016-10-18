import org.apache.commons.lang3.time.StopWatch;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.OptionalDouble;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Marcin_Bazarnik on 2016-10-18.
 */
public class ClosestTest {

    @Test
    public void testJoinToFirstArray() throws Exception {
        Integer[] left = {1, 2, 3};
        left = Closest.joinToFirstArray(left, new Integer[] { 4, 5, 6} );

        Assert.assertArrayEquals(new Integer[] { 1, 2, 3, 4, 5, 6}, left);
    }

    @Test
    public void testMinimalDistance() throws Exception {
        assertEquals(1, Closest.minimalDistance(new int[] {1, 2, 3, 4, 5, 6, 7}, new int[] {0, 0, 0, 0, 0, 0, 0}), 0.001);
        assertEquals(9, Closest.minimalDistance(new int[] {-30, -20, -10, 1, 10, 20, 30}, new int[] {0, 0, 0, 0, 0, 0, 0}), 0.001);
        assertEquals(9, Closest.minimalDistance(new int[] {0, 0, 0, 0, 0, 0, 0}, new int[] {-30, -20, -10, 1, 10, 20, 30}), 0.001);
        assertEquals(1, Closest.minimalDistance(new int[] {-10, -8, -3, 1, 0, 2, 4, 7, 8}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0}), 0.001);
        assertEquals(1, Closest.minimalDistance(new int[] {-10, -8, -3, 1, 1, 0, 2, 4, 7, 8}, new int[] {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}), 0.001);
    }

    @Test
    public void testAdditional() {
        Offset<Double> offset = Offset.offset(0.00001);

        int[] x;
        int[] y;

        x = new int[]{-23, -93, 53, -10, 35};
        y = new int[]{-61, -54, 41, -15, 64};
        assertThat(Closest.minimalDistance(x, y)).isCloseTo(29.206163733020468, offset);

        x = new int[]{0, 3};
        y = new int[]{0, 4};
        assertThat(Closest.minimalDistance(x, y)).isCloseTo(5.0, offset);

        x = new int[]{7, 1, 4, 7};
        y = new int[]{7, 100, 8, 7};
        assertThat(Closest.minimalDistance(x, y)).isCloseTo(0.0, offset);

        x = new int[]{4, -2, -3, -1, 2, -4, 1, -1,  3, -4, -2};
        y = new int[]{4, -2, -4,  3, 3,  0, 1, -1, -1,  2,  4};
        assertThat(Closest.minimalDistance(x, y)).isCloseTo(1.414213, offset);

        x = new int[]{0, 5, 3, 7};
        y = new int[]{0, 6, 4, 2};
        assertThat(Closest.minimalDistance(x, y)).isCloseTo(2.828427, offset);

        x = new int[]{0, 0, 0, 0};
        y = new int[]{0, 1, 2, 3};
        assertThat(Closest.minimalDistance(x, y)).isCloseTo(1, offset);

        x = new int[]{0, 1, 2, 3};
        y = new int[]{0, 0, 0, 0};
        assertThat(Closest.minimalDistance(x, y)).isCloseTo(1, offset);
    }    
    
    @Test
    public void testMinimalIterativeDistance() throws Exception {
        assertEquals(1, Closest.iteratveMinimalDistance(
                toPoints(new int[] {1, 2, 3, 4, 5, 6, 7}, new int[] {0, 0, 0, 0, 0, 0, 0}),
                0, 7,
                Double.POSITIVE_INFINITY), 0.001);

        assertEquals(1, Closest.iteratveMinimalDistance(
                toPoints(new int[] {20, 15, 11, 8, 6, 5, 4}, new int[] {0, 0, 0, 0, 0, 0, 0}),
                0, 7,
                Double.POSITIVE_INFINITY), 0.001);

        assertEquals(1, Closest.iteratveMinimalDistance(
                toPoints(new int[] {0, 0, 0, 0, 0, 0, 0}, new int[] {20, 15, 11, 8, 6, 5, 4}),
                0, 7,
                Double.POSITIVE_INFINITY), 0.001);

    }

    public Point[] toPoints(int[] x, int[] y) {
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        return points;
    }

    @Test
    public void testPerformance() throws Exception {
        Random random = new Random(new Date().getTime());

        int[] x = random.ints(100000, -100000000, 100000000).toArray();
        int[] y = random.ints(100000, -100000000, 100000000).toArray();


        LinkedList<Long> times = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            Closest.minimalDistance(x, y);
            stopWatch.stop();
            times.add(stopWatch.getTime());
            System.out.println("i=" + i + " " + stopWatch.getTime() + " ms");
        }
        OptionalDouble average = times.stream().skip(10).mapToLong(Long::longValue).average();
        System.out.println("Avg=" + average);
        assertTrue(average.getAsDouble() < 200);
    }
}