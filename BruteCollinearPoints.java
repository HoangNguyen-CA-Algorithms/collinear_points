/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class BruteCollinearPoints {

    private LineSegment[] segments;
    private int size = 0;


    // finds all line segments containing 4
    public BruteCollinearPoints(Point[] points) {
        segments = new LineSegment[points.length];
        for (int i = 0; i < points.length; i++){
            for (int j = i+1; j < points.length; j++){
                for (int k = j+1; k < points.length; k++){
                    for (int l = k+1; l < points.length; l++){
                        Point p1 = points[i];
                        Point p2 = points[j];
                        Point p3 = points[k];
                        Point p4 = points[l];

                        double slope1 = p1.slopeTo(p2);
                        double slope2 = p2.slopeTo(p3);
                        double slope3 = p3.slopeTo(p4);

                        if (slope1 == slope2 && slope2 == slope3){
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
    public LineSegment[] segments(){
        LineSegment[] temp = new LineSegment[size];
        for (int i =0; i < size;i++){
            temp[i] = segments[i];
        }
        return temp;
    }



    public static void main(String[] args) {

    }
}
