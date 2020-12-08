package chess;

/**********************************************************************
 * A class that determines the correct Knight values.
 *
 * @author Christian Thompson, James Weitzmanm, Josh Hubbard, 
 *         Lauren Vanderklok, & Scott Weaver
 *********************************************************************/

public class Knight extends ChessPiece {

    /******************************************************************
     * A constructor that calls the super class method on the player.
     * @param player the player being used.
     *****************************************************************/
    public Knight(Player player) {
        super(player);
    }

    /******************************************************************
     * A method that returns the piece type as a string.
     * @return type of piece
     *****************************************************************/
    public String type() {
        return "Knight";
    }

    /******************************************************************
     * A method that checks if an attempted move is valid.
     * @param move  a {@link chess.Move} object describing the move to
     * be made.
     * @param board the {@link chess.IChessPiece} in which this piece
     * resides.
     * @return true if valid, false if not
     *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board){

        boolean valid = false;
        // More code is needed
        if (super.isValidMove(move, board)) {
            if (move.fromRow == move.toRow - 2)
                if (move.fromColumn == move.toColumn - 1) {
                    valid = true;
                }
            if (move.fromRow == move.toRow + 2)
                if (move.fromColumn == move.toColumn - 1) {
                    valid = true;
                }
            if (move.fromRow == move.toRow + 2)
                if (move.fromColumn == move.toColumn + 1) {
                    valid = true;
                }
            if (move.fromRow == move.toRow - 2)
                if (move.fromColumn == move.toColumn + 1) {
                    valid = true;
                }
            if (move.fromRow == move.toRow - 1)
                if (move.fromColumn == move.toColumn - 2) {
                    valid = true;
                }
            if (move.fromRow == move.toRow - 1)
                if (move.fromColumn == move.toColumn + 2) {
                    valid = true;
                }
            if (move.fromRow == move.toRow + 1)
                if (move.fromColumn == move.toColumn - 2) {
                    valid = true;
                }
            if (move.fromRow == move.toRow + 1)
                if (move.fromColumn == move.toColumn + 2) {
                    valid = true;
                }
        }
        return valid;
    }

}
