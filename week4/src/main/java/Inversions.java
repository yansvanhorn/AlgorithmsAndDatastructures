import java.util.*;

public class Inversions {

    public static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;
        if (left + 1 >= right) {
            return numberOfInversions;
        }
        int ave = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, b, left, ave);
        numberOfInversions += getNumberOfInversions(a, b, ave, right);

        //write your code here
        int a1idx = left, a2idx = ave, bidx = left;

        while(a1idx < ave && a2idx < right) {
            if(a[a1idx] > a[a2idx]) {
                b[bidx++] = a[a2idx++];
                numberOfInversions++;
            }
            if(a[a1idx] <= a[a2idx]) {
                b[bidx++] = a[a1idx++];
            }
        }

        while(a1idx < ave) {
            b[bidx++] = a[a1idx++];
            numberOfInversions++;
        }

        while(a2idx < right) {
            b[bidx++] = a[a2idx++];
        }

        for(int i = left; i < right; i++) {
            a[i] = b[i];
        }

        return numberOfInversions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println(getNumberOfInversions(a, b, 0, a.length));
    }
}

