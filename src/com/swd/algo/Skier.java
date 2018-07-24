package com.swd.algo;

import java.util.HashSet;

public class Skier {

    static int count = 0;

    public static void main(String[] args) {

        System.out.println(" Answer == " + new Skier().ski(new int[][]{{0, 0, 0, 0}, {0, 0, 0, 1}, {0, 0, 1, 0}, {0, 0, 0, 0}}));
    }

    public boolean ski(int[][] trails) {
        return ski(0, 0, trails, new HashSet<String>());
    }

    public boolean ski(int x, int y, int[][] trails, HashSet<String> beenThere) {
//        System.out.println(hash(x,y) + " --> " + ++count);

        beenThere.add(hash(x, y));

        if (x == trails.length - 1 && y == trails[0].length - 1) {
            return true;
        }

        if (y + 1 < trails[0].length && trails[x][y + 1] == 0) {
            if (!beenThere.contains(hash(x, y + 1)) && ski(x, y + 1, trails, beenThere)) {
                return true;
            }

        }

        if (x + 1 < trails.length && trails[x + 1][y] == 0) {
            if (!beenThere.contains(hash(x + 1, y)) && ski(x + 1, y, trails, beenThere)) {
                return true;
            }
        }

        return false;
    }

    protected String hash(int x, int y) {
        return new String(x + ":" + y);
    }
}
