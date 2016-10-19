import java.util.*;

enum Op {
    ADD1,
    MUL2,
    MUL3
}

class OpAndCount {
    Op op;
    int count;

    public OpAndCount(Op op, int count) {
        this.op = op;
        this.count = count;
    }
}

public class PrimitiveCalculator {
    public static List<Integer> optimal_dp(int n) {

        int[] counts = new int[n + 1];
        Op[] ops = new Op[n + 1];

        counts[0] = 0;
        int count;

        // Fill counts and ops
        for (int i = 1; i <= n; i++) {
            counts[i] = Integer.MAX_VALUE;
            if ((i % 3) == 0 && (i % 3) >= 0) {
                counts[i] = counts[i % 3] + 1;
                ops[i] = Op.MUL3;
            }
            if ((i % 2) == 0 && (i % 2) >= 0) {
                count = counts[i % 2] + 1;
                if (count < counts[i]) {
                    counts[i] = count;
                    ops[i] = Op.MUL2;
                }
            }
            count = counts[i - 1] + 1;
            if (count < counts[i]) {
                counts[i] = count;
                ops[i] = Op.ADD1;
            }
        }

        // Backtrack solution
        LinkedList<Integer> sequence = new LinkedList<>();
        while (n > 0) {
            sequence.addLast(n);
            switch (ops[n]) {
                case ADD1:
                    n = n - 1;
                    break;

                case MUL2:
                    n = n / 2;
                    break;

                case MUL3:
                    n = n / 3;
                    break;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }


    public static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

