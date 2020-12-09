package chess;

/**********************************************************************
 * A class that determines the correct Pawn values.
 *
 * @author Christian Thompson, James Weitzmanm, Josh Hubbard, 
 *         Lauren Vanderklok, & Scott Weaver
 *********************************************************************/
public class Pawn extends ChessPiece {

    /******************************************************************
     * A method that calls the super class method.
     * @param player the player being used
     *****************************************************************/
    public Pawn(Player player) {
        super(player);
    }

    /******************************************************************
     * A method that returns the piece type as a string.
     * @return piece type name
     *****************************************************************/
    public String type() {
        return "Pawn";
    }

    /******************************************************************
     * A method that checks if the attempted move is valid.
     * @param move  a {@link chess.Move} object describing the move to
     * be made.
     * @param board the {@link chess.IChessPiece} in which this piece
     * resides.
     * @return true if valid anf false if not.
     *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = true;

        //checks super class method
        if (super.isValidMove(move, board)){

            if (Math.abs(move.toRow - move.fromRow) > 2)
                return false;
            //first if controls diagonal movement when capturing
            if (board[move.toRow][move.toColumn] != null) {
                if ((board[move.toRow][move.toColumn]).player() !=
                        super.player()) {
                    if (move.fromColumn - move.toColumn == 1 ||
                            move.fromColumn - move.toColumn == -1) {
                        valid = true;
                    }else{
                        valid = false;
                    }
                }
                if (move.fromColumn - move.toColumn == 0) {
                    valid = false;
                }
            }
            if (move.fromColumn - move.toColumn != 0 &&
                    board[move.toRow][move.toColumn] == null)
                valid = false;
            if (super.player() == Player.BLACK) {
                if (this.isEnPassant(move)) {
                    if (move.fromRow - move.toRow < -2 || move.fromRow
                            - move.toRow > -1) {
                        valid = false;

                    }else{
                        if (board[move.toRow][move.toColumn] != null){
                            valid = false;
                        }
                        if (move.fromRow - move.toRow == -2 && board
                            [move.toRow - 1][move.toColumn] != null)
                            valid = false;
                    }
                } else {
                    if (move.fromRow - move.toRow != -1) {
                        valid = false;
                    }
                }
            }else{
                if (this.isEnPassant(move)) {
                    if (move.fromRow - move.toRow > 2 || move.fromRow
                            - move.toRow < 1) {
                        valid = false;
                    }else{
                        if (board[move.toRow][move.toColumn] != null){
                            valid = false;
                        }
                        if (move.fromRow - move.toRow == 2 && board
                            [move.toRow + 1][move.toColumn] != null)
                            valid = false;
                    }
                } else {
                    if (move.fromRow - move.toRow != 1) {
                        valid = false;
                    }
                }
            }
        }else{
            valid = false;
        }

        return valid;
    }

    /******************************************************************
     * A helper method that determines which row it's in
     * @param move the attempted move
     * @return true if en passant and false if not.
     *****************************************************************/
    private boolean isEnPassant(Move move){
        if (super.player() == Player.WHITE){
            if (move.fromRow == 6){
                return true;
            }
        }else{
            if (move.fromRow == 1){
                return true;
            }
        }
        return false;
    }

}
