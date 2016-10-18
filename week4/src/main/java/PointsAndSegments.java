import java.util.*;

public class PointsAndSegments {

    public static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        Arrays.sort(starts);
        Arrays.sort(ends);

        int[] valueChanges = new int[starts.length + ends.length + 1];
        int[] values = new int[starts.length + ends.length + 1];
        int value = 0, lastValue = 0, vidx = 0, sidx = 0, eidx = 0, point = -200000000;

        valueChanges[vidx] = point;
        values[vidx++] = value;

        while(sidx < starts.length && eidx < ends.length) {
            if(starts[sidx] <= ends[eidx]) {
                point = starts[sidx];
//                System.out.printf("Start[%d] = %d\n", sidx, point);

                while(sidx < starts.length && point == starts[sidx]) {
                    value++;
                    sidx++;
//                    System.out.println("Value up " + value);
                }
            } else if (starts[sidx] >= ends[eidx]) {
                point = ends[eidx] + 1;
//                System.out.printf("End[%d] = %d\n", eidx, point);

                while(eidx < ends.length && point > ends[eidx]) {
                    value--;
                    eidx++;
//                    System.out.println("Value down " + value);
                }
            }

            if(value != lastValue) {
//                System.out.println("Value changed " + point + "=" + value);
                valueChanges[vidx] = point;
                values[vidx++] = value;
                lastValue = value;
            }
        }

        while (eidx < ends.length) {
            point = ends[eidx] + 1;
            while(eidx < ends.length && point > ends[eidx]) {
                value--;
                eidx++;
//                System.out.println("Value down " + value);
            }

            valueChanges[vidx] = point;
            values[vidx++] = value;
//            System.out.println("Value changed " + point + "=" + value);
        }

        // Shorten array
        values = Arrays.copyOfRange(values, 0, vidx);
        valueChanges = Arrays.copyOfRange(valueChanges, 0, vidx);

//        System.out.println("valueChanges = " + Arrays.toString(valueChanges));
//        System.out.println("      values = " + Arrays.toString(values));



        int[] cnt = new int[points.length];
        for (int p = 0; p < points.length; p++) {
            int idx = Arrays.binarySearch(valueChanges, points[p]);
            if(idx >= 0) {
                cnt[p] = values[idx];
            } else if(idx < 0) {
                cnt[p] = values[-idx-2];
            }
//            System.out.println("Point " + points[p] + " idx=" + idx + " value = " + cnt[p]);
        }

//        System.out.println(Arrays.toString(cnt));
      //write your code here
        return cnt;
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = naiveCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

