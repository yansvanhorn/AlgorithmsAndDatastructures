import java.util.*;

public class LCS3 {

    public static int lcs3(int[] a, int[] b, int[] c) {
        int[][][] seq = new int[a.length + 1][b.length + 1][c.length + 1];

//        for (int ia = 1; ia <= a.length; ia++) {
//            seq[ia][0][0] = ia;
//        }
//        for (int ib = 1; ib <= b.length; ib++) {
//            seq[0][ib][0] = ib;
//        }
//        for (int ic = 1; ic <= c.length; ic++) {
//            seq[0][0][ic] = ic;
//        }

        for (int ai = 1; ai <= a.length; ai++) {
            for (int bi = 1; bi <= b.length; bi++) {
                for (int ci = 1; ci <= c.length; ci++) {

                    seq[ai][bi][ci] = 0;

                    // Matches
                    if(seq[ai][bi][ci] <= seq[ai - 1][bi][ci]) { // ai
                        seq[ai][bi][ci] = seq[ai - 1][bi][ci];
                    }

                    if(seq[ai][bi][ci] <= seq[ai][bi - 1][ci]) { // bi
                        seq[ai][bi][ci] = seq[ai][bi - 1][ci];
                    }

                    if(seq[ai][bi][ci] <= seq[ai][bi][ci - 1]) { // ci
                        seq[ai][bi][ci] = seq[ai][bi][ci - 1];
                    }

                    if(seq[ai][bi][ci] <= seq[ai - 1][bi - 1][ci]) { // ai, bi
                        seq[ai][bi][ci] = seq[ai - 1][bi - 1][ci];
                    }

                    if(seq[ai][bi][ci] <= seq[ai - 1][bi][ci - 1]) { // ai, ci
                        seq[ai][bi][ci] = seq[ai - 1][bi][ci - 1];
                    }

                    if(seq[ai][bi][ci] <= seq[ai][bi - 1][ci - 1]) { // bi, ci
                        seq[ai][bi][ci] = seq[ai][bi - 1][ci - 1];
                    }

                    if(seq[ai][bi][ci] <= seq[ai - 1][bi - 1][ci - 1] + 1 && a[ai - 1] == b[bi -1] && b[bi - 1] == c[ci - 1]) { // match: ai, bi, ci
                        seq[ai][bi][ci] = seq[ai - 1][bi - 1][ci - 1] + 1;
                    }
                    else if (seq[ai][bi][ci] <= seq[ai - 1][bi - 1][ci - 1]) { // mismatch: ai, bi, ci
                        seq[ai][bi][ci] = seq[ai - 1][bi - 1][ci - 1];
                    }
                }
            }
        }

//        printArray(seq, a, b, c);

        return seq[a.length][b.length][c.length];
    }

    public static void printArray(int[][][] seq, int[] xs, int[] ys, int[] zs) {

        int zcount = seq[0][0].length;
        int ycount = seq[0].length;
        int xcount = seq.length;

        for (int z = 0; z < zcount; z++) {
            System.out.println("z=" + z);

            for (int y = 0; y < ycount; y++) {
                for (int x = 0; x < xcount; x++) {
                    System.out.printf("  %2d (%s,%s,%s)",
                            seq[x][y][z],
                            x > 0 ? Integer.toString(xs[x - 1]) : "-",
                            y > 0 ? Integer.toString(ys[y - 1]) : "-",
                            z > 0 ? Integer.toString(zs[z - 1]) : "-");
                }
                System.out.println("");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }
        System.out.println(lcs3(a, b, c));
    }
}

