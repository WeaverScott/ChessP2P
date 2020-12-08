package chess;

/**********************************************************************
 * A class that sets the player. 
 *
 * @author Christian Thompson, James Weitzmanm, Josh Hubbard, 
 *         Lauren Vanderklok, & Scott Weaver
 *********************************************************************/
public enum Player {
    BLACK, WHITE;

    /******************************************************************
     * Return the {@code Player} whose turn is next.
     *
     * @return the {@code Player} whose turn is next
     *****************************************************************/
    public Player next() {
        if (this == BLACK)
            return WHITE;
        else
            return BLACK;
    }
}
