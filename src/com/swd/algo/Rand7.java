package com.swd.algo;

public class Rand7 {

    public static void main(String[] args){

        for(int i =0;i<10;i++) {
            System.out.println("(thisDoesNotActuallyWork) ==> " + new Rand7().thisDoesNotActuallyWork());
        }
        for(int i =0;i<10;i++) {
            System.out.println("(reroll) ==> " + new Rand7().reroll());
        }

    }

    /**
     * Seems like this *should* work, but b/c the actual floating point random number between 0 and 1 has been
     * multiplied by 7 and rounded, multiplying by 5/7 doesn't give an even distribution :-)
     */
    public int thisDoesNotActuallyWork(){

        return (int)((5 * rand7() ) / 7);
    }

    /**
     * The only accurate solution which is O(<infinity>) b/c it may never return!
     */
    public int reroll(){

        while(true) {
            int roll = rand7();
            if (roll < 6) {
                return roll;
            }
        }
    }

    protected int rand7(){
        return (int)(8 * Math.random());
    }
}
