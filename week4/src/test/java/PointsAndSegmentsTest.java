import org.junit.Test;

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
    }
}