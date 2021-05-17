import java.util.Arrays;

public class FastCollinearPoints {

    private LineSegment[] segments;
    private int size;
    public FastCollinearPoints(Point[] points) {
        checkPoints(points);

        segments = new LineSegment[points.length * points.length];
        size = 0;


        Point[] slopePoints;

        for (int i = 0; i < points.length; i++ ) {
            Point p = points[i];

            slopePoints = Arrays.copyOf(points, points.length);
            Arrays.sort(slopePoints, p.slopeOrder());


            for (int j1 = 0,j2 = 1, j3 = 2; j3 < slopePoints.length; j1++, j2++, j3++) {

                Point p1 = slopePoints[j1];
                Point p2 = slopePoints[j2];
                Point p3 = slopePoints[j3];

                double slope1 = p1.slopeTo(p);
                double slope2 = p2.slopeTo(p);
                double slope3 = p3.slopeTo(p);


                if (slope1 == Double.NEGATIVE_INFINITY || slope2 == Double.NEGATIVE_INFINITY || slope3 == Double.NEGATIVE_INFINITY) continue;


                if (slope1 == slope2 && slope2 == slope3) {
                    LineSegment segment = new LineSegment(p, p3);
                    segments[size++] = segment;
                }
            }
        }
    }

    private void checkPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        for (Point p: points) {
            if (p == null) throw new IllegalArgumentException();
        }

        for (int i =0; i < points.length; i++ ) {
            for (int j =i+1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    public int numberOfSegments() {
        return this.size;
    }

    public LineSegment[] segments() {
        LineSegment[] temp = new LineSegment[size];
        for (int i = 0; i < size; i++) {
            temp[i] = segments[i];
        }
        return temp;
    }
}