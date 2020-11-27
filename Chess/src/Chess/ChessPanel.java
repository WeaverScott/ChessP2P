package Chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Panel to display board, buttons, labels, and all assets. Displays accordingly to moves made by player.
 * Extends JPanel
 */

public class ChessPanel extends JPanel {

    private JButton[][] board;
    private ChessModel model;

    private ImageIcon wRook;
    private ImageIcon wBishop;
    private ImageIcon wQueen;
    private ImageIcon wKing;
    private ImageIcon wPawn;
    private ImageIcon wKnight;

    private ImageIcon bRook;
    private ImageIcon bBishop;
    private ImageIcon bQueen;
    private ImageIcon bKing;
    private ImageIcon bPawn;
    private ImageIcon bKnight;

    private boolean firstTurnFlag;
    private int fromRow;
    private int toRow;
    private int fromCol;
    private int toCol;

    private JButton undoButton;
    private JLabel turnLabel;

    private listener listener;

    private ArrayList<Move> undoMoves = new ArrayList<Move>();           // used to undo moves
    private ArrayList<IChessPiece> undoTakes = new ArrayList<IChessPiece>();    // used to undo taken pieces
    private ArrayList<Boolean> pawnSwaps = new ArrayList<Boolean>();        // used to undo pawn swaps

    /******************************************************************
     * Default constructor places and displays pieces, buttons, and labels on the board
     ******************************************************************/
    public ChessPanel() {
        model = new ChessModel();   // instantiate a new ChessModel
        board = new JButton[model.numRows()][model.numColumns()];   // instantiate a new 8x8 board of JButtons
        listener = new listener();  // instantiate a new listener
        createIcons();  // instantiate icons to be used for each piece

        JPanel boardpanel = new JPanel();   // instantiate new JPanel
        JPanel buttonpanel = new JPanel();  // instantiate new JPanel
        boardpanel.setLayout(new GridLayout(model.numRows(), model.numColumns(), 1, 1));    // set layout of boardpanel
        buttonpanel.setLayout(new GridLayout(2, 1, 1, 1));  // set layout of buttonpanel

        turnLabel = new JLabel("Current: " + model.currentPlayer());    // instantiate turnLabel
        undoButton = new JButton("Undo");   // instantiate undoButton
        undoButton.addActionListener(listener);  // add listener to undoButton
        buttonpanel.add(turnLabel, BorderLayout.NORTH); // add turnLabel to buttonpanel
        buttonpanel.add(undoButton, BorderLayout.SOUTH);// add undoButton to buttonpanel

        for (int r = 0; r < model.numRows(); r++) {         // scan rows
            for (int c = 0; c < model.numColumns(); c++) {  // scan columns

                if (model.pieceAt(r, c) == null) {  // row/column is unoccupied
                    board[r][c] = new JButton("", null);    // instantiate new JButton at r, c
                    board[r][c].addActionListener(listener);    // add listener to JButton at r, c
                } else if (model.pieceAt(r, c).player() == Player.WHITE) {  // row/column occupied by WHITE piece
                    placeWhitePieces(r, c); // place corresponding WHITE piece at r, c
                } else if (model.pieceAt(r, c).player() == Player.BLACK) {  // row/column occupied by BLACK piece
                    placeBlackPieces(r, c); // place corresponding BLACK piece at r, c
                }

                setBackGroundColor(r, c);   // set the background color of space at r, c to its corresponding color
                boardpanel.add(board[r][c]);// add button at r, c to boardpanel
            }
        }
        add(boardpanel, BorderLayout.EAST); // add board panel to the JPanel
        boardpanel.setPreferredSize(new Dimension(600, 600));   // set size of boardpanel
        add(buttonpanel, BorderLayout.WEST);// add buttonpanel to the JPanel
        firstTurnFlag = true;   // set firstTurnFlag to true
    }

    /******************************************************************
     * Helper method sets background color of spaces
     *
     * @param r row
     * @param c column
     ******************************************************************/
    private void setBackGroundColor(int r, int c) {
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            board[r][c].setBackground(Color.LIGHT_GRAY);    // set space at r, c background to light gray
        } else {
            board[r][c].setBackground(Color.WHITE);         // set space at r, c background to white
        }
        board[r][c].setOpaque(true);            // make space at r, c opaque
        board[r][c].setBorderPainted(false);    // from StackOverflow, colors entire button's background
    }

    /******************************************************************
     * Helper method places white pieces
     *
     * @param r row
     * @param c column
     ******************************************************************/
    private void placeWhitePieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {    // piece at r, c is a Pawn
            board[r][c] = new JButton(null, wPawn); // instantiate new JButton with wPawn ImageIcon
            board[r][c].addActionListener(listener);     // add listener to button at r, c
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {    // piece at r, c is a Rook
            board[r][c] = new JButton(null, wRook); // instantiate new JButton with wRook ImageIcon
            board[r][c].addActionListener(listener);     // add listener to button at r, c
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {  // piece at r, c is a Knight
            board[r][c] = new JButton(null, wKnight); // instantiate new JButton with wKnight ImageIcon
            board[r][c].addActionListener(listener);     // add listener to button at r, c
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {  // piece at r, c is a Bishop
            board[r][c] = new JButton(null, wBishop); // instantiate new JButton with wBishop ImageIcon
            board[r][c].addActionListener(listener);     // add listener to button at r, c
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {   // piece at r, c is a Queen
            board[r][c] = new JButton(null, wQueen); // instantiate new JButton with wQueen ImageIcon
            board[r][c].addActionListener(listener);     // add listener to button at r, c
        }
        if (model.pieceAt(r, c).type().equals("King")) {    // piece at r, c is a King
            board[r][c] = new JButton(null, wKing); // instantiate new JButton with wKing ImageIcon
            board[r][c].addActionListener(listener);     // add listener to button at r, c
        }
    }

    /******************************************************************
     * Helper method places black pieces
     *
     * @param r row
     * @param c column
     ******************************************************************/
    private void placeBlackPieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {    // piece at r, c is a Pawn
            board[r][c] = new JButton(null, bPawn); // instantiate new JButton with bPawn ImageIcon
            board[r][c].addActionListener(listener);     // add listener to button at r, c
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {    // piece at r, c is a Rook
            board[r][c] = new JButton(null, bRook); // instantiate new JButton with bRook ImageIcon
            board[r][c].addActionListener(listener);     // add listener to button at r, c
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {    // piece at r, c is a Knight
            board[r][c] = new JButton(null, bKnight); // instantiate new JButton with bKnight ImageIcon
            board[r][c].addActionListener(listener);     // add listener to button at r, c
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {    // piece at r, c is a Bishop
            board[r][c] = new JButton(null, bBishop); // instantiate new JButton with bBishop ImageIcon
            board[r][c].addActionListener(listener);     // add listener to button at r, c
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {    // piece at r, c is a Queen
            board[r][c] = new JButton(null, bQueen); // instantiate new JButton with bQueen ImageIcon
            board[r][c].addActionListener(listener);     // add listener to button at r, c
        }
        if (model.pieceAt(r, c).type().equals("King")) {    // piece at r, c is a King
            board[r][c] = new JButton(null, bKing); // instantiate new JButton with bKing ImageIcon
            board[r][c].addActionListener(listener);     // add listener to button at r, c
        }
    }

    /******************************************************************
     * Helper method adds images to pieces
     ******************************************************************/
    private void createIcons() {
        // Sets the ImageIcon for white player pieces
        wRook = new ImageIcon("./Chess/src/Chess/wRook.png");
        wBishop = new ImageIcon("./Chess/src/Chess/wBishop.png");
        wQueen = new ImageIcon("./Chess/src/Chess/wQueen.png");
        wKing = new ImageIcon("./Chess/src/Chess/wKing.png");
        wPawn = new ImageIcon("./Chess/src/Chess/wPawn.png");
        wKnight = new ImageIcon("./Chess/src/Chess/wKnight.png");

        // sets the ImageIcon for black player pieces
        bRook = new ImageIcon("./Chess/src/Chess/bRook.png");
        bBishop = new ImageIcon("./Chess/src/Chess/bBishop.png");
        bQueen = new ImageIcon("./Chess/src/Chess/bQueen.png");
        bKing = new ImageIcon("./Chess/src/Chess/bKing.png");
        bPawn = new ImageIcon("./Chess/src/Chess/bPawn.png");
        bKnight = new ImageIcon("./Chess/src/Chess/bKnight.png");
    }

    /******************************************************************
     * Helper method that updates the display of the board
     ******************************************************************/
    private void displayBoard() {

        for (int r = 0; r < 8; r++) {       // scan rows
            for (int c = 0; c < 8; c++) {   // scan columns
                if (model.pieceAt(r, c) == null) {  // piece at r, c is null
                    board[r][c].setIcon(null);  // make button at r, c null
                } else if (model.pieceAt(r, c).player() == Player.WHITE) {  // piece at r, c belongs to white player
                    if (model.pieceAt(r, c).type().equals("Pawn")) {    // piece at r, c is a pawn
                        board[r][c].setIcon(wPawn); // set icon to wPawn
                    }

                    if (model.pieceAt(r, c).type().equals("Rook")) {    // piece at r, c is a rook
                        board[r][c].setIcon(wRook); // set icon to wRook
                    }

                    if (model.pieceAt(r, c).type().equals("Knight")) {    // piece at r, c is a knight
                        board[r][c].setIcon(wKnight); // set icon to wKnight
                    }

                    if (model.pieceAt(r, c).type().equals("Bishop")) {    // piece at r, c is a bishop
                        board[r][c].setIcon(wBishop); // set icon to wBishop
                    }

                    if (model.pieceAt(r, c).type().equals("Queen")) {    // piece at r, c is a queen
                        board[r][c].setIcon(wQueen); // set icon to wQueen
                    }

                    if (model.pieceAt(r, c).type().equals("King")) {    // piece at r, c is a king
                        board[r][c].setIcon(wKing); // set icon to wKing
                        model.setWhiteKingLocs(r, c);   // set location of white king
                    }
                } else if (model.pieceAt(r, c).player() == Player.BLACK) {  // piece at r, c belongs to player black
                    if (model.pieceAt(r, c).type().equals("Pawn")) {    // piece at r, c is a pawn
                        board[r][c].setIcon(bPawn); // set icon to bPawn
                    }

                    if (model.pieceAt(r, c).type().equals("Rook")) {    // piece at r, c is a rook
                        board[r][c].setIcon(bRook); // set icon to bRook
                    }

                    if (model.pieceAt(r, c).type().equals("Knight")) {    // piece at r, c is a knight
                        board[r][c].setIcon(bKnight); // set icon to bKnight
                    }

                    if (model.pieceAt(r, c).type().equals("Bishop")) {    // piece at r, c is a bishop
                        board[r][c].setIcon(bBishop); // set icon to bBishop
                    }

                    if (model.pieceAt(r, c).type().equals("Queen")) {    // piece at r, c is a queen
                        board[r][c].setIcon(bQueen); // set icon to bQueen
                    }

                    if (model.pieceAt(r, c).type().equals("King")) {    // piece at r, c is a king
                        board[r][c].setIcon(bKing); // set icon to bKing
                        model.setBlackKingLocs(r, c);   // set location of black king
                    }
                }
            }
        }
        repaint();  // update display of content of JPanel
    }

    /******************************************************************
     * Helper method changes pieces that reach the top or bottom row
     *
     * @param n chosen option
     * @param p player swapping their pawn
     * @param move move that was just made
     ******************************************************************/
    private void replacePawn(int n, Player p, Move move) {
        if (p == Player.WHITE) {    // replace white pawn that reached the top row with selected option
            if (n == 0) {   // rook option chosen
                model.setPiece(move.toRow, move.toColumn, new Rook(Player.WHITE));  // change piece at toRow/toColumn to a Rook
            } else if (n == 1) {   // knight option chosen
                model.setPiece(move.toRow, move.toColumn, new Knight(Player.WHITE));  // change piece at toRow/toColumn to a Knight
            } else if (n == 2) {   // bishop option chosen
                model.setPiece(move.toRow, move.toColumn, new Bishop(Player.WHITE));  // change piece at toRow/toColumn to a Bishop
            } else if (n == 3) {   // queen option chosen
                model.setPiece(move.toRow, move.toColumn, new Queen(Player.WHITE));  // change piece at toRow/toColumn to a Queen
            }
        } else {    // replace black pawn that reached the bottom row with selected option (will always be queen for the AI)
            if (n == 0) {
                model.setPiece(move.toRow, move.toColumn, new Rook(Player.BLACK));
            } else if (n == 1) {
                model.setPiece(move.toRow, move.toColumn, new Knight(Player.BLACK));
            } else if (n == 2) {
                model.setPiece(move.toRow, move.toColumn, new Bishop(Player.BLACK));
            } else if (n == 3) {    // queen option chosen
                model.setPiece(move.toRow, move.toColumn, new Queen(Player.BLACK));  // change piece at toRow/toColumn to a Queen
            }
        }
    }

    // inner class that represents action listener for buttons
    /******************************************************************
     * Private inner class to handle button clicks and logic associated to those buttons
     ******************************************************************/
    private class listener implements ActionListener {
        boolean moveMade = false;   // instantiate and set moveMade to false
        public void actionPerformed(ActionEvent event) {        // button clicked
            for (int r = 0; r < model.numRows(); r++) {         // scan rows
                for (int c = 0; c < model.numColumns(); c++) {  // scan columns
                    if (board[r][c] == event.getSource()) {     // button at r, c is source of click
                        if (firstTurnFlag) {    // first click of a potential move
                            if (board[r][c] != null && model.currentPlayer() == Player.WHITE) { // clicked on space occupied by a white player piece
                                fromRow = r;    // set fromRow to r
                                fromCol = c;    // set fromCol to c
                                firstTurnFlag = false;  // set firstTurnFlag to false

                                board[r][c].setBackground(new Color(74, 96, 140));  // change space's background color to indicate click
                            }
                        } else {    // second click of potential move
                            toRow = r;  // set toRow to r
                            toCol = c;  // set toCol to c
                            firstTurnFlag = true;   // set firstTurnFlag to true
                            setBackGroundColor(fromRow, fromCol);   // change space's background color back to what it was before first click
                            Move m = new Move(fromRow, fromCol, toRow, toCol);  // instantiate new move
                            // m is a valid move and piece at from belongs to the current player
                            if (model.isValidMove(m) && model.pieceAt(fromRow, fromCol).player() == model.currentPlayer()) {
                                undoMoves.add(new Move(toRow, toCol, fromRow, fromCol));    // add m to the undoMoves ArrayList
                                undoTakes.add(model.pieceAt(toRow, toCol)); // add the piece at to to the undoTakes ArrayList

                                if (model.currentPlayer() == Player.WHITE) {    // current player is WHITE
                                    model.move(m);      // move piece to m
                                    moveMade = true;    // set moveMade to true
                                }


                                displayBoard();     // show where current player moved to before checking for check

                                if (model.currentPlayer() == Player.WHITE) {    // current player is WHITE
                                    checkForStuff(m);    // checks for checkmate, check, and pawn swaps; prompts user appropriately
                                }


                                model.setNextPlayer();  // set current player to BLACK
                                Move AIMove = model.AI();   // instantiate Move to AI's move

                                // add AI's move to undoMoves ArrayList
                                undoMoves.add(new Move(AIMove.toRow, AIMove.toColumn, AIMove.fromRow, AIMove.fromColumn));
                                undoTakes.add(model.pieceAt(AIMove.toRow, AIMove.toColumn));    // add the piece at to to the undoTakes Arraylist
                                model.move(AIMove); // AI/BLACK makes its move
                                displayBoard();     // display proper label text
                                checkForStuff(AIMove);  // checks for checkmate, check, and pawn swaps; prompts user appropriately
                                model.setPlayerWhite(); // set current player to WHITE

                                break;  // get out of current nested for loop
                            }
                        }
                    }
                }

                if (moveMade) {     // a move was made
                    break;          // stop checking the rest of the spaces after finding button click source
                }
            }

            moveMade = false;       // set moveMade to false

            if (undoButton == event.getSource()) {  // undoButton clicked
                if (undoMoves.size() > 0) { // at least 1 move has been made
                    model.move(undoMoves.get(undoMoves.size() - 1));    // undo previous move

                    // set back a piece if it was taken
                    model.setPiece(undoMoves.get(undoMoves.size() - 1).fromRow, undoMoves.get(undoMoves.size() - 1).fromColumn, undoTakes.get(undoTakes.size() - 1));

                    if (model.currentPlayer() == Player.BLACK) {    // current player is BLACK
                        if (pawnSwaps.get(pawnSwaps.size() - 1)) {  // pawnSwap was made

                            // change changed piece back to a pawn
                            model.setPiece(6, undoMoves.get(undoMoves.size() - 1).fromColumn, new Pawn(Player.BLACK));
                        }
                        if (model.blackInCheck()) { // BLACK is in check

                            // notify user BLACK is in check
                            JOptionPane.showMessageDialog(null, "Player: Black, is in check.");
                        }

                    }
                    undoMoves.remove(undoMoves.size() - 1); // remove last element in undoMoves
                    undoTakes.remove(undoTakes.size() - 1); // remove last element in undoTakes
                    pawnSwaps.remove(pawnSwaps.size() - 1); // remove last element in pawnSwaps


                    model.move(undoMoves.get(undoMoves.size() - 1));    // undo previous move

                    // set back a piece if it was taken
                    model.setPiece(undoMoves.get(undoMoves.size() - 1).fromRow, undoMoves.get(undoMoves.size() - 1).fromColumn, undoTakes.get(undoTakes.size() - 1));

                    if (model.currentPlayer() == Player.WHITE) {    // current player is WHITE
                        if (pawnSwaps.get(pawnSwaps.size() - 1)) {  // pawnSwap was made

                            // set swapped piece back to a pawn
                            model.setPiece(1, undoMoves.get(undoMoves.size() - 1).fromColumn, new Pawn(Player.WHITE));
                        }
                        if (model.whiteInCheck()) { // WHITE is in check

                            // notify user WHITE is in check
                            JOptionPane.showMessageDialog(null, "Player: White, is in check.");
                        }

                    }

                    displayBoard(); // update display

                    undoMoves.remove(undoMoves.size() - 1); // remove last element in undoMoves
                    undoTakes.remove(undoTakes.size() - 1); // remove last element in undoTakes
                    pawnSwaps.remove(pawnSwaps.size() - 1); // remove last element in pawnSwaps

                }
            }
        }

        /******************************************************************
         * Helper method checks for pawns that make the back row and if
         * player is in check
         *
         * @param move  move that was just made
         ******************************************************************/
        private void checkForStuff(Move move) {
            if (board[move.toRow][move.toColumn] != null) {
                // check for pawn making it to top or bottom row
                String[] replacePieces = new String[]{"Rook", "Knight", "Bishop", "Queen"};    // options for user to swap pawn to

                // pawn made it to top or bottom
                if (model.pieceAt(move.toRow, move.toColumn).type().equals("Pawn") && (move.toRow == 0 || move.toRow == 7)) {
                    if (move.toRow == 0) {  // white pawn made it to top row

                        // get user response on what to change the pawn to
                        int response = JOptionPane.showOptionDialog(null, "What type of piece to replace this pawn?", "",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                                null, replacePieces, replacePieces[3]);
                        replacePawn(response, Player.WHITE, move);    // replace white pawn with selected piece
                    }
                    if (move.toRow == 7) {                       // black pawn made it to bottom
                        replacePawn(3, Player.BLACK, move);    // replace black pawn with random piece
                    }
                    displayBoard(); // update display


                    pawnSwaps.add(true);    // add true to pawnSwaps for undo purposes
                } else {                // no pawn on top/bottom row
                    pawnSwaps.add(false);   // add false to pawnSwaps for undo purposes
                }


                // check for check and checkmate
                if (model.isComplete()) {   // someone in checkmate
                    if (model.whiteInCheck() && model.currentPlayer() == Player.BLACK) {        // black put white in check
                        JOptionPane.showMessageDialog(null, "Checkmate!\nBLACK Wins!");
                    } else if (model.blackInCheck() && model.currentPlayer() == Player.WHITE) { // white put black in check
                        JOptionPane.showMessageDialog(null, "Checkmate!\nWHITE Wins!");
                    }
                } else {
                    if (model.whiteInCheck() && model.currentPlayer() == Player.BLACK) {        // black put white in check
                        JOptionPane.showMessageDialog(null, "Player: WHITE, is in check.");
                    } else if (model.blackInCheck() && model.currentPlayer() == Player.WHITE) { // white put black in check
                        JOptionPane.showMessageDialog(null, "Player: BLACK, is in check.");
                    } else if (model.whiteInCheck() && model.currentPlayer() == Player.WHITE) { // white put white in check
                        JOptionPane.showMessageDialog(null, "Player: WHITE, put itself in check.");
                        undoButton.doClick();
                    } else if (model.blackInCheck() && model.currentPlayer() == Player.BLACK) { // black put black in check
                        JOptionPane.showMessageDialog(null, "Player: BLACK, put itself in check.");

                        model.move(undoMoves.get(undoMoves.size() - 1));    // undo previous move

                        // set back a piece if it was taken
                        model.setPiece(undoMoves.get(undoMoves.size() - 1).fromRow, undoMoves.get(undoMoves.size() - 1).fromColumn, undoTakes.get(undoTakes.size() - 1));

                        if (model.currentPlayer() == Player.BLACK) {    // current player is BLACK
                            if (pawnSwaps.get(pawnSwaps.size() - 1)) {  // pawnSwap made

                                // set swapped piece back into a pawn
                                model.setPiece(6, undoMoves.get(undoMoves.size() - 1).fromColumn, new Pawn(Player.BLACK));
                            }
                            if (model.blackInCheck()) { // BLACK is in check

                                // notify user BLACK is in check
                                JOptionPane.showMessageDialog(null, "Player: Black, is in check.");
                            }

                        }
                        undoMoves.remove(undoMoves.size() - 1); // remove last element in undoMoves
                        undoTakes.remove(undoTakes.size() - 1); // remove last element in undoTakes
                        pawnSwaps.remove(pawnSwaps.size() - 1); // remove last element in pawnSwaps

                        Move AIMove = model.AI();   // instantiate new Move to AI's Move

                        // add AI's move to undoMoves
                        undoMoves.add(new Move(AIMove.toRow, AIMove.toColumn, AIMove.fromRow, AIMove.fromColumn));
                        undoTakes.add(model.pieceAt(AIMove.toRow, AIMove.toColumn));    // add to piece to undoTakes
                        model.move(AIMove); // move AI//BLACK piece
                        displayBoard();     // display proper label text
                        checkForStuff(AIMove);  // check for check and checkmate for BLACK
                        model.setPlayerWhite(); // change current player to WHITE
                    }
                }
            }
        }
    }
}
