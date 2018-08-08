package com.swd.algo;

import java.util.*;

public class IndeedMergeArrays {


    public static void main(String[] args){

        System.out.println(toStringIntArray(new IndeedMergeArrays().merge2Arrays(new int[]{0,1,2,3}, new int[]{-1,2,4,7,8})));
        System.out.println(toStringIntArray(new IndeedMergeArrays().merge(Arrays.asList(new int[]{0,1,2,3}, new int[]{-1,2,4,7,8}, new int[]{-5,1,7,9,11}))));

    }

    public int[] merge(List<int[]> arrays){

        Queue<int[]> queue = new ArrayDeque<>();
        for(int[] array: arrays){
            queue.add(array);
        }

        boolean more = true;
        while ( more ){

            // Get two items from queue - if there's only one, we're done
            int[] first = queue.poll();

            // If empty, break ( we're done )
            if (queue.isEmpty()){
                queue.add(first);
                more = false;
            }
            else {
                int[] second = queue.poll();

                // Merge the two and add back to the queue
                queue.add(merge2Arrays(first, second));
            }
        }
        return queue.poll();
    }


    public int[] merge2Arrays(int[] a, int[] b) {
        int aIndex = 0;
        int bIndex = 0;
        int outputIndex = 0;
        int[] output = new int[a.length + b.length];
        while (aIndex < a.length && bIndex < b.length) {

            if (a[aIndex] < b[bIndex]) {
                output[outputIndex] = a[aIndex];
                aIndex++;
                outputIndex++;
            } else if (b[bIndex] < a[aIndex]) {
                output[outputIndex] = b[bIndex];
                bIndex++;
                outputIndex++;
            } else {
                output[outputIndex] = a[aIndex];
                aIndex++;
                outputIndex++;
                output[outputIndex] = b[bIndex];
                bIndex++;
                outputIndex++;
            }

        }

        while (aIndex < a.length) {
            output[outputIndex] = a[aIndex];
            aIndex++;
            outputIndex++;

        }

        while (bIndex < b.length) {
            output[outputIndex] = b[bIndex];
            bIndex++;
            outputIndex++;

        }

        return output;
    }

    public static String toStringIntArray(int[] a){
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        boolean firstTime = true;
        for(int element: a){

            if(firstTime){
                firstTime = false;
            }
            else{
                builder.append(",");
            }
            builder.append(element);
        }
        builder.append("]");
        return builder.toString();
    }

}
