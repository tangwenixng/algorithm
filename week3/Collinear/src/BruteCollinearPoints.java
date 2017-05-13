import java.util.*;

/**
 * @author Haibo Yu on 05/01/2017.
 */
public class BruteCollinearPoints {

    private ArrayList<LineSegment> jSegments = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        Arrays.sort(points);
        if ( null == points ) {
            throw new NullPointerException("Points can't be null!");
        }
        for (int i = 0; i < points.length - 1; i++) {
            if ( null == points[i] ) {
                throw new NullPointerException("And point can't be null!");
            }
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
        Point[] jCopy = points.clone();
        Arrays.sort(jCopy);

        for (int first = 0; first < jCopy.length - 3; first++) {
            for (int second = first + 1; second < jCopy.length - 2; second++) {
                double slopeFS = jCopy[first].slopeTo(jCopy[second]);
                for (int third = second + 1; third < jCopy.length - 1; third++) {
                    double slopeFT = jCopy[first].slopeTo(jCopy[third]);
                    if (slopeFS == slopeFT) {
                        for (int forth = third + 1; forth < jCopy.length; forth++) {
                            double slopeFF = jCopy[first].slopeTo(jCopy[forth]);
                            if (slopeFS == slopeFF) {
                                jSegments.add(new LineSegment(jCopy[first], jCopy[forth]));
                            }
                        }
                    }
                }

            }
        }
    }


    // the number of line segments
    public int numberOfSegments() {
        return jSegments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return jSegments.toArray(new LineSegment[jSegments.size()]);
    }
}
