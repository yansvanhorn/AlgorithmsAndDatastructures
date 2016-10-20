import java.util.*;

class EditDistance {
    public static int editDistance(String s, String t) {

        int[][] dist = calculateDistance(s, t);
        return dist[s.length()][t.length()];
    }

    public static int[][] calculateDistance(String s, String t) {
        int[][] dist = new int[s.length() + 1][t.length() + 1];

        // Create edges (1-n) values
        for (int si = 1; si <= s.length(); si++) {
            dist[si][0] = si;
        }
        for (int ti = 1; ti <= t.length(); ti++) {
            dist[0][ti] = ti;
        }

        // Calculate edit distance
        for (int si = 1; si <= s.length(); si++) {
            for (int ti = 1; ti <= t.length(); ti++) {

                dist[si][ti] = Integer.MAX_VALUE;
                if (dist[si][ti] > dist[si - 1][ti - 1] && s.charAt(si - 1) == t.charAt(ti - 1)) { // match
                    dist[si][ti] = dist[si - 1][ti - 1];
                }
                if (dist[si][ti] > dist[si - 1][ti - 1] + 1 && s.charAt(si - 1) != t.charAt(ti - 1)) { // mismatch
                    dist[si][ti] = dist[si - 1][ti - 1] + 1;
                }
                if (dist[si][ti] > dist[si - 1][ti] + 1) { // insert
                    dist[si][ti] = dist[si - 1][ti] + 1;
                }
                if (dist[si][ti] > dist[si][ti - 1] + 1) { // deletion
                    dist[si][ti] = dist[si][ti - 1] + 1;
                }
            }
        }

//        printArray(dist);

        return dist;
    }

    public static String[] alignment(String s, String t) {
        int[][] dist = calculateDistance(s, t);

        String[] alignment = new String[2];

//        String salign = s.substring(s.length() - 1), talign = t.substring(t.length() - 1);
        String salign = "", talign = "";

        for (int ti = t.length(), si = s.length(); ti > 0 && si > 0; ) {
            int min = dist[si][ti];
            int offsets = 0, offsett = 0;

            if (si - 1 >= 0 && dist[si - 1][ti] <= min) { // insert/delete
                offsets = -1;
                offsett = 0;
                min = dist[si - 1][ti];
            }
            if (ti - 1 >= 0 && dist[si][ti - 1] <= min) { // insert/delete
                offsets = 0;
                offsett = -1;
                min = dist[si][ti - 1];
            }
            if (si - 1 >= 0 && ti - 1 >= 0 && dist[si - 1][ti - 1] <= min) { // match/mismatch
                offsets = -1;
                offsett = -1;
                min = dist[si - 1][ti - 1];
            }

            salign = (offsets == 0 ? "-" : s.charAt(si - 1)) + salign;
            talign = (offsett == 0 ? "-" : t.charAt(ti - 1)) + talign;

            si += offsets;
            ti += offsett;
        }

        alignment[0] = salign;
        alignment[1] = talign;

        return alignment;
    }

    public static void printArray(int[][] a) {
        for (int y = 0; y < a.length; y++) {
            for (int x = 0; x < a[y].length; x++) {
                System.out.printf("  %2d", a[y][x]);
            }
            System.out.println("");
        }
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        String s = scan.next();
        String t = scan.next();

        System.out.println(editDistance(s, t));
    }

}
