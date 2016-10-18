import java.io.*;
import java.util.*;

import static java.lang.Math.*;

class Point implements Comparable<Point> {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        return o.y == y ? Long.signum(x - o.x) : Long.signum(y - o.y);
    }

    public int compareByX(Point p) {
        return x - p.x;
    }

    public double distanceWithoutSqrt(Point p) {
        return Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2);
    }

    public double distance(Point p) {
        return Math.sqrt(distanceWithoutSqrt(p));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "{" + x + ", " + y + '}';
    }
}


public class Closest {
    public static double minimalDistance(int[] x, int y[]) {
//        System.out.println("-- Start --");

        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }

        Arrays.sort(points, Comparator.comparingInt(Point::getX));
        double ans =  minimalDistance(points, 0, points.length);

        return ans;
    }

    public static double minimalDistance(Point[] points, int left, int right) {
//        System.out.printf("Called %d-%d, %s\n", left, right, Arrays.toString(Arrays.copyOfRange(points, left, right)));
        if(right - left < 4) {
            return iteratveMinimalDistance(points, left, right, Double.POSITIVE_INFINITY);
        }

        int pivot = (right + left) / 2;

        double leftDistance = minimalDistance(points, left, pivot);
        double rightDistance = minimalDistance(points, pivot, right);

//        System.out.printf("%d-%d distance=%.3f\n", left, pivot, leftDistance);
//        System.out.printf("%d-%d distance=%.3f\n", pivot, right, rightDistance);

        Point pivotPoint = points[pivot];
        Point[] leftPoints = Arrays.stream(points, left, pivot).filter(p -> pivotPoint.x - p.x <= leftDistance).toArray(Point[]::new);
        Point[] rightPoints = Arrays.stream(points, pivot, right).filter(p -> p.x - pivotPoint.x <= rightDistance).toArray(Point[]::new);

//        System.out.printf("leftdistance=%.3f, leftPoints=%s\n", leftDistance, Arrays.toString(leftPoints));
//        System.out.printf("leftdistance=%.3f, rightPoints=%s\n", rightDistance, Arrays.toString(rightPoints));

        leftPoints = joinToFirstArray(leftPoints, rightPoints);

        double distance = iteratveMinimalDistance(leftPoints, 0, leftPoints.length, Math.min(leftDistance, rightDistance));
        return distance;
    }

    public static <T> T[] joinToFirstArray(T[] leftPoints, T[] rightPoints) {
        int offset = leftPoints.length;
        leftPoints = Arrays.copyOf(leftPoints, leftPoints.length + rightPoints.length);
        for (int i = 0; i < rightPoints.length; i++) {
            leftPoints[i + offset] = rightPoints[i];
        }
        return leftPoints;
    }

    public static double iteratveMinimalDistance(Point[] points, int left, int right, double distance) {
        for (int i = left; i < right - 1; i++) {
            Point pi = points[i];
            for(int j = i + 1; j < right; j++) {
                double d = pi.distance(points[j]);
                if(d < distance) {
                    distance = d;
                }
            }
        }
        return distance;
    }


    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }
        System.out.println(minimalDistance(x, y));
        writer.close();
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
