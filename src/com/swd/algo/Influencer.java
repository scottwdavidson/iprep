package com.swd.algo;

import java.util.TreeSet;

public class Influencer {

    public static void main(String[] args){
        new Influencer().run(null);
    }

    private void run(int[][] twoDimensionArray) {
        int[] influencers = new int[twoDimensionArray.length]; // initialized to zero
        TreeSet<Integer> influencerSet = new TreeSet<Integer>();
        for(int i = 0; i < twoDimensionArray.length; i++){
            influencerSet.add(i);
        }

    }
}
