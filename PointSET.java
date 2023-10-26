
/****************************************************************************
 *  Compilation:  javac PointSET.java
 *  Execution:    
 *  Dependencies:
 *  Author:
 *  Date:
 *
 *  Data structure for maintaining a set of 2-D points, 
 *    including rectangle and nearest-neighbor queries
 *
 *************************************************************************/

import java.util.Arrays;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.TreeSet;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

public class PointSET {

    private TreeSet<Point2D> BST;

    // construct an empty set of points
    public PointSET() {
        BST = new TreeSet<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return BST.isEmpty();
    }

    // number of points in the set
    public int size() {
        return BST.size();
    }

    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if(p == null){
            throw new IlligalArgumentExpression();
        }
        BST.add(p);
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) {
        if(p == null){
            throw new IllegalArgumentException();
        }

        return BST.contains(p);
    }

    // draw all of the points to standard draw
    public void draw() {
        for(Point2D point : BST){
            point.draw();
        }
    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        public Iterable<Point2D> range(RectHV rect) {
            if (rect == null) {
                throw new IllegalArgumentException();
            }
        
            for (Point2D point : BST) {
                if (rect.contains(point)) {
                    count++;
                }
            }
        
            Point2D[] pointsInRect = new Point2D[count];

            for (Point2D point : BST) {
                if (rect.contains(point)) {
                    pointsInRect[count] = point;
                }
            }
        
            return Arrays.asList(result);
        }
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        if(p == null){
            throw new IllegalArgumentException();
        }

        if(this.isEmpty()){
            return null;
        }

        Point2D nearest = null;
        double closestDistance = Double.MAX_VALUE;

        for(Point2D point : BST){
            if(point.distanceSquaredTo(p) < closestDistance){
                nearest = point;
                closestDistance = point.distanceSquaredTo(p);
            }
        }
        return nearest;
    }

    public static void main(String[] args) {
    }

}
