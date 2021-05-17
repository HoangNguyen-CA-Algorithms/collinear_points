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
        Point[] endingPointCheck = Arrays.copyOf(points, points.length);

        Arrays.sort(pointsCopy);

        Point[] slopePoints;
        ArrayList<Point> currentList = new ArrayList<>();


        for (int i = 0; i < pointsCopy.length; i++) {
            Point p = pointsCopy[i];

            slopePoints = Arrays.copyOfRange(pointsCopy, i, pointsCopy.length);
            Arrays.sort(slopePoints, p.slopeOrder());

            currentList.clear();
            currentList.add(p);
            currentList.add(slopePoints[0]);


            int count = 2; // counts p and slopePoints[0]
            double prevSlope = slopePoints[0].slopeTo(p);
            for (int j = 1; j < slopePoints.length; j++) {
                Point currPoint = slopePoints[j];
                double currSlope = currPoint.slopeTo(p);

                if (currSlope == prevSlope) {
                    currentList.add(currPoint);
                    count++;
                }
                else {
                    if (count >= 4 && p.compareTo(currentList.get(0)) < 0) {
                        Point[] temp = new Point[currentList.size()];
                        temp = currentList.toArray(temp);
                        Arrays.sort(temp);

                        LineSegment s = new LineSegment(temp[0], temp[temp.length - 1]);
                        segments[size++] = s;
                    }
                    currentList.clear();
                    currentList.add(p);
                    currentList.add(currPoint);
                    count = 2;
                }
                prevSlope = currSlope;
            }
            if (count >= 4) {
                Point[] temp = new Point[currentList.size()];
                temp = currentList.toArray(temp);
                Arrays.sort(temp);

                LineSegment s = new LineSegment(temp[0], temp[temp.length - 1]);
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