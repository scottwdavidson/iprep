package com.swd.misc;

public class GlassDoorIQuestions {

    public static void main(String[] s){
        try {
            System.out.println(s[1] + s[2] + s[3]);
        } catch ( Throwable cause) {
            System.out.println("Expected array out of bounds exception ... " + cause.getClass() + " : " + cause.getLocalizedMessage());
        }
    }
}
