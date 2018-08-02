package com.swd.hex;

import java.util.TreeSet;

public class Node {

    public static class Position implements Comparable<Position> {
        private final int x;
        private final int y;

        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public int compareTo(Position o) {
            int compareX = Integer.valueOf(x).compareTo(o.getX());
            if ( 0 != compareX ){
                return compareX;
            }
            else {
                return Integer.valueOf(y).compareTo(o.getY());
            }
        }


    }

    private final TreeSet<Node> adjacentNodes = new TreeSet<>();
    private Player player;

    public static Node newNoneNode(){
        return new Node(Player.None);
    }

    public static Node newXNode(){
        return new Node(Player.X);
    }

    public static Node newONode(){
        return new Node(Player.O);
    }

    public TreeSet<Node> getAdjacentNodes() {
        return adjacentNodes;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private Node(Player player){
        this.player = player;
    }

}
