import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcin_Bazarnik on 2016-10-06.
 */
public class BinarySearchTest {

    @Test
    public void testBinarySearch() throws Exception {
        assertEquals(0, BinarySearch.binarySearch(new int[] { 0, 1, 2, 5, 10, 20}, 0));
        assertEquals(4, BinarySearch.binarySearch(new int[] { 0, 1, 2, 5, 10, 20}, 10));
        assertEquals(5, BinarySearch.binarySearch(new int[] { 0, 1, 2, 5, 10, 20}, 20));

        assertEquals(-1, BinarySearch.binarySearch(new int[] { 0, 1, 2, 5, 10, 20}, -5));
        assertEquals(-1, BinarySearch.binarySearch(new int[] { 0, 1, 2, 5, 10, 20}, 21));
    }
}