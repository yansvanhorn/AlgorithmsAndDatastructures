import java.util.*;

public class FibonacciLastDigit {
    public static long getFibonacciLastDigitNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % 10;
    }

    public static long fibonacciLastDigit(long n) {
        if(n <= 1) {
            return n;
        }

        long n2 = 0, n1 = 1, v = 0;

        for(long i = 2; i <= n; i++) {
            v = (n2 + n1) % 10; // it is neeed to only keep the reminder
            n2 = n1;
            n1 = v;
        }

        return v;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long c = fibonacciLastDigit(n);
        System.out.print(c);
    }
}

