package chess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**********************************************************************
 * A class that provides the panel for the chess game so that the user
 * can see.
 *
 * @author Logan Jaglowski, Sarah, and Lauren
 * @version Winter 2019
 *********************************************************************/

public class ChessPanel extends JPanel {

    /** Used for saving and loading games */
    private StateOfTheGame state;

    /** A board of buttons */
    private JButton[][] board;

    /** The model for the game */
    private ChessModel model;

    /** The images to represent the white pieces */
    private ImageIcon wRook;
    private ImageIcon wBishop;
    private ImageIcon wQueen;
    private ImageIcon wKing;
    private ImageIcon wPawn;
    private ImageIcon wKnight;

    /** The images to represent the black pieces */
    private ImageIcon bRook;
    private ImageIcon bBishop;
    private ImageIcon bQueen;
    private ImageIcon bKing;
    private ImageIcon bPawn;
    private ImageIcon bKnight;

    /** Keeps track of first click on a piece */
    private boolean firstTurnFlag;

    /** The row being moved from */
    private int fromRow;

    /** The row being moved to */
    private int toRow;

    /** The column being moved from */
    private int fromCol;

    /** The column being moved to */
    private int toCol;

    /** The fromColumn saved for undo */
    private int undoSaveFromCol;

    /** The toColumn saved for undo */
    private int undoSaveToCol;

    /** A label that shows whose turn it is */
    private JLabel currentPlayerLabel;

    /** A button that triggers undo */
    private JButton undoBtn;

    /** The listener for the action listeners */
    private listener listener;

    private boolean AIisActive = false;

    private String otherPlayerIP;
    private int ThisPort; //port of this server process
    private int otherPort; //port of connecting server process
    private Player player;

    private Server server;
    private Client client;

    /******************************************************************
     * A constructor that sets up the panel.
     *****************************************************************/
    public ChessPanel() {

        if(this.askStartNewGame()){
            player = Player.WHITE;
            ThisPort = this.askForThisPort();
            otherPort = this.askForOtherPort();

            model = new ChessModel(player);
            server = new Server(ThisPort, model, this);

            //server tells client ip of joining
            client = new Client(otherPort, server.getOtherPlayerIP());

        } else{
            player = Player.BLACK;
            otherPlayerIP = this.askForJoiningIP();
            ThisPort = this.askForThisPort();
            otherPort = this.askForOtherPort();

            model = new ChessModel(player);
            client = new Client(otherPort, otherPlayerIP);
            server = new Server(ThisPort, model, this);

        }










        board = new JButton[model.numRows()][model.numColumns()];
        listener = new listener();
        createIcons();




        model.setPlayer(Player.WHITE);  // make it so WHITE is always first player





       // this.askForAI();
        //generates message at start of game prompting
        // the user for the number of players

        JPanel boardpanel = new JPanel();
        JPanel buttonpanel = new JPanel();
        boardpanel.setLayout(new GridLayout(model.numRows(), model.
                numColumns(), 1, 1));

        //goes through the board and sets action listeners, pieces
        for (int r = 0; r < model.numRows(); r++) {
            for (int c = 0; c < model.numColumns(); c++) {
                if (model.pieceAt(r, c) == null) {
                    board[r][c] = new JButton("", null);
                    board[r][c].addActionListener(listener);
                } else if(model.pieceAt(r, c).player() == Player.WHITE)
                    placeWhitePieces(r, c);
                else if(model.pieceAt(r, c).player() == Player.BLACK)
                    placeBlackPieces(r, c);

                setBackGroundColor(r, c);
                boardpanel.add(board[r][c]);
            }
        }

        //adds player label to panel
        currentPlayerLabel = new JLabel("Current player: " +
                model.currentPlayer());
        buttonpanel.add(currentPlayerLabel);

        //adds undo button to panel
        undoBtn = new JButton("Undo");
        buttonpanel.add(undoBtn);
        undoBtn.addActionListener(listener);

        add(boardpanel, BorderLayout.WEST);
        boardpanel.setPreferredSize(new Dimension(600, 600));
        add(buttonpanel);
        state = new StateOfTheGame(model);
        firstTurnFlag = true;
    }

    /******************************************************************
     * A method that uses the parameters to create the checkered chess
     * board appearance.
     * @param r number of rows
     * @param c number of columns
     *****************************************************************/
    private void setBackGroundColor(int r, int c) {
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            board[r][c].setBackground(Color.LIGHT_GRAY);
        } else if((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2
                == 1)) {
            board[r][c].setBackground(Color.WHITE);
        }
    }

    /******************************************************************
     * A method that places the images on squares designated as white
     * pieces.
     * @param r The current row
     * @param c The current column
     *****************************************************************/
    private void placeWhitePieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, wPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, wRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, wKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, wBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, wQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, wKing);
            board[r][c].addActionListener(listener);
        }
    }

    /******************************************************************
     * A method that places the images on squares designated as black
     * pieces.
     * @param r The current row
     * @param c The current column
     *****************************************************************/
    private void placeBlackPieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, bPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, bRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, bKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, bBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, bQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, bKing);
            board[r][c].addActionListener(listener);
        }
    }

    /******************************************************************
     * A method that creates the images and adds them to the icons.
     *****************************************************************/
//    private void createIcons() {
//        // Sets the Image for white player pieces
//        wRook = new ImageIcon("./chess/wRook.png");
//        wBishop = new ImageIcon("./chess/wBishop.png");
//        wQueen = new ImageIcon("./chess/wQueen.png");
//        wKing = new ImageIcon("./chess/wKing.png");
//        wPawn = new ImageIcon("./chess/wPawn.png");
//        wKnight = new ImageIcon("./chess/wKnight.png");
//        //Sets the Image for black player pieces
//        bRook = new ImageIcon("./chess/bRook.png");
//        bBishop = new ImageIcon("./chess/bBishop.png");
//        bQueen = new ImageIcon("./chess/bQueen.png");
//        bKing = new ImageIcon("./chess/bKing.png");
//        bPawn = new ImageIcon("./chess/bPawn.png");
//        bKnight = new ImageIcon("./chess/bKnight.png");
//    }

//    //for testing within intelliJ, delete later
    private void createIcons() {
        // Sets the Image for white player pieces
        wRook = new ImageIcon("./src/chess/wRook.png");
        wBishop = new ImageIcon("./src/chess/wBishop.png");
        wQueen = new ImageIcon("./src/chess/wQueen.png");
        wKing = new ImageIcon("./src/chess/wKing.png");
        wPawn = new ImageIcon("./src/chess/wPawn.png");
        wKnight = new ImageIcon("./src/chess/wKnight.png");
        //Sets the Image for black player pieces
        bRook = new ImageIcon("./src/chess/bRook.png");
        bBishop = new ImageIcon("./src/chess/bBishop.png");
        bQueen = new ImageIcon("./src/chess/bQueen.png");
        bKing = new ImageIcon("./src/chess/bKing.png");
        bPawn = new ImageIcon("./src/chess/bPawn.png");
        bKnight = new ImageIcon("./src/chess/bKnight.png");
    }

    /******************************************************************
     * A method that updates the board.
     *****************************************************************/
    public void displayBoard() {

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++)
                if (model.pieceAt(r, c) == null)
                    board[r][c].setIcon(null);
                else{
                    if (model.pieceAt(r, c).player() == Player.WHITE) {
                        if (model.pieceAt(r, c).type().equals("Pawn"))
                            board[r][c].setIcon(wPawn);

                        if (model.pieceAt(r, c).type().equals("Rook"))
                            board[r][c].setIcon(wRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                            board[r][c].setIcon(wKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                            board[r][c].setIcon(wBishop);

                        if (model.pieceAt(r, c).type().equals("Queen"))
                            board[r][c].setIcon(wQueen);

                        if (model.pieceAt(r, c).type().equals("King"))
                            board[r][c].setIcon(wKing);

                    }
                if (model.pieceAt(r, c).player() == Player.BLACK) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                            board[r][c].setIcon(bPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                            board[r][c].setIcon(bRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                            board[r][c].setIcon(bKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                            board[r][c].setIcon(bBishop);

                        if (model.pieceAt(r, c).type().equals("Queen"))
                            board[r][c].setIcon(bQueen);

                        if (model.pieceAt(r, c).type().equals("King"))
                            board[r][c].setIcon(bKing);
                    }
                }
        }

        currentPlayerLabel.setText("Current player: " + model.
                currentPlayer());
        if(model.isComplete())
            JOptionPane.showMessageDialog(null,
                    "Checkmate!");
        repaint();
    }
    public void askForAI(){

        //strings in this array correspond to the text of the buttons
        Object[] buttons = {"One Player", "Two Players"};

        // this is the overloaded constructor of the JOptionPane,
        // for custom buttons
        int result = JOptionPane.showOptionDialog(null,
                "One player or two?", null,
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, buttons, buttons[0]);

        if (result == JOptionPane.YES_OPTION){
            AIisActive = true;
        }else{
            AIisActive = false;
        }
    }

    private boolean askStartNewGame(){

        //strings in this array correspond to the text of the buttons
        Object[] buttons = {"Start New Game", "Join Existing Game"};

        // this is the overloaded constructor of the JOptionPane,
        // for custom buttons
        int result = JOptionPane.showOptionDialog(null,
                "Start or join game?", null,
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, buttons, buttons[0]);

        if (result == JOptionPane.YES_OPTION){
             return true;
        }else{
            return false;
        }

    }

    private String askForJoiningIP(){

       return JOptionPane.showInputDialog("Enter the IP address of the game");

    }

    private int askForThisPort(){
        return Integer.parseInt(JOptionPane.showInputDialog("Enter the port number of your local game"));
    }

    private int askForOtherPort(){
        return Integer.parseInt(JOptionPane.showInputDialog("Enter the port number of the game you are joining"));
    }

    private Player askForColor(){
        //strings in this array correspond to the text of the buttons
        // first is yes option second is no
        Object[] buttons = {"Black", "White"};

        // this is the overloaded constructor of the JOptionPane,
        // for custom buttons
        int result = JOptionPane.showOptionDialog(null,
                "What color do you want to play?", null,
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, buttons, buttons[0]);

        if (result == JOptionPane.YES_OPTION){
            return Player.BLACK;

        }else{
            return Player.WHITE;
        }
    }

    public void close(){
        server.close();
        client.close();
    }


    // inner class that represents action listener for buttons
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            for (int r = 0; r < model.numRows(); r++) {
                for (int c = 0; c < model.numColumns(); c++) {
                    if (player == model.currentPlayer()) {      // can only make a move when it is this client's turn
                        if (board[r][c] == event.getSource()) {
                            if (firstTurnFlag) {
                                fromRow = r;
                                fromCol = c;

                                firstTurnFlag = false;
                            } else {
                                toRow = r;
                                toCol = c;
                                firstTurnFlag = true;
                                Move m = new Move(fromRow, fromCol, toRow,
                                        toCol);
                                model.setCurrentMove(m);

                                //when move completes game
                                if (model.isComplete()) {
                                    break;
                                }
                                //when move is into check
                                if (model.isValidMove(m).isMovedIntoCheck()) {
                                    JOptionPane.showMessageDialog(null,
                                            "Cannot move into check");
                                }
                                //when move puts player into check
                                if (model.isValidMove(m).isInCheck()) {
                                    JOptionPane.showMessageDialog(null,
                                            model.currentPlayer() +
                                                    " is in check");
                                }

                                //insert ifs and dialog boxes
                                if ((model.isValidMove(m)
                                        .isMoveSuccessful())) {
                                    if (AIisActive) {
                                        state.saveState(model);
                                        if (model.isEnPassant(model.
                                                getCurrentMove(), model.
                                                getLastMove())) {
                                            model.removeFromBoard(model.
                                                            getLastMove().toRow,
                                                    model.getLastMove().
                                                            toColumn);
                                        }
                                        model.move(m);

                                        model.setLastMove(m);
                                        model.rookCastling(m);
                                        model.pawnPromoted(m);
                                        if (model.isComplete()) {
                                            JOptionPane.showMessageDialog(
                                                    null, "Checkmate!");
                                        } else if (model.inCheck(Player.BLACK)) {
                                            JOptionPane.showMessageDialog(
                                                    null, "BLACK" +
                                                            " is in check");
                                        }
                                        model.AI();
                                        model.setLastMove(m);
                                        displayBoard();
                                    } else {
                                        state.saveState(model);
                                        if (model.isEnPassant(model.
                                                getCurrentMove(), model.
                                                getLastMove())) {
                                            model.removeFromBoard(model.
                                                            getLastMove().toRow,
                                                    model.getLastMove().
                                                            toColumn);
                                        }
                                        model.move(m);
                                         // send move to other client
                                        model.setLastMove(m);
                                        model.rookCastling(m);
                                        int promotion = model.pawnPromoted(m);
                                        //System.out.println(promotion);
                                        if ( promotion == 0){
                                            client.sendToOtherPlayer(m.fromRow, m.fromColumn, m.toRow, m.toColumn, "queen");
                                        }else if (promotion == 1){
                                            client.sendToOtherPlayer(m.fromRow, m.fromColumn, m.toRow, m.toColumn, "knight");
                                        }else if (promotion == 2){
                                            client.sendToOtherPlayer(m.fromRow, m.fromColumn, m.toRow, m.toColumn, "rook");
                                        }else if (promotion == 4){
                                            client.sendToOtherPlayer(m.fromRow, m.fromColumn, m.toRow, m.toColumn, "bishop");
                                        } else {
                                            client.sendToOtherPlayer(m.fromRow, m.fromColumn, m.toRow, m.toColumn);
                                        }

                                        model.setNextPlayer();
                                        displayBoard();
                                    }

                                    displayBoard();

                                } else {
                                    JOptionPane.showMessageDialog(null,
                                            "Move invalid");
                                }
                            }
                        }
                    }

                }
            }
            if (undoBtn == event.getSource()) {
                model = state.loadState();
                if (!state.checkIfBeginningModel())
                    state.incrementState();
                displayBoard();
            }
        }
    }
    }
