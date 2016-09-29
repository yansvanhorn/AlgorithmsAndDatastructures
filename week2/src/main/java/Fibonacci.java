import java.util.Scanner;

public class Fibonacci {
    public static long slow_fibonacci(int n) {
        if (n <= 1)
            return n;

        return slow_fibonacci(n - 1) + slow_fibonacci(n - 2);
    }

    public static long fibonacci(int n) {
        if(n <= 1) {
            return n;
        }

        int n2 = 0, n1 = 1, v = 0;

        for(int i = 2; i <= n; i++) {
            v = n2 + n1;
            n2 = n1;
            n1 = v;
        }

        return v;
    }



    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(fibonacci(n));
    }
} 