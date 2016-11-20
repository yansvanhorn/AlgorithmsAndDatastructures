import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcin_Bazarnik on 2016-10-20.
 */
public class LCS3Test {

    @Test
    public void testLcs3() throws Exception {

        assertEquals(2, LCS3.lcs3(
                new int[] { 1, 2, 3, 4 },
                new int[] { 2, 1, 4, 3 },
                new int[] { 2, 1, 3, 4 }));
    }
}