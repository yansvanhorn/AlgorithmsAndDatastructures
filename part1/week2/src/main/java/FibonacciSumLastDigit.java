import java.util.*;
import java.util.function.Function;

public class FibonacciSumLastDigit {
    public static long getFibonacciSumNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current;
        }

        return sum % 10;
    }

    public static long getSumOfFibonacciLastDigit(long n) {
        if(n <= 1) {
            return n;
        }

        long n2 = 0, n1 = 1; // n-x values

        long[] sumsLastDigit = new long[] { n2, n1 };
        int size = sumsLastDigit.length;

        long lastDigit = 0;
        long sumLastDigit = n2 + n1;

        for(int i = 2; i <= n; i++) {
            lastDigit = (n2 + n1) % 10;
            n2 = n1;
            n1 = lastDigit;

            sumLastDigit = (sumLastDigit + lastDigit) % 10;

            // resize array
            if(i == size) {
                size *= 2;
                sumsLastDigit = Arrays.copyOf(sumsLastDigit, size);
            }
            sumsLastDigit[i] = sumLastDigit;

            // if sequence found
            if(sumsLastDigit[i-1] == 0 && sumsLastDigit[i] == 1) {
                int sequence = i - 1;
                return sumsLastDigit[(int)(n % sequence)];
            }
        }

        return sumLastDigit;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
//        long s = getFibonacciSumNaive(n);
        long s = getSumOfFibonacciLastDigit(n);
        System.out.println(s);

//        print(i -> getFibonacciSumNaive(i));
//        print(i -> getSumOfFibonacciLastDigit(i));
    }

    public static void print(Function<Long, Long> callback) {
        for (long i = 0; i <= 100; i++)
        {
            System.out.print(String.format("%3d", callback.apply(i)));
        }
        System.out.println("");
    }
}

