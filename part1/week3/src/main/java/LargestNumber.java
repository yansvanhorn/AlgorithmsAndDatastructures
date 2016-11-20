import java.util.*;
import java.util.stream.Collectors;

public class LargestNumber {
    public static String largestNumber(String[] values) {
        //write your code here
        String number = Arrays
                .stream(values)
                .map(a -> Integer.valueOf(a).toString()) // remove leading zeros
                .sorted(new CardComparatorJoining().reversed()).collect(Collectors.joining());
        return number;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}

class CardComparatorIterative implements Comparator<String> {
    @Override
    public int compare(String a, String b) {
        int ia = -1, ib = -1, value = 0;
        int alen = a.length() - 1;
        int blen = b.length() - 1;
        do {
            if (ia < alen) {
                ia++;
            }
            if (ib < blen) {
                ib++;
            }
            int aval = (a.charAt(ia) - '0');
            int bval = (b.charAt(ib) - '0');

            value += aval - bval;
        }
        while ((ia != alen || ib != blen) && value == 0);

        return value != 0 ? value : blen - alen; // prefer shorter if values equal
    }
}

class CardComparatorExtending implements Comparator<String> {
    @Override
    public int compare(String a, String b) {
        int alen = a.length() - 1;
        int blen = b.length() - 1;
        int maxlen = (alen > blen ? alen : blen);

        for (int i = alen + 1; i <= maxlen; i++) {
            a += a.charAt(alen);
        }
        for (int i = blen + 1; i <= maxlen; i++) {
            b += b.charAt(blen);
        }

        int value = Integer.valueOf(a) - Integer.valueOf(b);
        return value != 0 ? value : blen - alen; // prefer shorter if values equal
    }
}

class CardComparatorJoining implements Comparator<String> {
    @Override
    public int compare(String a, String b) {
        int value = Integer.valueOf(a + b) - Integer.valueOf(b + a);
        return value != 0 ? value : b.length() - a.length();
    }
}


