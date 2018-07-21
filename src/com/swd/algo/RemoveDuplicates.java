package com.swd.algo;

import java.util.ArrayList;
import java.util.List;

public class RemoveDuplicates {

    public static void main(String[] args){

        {
            Integer[] noDups =  new RemoveDuplicates().removeDuplicates(new int[]{-1,0,1,2,2,2,3,3,4,6,8,9,9,19,22,22});
            for(int i=0; i<noDups.length; i++){
                System.out.println("--> " + noDups[i]);
            }

        }

    }

    /**
     * Key points here:
     *  1:  This algorithm runs in O(n); don't get tricked by the fact that you iterate through the list twice (once
     *      to remove the duplicates and the 2nd time to convert ArrayList into an Array
     *  2:  Using the ArrayList keeps the values in sorted order; you can't immediately put the results in a new array
     *      b/c you don't know the size ( and can't know the size until you finish removing duplicates )
     *  3:  I *had* to use Integer b/c the ArrayList::toArray(T[]) method requires T to be an Object type ( and so
     *      int doesn't work ). The ::toArray() method runs in O(n)
     *  4:  Again - O(n) + O(n) may kind of equal O(2n), but O(2n) is the same as O(n)
     *
     */
    public Integer[] removeDuplicates(int[] sortedArrayOfInts){
        List<Integer> noDuplicates = new ArrayList<Integer>();
        int index = 0;
        int current = sortedArrayOfInts[index];
        noDuplicates.add(current);
        index++;
        while ( index < sortedArrayOfInts.length){

            if(current != sortedArrayOfInts[index] ){
                current = sortedArrayOfInts[index];
                noDuplicates.add(current);
            }
            index++;
        }

        return noDuplicates.toArray(new Integer[noDuplicates.size()]);

    }


}
