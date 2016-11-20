import java.math.BigInteger;
import java.util.*;

public class DotProduct {
    public static BigInteger maxDotProduct(int[] a, int[] b) {
        //write your code here

        Arrays.sort(a);
        Arrays.sort(b);

        BigInteger result = BigInteger.ZERO;

        for (int i = 0; i < a.length; i++) {
            result = result.add(BigInteger.valueOf((long)a[i] * (long)b[i]));
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        System.out.println(maxDotProduct(a, b));
    }
}

