import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcin_Bazarnik on 2016-09-30.
 */
public class CoveringSegmentsTest {

    @Test
    public void testOptimalPoints() throws Exception {

        assertArrayEquals(new int[] { 3 },
                CoveringSegments.optimalPoints(toSegment(new int[][] {
                        { 1, 3 },
                        { 2, 5 },
                        { 3, 6 }
                })));

        assertArrayEquals(new int[] { 3, 6 },
                CoveringSegments.optimalPoints(toSegment(new int[][] {
                        { 4, 7 },
                        { 1, 3 },
                        { 2, 5 },
                        { 5, 6 }
                })));
    }

    public Segment[] toSegment(int[][] values) {
        Segment[] segments = new Segment[values.length];

        int i = 0;
        for(int[] value : values) {
            segments[i++] = new Segment(value[0], value[1]);
        }

        return segments;
    }


}