package com.swd.algo;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class QueueViaTwoStacks {

    public static void main(String[] args){

        ScottQueue sq = new ScottQueue();
        sq.add(25);
        sq.add(24);
        sq.add(23);
        sq.add(22);
        sq.add(15);
        sq.add(14);
        sq.add(13);
        sq.add(12);
        sq.add(5);
        sq.add(4);
        sq.add(3);
        sq.add(2);
        System.out.println(sq.toString());
    }

    public static class ScottQueue {

        private Stack<Integer> stackOne = new Stack<>();
        private Stack<Integer> stackTwo = new Stack<>();
        private Stack<Integer> current = stackOne;
        private Stack<Integer> helper = stackTwo;

        public void add(Integer value){
            helper.add(value);
            add(current,helper);
            swap(current, helper);
        }

        protected void swap(Stack<Integer> s1, Stack<Integer> s2){
            Stack<Integer> temp = s1;
            s1 = s2;
            s2 = temp;
        }
        protected void add(Stack<Integer> s1, Stack<Integer> s2){

            if(s1.empty()){
                return;
            }else {
                Integer value = s1.pop();
                add(s1,s2);
                s2.push(value);
            }
        }

        public Integer peek(){
            return this.current.peek();
        }

        public Integer poll(){
            return this.current.pop();
        }

        public String toString(){

            StringBuilder builder = new StringBuilder();
            Iterator<Integer> iterator  =  this.helper.iterator();
            boolean firstTime = true;
            while ( iterator.hasNext() ){
                if ( firstTime ){
                    firstTime = false;
                }
                else {
                    builder.append(" , ");
                }
                builder.append(iterator.next());
            }

            return builder.toString();
        }
    }
}
