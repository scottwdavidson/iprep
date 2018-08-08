package com.swd.algo;

import java.util.*;

/**
 * Problem: given an array of words, return a list containing lists of words which are the same length.
 *
 * Clarifying Question: what to do about duplicate words ? ( Answer : disregard them )
 *
 * 1. Create a structure to hold the words when iterating. The structure needs to be hashed by an Integer ( representing
 *    the word length ) and should have a Collection as the value portion to hold the words. B/c there's no duplicates,
 *    the appropriate Collection type would be Set ( HashSet )
 * 2. When iterating, you'll need to recognize when a new Set must be instantiated ( that is, when there isn't currently
 *    a TreeMap member variable of the word size). Instantiating a TreeMap<Integer,Set<String>> only creates the
 *    TreeMap, not the inner Set instance.
 * 3. Once complete, iterate through the TreeMap to provide a List<List<String>>.
 */
public class VWordGroupByLength {

    // “test", "abc", "qualtrics", "ok", "is", "dog”, “zoo”, “dog”, “ok”
    public static void main(String[] args){

        System.out.println(printListOfList(new VWordGroupByLength().wordGroupByLength(new String[]{"test", "abc", "qualtrics", "ok", "is","dog","zoo","dog","ok"})));
    }

    public  List<List<String>> wordGroupByLength(String[] words){

        TreeMap<Integer, Set<String>> wordGroups = new TreeMap<Integer, Set<String>>();

        for(String word: words){

            if(!wordGroups.containsKey(word.length())){
                wordGroups.put(word.length(), new HashSet<String>());
            }
            wordGroups.get(word.length()).add(word);
        }

        List<List<String>> returnList = new ArrayList<List<String>>();
        NavigableSet<Integer> descendingKeySet = wordGroups.descendingKeySet();
        for(int key : descendingKeySet){
            Set<String> set = wordGroups.get(key);
            List list = new ArrayList();
            list.addAll(set);
            returnList.add(list);
        }

        return returnList;
    }

    public static String printListOfList(List<List<String>> listOfList){
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for(List<String> words: listOfList){
            builder.append("{");
            boolean firstTime = true;
            for (String word: words){
                if(firstTime){
                    firstTime = false;
                }
                else {
                    builder.append(",");
                }
                builder.append(word);
            }
            builder.append("}");
        }
        builder.append("]");
        return builder.toString();
    }
}
