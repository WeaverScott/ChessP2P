package chess;

/**********************************************************************
 * A class that  
 *
 * @author Christian Thompson, James Weitzmanm, Josh Hubbard, 
 *         Lauren Vanderklok, & Scott Weaver
 *********************************************************************/
public interface IChessModel {

    /******************************************************************
     * Returns whether the game is complete.
     *
     * @return {@code true} if complete, {@code false} otherwise.
     *****************************************************************/
    boolean isComplete();

    /******************************************************************
     * Returns whether the piece at location {@code [move.fromRow,
     * move.fromColumn]} is allowed to move to location
     * {@code [move.fromRow, move.fromColumn]}.
     *
     * @param move a {@link chess.Move} object describing the move to
     * be made.
     * @return an {@link chess.GameStatus} representing values.
     * @throws IndexOutOfBoundsException if either {@code [move.
     * fromRow, move.fromColumn]} or {@code [move.toRow,
     * move.toColumn]} don't represent valid locations on the board.
     *****************************************************************/
    GameStatus isValidMove(Move move);

    /*****************************************************************
     * Moves the piece from location {@code [move.fromRow, move.
     * fromColumn]} to location {@code [move.fromRow,move.fromColumn]}.
     * @param move a {@link chess.Move} object describing the move to
     * be made.
     * @throws IndexOutOfBoundsException if either {@code [move.
     * fromRow, move.fromColumn]} or {@code [move.toRow,
     * move.toColumn]} don't represent valid locations on the board.
     *****************************************************************/
    void move(Move move);

    /******************************************************************
     * Report whether the current player p is in check.
     * @param  p {@link chess.Move} the Player being checked
     * @return {@code true} if the current player is in check,
     * {@code false} otherwise.
     *****************************************************************/
    boolean inCheck(Player p);

    /******************************************************************
     * Return the current player.
     *
     * @return the current player
     *****************************************************************/
    Player currentPlayer();

}

//end of class