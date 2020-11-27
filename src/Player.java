package chess;

//cannot edit this class

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
