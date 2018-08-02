package com.swd.hex;

public class MainApplication {

    public static void main(String[] args){

        // Create the board, dimension 6
        HexBoard board =  HexBoard.newHexBoard(6);

        // Add player moves to a few nodes

        // Print the board
        System.out.println(prettyPrintBoard(board));

    }

    public static String prettyPrintBoard(HexBoard board){

        final String SPACE = " ";
        final String ASTERISK = "*";
        final String UNDERLINE = "_";

        StringBuilder builder = new StringBuilder();
        Node[][] nodes = board.getNodes();
        int rowCount = 0;
        for(Node[] row: nodes){

            // Insert leading blanks ( rowCount )
            StringBuilder rowMarginBuilder = new StringBuilder();
            for(int lb = 0; lb < rowCount; lb++ ){
                builder.append(SPACE);
                rowMarginBuilder.append(SPACE);
            }

            for(Node colNode: row){
                String colNodePresentation = (colNode.getPlayer() == Player.None) ? ASTERISK : colNode.getPlayer().name();
                builder.append(colNodePresentation).append(SPACE);
                rowMarginBuilder.append(UNDERLINE).append(UNDERLINE);
            }
            builder.append("\n").append(rowMarginBuilder.toString()).append("\n");
            rowCount++;
        }


        return builder.toString();
    }
}
