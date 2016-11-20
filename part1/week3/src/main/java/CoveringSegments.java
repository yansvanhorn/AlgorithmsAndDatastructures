import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class CoveringSegments {

    public static int[] optimalPoints(Segment[] segments) {

        LinkedList<Segment> pointRanges = new LinkedList<>();

        Arrays.sort(segments, Comparator.comparing(Segment::getStart));

        for(Segment segment : segments) {
            boolean found = false;

            for(Segment pointRange : pointRanges) {
                if(pointRange.isOverlapping(segment)) {
                    found = true;
                    pointRange.narrowTo(segment);
                }
            }

            if(!found) {
                pointRanges.add(new Segment(segment));
            }
        }

        return pointRanges.stream().mapToInt(p -> p.getEnd()).toArray();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}

class Segment {
    private int start, end;

    Segment(int start, int end) {
        this.start = start;
        this.end = end;
    }

    Segment(Segment other){
        this.start = other.getStart();
        this.end = other.getEnd();
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public boolean isOverlapping(Segment other) {
        return other.start <= end && other.end >= start;
    }

    public Segment overlap(Segment other) {
        return new Segment(
                max(start, other.getStart()),
                min(end, other.getEnd()));
    }

    public void narrowTo(Segment other) {
        start = max(start, other.getStart());
        end = min(end, other.getEnd());
    }

    public int length() {
        return end - start;
    }
}
