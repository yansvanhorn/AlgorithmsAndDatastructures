import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Marcin_Bazarnik on 2016-10-07.
 */
public class SortingTest {

    @Test
    public void testPartition2() throws Exception {
        int[] a = {3, 6, 3, 4, 2, 5, 1};
        assertEquals(3, Sorting.partition2(a, 0, a.length - 1));
        assertArrayEquals(new int[]{1, 3, 2, 3, 6, 5, 4}, a);
    }

    @Test
    public void testReversed() throws Exception {
        int[] a = new int[]{9, 2, 3, 2, 9};
        assertArrayEquals(new int[]{3, 4}, Sorting.partition3(a, 0, a.length - 1));
        assertArrayEquals(Arrays.toString(a), new int[]{2, 2, 3, 9, 9}, a);
    }

    @Test
    public void testPartition3_2() throws Exception {
        int[] a = new int[]{8, 2, 9, 2, 5, 2, 2, 10};
        assertArrayEquals(new int[]{5, 5}, Sorting.partition3(a, 0, a.length - 1));
        assertArrayEquals(Arrays.toString(a), new int[]{2, 2, 2, 5, 2, 8, 9, 10}, a);
    }

    @Test
    public void testPartition3_3() throws Exception {
        int[] a = new int[]{3, 6, 3, 4, 2, 5, 1, 3, 3};
        assertArrayEquals(new int[]{2, 5}, Sorting.partition3(a, 0, a.length - 1));
        assertArrayEquals(new int[]{1, 2, 3, 3, 3, 3, 4, 6, 5}, a);
    }

    @Test
    public void testPartition3_4() throws Exception {

        int[] a = new int[]{3, 3};
        assertArrayEquals(new int[]{0, 1}, Sorting.partition3(a, 0, a.length - 1));
        assertArrayEquals(new int[]{3, 3}, a);

    }

    @Test
    public void testPartition3() throws Exception {

        {
            int[] a = new int[]{5, 4, 2, 3, 1, 0};
            assertArrayEquals(new int[]{3, 3}, Sorting.partition3(a, 2, 4));
            assertArrayEquals(new int[]{5, 4, 1, 2, 3, 0}, a);
        }

        {
            int[] a = new int[]{3, 6};
            assertArrayEquals(new int[]{0, 0}, Sorting.partition3(a, 0, a.length - 1));
            assertArrayEquals(new int[]{3, 6}, a);
        }
        {
            int[] a = new int[]{6, 3};
            assertArrayEquals(new int[]{1, 1}, Sorting.partition3(a, 0, a.length - 1));
            assertArrayEquals(new int[]{3, 6}, a);
        }
        {
            int[] a = new int[]{6, 3};
            assertArrayEquals(new int[]{1, 1}, Sorting.partition3(a, 0, a.length - 1));
            assertArrayEquals(new int[]{3, 6}, a);
        }
        {
            int[] a = new int[]{3, 3};
            assertArrayEquals(new int[]{0, 1}, Sorting.partition3(a, 0, a.length - 1));
            assertArrayEquals(new int[]{3, 3}, a);
        }
        {
            int[] a = new int[]{2, 3, 9, 2, 9};
            assertArrayEquals(new int[]{0, 1}, Sorting.partition3(a, 0, a.length - 1));
            assertArrayEquals(Arrays.toString(a), new int[]{2, 2, 9, 3, 9}, a);
        }
        {
            int[] a = new int[]{3, 2, 9, 2, 9};
            assertArrayEquals(new int[]{2, 2}, Sorting.partition3(a, 0, a.length - 1));
            assertArrayEquals(new int[]{2, 2, 3, 9, 9}, a);
        }

        int[] a = new int[]{3, 6, 3, 4, 2, 5, 1, 3, 3};
        assertArrayEquals(new int[]{2, 5}, Sorting.partition3(a, 0, a.length - 1));
        assertArrayEquals(new int[]{1, 2, 3, 3, 3, 3, 4, 6, 5}, a);

        a = new int[]{3, 6, 3, 4, 2, 5, 1};
        assertArrayEquals(new int[]{2, 3}, Sorting.partition3(a, 0, a.length - 1));
        assertArrayEquals(new int[]{1, 2, 3, 3, 6, 5, 4}, a);

        a = new int[]{3, 3, 3, 3, 3};
        assertArrayEquals(new int[]{0, 4}, Sorting.partition3(a, 0, a.length - 1));
        assertArrayEquals(new int[]{3, 3, 3, 3, 3}, a);
    }

//    @Test
//    public void testStress() throws Exception {
//
//        Random r = new Random(new Date().getTime());
//
//        for (int i = 0; i < 100; i++) {
//            int[] ints = r.ints(20, 0, 10 + 1).toArray();
//            int[] copy = Arrays.copyOf(ints, ints.length);
//
//
//        }
//    }

    @Test
    public void testSort() throws Exception {

//        int[] a = { 0, 8, 4, 2, 7, 3, 4, 4, 4, 4, 0 };
//        Sorting.randomizedQuickSort(a, 0, a.length - 1);
//        assertArrayEquals(new int[] { 0, 0, 2, 3, 4, 4, 4, 4, 4, 7, 8 }, a);

        {
            int[] a = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
            Sorting.randomizedQuickSort(a, 0, a.length - 1);
            assertArrayEquals(Arrays.toString(a), new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, a);
        }

        {
            int[] a = new int[]{2, 3, 9, 2, 9};
            Sorting.randomizedQuickSort(a, 0, a.length - 1);
            assertArrayEquals(new int[]{2, 2, 3, 9, 9}, a);
        }
    }
}