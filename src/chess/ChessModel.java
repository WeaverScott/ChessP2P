package chess;

import javax.swing.JOptionPane;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**********************************************************************
 * A class that determines how the chess game functions, and gives the
 * blueprints to how the panel will work.
 *
 * @author Christian Thompson, James Weitzmanm, Josh Hubbard, 
 *         Lauren Vanderklok, & Scott Weaver
 *********************************************************************/
public class ChessModel implements IChessModel {

    /** the last move */
    private Move lastMove = null;

    /** the current move */
    private Move currentMove = null;

    /** The board */
    private IChessPiece[][] board;

    /** The current player */
    private Player player;

    private boolean firstTurn = true;

    private IChessPiece oldPiece;

    /*****************************************************************
     * A constructor that creates the chess model.
     *****************************************************************/
    public ChessModel(Player color) {
        //creates the board and sets the first player to white
        board = new IChessPiece[8][8];
        player = color;

        /** Set white pieces */
        board[7][0] = new Rook(Player.WHITE);
        board[7][1] = new Knight(Player.WHITE);
        board[7][2] = new Bishop(Player.WHITE);
        board[7][3] = new Queen(Player.WHITE);
        board[7][4] = new King(Player.WHITE);
        board[7][5] = new Bishop(Player.WHITE);
        board[7][6] = new Knight(Player.WHITE);
        board[7][7] = new Rook(Player.WHITE);
        board[6][0] = new Pawn(Player.WHITE);
        board[6][1] = new Pawn(Player.WHITE);
        board[6][2] = new Pawn(Player.WHITE);
        board[6][3] = new Pawn(Player.WHITE);
        board[6][4] = new Pawn(Player.WHITE);
        board[6][5] = new Pawn(Player.WHITE);
        board[6][6] = new Pawn(Player.WHITE);
        board[6][7] = new Pawn(Player.WHITE);

        /** Set black pieces */
        board[0][0] = new Rook(Player.BLACK);
        board[0][1] = new Knight(Player.BLACK);
        board[0][2] = new Bishop(Player.BLACK);
        board[0][3] = new Queen(Player.BLACK);
        board[0][4] = new King(Player.BLACK);
        board[0][5] = new Bishop(Player.BLACK);
        board[0][6] = new Knight(Player.BLACK);
        board[0][7] = new Rook(Player.BLACK);
        board[1][0] = new Pawn(Player.BLACK);
        board[1][1] = new Pawn(Player.BLACK);
        board[1][2] = new Pawn(Player.BLACK);
        board[1][3] = new Pawn(Player.BLACK);
        board[1][4] = new Pawn(Player.BLACK);
        board[1][5] = new Pawn(Player.BLACK);
        board[1][6] = new Pawn(Player.BLACK);
        board[1][7] = new Pawn(Player.BLACK);

    }

    public ChessModel(Player color, Boolean load) {
        //creates the board and sets the first player to white
        board = new IChessPiece[8][8];
        player = color;

        try {
            setLoadedBoard(loadBoard());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void removeFromBoard(int row, int col){
        board[row][col] = null;
    }

    /******************************************************************
     * A method that returns whether the game is complete or not.
     * @return true if complete, false if incomplete.
     *****************************************************************/
    public boolean isComplete() {
        boolean valid = false;
        IChessPiece oldPiece;
        if (inCheck(Player.WHITE)) {
            valid = true;
            for (int x = 0; x < numRows(); x++) {
                for (int y = 0; y < numColumns(); y++) {
                    if (board[x][y] != null && board[x][y].player()
                            == Player.WHITE) {
                        for (int r = 0; r < numRows(); r++) {
                            for (int c = 0; c < numColumns(); c++) {
                                Move m = new Move(x, y, r, c);
                                if (board[x][y].isValidMove(m, board))
                                {
                                    oldPiece = board[m.toRow]
                                            [m.toColumn];
                                    board[m.toRow][m.toColumn] = board
                                            [m.fromRow][m.fromColumn];
                                    board[m.fromRow][m.fromColumn] =
                                            null;
                                    if(!inCheck(Player.WHITE)) {
                                        board[m.fromRow][m.fromColumn]
                                        = board[m.toRow][m.toColumn];
                                        board[m.toRow][m.toColumn] =
                                                oldPiece;
                                        return false;
                                    }
                                    board[m.fromRow][m.fromColumn] =
                                            board[m.toRow][m.toColumn];
                                    board[m.toRow][m.toColumn] =
                                            oldPiece;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (inCheck(Player.BLACK)) {
            valid = true;
            for (int x = 0; x < numRows(); x++) {
                for (int y = 0; y < numColumns(); y++) {
                    if (board[x][y] != null && board[x][y].player() ==
                            Player.BLACK) {
                        for (int r = 0; r < numRows(); r++) {
                            for (int c = 0; c < numColumns(); c++) {
                                Move m = new Move(x, y, r, c);
                                if (board[x][y].isValidMove(m, board)){
                                    oldPiece = board[m.toRow]
                                            [m.toColumn];
                                    board[m.toRow][m.toColumn] = board
                                            [m.fromRow][m.fromColumn];
                                    board[m.fromRow][m.fromColumn] =
                                            null;
                                    if(!inCheck(Player.BLACK)) {
                                        board[m.fromRow][m.fromColumn]
                                                = board[m.toRow]
                                                [m.toColumn];
                                        board[m.toRow][m.toColumn] =
                                                oldPiece;
                                        return false;
                                    }
                                    board[m.fromRow][m.fromColumn] =
                                            board[m.toRow][m.toColumn];
                                    board[m.toRow][m.toColumn] =
                                            oldPiece;
                                }
                            }
                        }
                    }
                }
            }
        }
        return valid;
    }

    public void saveLastMove(Move m) {
        oldPiece = board[m.toRow][m.toColumn];
        board[m.toRow][m.toColumn] = board[m.fromRow][m.fromColumn];
        board[m.fromRow][m.fromColumn] = null;
    }

    public void undoLastMove(Move m) {
        board[m.fromRow][m.fromColumn] = board[m.toRow][m.toColumn];
        board[m.toRow][m.toColumn] = oldPiece;
    }

    /*****************************************************************
     * A method that decides whether the given move is valid.
     * @param move a {@link chess.Move} object describing the move to
     * be made.
     * @return the {@link chess.GameStatus} object with correct values.
     *****************************************************************/
    public GameStatus isValidMove(Move move) {
        GameStatus status = new GameStatus();

        //uses polymorphic isValidMove() from super class
        if (board[move.fromRow][move.fromColumn] != null && move
                != null)
            if (board[move.fromRow][move.fromColumn].player() ==
                    currentPlayer())
                if (board[move.fromRow][move.fromColumn].isValidMove
                        (move, board) || isEnPassant(currentMove,
                        lastMove)){
                    if (isEnPassant(currentMove, lastMove)) {
                        status.setMoveSuccessful(true);
                    }
                    //temporary pieces to test check conditions, same
                    // as parameter
                    IChessPiece toPiece = board[move.toRow]
                            [move.toColumn];

                    //asks if player is in check, changes condition
                    if(inCheck(player)) {
                        status.setInCheck(true);
                    }else if(!inCheck(player)){
                        status.setInCheck(false);
                    }
                    //temporary move to test inCheck conditions
                    Move m = new Move(move.fromRow, move.fromColumn,
                            move.toRow,
                            move.toColumn);
                    toPiece = board[m.toRow][m.toColumn];
                    board[m.toRow][m.toColumn] = board[m.fromRow]
                            [m.fromColumn];
                    board[m.fromRow][m.fromColumn] = null;
                    //method call to do move

                    //determines if the new location put the player
                    // in check
                    if(inCheck(currentPlayer())) {
                        status.setMovedIntoCheck(true);
                    }else{
                        status.setMoveSuccessful(true);
                    }

                    board[m.fromRow][m.fromColumn] = board[m.toRow]
                            [m.toColumn];
                    board[m.toRow][m.toColumn] = toPiece;

                }

        return status;
    }

    /******************************************************************
     * A method that creates the move for a piece.
     * @param move a {@link chess.Move} object describing the move to
     * be made.
     *****************************************************************/
    public void move(Move move) {
        board[move.toRow][move.toColumn] = board[move.fromRow][move.
                fromColumn];
        board[move.fromRow][move.fromColumn] = null;
        firstTurn = false;
    }

    /******************************************************************
     * A method that sets the current player.
     * @param p the player to be set to.
     *****************************************************************/
    public void setPlayer(Player p){
        player = p;
    }

    /*****************************************************************
     * A method that determines whether either king is in check.
     * @param  p {@link chess.Move} the Player being checked
     * @return true if player is in check, false if not
     *****************************************************************/
    public boolean inCheck(Player p) {
        boolean valid = false;
        int kingX = 0;
        int kingY = 0;
        //finds the position of the king
        for (int x = 0; x < numRows(); x++) {
            for (int y = 0; y < numColumns(); y++) {
                if (board[x][y] != null && board[x][y].type().equals
                        ("King") && board[x][y].player() == p) {
                    kingX = x;
                    kingY = y;
                    x = numColumns() - 1;
                    y = numRows() - 1;
                }
            }
        }

        //checks if the king is in check
        for (int x = 0; x < numRows(); x++) {
            for (int y = 0; y < numColumns(); y++) {
                if (board[x][y] != null && board[x][y].player() !=
                        board[kingX][kingY].player()) {
                    Move m = new Move(x, y, kingX, kingY);
                    if (board[x][y].isValidMove(m, board))
                        valid = true;
                }
            }
        }
        return valid;
    }

    /******************************************************************
     * A method that returns the current player.
     * @return the player.
     *****************************************************************/
    public Player currentPlayer() {
        return player;
    }

    /*****************************************************************
     * A method that returns the number of rows on the board.
     * @return the number of rows.
     *****************************************************************/
    public int numRows() {
        return 8;
    }

    /*****************************************************************
     * A method that returns the number of columns on the board.
     * @return the number of columns.
     *****************************************************************/
    public int numColumns() {
        return 8;
    }

    /*****************************************************************
     * A method that returns the type of piece at board[row][col].
     * @return the piece there.
     *****************************************************************/
    public IChessPiece pieceAt(int row, int column) {
        return board[row][column];
    }

    /*****************************************************************
     * A method that switches the player (turn).
     *****************************************************************/
    public void setNextPlayer() {
        player = player.next();
    }

    /******************************************************************
     * A method that sets the square to the specified piece.
     * @param row square row coordinate
     * @param column square column coordinate
     * @param piece type of piece to set square to
     *****************************************************************/
    public void setPiece(int row, int column, IChessPiece piece) {
        board[row][column] = piece;
    }

    public void saveGame(){
        try {

            String filename = "SavedGame.txt";
            File file = new File(filename);

            PrintWriter write = new PrintWriter(file);

            String buffer = "";

            char team = 'w';

            for (int x = 0; x < numRows(); x++) {
                for (int y = 0; y < numColumns(); y++) {
                    if (pieceAt(x, y) != null) {
                        if (pieceAt(x, y).player().equals(Player.BLACK)) {
                            team = 'b';
                        } else  {
                            team = 'w';
                        }
                    }
                    if (pieceAt(x, y) == null) {
                        buffer = buffer.concat("0" + x + y + team);
                        buffer = buffer.concat(" ");
                    } else if (pieceAt(x, y).type().equals("Pawn")) {
                        buffer = buffer.concat("p" + x + y + team);
                        buffer = buffer.concat(" ");

                    } else if (pieceAt(x, y).type().equals("Rook")) {
                        buffer = buffer.concat("r" + x + y + team);
                        buffer = buffer.concat(" ");

                    } else if (pieceAt(x, y).type().equals("Knight")) {
                        buffer = buffer.concat("n" + x + y + team);
                        buffer = buffer.concat(" ");

                    } else if (pieceAt(x, y).type().equals("Bishop")) {
                        buffer = buffer.concat("b" + x + y + team);
                        buffer = buffer.concat(" ");

                    } else if (pieceAt(x, y).type().equals("Queen")) {
                        buffer = buffer.concat("q" + x + y + team);
                        buffer = buffer.concat(" ");

                    } else if (pieceAt(x, y).type().equals("King")) {
                        buffer = buffer.concat("k" + x + y + team);
                        buffer = buffer.concat(" ");
                    }
                }
            }

            write.print(buffer);
            write.close();
        }catch (Exception e){
            System.err.println(e);
        }
    }


    public ArrayList<String> loadBoard() throws IOException {
        BufferedReader reader;
        String piece;
        ArrayList<String> listOfPieces = new ArrayList<String>();

            reader = new BufferedReader(new FileReader("SavedGame.txt"));
            String line = reader.readLine();
            StringTokenizer tokens = new StringTokenizer(line);
            while (tokens.hasMoreTokens()) {
                listOfPieces.add(tokens.nextToken(" "));
            }
            return listOfPieces;
    }

    public void setLoadedBoard(ArrayList <String> loadedBoard){
        for (int i = 0; i < loadedBoard.size(); i++) {
            int x = Character.getNumericValue(loadedBoard.get(i).charAt(1));
            int y = Character.getNumericValue(loadedBoard.get(i).charAt(2));
            char team = loadedBoard.get(i).charAt(3);
            if (loadedBoard.get(i).charAt(0) == 'p') {
                if (team == 'b') {
                    setPiece(x, y, new Pawn(Player.BLACK));
                }
                else {
                    setPiece(x, y, new Pawn(Player.WHITE));
                }
            }
            if (loadedBoard.get(i).charAt(0) == 'r') {
                if (team == 'b') {
                    setPiece(x, y, new Rook(Player.BLACK));
                }
                else {
                    setPiece(x, y, new Rook(Player.WHITE));
                }
            }
            if (loadedBoard.get(i).charAt(0) == 'n') {
                if (team == 'b') {
                    setPiece(x, y, new Knight(Player.BLACK));
                }
                else {
                    setPiece(x, y, new Knight(Player.WHITE));
                }
            }
            if (loadedBoard.get(i).charAt(0) == 'b') {
                if (team == 'b') {
                    setPiece(x, y, new Bishop(Player.BLACK));
                }
                else {
                    setPiece(x, y, new Bishop(Player.WHITE));
                }
            }
            if (loadedBoard.get(i).charAt(0) == 'q') {
                if (team == 'b') {
                    setPiece(x, y, new Queen(Player.BLACK));
                }
                else {
                    setPiece(x, y, new Queen(Player.WHITE));
                }
            }
            if (loadedBoard.get(i).charAt(0) == 'k') {
                if (team == 'b') {
                    setPiece(x, y, new King(Player.BLACK));
                }
                else {
                    setPiece(x, y, new King(Player.WHITE));
                }
            }
            if (loadedBoard.get(i).charAt(0) == '0') {
                setPiece(x, y, null);
            }
        }
    }



    /******************************************************************
     * A method that promotes pawns that achieve the opposing player's
     * back row.
     * @param move the move (hopefully to the back row).
     *****************************************************************/
    public int pawnPromoted(Move move) {
        if(board[move.toRow][move.toColumn] != null) {
            if (board[move.toRow][move.toColumn].type().equals("Pawn")
                    &&
                    (move.toRow == 0 || move.toRow == 7)) {
                String[] promotion = {"Queen", "Knight",
                        "Rook", "Bishop"};
                int pick = JOptionPane.showOptionDialog(null,
                        "Pick which"
                        + " piece you would like to promote to: ",
                        "", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, promotion,
                        promotion[0]);
                if (pick == 0) {
                board[move.toRow][move.toColumn] = new Queen(player);
                }
                if (pick == 1) {
                board[move.toRow][move.toColumn] = new Knight(player);
                }
                if (pick == 2) {
                board[move.toRow][move.toColumn] = new Rook(player);
                }
                if (pick == 3) {
                board[move.toRow][move.toColumn] = new Bishop(player);
                }
                return pick;
            }
        }
        return 5;
    }

    public void pawnPromoted(Move move, String promotion) {
        if(board[move.toRow][move.toColumn] != null) {
            if (board[move.toRow][move.toColumn].type().equals("Pawn")
                    &&
                    (move.toRow == 0 || move.toRow == 7)) {

                if (promotion.equals("queen")) {
                    board[move.toRow][move.toColumn] = new Queen(player);
                }
                if (promotion.equals("knight")) {
                    board[move.toRow][move.toColumn] = new Knight(player);
                }
                if (promotion.equals("rook")) {
                    board[move.toRow][move.toColumn] = new Rook(player);
                }
                if (promotion.equals("bishop")) {
                    board[move.toRow][move.toColumn] = new Bishop(player);
                }

            }
        }
    }

    /******************************************************************
     * A method that handles the castling maneuver with rook and king.
     * @param move the move being made toward castling.
     *****************************************************************/
    public void rookCastling (Move move) {
        if (board[move.toRow][move.toColumn] != null) {
            if (board[move.toRow][move.toColumn].type().equals("King")
                    &&
                    Math.abs(move.fromColumn - move.toColumn) == 2) {
                if (move.toColumn < move.fromColumn) {
                    board[move.fromRow][move.fromColumn - 1] =
                            board[move.fromRow][move.fromColumn - 4];
                    board[move.fromRow][move.fromColumn - 4] = null;
                } else {
                    board[move.fromRow][move.fromColumn + 1] =
                            board[move.fromRow][move.fromColumn + 3];
                    board[move.fromRow][move.fromColumn + 3] = null;
                }
            }
        }
    }

    // returns boolean value. true if the spot (row, col) can be taken
    // by any white piece
    public boolean isDangerous(int row, int col){
        for (int r = 0; r < numRows(); r++){
            for (int c = 0; c < numColumns(); c++){
                if (board[r][c] != null){
                    if (board[r][c].player() == Player.WHITE){
                        Move m = new Move(r, c, row, col);
                        if (this.isValidMove(m).isMoveSuccessful()){
                            return true;
                        }
                    }

                }
            }
        }
        return false;
    }

    /******************************************************************
     * A method that sets the player's last move
     * @param lastMove the Last move the player did
     *****************************************************************/
    public void setLastMove(Move lastMove) {
        this.lastMove = lastMove;
    }

    /******************************************************************
     * A method that sets the player's current move
     * @param currentMove the current move the player's trying
     *****************************************************************/
    public void setCurrentMove(Move currentMove) {
        this.currentMove = currentMove;
    }

    /******************************************************************
     * A method that sets the square to the specified piece.
     * @param recent the recent move
     * @param last the last move
     * @returns true or false depending on whether or not it is an
     * EnPassant
     *****************************************************************/
    public boolean isEnPassant(Move recent, Move last) {
        boolean valid = false;
        if (last != null && recent != null) {
            if (board[last.toRow][last.toColumn] != null) {
                if (board[recent.fromRow][recent.fromColumn] != null) {
                    if (board[recent.fromRow][recent.fromColumn].type()
                            .equals("Pawn")) {
                        if (board[last.toRow][last.toColumn].type().
                                equals("Pawn")) {
                            if (board[last.toRow][last.toColumn].
                                    player() == Player.BLACK) {
                                if (board[recent.fromRow]
                                        [recent.fromColumn].player()
                                        == Player.WHITE) {
                                    if (last.toRow - last.fromRow == 2
                                            && last.fromColumn ==
                                            last.toColumn) {
                                        if (Math.abs(recent.toColumn -
                                                recent.fromColumn) == 1
                                                && recent.toRow -
                                                recent.fromRow == -1) {
                                            if (last.toColumn == recent
                                                    .toColumn && recent
                                                    .toRow - last.toRow
                                                    == -1) {
                                                valid = true;
                                            }
                                        }
                                    }

                                }
                            } else if (board[last.toRow][last.toColumn]
                                    .player() == Player.WHITE) {
                                if (board[recent.fromRow]
                                        [recent.fromColumn].player() ==
                                        Player.BLACK) {
                                    if (last.toRow - last.fromRow == -2
                                            && last.fromColumn ==
                                            last.toColumn) {
                                        if (Math.abs(recent.toColumn -
                                                recent.fromColumn) == 1
                                                && recent.toRow -
                                                recent.fromRow == 1) {
                                            if (last.toColumn == recent
                                                    .toColumn &&
                                                    recent.toRow -
                                                    last.toRow == 1) {
                                                valid = true;
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
        return valid;
    }

    /******************************************************************
     * A method that returns the current move.
     *
     * @Returns the current move
     *****************************************************************/
    public Move getCurrentMove() {
        return currentMove;
    }

    /******************************************************************
     * A method that returns the last move.
     *
     * @Returns the last move.
     *****************************************************************/
    public Move getLastMove() {
        return lastMove;
    }
}

//end of class
