import java.util.*;

public class FibonacciPartialSum {
    public static long getFibonacciPartialSumNaive(long from, long to) {
        if (to <= 1)
            return to;

        long previous = 0;
        long current = 1;

        for (long i = 0; i < from - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        long sum = current;

        for (long i = 0; i < to - from; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current;
        }

        return sum % 10;
    }

    public static long getFibonacciPartialSum(long from, long to) {

        if(from == 0) {
            return getSumOfFibonacciLastDigit(to);
        }

        long possiblyNegativeSum = getSumOfFibonacciLastDigit(to) - getSumOfFibonacciLastDigit(from - 1);
        return possiblyNegativeSum < 0 ? possiblyNegativeSum + 10 : possiblyNegativeSum;
    }

    public static long getSumOfFibonacciLastDigit(long n) {
        if (n <= 1) {
            return n;
        }

        long n2 = 0, n1 = 1; // n-x values

        long[] sumsLastDigit = new long[]{n2, n1};
        int size = sumsLastDigit.length;

        long lastDigit = 0;
        long sumLastDigit = n2 + n1;

        for (int i = 2; i <= n; i++) {
            lastDigit = (n2 + n1) % 10;
            n2 = n1;
            n1 = lastDigit;

            sumLastDigit = (sumLastDigit + lastDigit) % 10;

            // resize array
            if (i == size) {
                size *= 2;
                sumsLastDigit = Arrays.copyOf(sumsLastDigit, size);
            }
            sumsLastDigit[i] = sumLastDigit;

            // if sequence found
            if (sumsLastDigit[i - 1] == 0 && sumsLastDigit[i] == 1) {
                int sequence = i - 1;
                return sumsLastDigit[(int) (n % sequence)];
            }
        }

        return sumLastDigit;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSum(from, to));
    }
}

