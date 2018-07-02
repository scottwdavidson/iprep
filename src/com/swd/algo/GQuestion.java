package com.swd.algo;

import java.util.*;

/**
 * Problem: given an array of integers, create a corresponding array of *distances* where the distance value is the
 * number of array elements past the current which is greater than the current. For example,
 *    input: [5,3,6,7] , result: [2,1,1,-1] ( b/c 2 spaces past 5 is 6 (2), 1 space past 3 is 6 (1) and one space past
 *    6 is 7 (1). The final element (and any others which do not have a value greater after them will be given -1).
 */
public class GQuestion {

    public static class Element {
        public int value;
        public List<Integer> positions = new ArrayList<Integer>();

        public Element(int value, int position) {
            this.value = value;
            this.positions.add(position);
        }

        @Override
        public String toString() {
            StringBuffer buffer = new StringBuffer();
            buffer.append("[").append(value).append(" , [");
            for (int position : positions) {
                buffer.append(position).append(",");
            }
            buffer.append("] ]");
            return buffer.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello, world!");
        new GQuestion().run();
    }

    private void run() {

//        {
//            int[] arrayOfInt = new int[]{5, 4, 1, 3, 6, 7};
//            System.out.println(printResults(arrayOfInt, algo(arrayOfInt)));
//        }
        {
            int[] arrayOfInt = new int[]{5, 5, 4, 1, 3, 3, 3, 3, 5, 5, 6, 3, 6, 7};
            System.out.println(printResults(arrayOfInt, algo(arrayOfInt)));
        }
    }

    private int[] algo(int[] intList) {

        // Declare the results
        int[] results = new int[intList.length];

        // Create a TreeMap to store intermediate data
        TreeMap<Integer, Element> map = new TreeMap<Integer, Element>();

        for (int index = 0; index < intList.length; index++) {

            // Initialize to -1
            results[index] = -1;

            // Resolve as many current values as possible
            //
            // Get the NavigableMap of elements < current
            NavigableMap<Integer, Element> navMap = map.headMap(intList[index], false);
            {
                System.out.println(" >>>>>>> ( " + intList[index] + " )");
                debugNavMap(navMap);
                System.out.println(" ------- ");
            }

            List<Integer> toBeRemovedList = new ArrayList<Integer>();
            for (int value : navMap.keySet()) {

                Element element = navMap.get(value);
                for (int position : element.positions) {
                    results[position] = index - position;
                }

                // Remove the element from the TreeMap
                toBeRemovedList.add(value);

            }

            for (Integer toBeRemoved : toBeRemovedList) {
                map.remove(toBeRemoved);
            }

            // Create the Element
            Element element = getOrCreateElement(map, intList[index], index);

        }

        return results;

    }

    private Element getOrCreateElement(TreeMap<Integer, Element> map, int value, int position) {
        Element existingElement = map.get(value);
        if (existingElement == null) {
            Element newElement = new Element(value, position);
            map.put(value, newElement);
            return newElement;
        } else {
            existingElement.positions.add(position);
            return existingElement;
        }
    }

    private String printResults(int[] values, int[] results) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        {
            boolean firstTime = true;
            for (Integer value : values) {
                if (!firstTime) {
                    buffer.append(",");
                }
                buffer.append(value);
                firstTime = false;
            }
        }
        buffer.append("]").append("\n");
        buffer.append("[");
        {
            boolean firstTime = true;
            for (Integer result : results) {
                if (!firstTime) {
                    buffer.append(",");
                }
                buffer.append(result);
                firstTime = false;
            }
        }
        buffer.append("]");
        return buffer.toString();
    }

    private void debugNavMap(NavigableMap<Integer, Element> elements) {
        for (int value : elements.keySet()) {
            System.out.println(" " + elements.get(value).toString());
        }

    }
}
