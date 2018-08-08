package com.swd.algo;

import java.util.Arrays;
import java.util.List;

/**
 * Given a string determine if it is or is *almost* a palidrome (one character off that if deleted results in a palindrome).
 *
 * Notes:
 *   1. The base recursive algorithm plucks a single character off each end, compares and if equal, continues on the
 *      reduced string. If the string is length 1, then you're at the middle and done; if string is empty, you're done.
 *   2. For the "almost", simply add an argument to the recursive call keeping track of whether you've used your one
 *      bogey or not and in case where the first and last char do *not* match, try both alternatives (recursively),
 *      reseting the almost used to true. You *must* check for a string of length 2 in the almost case b/c if the two
 *      middle characters differ, then it should fail.
 */
public class FBAlmostPalindrome {


    public static void main(String[] args){

        List<String> testStrings = Arrays.asList("a", "bb", "aabbbbaa", "aabbcbbaa", "aabbcbbaza","aabbcdbbaa", "aabrbcbbaza");
        for(String s: testStrings){
            System.out.println(s+ " : " + new FBAlmostPalindrome().almostPalindrome(s, false));
        }

    }

    public boolean almostPalindrome(String s, boolean almostExceptionUsed){

        if ( s.length() ==1 || s.isEmpty() ){
            return true;
        }

        Character firstChar = s.charAt(0);
        Character secondChar = s.charAt(s.length()-1);

        if ( firstChar == secondChar ){
            return almostPalindrome(s.substring(1,s.length()-1), almostExceptionUsed);
        }
        else if ( !almostExceptionUsed && s.length() != 2){
            return almostPalindrome(s.substring(0,s.length()-1), true) || almostPalindrome(s.substring(1,s.length()-1),true);
        }
        else {
            return false;
        }
    }
}
