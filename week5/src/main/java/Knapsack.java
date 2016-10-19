import java.util.*;

public class Knapsack {
//    public static int optimalWeight(int W, int[] w) {
//        //write you code here
//        int result = 0;
//        for (int i = 0; i < w.length; i++) {
//          if (result + w[i] <= W) {
//            result += w[i];
//          }
//        }
//        return result;
//    }

    public static int optimalWeightDP(int w, int[] bars) {

        int[] weight = new int[w + 1];
        boolean[][] taken = new boolean[w + 1][];

        weight[0] = 0;
        taken[0] = new boolean[bars.length];

        for (int i = 1; i <= w; i++) {
            weight[i] = weight[i - 1];
            int bar = -1;

            for (int b = 0; b < bars.length; b++) {
                if (i - bars[b] >= 0 && !taken[i - bars[b]][b]) {
                    int maybeWeight = weight[i - bars[b]] + bars[b];
                    if (maybeWeight > weight[i]) {
                        weight[i] = maybeWeight;
                        bar = b;
                    }
                }
            }
            if (bar > -1) {
                taken[i] = Arrays.copyOf(taken[i - bars[bar]], bars.length);
                taken[i][bar] = true;
            } else {
                taken[i] = Arrays.copyOf(taken[i - 1], bars.length);
            }
        }

        return weight[w];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeightDP(W, w));
    }
}

