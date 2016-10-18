import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Marcin_Bazarnik on 2016-10-17.
 */
public class InversionsTest {

    @Test
    public void testGetNumberOfInversions() throws Exception {
        assertEquals(2L, getNumberOfInversionsWrapper(new int[] {2, 3, 9, 2, 9}));
        assertEquals(15L, getNumberOfInversionsWrapper(new int[] {9, 8, 7, 3, 2, 1}));
    }

    public long getNumberOfInversionsWrapper(int[] input) {
        int[] b = new int[input.length];
        long numberOfInversions = Inversions.getNumberOfInversions(input, b, 0, input.length);
        return numberOfInversions;
    }
}