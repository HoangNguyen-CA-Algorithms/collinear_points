import java.util.ArrayList;
import java.util.Arrays;


public class FastCollinearPoints {

    private LineSegment[] segments;
    private int size;

    public FastCollinearPoints(Point[] points) {
        checkPoints(points);

        segments = new LineSegment[points.length * points.length];
        size = 0;


        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        ArrayList<Point> currentList = new ArrayList<>();


        for (int i = 0; i < pointsCopy.length; i++) {
            Arrays.sort(pointsCopy);
            Point p = pointsCopy[i];
            Arrays.sort(pointsCopy,p.slopeOrder());


            currentList.clear();
            currentList.add(pointsCopy[0]);

            int count = 1;
            double prevSlope = pointsCopy[0].slopeTo(p);
            for (int j = 1; j < pointsCopy.length; j++) {
                Point currPoint = pointsCopy[j];
                double currSlope = currPoint.slopeTo(p);

                if (currSlope == prevSlope) {
                    currentList.add(currPoint);
                    count++;
                }

                else {
                    if (count >= 3 && p.compareTo(currentList.get(0)) < 0) {
                        Point[] temp = new Point[currentList.size()];
                        temp = currentList.toArray(temp);
                        Arrays.sort(temp);

                        LineSegment s = new LineSegment(p, temp[temp.length - 1]);
                        segments[size++] = s;
                    }
                    currentList.clear();
                    currentList.add(currPoint);
                    count = 1;
                }
                prevSlope = currSlope;
            }

            if (count >= 3 && p.compareTo(currentList.get(0)) < 0) {

                Point[] temp = new Point[currentList.size()];
                temp = currentList.toArray(temp);
                Arrays.sort(temp);

                LineSegment s = new LineSegment(p, temp[temp.length - 1]);
                segments[size++] = s;
            }
        }
    }


    private void checkPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        for (Point p : points) {
            if (p == null) throw new IllegalArgumentException();
        }

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
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