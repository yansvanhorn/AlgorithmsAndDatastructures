import java.io.*;
import java.util.*;

public class Sorting {
    private static Random random = new Random();

    public static int[] partition3(int[] a, int left, int right) {
        //write your code here
        int x = a[left];
        int xend = left;
        int loend = left;

        for (int i = left + 1; i <= right; i++) {

            if(a[i] < x) {
                swap(a, i, ++loend);
            }

            else if(a[i] == x) {
                swap(a, ++xend, ++loend);
                if(loend != i) {
                    swap(a, xend, i);
                }
            }
        }

        if(loend > xend) {
            int oldloend = loend;
            for(int i = left; i <= xend && loend > xend; i++) {
                swap(a, i, loend--);
            }
            loend = left + oldloend - xend;
            xend = oldloend;
        }
        else if(loend == xend) {
            loend = left;
        }

        return new int[] {loend, xend}; // end of lower values, end of equal values
    }

    private static void swap(int[] a, int i, int j) {
        if(i == j){
            return;
        }

        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static int partition2(int[] a, int l, int r) {
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[l];
        a[l] = a[j];
        a[j] = t;
        return j;
    }

    public static void randomizedQuickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        int x = a[k];
        a[k] = a[l];
        a[l] = x;
        //use partition3
//        int m = partition2(a, l, r);
//        randomizedQuickSort(a, l, m - 1);
//        randomizedQuickSort(a, m + 1, r);
        int[] m = partition3(a, l, r);
        randomizedQuickSort(a, l, m[0] - 1);
        randomizedQuickSort(a, m[1] + 1, r);
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        randomizedQuickSort(a, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

