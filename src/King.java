package chess;

/**********************************************************************
 * A class that determines the correct King values.
 *
 * @author Logan Jaglowski, Sarah, and Lauren
 * @version Winter 2019
 *********************************************************************/

public class King extends ChessPiece {

    /******************************************************************
     * A constructor that calls the super class method on the player.
     * @param player the player being used.
     *****************************************************************/
    public King(Player player) {
        super(player);
    }

    /******************************************************************
     * A method that returns the piece type as a string.
     * @return type of piece
     *****************************************************************/
    public String type() {
        return "King";
    }

    /******************************************************************
     * A method that checks if an attempted move is valid.
     * @param move  a {@link chess.Move} object describing the move to
     * be made.
     * @param board the {@link chess.IChessPiece} in which this piece
     * resides.
     * @return true if valid, false if not
     *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = false;
        boolean moved = false;

        //checks if attempted move is within one square of king
        if(super.isValidMove(move, board)){

            //castling
            if (Math.abs(move.fromColumn - move.toColumn) == 2 &&
                    !moved){
                //castling to the left
                if (move.toColumn < move.fromColumn){
                    if (move.toColumn - 2 >= 0 && move.fromColumn >= 0)
                    {
                        if (board[move.toRow][move.toColumn - 2] !=
                                null && board[move.toRow][move.toColumn
                                - 2].type().equals("Rook")) {
                            Rook rook = (Rook) board[move.toRow]
                                    [move.toColumn - 2];
                            if (!rook.hasMoved()) {
                                for (int i = 1; i < 4; i++) {
                                    if (board[move.fromRow][move.
                                            fromColumn - i]
                                            != null) {
                                        return false;
                                    }
                                }
                                return true;
                            }
                        }
                    }
                    //castling to the right
                } else {
                    if (move.toColumn + 1 <= 7)
                    if (board[move.toRow][move.toColumn + 1] != null)
                    {
                        if (board[move.toRow][move.toColumn + 1].type()
                                .equals("Rook")) {
                            Rook rook = (Rook) board[move.toRow]
                                    [move.toColumn + 1];
                            if (!rook.hasMoved()) {
                                for (int i = 1; i < 3; i++) {
                                    if (board[move.fromRow][move.
                                            fromColumn + i] != null) {
                                        return false;
                                    }
                                }
                                return true;
                            }
                        }
                    }
                }
            }
                //checks upper left square
                if (move.toRow == move.fromRow - 1 &&
                        move.toColumn == move.fromColumn - 1) {
                    valid = true;
                    //checks upper mid square, directly above current
                } else if (move.toRow == move.fromRow - 1 &&
                        move.toColumn == move.fromColumn) {
                    valid = true;
                    //checks upper right square
                } else if (move.toRow == move.fromRow - 1 &&
                        move.toColumn == move.fromColumn + 1) {
                    valid = true;
                    //checks left square
                } else if (move.toRow == move.fromRow && move.toColumn
                        == move.fromColumn - 1) {
                    valid = true;
                    //checks right square
                } else if (move.toRow == move.fromRow &&
                        move.toColumn == move.fromColumn + 1) {
                    valid = true;
                    //checks lower left square
                } else if (move.toRow == move.fromRow + 1 &&
                        move.toColumn == move.fromColumn - 1) {
                    valid = true;
                    //checks lower mid square
                } else if (move.toRow == move.fromRow + 1 &&
                        move.toColumn == move.fromColumn) {
                    valid = true;
                    //checks lower right square
                } else if (move.toRow == move.fromRow + 1 &&
                        move.toColumn == move.fromColumn + 1) {
                    valid = true;
                }
            }
        moved = true;
        return valid;
    }
}

//end of class
