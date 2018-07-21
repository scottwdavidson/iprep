package com.swd.algo;

import com.sun.xml.internal.xsom.impl.scd.Iterators;

import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

/**
 * * 'n' is an influencer of 'm' if (n,m) == 1 ( and not if (n,m) == 0 )
 * *  'n' is an INFLUENCER if it influences everyone else but and is never influenced by someone else 
 *
 * * Assumptions:
 *     * The input 2 dimensional array must be square [n][m], where n == m, otherwise if n > m you wouldn't have data
 *       about whether others influenced n *or* if n > m you wouldn't have data about whether n influences anyone else
 *     * If you influence someone, then they cannot influence you back ( not sure this is a good assumption, but makes
 *       the question must better )
 *     * Given above, there can be only 1 influencer at most or zero influencers
 */
public class Influencer {

//    private static Predicate<Array> predicate = (s) -> s.length() > 0;


    public static void main(String[] args){

        printDataset(dataset1());
        new Influencer().run(dataset1());
    }

    private void run(int[][] influencerData) {
        System.out.println("--> " + influencerData.length);

        // Create a set of influencers and weed it out as we go along
        TreeSet<Integer> influencerSet = new TreeSet<Integer>();
        for(int row = 0; row < influencerData.length; row++){
            influencerSet.add(row);
        }

        // Check all values in ith column (which can still contribute to the influencer descision) and remove as many
        // influencers as possible
        int currentColumn = 0;
        for(int col = 0; col < influencerData[0].length; col++){

            // If the col represents a potential influencer still, iterate *all* [0-n,col] values to verify it's all 1's
            if ( influencerSet.contains(col)){

                for ( int row = 0; row < influencerData.length; row++){

                    // Skip [n,n] 
                    if ( col == row ) {
                        ;
                        // Still an influencer, remove *row* from influencer go to next row
                    } else if ( influencerData[row][col] == 0 ){
                        influencerSet.remove(row);
                    }
                    // Not an influencer, remove *col* from influencer (if still in the set)
                    else {
                        influencerSet.remove(col);
                    }
                }
            }

            // Else, iterate *just* the remaining influencers to see if any can be eliminated
            else {
                for (int row : influencerSet){

                    // Skip [n,n]
                    if ( col == row ) {
                        ;
                    } else if ( influencerData[row][col] == 0 ){
                        influencerSet.remove(row);
                    }
                }
            }

            // Short circuit ( 2 options )
            if ( influencerSet.isEmpty() || influencerSet.size() == 1) {
                currentColumn = col;
                break; // didn't find any, we're done
            }

        }

        if ( influencerSet.isEmpty() ) {
            System.out.println( "NO INFLUENCERS !!");
        }
        else {

            // Check the rest of the columns to confirm whether it's an influencer or not ...
            for ( int col = currentColumn; col < influencerData.length; col++){
                if (influencerData[influencerSet.first()][col] == 0){
                    System.out.println( "NO INFLUENCERS !!");
                }
            }

            System.out.println( "THERE'S AN INFLUENCER !! --> " + influencerSet.first());
        }


    }

    private static int[][] dataset1(){

        // 0 influences everyone, 1 influences everyone except 0, 2 influences everyone except 0 and 1
        int[] row0 = new int[] {8, 1, 1, 1, 1};
        int[] row1 = new int[] {0, 8, 1, 1, 1};
        int[] row2 = new int[] {0, 0, 8, 1, 0};
        int[] row3 = new int[] {0, 0, 0, 8, 0};
        int[] row4 = new int[] {0, 0, 0, 0, 8};

        int[][] dataset = new int[5][];
        dataset[0] = row0;
        dataset[1] = row1;
        dataset[2] = row2;
        dataset[3] = row3;
        dataset[4] = row4;
        return dataset;

    }
    private static int[][] dataset2(){

        // 0 influences everyone, 1 influences everyone except 0, 2 influences everyone except 0 and 1
        int[] row0 = new int[] {8, 1, 1, 1, 0};
        int[] row1 = new int[] {0, 8, 1, 1, 1};
        int[] row2 = new int[] {0, 0, 8, 1, 0};
        int[] row3 = new int[] {0, 0, 0, 8, 0};
        int[] row4 = new int[] {1, 0, 0, 0, 8};

        int[][] dataset = new int[5][];
        dataset[0] = row0;
        dataset[1] = row1;
        dataset[2] = row2;
        dataset[3] = row3;
        dataset[4] = row4;
        return dataset;

    }

    private static void printDataset(int[][] a){
        StringBuffer buffer = new StringBuffer();
        for(int[] row: a){
            buffer.append("[");
            boolean firstTime = true;
            for (int col: row) {
                if ( !firstTime ){
                    buffer.append(" | ");
                }
                buffer.append(col);
                firstTime = false;
            }
            buffer.append("]\n");
        }

        System.out.println(buffer.toString());
    }
}
