import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PointsAndSegmentsPawel {

    public int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];

        List<Item> items = new LinkedList<>();

        for (int i = 0; i < starts.length; i++) {
            items.add(new Item(starts[i], ItemType.START, -1));
            items.add(new Item(ends[i], ItemType.END, -1));
        }

        for (int i = 0; i < points.length; i++) {
            items.add(new Item(points[i], ItemType.POINT, i));
        }

        items.sort(Comparator.<Item>comparingInt(item -> item.x).thenComparing(item -> item.type));

        int insideSegment = 0;
        for (Item item : items) {
            switch (item.type) {
                case START:
                    insideSegment++;
                    continue;
                case END:
                    insideSegment--;
                    continue;
                case POINT:
                    if (insideSegment > 0) {
                        cnt[item.cntIndex] += insideSegment;
                    }

            }
        }


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
//        int[] cnt = naiveCountSegments(starts, ends, points);
        int[] cnt = new PointsAndSegments().fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

enum ItemType {
    START, POINT, END;
}

class Item {
    final int x;
    final ItemType type;
    final int cntIndex;

    public Item(int x, ItemType type, int cntIndex) {
        this.x = x;
        this.type = type;
        this.cntIndex = cntIndex;
    }

    @Override
    public String toString() {
        return x + ":" + type + ":" + cntIndex;
    }
}


