import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcin_Bazarnik on 2016-10-07.
 */
public class MajorityElementTest {

    @Test
    public void testGetMajorityElement() throws Exception {
        assertEquals(-1, MajorityElement.getMajorityElement(new int[] { 1, 2, 3, 4 }, 0, 0));
        assertEquals(-1, MajorityElement.getMajorityElement(new int[] { 1, 2, 2, 4 }, 0, 0));
        assertEquals(2, MajorityElement.getMajorityElement(new int[] { 1, 2, 2, 4, 2 }, 0, 0));
    }
}