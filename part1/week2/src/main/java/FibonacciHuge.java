import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;

public class FibonacciHuge {
    private static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % m;
    }



    public static long getFibonacciModuloM(long n, long m) {
        if(n <= 1) {
            return n;
        }

        // Find sequence
        long n2 = 0, n1 = 1; // n-x values

        long[] modulos = new long[] { n2, n1 };
        int size = modulos.length;

        long modulo = 0;

        for(int i = 2; i <= n; i++) {
            modulo = (n2 + n1) % m;
            n2 = n1;
            n1 = modulo;

            // resize array
            if(i == size) {
                size *= 2;
                modulos = Arrays.copyOf(modulos, size);
            }
            modulos[i] = modulo;

            // if sequence found
            if(modulos[i-1] == 0 && modulos[i] == 1) {
                int sequence = i - 1;
                return modulos[(int)(n % sequence)];
            }
        }

        // no sequence
        return modulo;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
//        System.out.println(getFibonacciModuloM(n, m));
        System.out.println(getFibonacciModuloM(n, m));

//        print(i -> i);
//        print(i -> getFibonacciHugeNaive(i, 2));
//        print(i -> getFibonacciModuloM(i, 2));
//        print(i -> getFibonacciHugeNaive(i, 3));
//        print(i -> getFibonacciModuloM(i, 3));
//        print(i -> getFibonacciHugeNaive(i, 4));
//        print(i -> getFibonacciModuloM(i, 4));
//        print(i -> getFibonacciHugeNaive(i, 5));
//        print(i -> getFibonacciModuloM(i, 5));

//        System.out.println("Result:");
//        for (int modulo = 2; modulo < 10; modulo++) {
//            getFibonacciModuloM(40, modulo);
//        }
    }

    public static void print(Function<Long, Long> callback) {
        for (long i = 0; i <= 40; i++)
        {
            System.out.print(String.format("%4d", callback.apply(i)));
        }
        System.out.println("");
    }
}

