package chess;

/**********************************************************************
 * A class that saves and loads states of the game.
 *
 * @author Logan Jaglowski, Sarah, and Lauren
 * @version Winter 2019
 *********************************************************************/

import java.util.ArrayList;

public class StateOfTheGame {
    /** An arraylist of ChessModel objects */
    private ArrayList<ChessModel> state;

    /** An int representing the current state */
    private int currentState = 0;

    /** An int representing the total states */
    private int totalStates = 0;

    /** The model used at the start */
    private ChessModel beginningModel;

    /******************************************************************
     * A constructor that creates the state of the game.
     * @param model the model to be saved
     *****************************************************************/
    public StateOfTheGame (ChessModel model) {
        state = new ArrayList<>();
        beginningModel = new ChessModel();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                IChessPiece piece = model.pieceAt(row,col);
                beginningModel.setPiece(row, col, piece);
            }
        }
        beginningModel.setPlayer(model.currentPlayer());
        state.add(0, beginningModel);
        totalStates++;
    }

    /******************************************************************
     * A method that saves the current state of the game
     * @param model model to be saved
     *****************************************************************/
    public void saveState (ChessModel model) {
        if (currentState > 0) {
            for (int delete = 0; delete < currentState; delete++) {
                state.remove(0);
            }
        }
        ChessModel oldModel = new ChessModel();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                IChessPiece piece = model.pieceAt(row,col);
                oldModel.setPiece(row, col, piece);
            }
        }
        oldModel.setPlayer(model.currentPlayer());
        state.add(0, oldModel);
        currentState = 0;
        totalStates++;
    }

    /******************************************************************
     * A method that loads a previous state of the game.
     * @return the previous ChessModel object (state)
     *****************************************************************/
    public ChessModel loadState () {
        if(currentState >= state.size() - 1)
            return state.get(state.size() - 1);
        return state.get(currentState);
    }

    /******************************************************************
     * A method that checks if the current model is the first model.
     * @return true if current model is first model, false if not.
     *****************************************************************/
    public boolean checkIfBeginningModel() {
        if (currentState - state.size() == 0)
            return true;
        return false;
    }

    /******************************************************************
     * A method that increments through the arraylist of states.
     *****************************************************************/
    public void incrementState() {
        currentState++;
    }

}
