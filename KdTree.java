
/*************************************************************************
 *************************************************************************/

import java.util.Arrays;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
    private static class Node {
        
        private Point2D p; 
        private Node lb;
        private Node rt;
        private RectHV rect;
        private boolean vertical;

        private Node(Point2D p) {
            this.p = p;
        }
    }

    private int size;
    private Node root;
    private double closestDistance;
    private Point2D nearest;

    // construct an empty set of points
    public KdTree() {
        size = 0;
    }

    // is the set empty?
    public boolean isEmpty() {
        return root == null;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        root = insertrecursive(root, null, p, true);
    };

    private Node insertrecursive(Node node, Node parent, Point2D p, boolean vertical) {
        if (node == null) {
            size++;
            Node childNode = new Node(p);
            childNode.vertical = vertical;

            if (parent == null) {
                childNode.rect = new RectHV(0, 0, 1, 1);
            } else {
                if (vertical) {
                    if (childNode.p.y() < parent.p.y()) {
                        childNode.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.rect.xmax(), parent.p.y());
                    } else {
                        childNode.rect = new RectHV(parent.rect.xmin(), parent.p.y(), parent.rect.xmax(), parent.rect.ymax());
                    }
                } else {
                    if (childNode.p.x() < parent.p.x()) {
                        childNode.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.p.x(), parent.rect.ymax());
                    } else {
                        childNode.rect = new RectHV(parent.p.x(), parent.rect.ymin(), parent.rect.xmax(), parent.rect.ymax());
                    }
                }
            }

            return childNode;
        }

        if (node.p.equals(p)) {
            return node;
        }

        if (vertical) {
            if (p.x() < node.p.x()) {
                node.lb = insertrecursive(node.lb, node, p, false);
            } else {
                node.rt = insertrecursive(node.rt, node, p, false);
            }
        } else {
            if (p.y() < node.p.y()) {
                node.lb = insertrecursive(node.lb, node, p, true);
            } else {
                node.rt = insertrecursive(node.rt, node, p, true);
            }
        }
        return node;
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        return containsrecursive(root, p, true);
    }

    private boolean containsrecursive(Node node, Point2D p, boolean vertical) {
        if (node == null) {
            return false;
        }

        if (node.p.equals(p)) {
            return true;
        }

        if (vertical) {
            if (p.x() < node.p.x()) {
                return containsrecursive(node.lb, p, false);
            } else {
                return containsrecursive(node.rt, p, false);
            }
        } else {
            if (p.y() < node.p.y()) {
                return containsrecursive(node.lb, p, true);
            } else {
                return containsrecursive(node.rt, p, true);
            }
        }
    }

    // draw all of the points to standard draw
    public void draw() {

    }

    // all points in the set that are inside the rectangle
    public Point2D[] range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }

        Point2D[] result = new Point2D[size]; // Create an array to store matching points
        int[] count = {0}; // An array to keep track of the count of matching points

        rangerecursive(root, rect, result, count);

        // Trim the result array to remove null elements
        return Arrays.copyOf(result, count[0]);
    }

    private void rangerecursive(Node node, RectHV rect, Point2D[] result, int[] count) {
        if (node == null) {
            return;
        }

        if (rect.contains(node.p)) {
            result[count[0]++] = node.p; // Add the point to the result array
        }

        if (node.vertical) {
            if (node.p.x() > rect.xmax()) {
                rangerecursive(node.lb, rect, result, count);
            } else if (node.p.x() < rect.xmin()) {
                rangerecursive(node.rt, rect, result, count);
            } else {
                rangerecursive(node.lb, rect, result, count);
                rangerecursive(node.rt, rect, result, count);
            }
        } else {
            if (node.p.y() > rect.ymax()) {
                rangerecursive(node.lb, rect, result, count);
            } else if (node.p.y() < rect.ymin()) {
                rangerecursive(node.rt, rect, result, count);
            } else {
                rangerecursive(node.lb, rect, result, count);
                rangerecursive(node.rt, rect, result, count);
            }
        }
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        if (isEmpty()) {
            return null;
        }

        nearest = root.p;
        closestDistance = root.p.distanceSquaredTo(p);
        nearestrecursive(root, p);

        Point2D result = nearest; // Create an array to store the nearest point
        return result;
    }

    private void nearestrecursive(Node node, Point2D p) {
        if (node == null) {
            return;
        }

        if (node.rect.distanceSquaredTo(p) < closestDistance) {
            double nodeToPDistance = node.p.distanceSquaredTo(p);
            if (nodeToPDistance < closestDistance) {
                nearest = node.p;
                closestDistance = nodeToPDistance;
            }

            if (node.vertical) {
                if (p.x() < node.p.x()) {
                    nearestrecursive(node.lb, p);
                    if (p.x() * p.x() < node.p.x() * node.p.x()) {
                        nearestrecursive(node.rt, p);
                    }
                } else {
                    nearestrecursive(node.rt, p);
                    if (p.x() * p.x() < node.p.x() * node.p.x()) {
                        nearestrecursive(node.lb, p);
                    }
                }
            } else {
                if (p.y() < node.p.y()) {
                    nearestrecursive(node.lb, p);
                    if (p.y() * p.y() < node.p.y() * node.p.y()) {
                        nearestrecursive(node.rt, p);
                    }
                } else {
                    nearestrecursive(node.rt, p);
                    if (p.y() * p.y() < node.p.y() * node.p.y()) {
                        nearestrecursive(node.lb, p);
                    }
                }
            }
        }
    }

    /*******************************************************************************
     * Test client
     ******************************************************************************/
    public static void main(String[] args) {
        In in = new In();
        Out out = new Out();
        int nrOfRecangles = in.readInt();
        int nrOfPointsCont = in.readInt();
        int nrOfPointsNear = in.readInt();
        RectHV[] rectangles = new RectHV[nrOfRecangles];
        Point2D[] pointsCont = new Point2D[nrOfPointsCont];
        Point2D[] pointsNear = new Point2D[nrOfPointsNear];
        for (int i = 0; i < nrOfRecangles; i++) {
            rectangles[i] = new RectHV(in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble());
        }
        for (int i = 0; i < nrOfPointsCont; i++) {
            pointsCont[i] = new Point2D(in.readDouble(), in.readDouble());
        }
        for (int i = 0; i < nrOfPointsNear; i++) {
            pointsNear[i] = new Point2D(in.readDouble(), in.readDouble());
        }
        KdTree set = new KdTree();
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble(), y = in.readDouble();
            set.insert(new Point2D(x, y));
        }
        for (int i = 0; i < nrOfRecangles; i++) {
            // Query on rectangle i, sort the result, and print
            Iterable<Point2D> ptset = Arrays.asList(set.range(rectangles[i]));
            int ptcount = 0;
            for (Point2D p : ptset)
                ptcount++;
            Point2D[] ptarr = new Point2D[ptcount];
            int j = 0;
            for (Point2D p : ptset) {
                ptarr[j] = p;
                j++;
            }
            Arrays.sort(ptarr);
            out.println("Inside rectangle " + (i + 1) + ":");
            for (j = 0; j < ptcount; j++)
                out.println(ptarr[j]);
        }
        out.println("Contain test:");
        for (int i = 0; i < nrOfPointsCont; i++) {
            out.println((i + 1) + ": " + set.contains(pointsCont[i]));
        }

        out.println("Nearest test:");
        for (int i = 0; i < nrOfPointsNear; i++) {
            out.println((i + 1) + ": " + set.nearest(pointsNear[i]));
        }

        out.println();
    }
}
