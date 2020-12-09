package chess;

/**********************************************************************
 * A class that determines the correct Queen values.
 *
 * @author Christian Thompson, James Weitzmanm, Josh Hubbard, 
 *         Lauren Vanderklok, & Scott Weaver
 *********************************************************************/

public class Queen extends ChessPiece {

    /******************************************************************
     * A constructor that calls the super class method.
     * @param player the player being used
     *****************************************************************/
    public Queen(Player player) {
        super(player);
    }

    /******************************************************************
     * A method that returns the type of piece as a string.
     * @return piece name
     *****************************************************************/
    public String type() {
        return "Queen";
    }

    /******************************************************************
     * A method that uses the bishop and rook classes to determine if
     * the attempted move is valid.
     * @param move  a {@link chess.Move} object describing the move to
     * be made.
     * @param board the {@link chess.IChessPiece} in which this piece
     * resides.
     * @return true if valid and false if not.
     *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        Bishop move1 = new Bishop(board[move.fromRow][move.fromColumn]
                .player());
        Rook move2 = new Rook(board[move.fromRow][move.fromColumn].
                player());
        return (move1.isValidMove(move, board) || move2.isValidMove
                (move, board));
    }
}

//end of class
