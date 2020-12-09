package chess;

/**********************************************************************
 * A class that gets called to move the chess pieces. 
 *
 * @author Christian Thompson, James Weitzmanm, Josh Hubbard, 
 *         Lauren Vanderklok, & Scott Weaver
 *********************************************************************/
public class Move {
    
    /** Rows and columns being moved from and to */
    public int fromRow, fromColumn, toRow, toColumn;

    /******************************************************************
     * A blank constructor to satisfy the compiler.
     *****************************************************************/
    public Move() {
    }

    /******************************************************************
     * A constructor that sets the coordinates moved to and from.
     * @param fromRow origin row
     * @param fromColumn origin column
     * @param toRow destination row
     * @param toColumn destination column
     *****************************************************************/
    public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
        this.fromRow = fromRow;
        this.fromColumn = fromColumn;
        this.toRow = toRow;
        this.toColumn = toColumn;
    }

    /******************************************************************
     * A method that returns the parameters of the move method.
     * @return origin and destination coordinates of piece
     *****************************************************************/
    @Override
    public String toString() {
        return "Move [fromRow=" + fromRow +", fromColumn=" + fromColumn
                + ", toRow=" + toRow + ", toColumn=" + toColumn + "]";
    }
}
