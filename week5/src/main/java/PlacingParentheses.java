import java.util.ArrayList;
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
    private BiFunction<Integer, Integer, Integer> function;

    static HashMap<String, Op> ops = new HashMap<>();
    static {
        for(Op op : Op.values()) {
            ops.put(op.op, op);
        }
    }

    Op(String op, BiFunction<Integer, Integer, Integer> function) {
        this.op = op;
        this.function = function;
    }

    public static Op valueOfOp(String op) {
        return ops.get(op);
    }

    public int apply(int a, int b) {
        return function.apply(a, b);
    }


}

public class PlacingParentheses {
    public static long getMaximValue(String exp) {
        // parse input
        int[] values = parseValues(exp);
        Op[] ops = parseOps(exp);

        int[][] maxs = new int[values.length + 1][values.length + 1];
        int[][] mins = new int[values.length + 1][values.length + 1];

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

                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;

                for(int k = i; k <= j - 1; k++) {
                    int a = ops[k-1].apply(maxs[i][k], maxs[k + 1][j]);
                    int b = ops[k-1].apply(maxs[i][k], mins[k + 1][j]);
                    int c = ops[k-1].apply(mins[i][k], maxs[k + 1][j]);
                    int d = ops[k-1].apply(mins[i][k], mins[k + 1][j]);

                    min = min(min, min(min(a, b), min(c, d)));
                    max = max(max, max(max(a, b), max(c, d)));
                }

                mins[i][j] = min;
                maxs[i][j] = max;

//                printArray(mins, "Mins");
//                printArray(maxs, "Maxs");
            }
        }

        return maxs[1][values.length];
    }

    public static int[] parseValues(String exp) {

        String[] tokens = exp.split(" ");
        int[] values = new int[(tokens.length + 1) / 2];

        for (int i = 0; i < tokens.length; i += 2) {
            values[i / 2] = Integer.valueOf(tokens[i]);
        }
        return values;
    }

    public static Op[] parseOps(String exp) {

        String[] tokens = exp.split(" ");
        Op[] ops = new Op[(tokens.length - 1) / 2];
        for (int i = 1; i < tokens.length; i += 2) {
            ops[i / 2] = Op.valueOfOp(tokens[i]);
        }
        return ops;
    }

    public static void printArray(int[][] a, String... titles) {
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

