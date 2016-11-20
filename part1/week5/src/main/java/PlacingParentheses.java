import java.util.HashMap;
import java.util.Scanner;
import java.util.function.BiFunction;

import static java.lang.Math.max;
import static java.lang.Math.min;

enum Op {
    ADD("+", (a, b) -> a + b),
    MUL("*", (a, b) -> a * b),
    SUB("-", (a, b) -> a - b);

    private String op;
    private BiFunction<Long, Long, Long> function;

    static HashMap<String, Op> ops = new HashMap<>();
    static {
        for(Op op : Op.values()) {
            ops.put(op.op, op);
        }
    }

    Op(String op, BiFunction<Long, Long, Long> function) {
        this.op = op;
        this.function = function;
    }

    public static Op valueOfOp(String op) {
        return ops.get(op);
    }

    public long apply(long a, long b) {
        return function.apply(a, b);
    }


}

public class PlacingParentheses {
    public static long getMaximValue(String exp) {
        // parse input
        long[] values = parseValues(exp);
        Op[] ops = parseOps(exp);

        long[][] maxs = new long[values.length + 1][values.length + 1];
        long[][] mins = new long[values.length + 1][values.length + 1];

        // init (diagonal: 1 - values.length)
        for (int i = 1; i <= values.length; i++) {
            mins[i][i] = values[i - 1];
            maxs[i][i] = values[i - 1];
        }
//        printArray(mins, "Mins");
//        printArray(maxs, "Maxs");

        for(int s = 1; s <= values.length; s++) {
            for (int i = 1; i <= values.length - s; i++) {
                int j = i + s;

                long min = Integer.MAX_VALUE;
                long max = Integer.MIN_VALUE;

                for(int k = i; k <= j - 1; k++) {
                    long a = ops[k-1].apply(maxs[i][k], maxs[k + 1][j]);
                    long b = ops[k-1].apply(maxs[i][k], mins[k + 1][j]);
                    long c = ops[k-1].apply(mins[i][k], maxs[k + 1][j]);
                    long d = ops[k-1].apply(mins[i][k], mins[k + 1][j]);

                    min = min(min, min(min(a, b), min(c, d)));
                    max = max(max, max(max(a, b), max(c, d)));
                }

                mins[i][j] = min;
                maxs[i][j] = max;

                printArray(mins, "Mins");
                printArray(maxs, "Maxs");
            }
        }

        return maxs[1][values.length];
    }

    public static long[] parseValues(String exp) {
        long[] values = new long[(exp.length() + 1) / 2];

        for (int i = 0; i < exp.length(); i += 2) {
            values[i / 2] = Long.valueOf(exp.substring(i, i + 1));
        }
        return values;
    }

    public static Op[] parseOps(String exp) {

        Op[] ops = new Op[(exp.length() - 1) / 2];
        for (int i = 1; i < exp.length(); i += 2) {
            ops[i / 2] = Op.valueOfOp(exp.substring(i, i + 1));
        }
        return ops;
    }

    public static void printArray(long[][] a, String... titles) {
        if(titles.length > 0) {
            for(String title : titles) {
                System.out.println(title);
            }
        }
        for (int y = 0; y < a.length; y++) {
            for (int x = 0; x < a[y].length; x++) {
                System.out.printf("  %4d", a[y][x]);
            }
            System.out.println("");
        }
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

