package com.swd.algo;

public class Weird {

    public static void main(String[] args){
        swapTwoNoTempVariable(5,9);
        System.out.println("squareRoot(100) = " + squareRoot(100));
    }

    public static void swapTwoNoTempVariable(int a, int b){
        System.out.println("a: " + a + " , b: " + b);
        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println("a: " + a + " , b: " + b);
    }

    public static double squareRoot(double x){

        double low = 1.0;
        double high = x / 2.0;
        double precision = 0.00001;
        double currentEstimate = high;

        while (precision < Math.abs((currentEstimate * currentEstimate) - x)){
            if ( currentEstimate * currentEstimate > x ){
                high = currentEstimate;
            }
            else {
                low = currentEstimate;
            }
            currentEstimate = (high + low) / 2.0;
        }

        return currentEstimate;
    }
}
