package com.swd.algo;

import java.util.*;

public class Anagram {

    public static class FactoredWord {

        private String originalWord;
        private String anagramFactoring;

        public FactoredWord(String originalWord) {
            this.originalWord = originalWord;
            this.anagramFactoring = factorIntoAnagramForm(this.originalWord);

        }

        public String getOriginalWord() {
            return originalWord;
        }

        public void setOriginalWord(String originalWord) {
            this.originalWord = originalWord;
        }

        public String getAnagramFactoring() {
            return anagramFactoring;
        }

        public void setAnagramFactoring(String anagramFactoring) {
            this.anagramFactoring = anagramFactoring;
        }

        protected String factorIntoAnagramForm(String originalWord){

            // Convert word into a character array
            char[] characterArray = originalWord.toLowerCase().toCharArray();

            // Sort using natural ordering (using Collections w/ an "s" class)
            Arrays.sort(characterArray);

            // Convert the sorted character array back into a String
            return String.valueOf(characterArray);
        }
    }


    public static void main(String[] args){

        systemOutAnagramList(new Anagram().run(Arrays.asList("cab","ear","are","tea", "ate","joe", "ted","sue", "use")));
        systemOutAnagramList(new Anagram().run(Arrays.asList("abc","zxy","rrt","wse", "dgf","ijk")));
        systemOutAnagramList(new Anagram().run(Arrays.asList("rRRrrt","trrrrr","rrTrrr","PAnama", "aMANap","paaANm")));
    }

    public Map<String, List<FactoredWord>> run(List<String> words){
         Map<String, FactoredWord> wordList = new HashMap<>();
         Map<String, List<FactoredWord>> anagramList = new HashMap<>();


        for(String word: words) {

            // Factor the word
            FactoredWord factoredWord = new FactoredWord(word);
//            System.out.println("word: " + word + " , " + factoredWord.getAnagramFactoring());

            // If we've seen this anagram already, add this one to anagram list
            if( anagramList.containsKey(factoredWord.getAnagramFactoring())){
                anagramList.get(factoredWord.getAnagramFactoring()).add(factoredWord);
            }

            // If there's been a single instance already, add *both* to the anagram list
            else if ( wordList.containsKey(factoredWord.getAnagramFactoring())){

                FactoredWord firstFactoredWord = wordList.get(factoredWord.getAnagramFactoring());
                anagramList.put(factoredWord.getAnagramFactoring(), new ArrayList<FactoredWord>());
                anagramList.get(factoredWord.getAnagramFactoring()).add(firstFactoredWord);
                anagramList.get(factoredWord.getAnagramFactoring()).add(factoredWord);
            }

            // Add the so far unseen possible anagram to the word list
            else {
                wordList.put(factoredWord.getAnagramFactoring(), factoredWord);
            }
        }

        return anagramList;
    }

    public static void systemOutAnagramList(Map<String, List<FactoredWord>> anagramList){

        if(anagramList.isEmpty()){
            System.out.println("\n -- NO ANAGRAMS FOUNDS == \n");
        }
        int count = 1;
        for(List<FactoredWord> fWordList: anagramList.values()){
            StringBuffer buffer = new StringBuffer();
            buffer.append("[" + count + "]: " + fWordList.get(0).anagramFactoring + " --> (");
            boolean firstTime = true;
            for(FactoredWord fWord: fWordList){
                if(firstTime){
                    firstTime = false;
                }
                else {
                    buffer.append(" , ");
                }
                buffer.append(fWord.originalWord);
            }
            buffer.append(")");
            System.out.println(buffer.toString());
            count++;
        }
        System.out.println("\n");

    }
}
