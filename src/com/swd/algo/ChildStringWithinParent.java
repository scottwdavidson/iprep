package com.swd.algo;

public class ChildStringWithinParent {

    private final static String PARENT1  = "xxhxxqxxxqrxxgxxxqr";
    private final static String CHILD1  = "xxxqr";
    private final static String PARENT2  = "xxgxxqxxxqqxxgxxqb";
    private final static String CHILD2  = "xxxqr";

    public static void main(String[] args){

        System.out.println("(SIMPLE) parent = " + PARENT1 + " , child = " + CHILD1 + " , index = " +
                new ChildStringWithinParent().easySolutionUsingJava(PARENT1,CHILD1));
        System.out.println("(SIMPLE) parent = " + PARENT2 + " , child = " + CHILD2 + " , index = " +
                new ChildStringWithinParent().easySolutionUsingJava(PARENT2,CHILD2));

        System.out.println("(O(N)) parent = " + PARENT1 + " , child = " + CHILD1 + " , index = " +
                new ChildStringWithinParent().characterIterationSolution(PARENT1,CHILD1));
        System.out.println("(O(N)) parent = " + PARENT2 + " , child = " + CHILD2 + " , index = " +
                new ChildStringWithinParent().characterIterationSolution(PARENT2,CHILD2));

    }

    public int easySolutionUsingJava(String parent, String child){
        return parent.indexOf(child);
    }

    public int characterIterationSolution(String parent, String child){

        int parentIndex = 0;
        int childIndex = 0;
        while ( parentIndex < parent.length()){

            if(child.charAt(childIndex) == parent.charAt(parentIndex)){

                if (childIndex == child.length()-1 ){
                    return parentIndex - (child.length() -1);
                }
                else {
                    childIndex++;
                }
            }
            else {
                // start over
                childIndex = 0;
            }
            parentIndex++;
        }
        return -1;

    }
}
