package com.swd.algo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Problem: Given a array of integer arrays ( points on a graph ), find the "k" closest points to the origin.
 *
 * 1. Create a sorted ( TreeMap ) collection which uses the distance as a key. Iterate through all of the points and insert
 *    a new record for each point w/ its distance as the key.
 * 2. Iterate through the first k elements of the TreeMap elements and store them in the return array.
 *
 * Note: AMZ talks about a structure called a HeapMap which is like a TreeMap *except* that has a specified capacity. So,
 * once you've hit the capacity, each new attempted add either: a) rejects the value b/c it's not one of the k shortest
 * distances or b) discards the longest distance of the k distances and inserts the new (short) distance. This approach
 * deals w/ the space performance if the input is very large ( not duplicating the whole input array size )
 */
public class AmzKClosestPoints {

    public static void main(String[] args){
//        System.out.println("4,3 : " + new AmzKClosestPoints().distance(4,3));
        int [][] testData = new int[][]{{3,4}, {1,2} , {1,4}, {5,5} , {3,3}, {10,11}};
        System.out.println(pointsToString(new AmzKClosestPoints().closest(testData, 4)));
    }

    public int[][] closest(int[][] points, int k){

        Map<Double, int[]> distances = new TreeMap<Double, int[]>();
        for(int[] point: points){
            distances.put(distance(point[0], point[1]), point);
        }
        System.out.println("size of distances: " + distances.size());

        int[][] results = new int[k][2];

        int count = 0;
        for ( int[] point: distances.values()) {
            results[count][0] = point[0];
            results[count][1] = point[1];
            count++;
            if ( count == k){
                break;
            }
        }

        return results;
    }

    protected double distance(int x, int y){
        return Math.sqrt(x*x + y*y);
    }

    protected static String pointsToString(int[][] points){
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        boolean firstTime = true;
        for(int[] point: points){
            if(firstTime){
                firstTime = false;
            }
            else {
                builder.append(",");
            }
            builder.append(pointToString(point));
        }
        builder.append("}");
        return builder.toString();
    }

    protected static String pointToString(int[] point){
        return "[" + point[0] + "," + point[1] + "]";
    }
}
