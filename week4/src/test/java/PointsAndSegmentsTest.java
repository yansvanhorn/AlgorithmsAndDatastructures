import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.junit.rules.Stopwatch;

import java.util.Date;
import java.util.LinkedList;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by Marcin_Bazarnik on 2016-10-17.
 */
public class PointsAndSegmentsTest {

    @Test
    public void testFastCountSegments() throws Exception {

        assertArrayEquals(
                new int[] { 0, 0, 1, 2, 3, 2, 1, 0, 0 },
                PointsAndSegments.fastCountSegments(new int[] { 1, 2, 3 }, new int[] {3, 4, 5 }, new int[] { -1, 0, 1, 2, 3, 4, 5, 6, 7 })
        );

        assertArrayEquals(
                new int[] { 0, 3, 3, 0 },
                PointsAndSegments.fastCountSegments(new int[] { 1, 1, 1 }, new int[] {3, 3, 3 }, new int[] { 0, 1, 3, 4 })
        );

        assertArrayEquals(
                new int[] { 0, 1, 2, 2, 1, 0 },
                PointsAndSegments.fastCountSegments(new int[] { 1, 2, 3 }, new int[] {2, 3, 4 }, new int[] { 0, 1, 2, 3, 4, 5 })
        );
    }

    @Test
    public void testPerformance() throws Exception {
        Random random = new Random(new Date().getTime());

        int[] points = random.ints(50000, -100000000, 100000000).toArray();
        int[] starts = random.ints(50000, -100000000, 100000000).toArray();
        int[] ends = random.ints(50000, -100000000, 100000000).toArray();

        PointsAndSegmentsPawel segmentsPawel = new PointsAndSegmentsPawel();

        LinkedList<Long> times = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
//            int[] results = PointsAndSegments.naiveCountSegments(starts, ends, points);
            int[] results = PointsAndSegments.fastCountSegments(starts, ends, points);
//            int[] results = segmentsPawel.fastCountSegments(starts, ends, points);
            assertEquals(points.length, results.length);
            stopWatch.stop();
            times.add(stopWatch.getTime());
//            System.out.println("i=" + i + " " + stopWatch.getTime() + " ms");
        }
        OptionalDouble average = times.stream().mapToLong(Long::longValue).average();
        System.out.println("Avg=" + average);
        assertTrue(average.getAsDouble() < 100);
    }
}