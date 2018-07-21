package com.swd.algo;

import java.util.Arrays;

public class Riffled {

    private final static int[] CARDS_RIFFLED_1 = new int[]
            { 1,2,3,27,28,29,30,31,32,4,5,6,33,34,35,36,7,8,9,10,11,12,13,14,15,37,38,39,40,41,42,43,44,16,17,18,19,45,46,47,48,20,21,49,50,51,22,23,24,25,26,52};
    private final static int[] CARDS_RIFFLED_2 = new int[]
            { 27,28,29,30,1,2,3,31,32,4,5,6,33,34,35,36,7,8,9,10,11,12,13,14,15,37,38,39,40,41,42,43,44,16,17,18,19,45,46,47,48,20,21,49,50,51,22,23,24,25,26,52};
    private final static int[] CARDS_NOT_RIFFLED_1 = new int[]
            { 1,2,3,27,52,29,30,31,32,4,5,6,33,34,35,36,7,8,9,10,11,12,13,14,15,37,38,39,40,41,42,43,44,16,17,18,19,45,46,47,48,20,21,49,50,51,22,23,24,25,26};
    private final static int[] CARDS_IN_ORDER = new int[]
            { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52};

    public static void main(String[] args){

        System.out.println("(CARDS_RIFFLED_1) ==> " + new Riffled().riffled(CARDS_RIFFLED_1));
        System.out.println("(CARDS_RIFFLED_2) ==> " + new Riffled().riffled(CARDS_RIFFLED_2));
        System.out.println("(CARDS_NOT_RIFFLED_1) ==> " + new Riffled().riffled(CARDS_NOT_RIFFLED_1));
        System.out.println("(CARDS_IN_ORDER) ==> " + new Riffled().riffled(CARDS_IN_ORDER));

    }

    public boolean riffled(int[] cards){

        int currentFirstHalf = 0;
        int currentSecondHalf = cards.length;
        int currentIndex = 0;

        // Iterate through the first half of the deck until you hit the first card of the second half
        while(currentIndex < cards.length && (cards[currentIndex] == currentFirstHalf + 1)) {
            currentFirstHalf = cards[currentIndex];
            currentIndex++;
        }

        if( currentIndex == cards.length ){
            return false; // not even riffled at all - still in order :-)
        }
        else {
            currentSecondHalf = cards[currentIndex];
            currentIndex++;
        }

        // Iterate until an out of order card is detected
        while ( currentIndex < cards.length ){

            // lower
            if ( cards[currentIndex] == currentFirstHalf + 1){
                currentFirstHalf = cards[currentIndex];
                currentIndex++;
            }
            else if ( cards[currentIndex] == currentSecondHalf + 1){
                currentSecondHalf = cards[currentIndex];
                currentIndex++;
            }
            else {
                return false; // something's out of order
            }
        }

        // Made it through the whole deck, so it was riffled
        return true;
    }

}
