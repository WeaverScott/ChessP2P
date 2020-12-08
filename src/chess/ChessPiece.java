package chess;

/**********************************************************************
 * A class that determines the correct ChessPiece values.
 *
 * @author Christian Thompson, James Weitzmanm, Josh Hubbard, 
 *         Lauren Vanderklok, & Scott Weaver
 *********************************************************************/

public abstract class ChessPiece implements IChessPiece {

    /** The player */
    private Player owner;

    /******************************************************************
     * A constructor that sets the player object to the parameter.
     * @param player the current player
     *****************************************************************/
    protected ChessPiece(Player player) {
        this.owner = player;
    }

    /******************************************************************
     * An abstract method with return type String.
     * @return a String.
     *****************************************************************/
    public abstract String type();

    /******************************************************************
     * A method that returns the player owner.
     * @return the owner player
     *****************************************************************/
    public Player player() {
        return owner;
    }

    /******************************************************************
     * A method that checks whether the attempted move is valid.
     * @param move  a {@link chess.Move} object describing the move to
     * be made.
     * @param board the {@link chess.IChessPiece} in which this piece
     * resides.
     * @return true if the move is valid, false if not
     *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = false;

        //checks if the attempted move is to the same square
        if ((move.fromRow != move.toRow) || (move.fromColumn != move.
                toColumn))
            //checks if it's on the board
            if ((move.fromRow != move.toRow) || (move.fromColumn !=
                    move.toColumn)) { //piece is actually moving
                if (move.fromRow >= 0 && move.fromRow < 8) {
                    if (move.toRow >= 0 && move.toRow < 8) {
                        if (move.fromColumn >= 0 && move.toRow < 8) {
                            if (move.toColumn >= 0 && move.toColumn <
                                    8) { //attempted destination exists
                                if ((board[move.fromRow][move.
                                        fromColumn]) != null) {

                                    if(board[move.toRow][move.toColumn]
                                            != null) {
                                        //piece isn't taking same team
                                        if (board[move.toRow][move.
                                                toColumn].player() !=
                                                this.player()) {
                                            valid = true;
                                        }
                                    } else {
                                        valid = true;
                                    }
                                }
                            }
                        }
                    }

                }
            }
        return valid;
    }
}

//end of class
