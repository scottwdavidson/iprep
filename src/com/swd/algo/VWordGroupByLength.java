package com.swd.algo;

import java.util.*;

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
