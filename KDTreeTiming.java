import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Stopwatch;

public class KDTreeTiming {

    public static void main(String[] args) {
        int[] valuesOfN = {10, 100, 1000, 10000, 100000, 1000000}; // Adjust as needed

        System.out.println("N\tTime_empirical (milliseconds)  Time_expected (milliseconds)");

        for (int N : valuesOfN) {
            Point2D[] points = generateRandomPoints(N);
            KdTree kdtree = new KdTree(); 
            Stopwatch timer = new Stopwatch();
            for (Point2D point : points) {
                kdtree.insert(point);
            }
            double timeInSeconds = timer.elapsedTime();

            System.out.println(N + "\t" + timeInSeconds*1000 + "\t" + N*Math.log(N));
            
        }
    }

    // Generate N random points in the unit square
    private static Point2D[] generateRandomPoints(int N) {
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            double x = Math.random();
            double y = Math.random();
            points[i] = new Point2D(x, y);
        }
        return points;
    }
}
