import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Comparator.comparingInt;

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

class Result {
    double distance;
    Point[] leftByY;
    Point[] rightByY;

    public Result(double distance, Point[] leftByY, Point[] rightByY) {
        this.distance = distance;
        this.rightByY = rightByY;
        this.leftByY = leftByY;
    }

    @Override
    public String toString() {
        return "Result{" +
                "distance=" + distance +
                ", leftByY=" + Arrays.toString(leftByY) +
                ", rightByY=" + Arrays.toString(rightByY) +
                "}";
    }
}


public class Closest {
    public static double minimalDistance(int[] x, int y[]) {
//        System.out.println("-- Start --");

        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }

        Arrays.sort(points, comparingInt(Point::getX));
        Result result = minimalDistance(points, 0, points.length);

        return result.distance;
    }

    public static Result minimalDistance(Point[] points, int left, int right) {
//        System.out.printf("Called %d-%d, %s\n", left, right, Arrays.toString(Arrays.copyOfRange(points, left, right)));
        if (right - left < 4) {
            Result result = iterativePrepareResult(points, left, right, Double.POSITIVE_INFINITY);
//            System.out.println("Returning " + result + "\n");
            return result;
        }

        int pivot = (right + left) / 2;

        Result leftResult = minimalDistance(points, left, pivot);
        Result rightResult = minimalDistance(points, pivot, right);

//        System.out.println("left = " + leftResult);
//        System.out.println("right = " + rightResult);

        // min distance
        double distance = leftResult.distance < rightResult.distance
                ? leftResult.distance
                : rightResult.distance;

        // filter
        if(distance < leftResult.distance) {
            leftResult.rightByY = filterByX(leftResult.rightByY, points[pivot - 1], distance);
        }
        if(distance < rightResult.distance) {
            rightResult.leftByY = filterByX(rightResult.leftByY, points[pivot], distance);
        }

        // merge for strip
        Point[] strip = mergeResult(leftResult.rightByY, rightResult.leftByY);

        // search strip
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < distance; j++) {
                distance = min(distance, strip[i].distance(strip[j]));
            }
        }

        // refilter after searching strip
        if(distance < leftResult.distance) {
            leftResult.leftByY = filterByX(leftResult.leftByY, points[left], distance);
        }
        if(distance < rightResult.distance) {
            rightResult.rightByY = filterByX(rightResult.rightByY, points[right - 1], distance);
        }

//        System.out.println("Returning " + distance + "\n");
        return new Result(distance, leftResult.leftByY, rightResult.rightByY);
    }

    private static Point[] filterByX(Point[] points, Point pivot, double distance) {
        return Arrays.stream(points)
                .filter(p -> Math.abs(pivot.x - p.x) < distance)
                .toArray(Point[]::new);
    }

    public static Point[] mergeResult(Point[] rightByY, Point[] leftByY) {
        Point[] result = new Point[rightByY.length + leftByY.length];

        int l = 0, r = 0, i = 0;
        while(r < rightByY.length && l < leftByY.length) {
            if(leftByY[l].y < rightByY[r].y) {
                result[i++] = leftByY[l++];
            } else {
                result[i++] = rightByY[r++];
            }
        }

        while(l < leftByY.length) {
            result[i++] = leftByY[l++];
        }

        while(r < rightByY.length) {
            result[i++] = rightByY[r++];
        }

        return result;
    }

    public static <T> T[] joinToFirstArray(T[] leftPoints, T[] rightPoints) {
        int offset = leftPoints.length;
        T[] joined = Arrays.copyOf(leftPoints, leftPoints.length + rightPoints.length);
        for (int i = 0; i < rightPoints.length; i++) {
            joined[i + offset] = rightPoints[i];
        }
        return joined;
    }

    public static double iteratveMinimalDistance(Point[] points, int left, int right, double distance) {
        for (int i = left; i < right - 1; i++) {
            Point pi = points[i];
            for (int j = i + 1; j < right; j++) {
                double d = pi.distance(points[j]);
                if (d < distance) {
                    distance = d;
                }
            }
        }
        return distance;
    }

    public static Result iterativePrepareResult(Point[] points, int left, int right, double distance) {
        // solve iteratively
        double sigma = iteratveMinimalDistance(points, left, right, distance);

        int pivotLeft = points[left].x;
        int pivotRight = points[right - 1].x;

        Point[] leftByY = Arrays.stream(points, left, right)
                .filter(p -> p.x - pivotLeft < sigma)
                .sorted(comparingInt(Point::getY))
                .toArray(Point[]::new);

        Point[] rightByY = Arrays.stream(points, left, right)
                .filter(p -> pivotRight - p.x < sigma)
                .sorted(comparingInt(Point::getY))
                .toArray(Point[]::new);

        return new Result(sigma, leftByY, rightByY);
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
