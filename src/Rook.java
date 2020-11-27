package chess;

/**********************************************************************
 * A class that determines the correct Rook values.
 *
 * @author Logan Jaglowski, Sarah, and Lauren
 * @version Winter 2019
 *********************************************************************/

public class Rook extends ChessPiece {

    boolean moved = false;

    /******************************************************************
     * A constructor that calls the same method in the super class.
     * @param player the current player owner of the rook
     *****************************************************************/
    public Rook(Player player) {

        super(player);

    }

    /******************************************************************
     * A toString method that returns the piece name.
     * @return rook for the piece type
     *****************************************************************/
    public String type() {
        return "Rook";
    }

    public boolean hasMoved() {
        return moved;
    }

    // determines if the move is valid for a rook piece
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        int howFarVertical = move.toColumn - move.fromColumn;
        int howFarHorizontal = move.toRow - move.fromRow;

        boolean valid = false;
        //checks if the rook is moving within the same row or column
        if (super.isValidMove(move, board)) {
            if (move.fromRow == move.toRow) {
                if (move.fromColumn != move.toColumn) {
                    if (move.fromColumn < move.toColumn) {
                        if (howFarVertical == 2)
                            if (board[move.fromRow][move.fromColumn +1]
                                    != null)
                                return false;
                        for (int i = 1; i <= Math.abs(howFarVertical)-
                                1; i++) {
                            if (board[move.fromRow][move.fromColumn +
                                    i] != null) {
                                return false;
                            }
                        }
                    }
                    if (move.fromColumn > move.toColumn) {
                        if (howFarVertical == -2)
                            if (board[move.fromRow][move.fromColumn -
                                    1] != null)
                                return false;
                        for (int i = 1; i <= Math.abs(howFarVertical)
                                - 1; i++) {
                            if (board[move.fromRow][move.fromColumn -
                                    i] != null) {
                                return false;
                            }
                        }
                    }
                    moved = true;
                    valid = true;
                }
            }

            //prevents from hopping over pieces
            if (move.fromColumn == move.toColumn) {
                if (move.fromRow != move.toRow) {
                    if (move.fromRow < move.toRow) {
                        if (howFarHorizontal == 2)
                            if(board[move.fromRow + 1][move.fromColumn]
                                    != null)
                                return false;
                        for (int i = 1; i <= Math.abs(howFarHorizontal)
                                - 1; i++) {
                            if(board[move.fromRow +i][move.fromColumn]
                                    != null) {
                                return false;
                            }
                        }
                    }
                    if (move.fromRow > move.toRow) {
                        if (howFarHorizontal == -2)
                            if (board[move.fromRow -1][move.fromColumn]
                                    != null)
                                return false;
                        for (int i = 1; i <= Math.abs(howFarHorizontal)
                                - 1; i++) {
                            if (board[move.fromRow- i][move.fromColumn]
                                    != null) {
                                return false;
                            }
                        }
                    }
                    moved = true;
                    valid = true;
                }
            }
        }
        return valid;
    }
}

//end of class
