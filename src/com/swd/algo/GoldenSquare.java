package com.swd.algo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GoldenSquare {

    public static class Square {
        private  String position;
        private  Square north;
        private  Square south;
        private  Square east;
        private  Square west;
        private  boolean isGolden;

        public Square(){};

        public Square setPosition(String position) {
            this.position = position;
            return this;
        }

        public Square setNorth(Square north) {
            this.north = north;
            return this;
        }

        public Square setSouth(Square south) {
            this.south = south;
            return this;
        }

        public Square setEast(Square east) {
            this.east = east;
            return this;
        }

        public Square setWest(Square west) {
            this.west = west;
            return this;
        }

        public Square setGolden(boolean golden) {
            isGolden = golden;
            return this;
        }

        @Override
        public String toString() {
            return position;
        }

        public String getPosition() {
            return position;
        }

        public Square getNorth() {
            return north;
        }

        public Square getSouth() {
            return south;
        }

        public Square getEast() {
            return east;
        }

        public Square getWest() {
            return west;
        }

        public boolean isGolden() {
            return isGolden;
        }


    }

    private Set<String> processedSquares = new HashSet<>();

    public static void main(String[] args) {

        System.out.println("Square 1: " + prettyPath(new GoldenSquare().pathToGolden(generateSquare1())));
        System.out.println("Square None: " + prettyPath(new GoldenSquare().pathToGolden(generateSquareNone())));
        System.out.println("Square 2: " + prettyPath(new GoldenSquare().pathToGolden(generateSquare2())));
    }

    public List<String> pathToGolden(Square from) {

        List<String> startingPoint = new ArrayList<String>();
        startingPoint.add("[START] " + from.getPosition());
        return pathToGolden(from, startingPoint);
    }

    public List<String> pathToGolden(Square from, List<String> currentPath) {

        // Found it!
        if (from.isGolden()) {
            return currentPath;
        }

        // Already been checked
        if (processedSquares.contains(from.getPosition())) {
            return null;
        }

        // Keep looking (in all possible directions
        processedSquares.add(from.getPosition());
        if (from.getNorth() != null) {
            List<String> path = pathToGolden(from.getNorth(), addPosition(currentPath, from.getNorth().getPosition()));
            if (path != null) {
                return path;
            }
        }
        if (from.getEast() != null) {
            List<String> path = pathToGolden(from.getEast(), addPosition(currentPath, from.getEast().getPosition()));
            if (path != null) {
                return path;
            }
        }
        if (from.getSouth() != null) {
            List<String> path = pathToGolden(from.getSouth(), addPosition(currentPath, from.getSouth().getPosition()));
            if (path != null) {
                return path;
            }
        }
        if (from.getWest() != null) {
            List<String> path = pathToGolden(from.getWest(), addPosition(currentPath, from.getWest().getPosition()));
            if (path != null) {
                return path;
            }
        }
        return null;
    }

    protected List<String> addPosition(List<String> currentPath, String position) {
        List<String> newPath = new ArrayList<String>();
        newPath.addAll(currentPath);
        newPath.add(position);
        return newPath;
    }

    protected static String prettyPath(List<String> path) {

        if (null == path) {
            return "** no golden square **";
        }
        StringBuffer buffer = new StringBuffer();
        boolean firstTime = true;
        for (String position : path) {
            if (!firstTime) {
                buffer.append("-->");
            } else {
                firstTime = false;
            }
            buffer.append(position);
        }
        return buffer.toString();
    }

    public static Square generateSquare1() {

        // 3 x 3, Golden Square is top right (0,2)
        Square s02 = new Square();// = new Square("(0,2)",null, s12, null, s01);
        Square s01 = new Square();// = new Square("(0,1)",null, s11, s02, s00);
        Square s00 = new Square();// = new Square("(0,0)",null, s10, s01, null);

        Square s12 = new Square();// = new Square("(1,2)",s02, s22, null, s11);
        Square s11 = new Square();// = new Square("(1,1)",s01, s21, s12, s10);
        Square s10 = new Square();// = new Square("(1,0)",s00, s20, s11, null);

        Square s22 = new Square();// = new Square("(2,2)",s12, null, null, s21);
        Square s21 = new Square();// = new Square("(2,1)",s11, null, s22, s20);
        Square s20 = new Square();// = new Square("(2,0)",s10, null, s21, null);

        s02 = s02.setPosition("(0,2)").setNorth(null).setSouth(s12).setEast(null).setWest(s01).setGolden(true);
        s01 = s01.setPosition("(0,1)").setNorth(null).setSouth(s11).setEast(s02).setWest(s00).setGolden(false);
        s00 = s00.setPosition("(0,0)").setNorth(null).setSouth(s10).setEast(s01).setWest(null).setGolden(false);

        s12 = s12.setPosition("(1,2)").setNorth(s02).setSouth(s22).setEast(null).setWest(s11).setGolden(false);
        s11 = s11.setPosition("(1,1)").setNorth(s01).setSouth(s21).setEast(s12).setWest(s10).setGolden(false);
        s10 = s10.setPosition("(1,0)").setNorth(s00).setSouth(s20).setEast(s11).setWest(null).setGolden(false);

        s22 = s22.setPosition("(2,2)").setNorth(s12).setSouth(null).setEast(null).setWest(s21).setGolden(false);
        s21 = s21.setPosition("(2,1)").setNorth(s11).setSouth(null).setEast(s22).setWest(s20).setGolden(false);
        s20 = s20.setPosition("(2,0)").setNorth(s10).setSouth(null).setEast(s21).setWest(null).setGolden(false);

        return s20;

    }

    public static Square generateSquare2() {

        // 3 x 3, Golden Square is top right (0,2)
        Square s02 = new Square();// = new Square("(0,2)",null, s12, null, s01);
        Square s01 = new Square();// = new Square("(0,1)",null, s11, s02, s00);
        Square s00 = new Square();// = new Square("(0,0)",null, s10, s01, null);

        Square s12 = new Square();// = new Square("(1,2)",s02, s22, null, s11);
        Square s11 = new Square();// = new Square("(1,1)",s01, s21, s12, s10);
        Square s10 = new Square();// = new Square("(1,0)",s00, s20, s11, null);

        Square s22 = new Square();// = new Square("(2,2)",s12, null, null, s21);
        Square s21 = new Square();// = new Square("(2,1)",s11, null, s22, s20);
        Square s20 = new Square();// = new Square("(2,0)",s10, null, s21, null);

        s02 = s02.setPosition("(0,2)").setNorth(null).setSouth(s12).setEast(null).setWest(s01).setGolden(false);
        s01 = s01.setPosition("(0,1)").setNorth(null).setSouth(s11).setEast(s02).setWest(s00).setGolden(false);
        s00 = s00.setPosition("(0,0)").setNorth(null).setSouth(s10).setEast(s01).setWest(null).setGolden(false);

        s12 = s12.setPosition("(1,2)").setNorth(s02).setSouth(s22).setEast(null).setWest(s11).setGolden(false);
        s11 = s11.setPosition("(1,1)").setNorth(s01).setSouth(s21).setEast(s12).setWest(s10).setGolden(true);
        s10 = s10.setPosition("(1,0)").setNorth(s00).setSouth(s20).setEast(s11).setWest(null).setGolden(false);

        s22 = s22.setPosition("(2,2)").setNorth(s12).setSouth(null).setEast(null).setWest(s21).setGolden(false);
        s21 = s21.setPosition("(2,1)").setNorth(s11).setSouth(null).setEast(s22).setWest(s20).setGolden(false);
        s20 = s20.setPosition("(2,0)").setNorth(s10).setSouth(null).setEast(s21).setWest(null).setGolden(false);

        return s00;

    }

    public static Square generateSquareNone() {

        // 3 x 3, Golden Square is top right (0,2)
        Square s02 = new Square();// = new Square("(0,2)",null, s12, null, s01);
        Square s01 = new Square();// = new Square("(0,1)",null, s11, s02, s00);
        Square s00 = new Square();// = new Square("(0,0)",null, s10, s01, null);

        Square s12 = new Square();// = new Square("(1,2)",s02, s22, null, s11);
        Square s11 = new Square();// = new Square("(1,1)",s01, s21, s12, s10);
        Square s10 = new Square();// = new Square("(1,0)",s00, s20, s11, null);

        Square s22 = new Square();// = new Square("(2,2)",s12, null, null, s21);
        Square s21 = new Square();// = new Square("(2,1)",s11, null, s22, s20);
        Square s20 = new Square();// = new Square("(2,0)",s10, null, s21, null);

        s02 = s02.setPosition("(0,2)").setNorth(null).setSouth(s12).setEast(null).setWest(s01).setGolden(false);
        s01 = s01.setPosition("(0,1)").setNorth(null).setSouth(s11).setEast(s02).setWest(s00).setGolden(false);
        s00 = s00.setPosition("(0,0)").setNorth(null).setSouth(s10).setEast(s01).setWest(null).setGolden(false);

        s12 = s12.setPosition("(1,2)").setNorth(s02).setSouth(s22).setEast(null).setWest(s11).setGolden(false);
        s11 = s11.setPosition("(1,1)").setNorth(s01).setSouth(s21).setEast(s12).setWest(s10).setGolden(false);
        s10 = s10.setPosition("(1,0)").setNorth(s00).setSouth(s20).setEast(s11).setWest(null).setGolden(false);

        s22 = s22.setPosition("(2,2)").setNorth(s12).setSouth(null).setEast(null).setWest(s21).setGolden(false);
        s21 = s21.setPosition("(2,1)").setNorth(s11).setSouth(null).setEast(s22).setWest(s20).setGolden(false);
        s20 = s20.setPosition("(2,0)").setNorth(s10).setSouth(null).setEast(s21).setWest(null).setGolden(false);

        return s00;

    }

}
