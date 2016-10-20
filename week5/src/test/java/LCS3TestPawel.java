import org.junit.ComparisonFailure;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;


public class LCS3TestPawel {

    @Test
    public void test() {
        assertThat(LCS3.lcs3(
                new int[]{1, 5, 2},
                new int[]{1, 3, 0},
                new int[]{0, 5, 1})).isEqualTo(1);

        assertThat(LCS3.lcs3(
                new int[]{1, 2, 3},
                new int[]{2, 1, 3},
                new int[]{1, 3, 5})).isEqualTo(2);

        assertThat(LCS3.lcs3(
                new int[]{8, 3, 2, 1, 7},
                new int[]{8, 2, 1, 3, 8, 10, 7},
                new int[]{6, 8, 3, 1, 4, 7})).isEqualTo(3);

        assertThat(LCS3.lcs3(
                new int[]{1, 2},
                new int[]{2, 3},
                new int[]{3, 4})).isEqualTo(0);

        assertThat(LCS3.lcs3(
                new int[]{1, 2},
                new int[]{1, 3},
                new int[]{1, 4})).isEqualTo(1);

        assertThat(LCS3.lcs3(
                new int[]{1},
                new int[]{1},
                new int[]{1})).isEqualTo(1);

        assertThat(LCS3.lcs3(
                new int[]{1, 2, 3, 4, 5, 6, 7,},
                new int[]{2, 3, 4, 5, 6},
                new int[]{3, 4, 5})).isEqualTo(3);
    }

//    @Test
//    public void testNaive() {
//        assertThat(LCS3.naiveSolution(
//                new int[]{1, 2, 3},
//                new int[]{2, 1, 3},
//                new int[]{1, 3, 5})).isEqualTo(2);
//
//        assertThat(LCS3.naiveSolution(
//                new int[]{8, 3, 2, 1, 7},
//                new int[]{8, 2, 1, 3, 8, 10, 7},
//                new int[]{6, 8, 3, 1, 4, 7})).isEqualTo(3);
//
//        assertThat(LCS3.naiveSolution(
//                new int[]{1, 2},
//                new int[]{2, 3},
//                new int[]{3, 4})).isEqualTo(0);
//
//        assertThat(LCS3.naiveSolution(
//                new int[]{1, 2},
//                new int[]{1, 3},
//                new int[]{1, 4})).isEqualTo(1);
//
//        assertThat(LCS3.naiveSolution(
//                new int[]{1},
//                new int[]{1},
//                new int[]{1})).isEqualTo(1);
//
//        assertThat(LCS3.naiveSolution(
//                new int[]{1, 2, 3, 4, 5, 6, 7,},
//                new int[]{2, 3, 4, 5, 6},
//                new int[]{3, 4, 5})).isEqualTo(3);
//    }

//    @Ignore
//    @Test
//    public void stressTest() {
//        Random random = new Random(new Date().getTime());
//
//        int length = 3;
//        while (true) {
//            int[] a = random.ints(length, 0, length * 2).toArray();
//            int[] b = random.ints(length, 0, length * 2).toArray();
//            int[] c = random.ints(length, 0, length * 2).toArray();
//
//            try {
//                assertThat(LCS3.lcs3(a, b, c)).isEqualTo(LCS3.naiveSolution(a, b, c));
//            } catch (ComparisonFailure e) {
//                System.out.println(Arrays.toString(a));
//                System.out.println(Arrays.toString(b));
//                System.out.println(Arrays.toString(c));
//
//                e.printStackTrace();
//                break;
//            }
//        }
//
//    }
}
