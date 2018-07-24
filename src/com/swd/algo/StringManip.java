package com.swd.algo;

import java.util.*;

public class StringManip {


    public static void main(String[] args) {

        System.out.println("[reverse] abcxyz --> " + StringManip.reverseString("abcxyz"));

        System.out.println("[non-repeated char] hello --> " + StringManip.firstNonRepeatedCharacter("hello"));
        System.out.println("[non-repeated char] swiss --> " + StringManip.firstNonRepeatedCharacter("swiss"));
        System.out.println("[non-repeated char] toot --> " + StringManip.firstNonRepeatedCharacter("toot"));

        System.out.println("[all permutations] rot --> ");
        StringManip.permutation("roar");


    }

    public static String reverseString(String aString) {

        if (aString.length() == 1) {
            return aString;
        } else if (aString.length() == 2) {
            return aString.substring(1, 2) + aString.substring(0, 1);
        } else {
            return aString.substring(aString.length() - 1, aString.length()) + reverseString(aString.substring(1, aString.length() - 1)) + aString.substring(0, 1);
        }
    }

    public static Character firstNonRepeatedCharacter(String aString) {

        Map<Character, Integer> characterCount = new LinkedHashMap<>(); // use a LinkedHashMap and then you can iterate the keys in order

        // Iterate through the String (via the character array)
        for (char c : aString.toCharArray()) {
            if (characterCount.containsKey(c)) {
                characterCount.put(c, characterCount.get(c) + 1);
            } else {
                characterCount.put(c, 1);
            }
        }

        for (Character c : characterCount.keySet()) {
            if (characterCount.get(c) == 1) {
                return c;
            }
        }

//        for(char c: aString.toCharArray()){
//            if( characterCount.get(c) == 1 ){
//                return c;
//            }
//        }

        return null;
    }


    public static void permutation(String word){
        permutation("", word);
    }
    private static void permutation(String perm, String word) {
        if (word.isEmpty()) {
            System.out.println(perm + word);
        } else {
            for (int i = 0; i < word.length(); i++){
                permutation(perm + word.charAt(i), word.substring(0, i) + word.substring(i + 1, word.length()));
            }
        }
    }

}
