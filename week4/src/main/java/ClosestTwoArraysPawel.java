import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringTokenizer;

import static java.lang.Integer.signum;
import static java.lang.Math.min;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.abs;
import static java.lang.String.format;
import static java.lang.System.arraycopy;
import static java.util.Arrays.sort;

public class ClosestTwoArraysPawel {


    static Comparator<Point> BY_X = (p1, p2) -> signum(p1.x - p2.x);
    static Comparator<Point> BY_Y = (p1, p2) -> signum(p1.y - p2.y);
    static Comparator<Point> BY_X_THEN_BY_Y = (p1, p2) -> (p1.x != p2.x) ? signum(p1.x - p2.x) : signum(p1.y - p2.y);

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return Objects.equals(x, point.x) &&
                    Objects.equals(y, point.y);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public double minimalDistance(int[] x, int y[]) {
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        return minimalDistance(points);
    }

    public double minimalDistance(Point[] pointsByX) {
        int length = pointsByX.length;

        sort(pointsByX, BY_X_THEN_BY_Y);
//        sort(pointsByX, BY_X);

//        for (int i = 0; i < length - 1; i++) {
//            if (pointsByX[i].equals(pointsByX[i + 1])) {
//                return 0;
//            }
//        }

        return minDistance(pointsByX, new Point[length], 0, length);
    }

    private double minDistance(Point[] pointsByX, Point[] pointsByY, int left, int right) {
        Double distance = minDistanceSmallSet(pointsByX, pointsByY, left, right);
        if (distance != null) {
            return distance;
        }

        int mid = (left + right) / 2;
        int midPointX = pointsByX[mid].x;

        double dLeft = minDistance(pointsByX, pointsByY, left, mid);
        double dRight = minDistance(pointsByX, pointsByY, mid, right);

        mergeByY(pointsByY, left, right);


        Point[] strip = new Point[right - left];
        double delta = min(dLeft, dRight);
        int stripSize = 0;
        for (int i = left; i < right; i++) {
            if (abs(pointsByY[i].x - midPointX) < delta) {
                strip[stripSize++] = pointsByY[i];
            }
        }

        for (int i = 0; i < stripSize; i++) {
            for (int j = i + 1; j < stripSize && (strip[j].y - strip[i].y) < delta; j++) {
                delta = min(delta, distance(strip[i], strip[j]));
            }
        }

        return delta;
    }

    private void mergeByY(Point[] pointsByY, int left, int right) {
        Point[] tmp = new Point[right - left];
        int li = left;
        int ri = (left + right) / 2;
        int mi = 0;
        while (mi < tmp.length) {
            if (ri == right || li < (left + right) / 2 && BY_Y.compare(pointsByY[li], pointsByY[ri]) < 0) {
                tmp[mi++] = pointsByY[li++];
            } else {
                tmp[mi++] = pointsByY[ri++];
            }
        }
        arraycopy(tmp, 0, pointsByY, left, tmp.length);
    }

    private Double minDistanceSmallSet(Point[] pointsByX, Point[] pointsByY, int left, int right) {
        if (right - left <= 1) {
            pointsByY[left] = pointsByX[left];
            return Double.MAX_VALUE;
        }

        if (right - left == 2) {
            if (BY_Y.compare(pointsByX[left], pointsByX[left + 1]) <= 0) {
                pointsByY[left] = pointsByX[left];
                pointsByY[left + 1] = pointsByX[left + 1];
            } else {
                pointsByY[left] = pointsByX[left + 1];
                pointsByY[left + 1] = pointsByX[left];
            }
            return distance(pointsByX[left], pointsByX[left + 1]);
        }

//        if (right - left == 3) {
//            double distance = distance(pointsByX[left], pointsByX[left + 1]);
//            distance = min(distance, distance(pointsByX[left], pointsByX[left + 2]));
//            distance = min(distance, distance(pointsByX[left + 1], pointsByX[left + 2]));
//            return distance;
//        }

        return null;
    }

    private double distance(Point a, Point b) {
        double ax = a.x;
        double ay = a.y;
        return sqrt((ax - b.x) * (ax - b.x) + (ay - b.y) * (ay - b.y));
    }

    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        Point[] points = new Point[n];
//        int[] x = new int[n];
//        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(nextInt(), nextInt());
        }
        System.out.println(new ClosestTwoArraysPawel().minimalDistance(points));
        writer.close();
    }

    public double bruteDistance(int[] x, int[] y) {
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }

        double min = Double.MAX_VALUE;

        for (int i = 0; i < x.length - 1; i++) {
            for (int j = i + 1; j < x.length; j++) {
                double distance = distance(points[i], points[j]);
//                System.out.println(points[i] + "-" + points[j] + " : " + distance);
                min = min(min, distance);
                if (min == 0) {
                    return 0;
                }
            }
        }

        return min;
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
