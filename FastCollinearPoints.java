import java.util.Arrays;

public class FastCollinearPoints {

    private LineSegment[] segments;
    private int size;
    public FastCollinearPoints(Point[] points) {
        segments = new LineSegment[points.length];
        size = 0;


        double[] slopes = new double[points.length];

        for (int i = 0; i < points.length; i++ ) {
            Point p = points[i];

            for (int j = 0; j < points.length; j++){
                Point q = points[q];
                double slope = p.slopeTo(q);
                slopes[j] = slope;
            }

            Arrays.sort(slopes);
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