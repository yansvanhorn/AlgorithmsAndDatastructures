import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Marcin_Bazarnik on 2016-09-29.
 */
public class DifferentSummandsTest {

    @Test
    public void testOptimalSummands() throws Exception {

//        assertEquals(Arrays.asList(1, 2, 3), DifferentSummands.optimalSummands(6));
        assertEquals(Arrays.asList(1, 2, 5), DifferentSummands.optimalSummands(8));
        assertEquals(Arrays.asList(1, 2, 6), DifferentSummands.optimalSummands(9));
        assertEquals(Arrays.asList(1, 2, 3, 4), DifferentSummands.optimalSummands(10));
//        assertEquals(Arrays.asList(2), DifferentSummands.optimalSummands(2));
    }
}