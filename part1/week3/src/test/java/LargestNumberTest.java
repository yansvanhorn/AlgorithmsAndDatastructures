import org.junit.Test;

import java.util.Comparator;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by Marcin_Bazarnik on 2016-09-30.
 */
public class LargestNumberTest {

    @Test
    public void testLargestNumber() throws Exception {

        assertEquals("1101000", LargestNumber.largestNumber(new String[]{"1", "10", "1000"}));
        assertEquals("1101000", LargestNumber.largestNumber(new String[]{"1000", "10", "1"}));
        assertEquals("1101000", LargestNumber.largestNumber(new String[]{"10", "1000", "1"}));

        assertEquals("1101000", LargestNumber.largestNumber(new String[]{"00010", "01000", "001"}));

        assertEquals("9876543210", LargestNumber.largestNumber(new String[]{"9", "8", "7", "6", "5", "4", "3", "2", "1", "0"}));

        assertEquals("5121110", LargestNumber.largestNumber(new String[]{"10", "11", "12", "5"}));

        assertEquals("34333533", LargestNumber.largestNumber(new String[]{"33", "335", "343"}));

        assertEquals("551252111", LargestNumber.largestNumber(new String[]{"1", "2", "5", "51", "11", "25"}));

        assertEquals("353533", LargestNumber.largestNumber(new String[]{"3", "35", "35", "3"}));

        assertEquals("12321123", LargestNumber.largestNumber(new String[]{"123", "12321"}));
        assertEquals("12321123", LargestNumber.largestNumber(new String[]{"12321", "123"}));

    }

    @Test
    public void testCompare() throws Exception {

        CardComparatorExtending comparator = new CardComparatorExtending();
        assertUsingComparator(comparator);
    }

    @Test
    public void testCompareIterative() throws Exception {

        CardComparatorIterative comparator = new CardComparatorIterative();
        assertUsingComparator(comparator);
    }

    @Test
    public void testComparatorJoining() throws Exception {

        assertUsingComparator(new CardComparatorJoining());
    }

    private void assertUsingComparator(Comparator<String> comparator) {
        assertTrue(comparator.compare("100", "10") < 0);
        assertTrue(comparator.compare("10", "100") > 0);

        assertTrue(comparator.compare("12", "123") < 0);
        assertTrue(comparator.compare("123", "12") > 0);

        assertTrue(comparator.compare("123", "12321") < 0);
        assertTrue(comparator.compare("12321", "123") > 0);

        assertTrue(comparator.compare("88", "8888") > 0);
        assertTrue(comparator.compare("8888", "88") < 0);

        assertTrue(comparator.compare("88", "88898") < 0);
        assertTrue(comparator.compare("88898", "88") > 0);

        assertTrue(comparator.compare("343", "335") > 0);
        assertTrue(comparator.compare("335", "343") < 0);

        assertTrue(comparator.compare("88", "88889") < 0);
        assertTrue(comparator.compare("88889", "88") > 0);

        assertTrue(comparator.compare("343", "335") > 0);
        assertTrue(comparator.compare("335", "343") < 0);

        assertTrue(comparator.compare("99", "999995") > 0);
        assertTrue(comparator.compare("999995", "99") < 0);

        assertTrue(comparator.compare("99", "995") > 0);
        assertTrue(comparator.compare("995", "99") < 0);

        assertTrue(comparator.compare("1", "0") > 0);
        assertTrue(comparator.compare("0", "1") < 0);

        assertTrue(comparator.compare("11", "10") > 0);
        assertTrue(comparator.compare("10", "11") < 0);
        assertTrue(comparator.compare("11", "10") > 0);
        assertTrue(comparator.compare("123", "123") == 0);
    }

    public static void main(String[] params) throws Exception {
        Comparator<String> comparator = new CardComparatorExtending();

        Random random = new Random(new Date().getTime());
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String a = Integer.toString(random.nextInt(10001));
            String b = Integer.toString(random.nextInt(10001));

            int ab = comparator.compare(a, b);
            int ba = comparator.compare(b, a);

            System.out.printf("%s %s %s, ", a, ab == 0 ? "=" : (ab < 0 ? "<" : ">"), b);
            System.out.printf("%s %s %s, ", a, ba == 0 ? "=" : (-ba < 0 ? "<" : ">"), b);
            System.out.println();

            scanner.nextLine();
        }
    }
}

