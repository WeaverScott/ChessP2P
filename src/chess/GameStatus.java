package chess;

/**********************************************************************
 * A class that stores conditions about game, changed when pieces move.
 * Changes in booleans should result in JOptionPanes being triggered.
 * 
 * @author Christian Thompson, James Weitzmanm, Josh Hubbard, 
 *         Lauren Vanderklok, & Scott Weaver
 *********************************************************************/
public class GameStatus {

    /** A boolean that detects if the game is complete or not */
    private boolean isComplete;

    /** A boolean that detects if the game is has a check or not */
    private boolean inCheck;

    /** A boolean that detects if a player moves into check */
    private boolean movedIntoCheck;

    /** A boolean that detects if a move is successful */
    private boolean moveSuccessful;

    /******************************************************************
     * A method that returns whether the game is complete or not
     * @returns whether game is complete or not
     *****************************************************************/
    public boolean isComplete() {
        return isComplete;
    }

    /******************************************************************
     * A method that sets whether the game is complete or not
     * @param complete whether game is complete or not
     *****************************************************************/
    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    /******************************************************************
     * A method that returns whether the move is successful or not
     * @returns whether move is successful or not
     *****************************************************************/
    public boolean isMoveSuccessful() {
        return moveSuccessful;
    }

    /******************************************************************
     * A method that sets whether the move is successful or not
     * @param moveSuccessful whether move is successful or not
     *****************************************************************/
    public void setMoveSuccessful(boolean moveSuccessful) {
        this.moveSuccessful = moveSuccessful;
    }

    /******************************************************************
     * A method that returns whether the player is in check or not
     * @returns whether in check is successful or not
     *****************************************************************/
    public boolean isInCheck() {
        return inCheck;
    }

    /******************************************************************
     * A method that sets whether the player is in check or not
     * @param inCheck whether in check is successful or not
     *****************************************************************/
    public void setInCheck(boolean inCheck) {
        this.inCheck = inCheck;
    }

    /******************************************************************
     * A method that returns whether the move is successful or not
     * @returns whether move is successful or not
     *****************************************************************/
    public boolean isMovedIntoCheck() {
        return movedIntoCheck;
    }

    /******************************************************************
     * A method that sets whether the move is successful or not
     * @param movedIntoCheck whether move is successful or not
     *****************************************************************/
    public void setMovedIntoCheck(boolean movedIntoCheck) {
        this.movedIntoCheck = movedIntoCheck;
    }

    /******************************************************************
     * A default method to set all the parameters to false.
     *****************************************************************/
    public GameStatus(){
        isComplete = false;
        moveSuccessful = false;
        movedIntoCheck = false;
        inCheck = false;
    }
}
