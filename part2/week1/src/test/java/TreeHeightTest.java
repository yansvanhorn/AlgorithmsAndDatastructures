import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcin_Bazarnik on 2016-11-20.
 */
public class TreeHeightTest {

    @Test
    public void testComputeHeight() throws Exception {
        assertEquals(5, new TreeHeight(new int[] {-1, 0, 1, 2, 3}).computeHeight());
        assertEquals(3, new TreeHeight(new int[] {4, -1, 4, 1, 1}).computeHeight());
    }
}