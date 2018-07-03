package com.swd.algo;

public class DivisibleBy3Not9 {

    public static void main(String[] args){

        StringBuffer buffer = new StringBuffer("[");
        boolean firstTime = true;
        for(int i = 1; i<=100; i++){

            if(i%3 == 0 && i%9 != 0) {

                if (!firstTime) {
                    buffer.append(", ");
                }
                buffer.append(i);
                firstTime = false;
            }
        }
        buffer.append("]");
        System.out.println(buffer.toString());
    }
}
