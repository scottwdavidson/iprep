package com.swd.hex;

public class HexBoard {

    private Node[][] nodes;

    public static HexBoard newHexBoard(int dimension){
        return new HexBoard(dimension);
    }

    public Node[][] getNodes(){
        return this.nodes;
    }

    private HexBoard(int dimension){
        this.nodes = new Node[dimension][dimension];
        for(int row = 0; row < dimension; row++){
            for (int col =0; col < dimension; col++){
                this.nodes[row][col] = Node.newNoneNode();
            }
        }
    }

}
