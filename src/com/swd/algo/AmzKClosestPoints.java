package com.swd.algo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
