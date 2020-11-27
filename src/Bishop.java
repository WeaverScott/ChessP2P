package chess;

/**********************************************************************
 * A class that determines the correct Bishop values.
 *
 * @author Logan Jaglowski, Sarah, and Lauren
 * @version Winter 2019
 *********************************************************************/
public class Bishop extends ChessPiece {

    /*****************************************************************
     * A constructor that calls the super class method.
     * @param player the player that the method is called on.
     *****************************************************************/
    public Bishop(Player player) {
        super(player);
    }

    /******************************************************************
     * A method that returns the type of piece as a string.
     * @return piece name
     *****************************************************************/
    public String type() {
        return "Bishop";
    }

    /*****************************************************************
     * A method that checks whether the attempted move is valid.
     * @param move attempted move
     * @param board game board
     * @return true if the move is valid and false if not
     *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = true;

        //checks if the move is valid for any chess piece
        if (super.isValidMove(move, board)){
            valid = false;
            for (int i = 1; i < 9; i++){
                if (move.fromRow + i == move.toRow && move.fromColumn
                        + i == move.toColumn){
                    valid = true;
                    for (int j = 1; j < i; j++){
                        if (board[move.fromRow + j][move.fromColumn +
                                j] != null){
                            valid = false;
                            break;
                        }
                    }
                    break;
                }
                if (move.fromRow - i == move.toRow && move.fromColumn
                        + i == move.toColumn){
                    valid = true;
                    for (int j = 1; j < i; j++){
                        if (board[move.fromRow - j][move.fromColumn +
                                j] != null){
                            valid = false;
                            break;
                        }
                    }
                    break;
                }
                if (move.fromRow - i == move.toRow && move.fromColumn
                        - i == move.toColumn){
                    valid = true;
                    for (int j = 1; j < i; j++){
                        if (board[move.fromRow - j][move.fromColumn -
                                j] != null){
                            valid = false;
                            break;
                        }
                    }
                    break;
                }
                if (move.fromRow + i == move.toRow && move.fromColumn -
                        i == move.toColumn){
                    valid = true;
                    for (int j = 1; j < i; j++){
                        if (board[move.fromRow + j][move.fromColumn -
                                j] != null){
                            valid = false;
                            break;
                        }
                    }
                    break;
                }
            }
        }else{
            valid = false;
        }


        return valid;
    }
}

//end of class
