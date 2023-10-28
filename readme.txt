/**********************************************************************
 *  readme.txt template                                                   
 *  Kd-tree
**********************************************************************/

Name 1: Dagur Kári Ólafsson    
kt 1: 311003-2750

Name 2: Jónas Ingi Þórisson
kt 2: 120202-2940

/**********************************************************************
 *  Briefly describe the Node data type you used to implement the
 *  2d-tree data structure.
 **********************************************************************/
    we used a data type that has the point using point2D, a boolean wether
    it is a vertical point. both its children and a rectangle which includes
    all the points its children could end up in.
/**********************************************************************
 *  Describe your method for range search in a kd-tree.
 **********************************************************************/
    we recursively go through all relevant subtrees (where the point 
    rectangle intersects the range rectangle) to find all points that 
    are in that specific range.

/**********************************************************************
 *  Describe your method for nearest neighbor search in a kd-tree.
 **********************************************************************/
    first finds the leaf node where that nodes rectangle has the point inside
    then only checks other subtrees if the distance from that nodes rectangle 
    is closer to the point than the current closest distance. 

/**********************************************************************
 *  Give the total memory usage in bytes (using tilde notation and 
 *  the standard 64-bit memory cost model) of your 2d-tree data
 *  structure as a function of the number of points N. Justify your
 *  answer below.
 *
 *  Include the memory for all referenced objects (deep memory),
 *  including memory for the nodes, points, and rectangles.
 **********************************************************************/

bytes per Point2D: 32 bytes

bytes per RectHV: 64 bytes 

bytes per KdTree of N points (using tilde notation):   ~ 291 N bytes (97 per node times 3 nodes)
[include the memory for any referenced Node, Point2D and RectHV objects]


/**********************************************************************
 *  Give the expected running time in seconds (using tilde notation)
 *  to build a 2d-tree on N random points in the unit square.
 *  Use empirical evidence by creating a table of different values of N
 *  and the timing results. (Do not count the time to generate the N 
 *  points or to read them in from standard input.)
 **********************************************************************/
The expcected running time can be calculated by knowing the time complexity which is O(NlogN)
N      |  Time_empirical  
10          0.001                        
100         0.001                
1000        0.001                
10000       0.005                
100000      0.044              
1000000     0.875              
As we can see from the table the running time is growing at NlogN rate.


/**********************************************************************
 *  How many nearest neighbor calculations can your brute-force
 *  implementation perform per second for input100K.txt (100,000 points)
 *  and input1M.txt (1 million points), where the query points are
 *  random points in the unit square? Explain how you determined the
 *  operations per second. (Do not count the time to read in the points
 *  or to build the 2d-tree.)
 *
 *  Repeat the question but with the 2d-tree implementation.
 **********************************************************************/

                     calls to nearest() per second
                     brute force           2d-tree

input100K.txt

input1M.txt



/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/


/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and d�mat�mar, but do
 *  include any help from people (including course staff, 
 *  classmates, and friends) and attribute them by name.
 **********************************************************************/


/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/


