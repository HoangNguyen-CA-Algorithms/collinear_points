/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] segments;
    private int size = 0;


    // finds all line segments containing 4
    public BruteCollinearPoints(Point[] points) {
        checkPoints(points);

        segments = new LineSegment[points.length * points.length];

        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);

        for (int i = 0; i < pointsCopy.length; i++) {
            for (int j = i+1; j < pointsCopy.length; j++) {
                for (int k = j+1; k < pointsCopy.length; k++) {
                    for (int l = k+1; l < pointsCopy.length; l++) {
                        Point p1 = pointsCopy[i];
                        Point p2 = pointsCopy[j];
                        Point p3 = pointsCopy[k];
                        Point p4 = pointsCopy[l];

                        double slope1 = p1.slopeTo(p2);
                        double slope2 = p2.slopeTo(p3);
                        double slope3 = p3.slopeTo(p4);

                        if (slope1 == slope2 && slope2 == slope3) {
                            LineSegment seg = new LineSegment(p1, p4);
                            segments[size++] = seg;
                        }
                    }
                }
            }
        }
    }




    // the number of line segments
    public int numberOfSegments() {
        return size;
    }


    // the line segments
    public LineSegment[] segments() {
        LineSegment[] temp = new LineSegment[size];
        for (int i =0; i < size;i++) {
            temp[i] = segments[i];
        }
        return temp;

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


    public static void main(String[] args) {
        Point temp = new Point(1,1);
        Point[] p = {temp, temp, temp, null};
        BruteCollinearPoints b = new BruteCollinearPoints(p);
    }
}
