package Chess;

import java.util.ArrayList;
import java.util.Random;

/**
 * Set up the board and handles all game logic.
 * Implements IChessModel
 */

public class ChessModel implements IChessModel {
	private IChessPiece[][] board;		// board to use in game
	private IChessPiece[][] boardCopy;	// copy of board
	private Player player;	// current player

	private int bKingRLoc;	// row black king is on
	private int bKingCLoc;	// column black king is on
	private int wKingRLoc;	// row white king is on
	private int wKingCLoc;	// column white king is on

	/******************************************************************
	 * Default constructor sets up white and black sides of the chess board
	 ******************************************************************/
	public ChessModel() {
		board = new IChessPiece[8][8];		// instantiate new 8x8 board of IChessPiece
		boardCopy = new IChessPiece[8][8];	// instantiate new 8x8 board of IChessPiece
		player = Player.WHITE;	// set current player to WHITE

		// place white pieces on initial board
		board[7][0] = new Rook(Player.WHITE);
		board[7][1] = new Knight(Player.WHITE);
		board[7][2] = new Bishop(Player.WHITE);
		board[7][3] = new Queen(Player.WHITE);
		board[7][4] = new King(Player.WHITE);
		board[7][5] = new Bishop(Player.WHITE);
		board[7][6] = new Knight (Player.WHITE);
		board[7][7] = new Rook(Player.WHITE);
		board[6][0] = new Pawn(Player.WHITE);
		board[6][1] = new Pawn(Player.WHITE);
		board[6][2] = new Pawn(Player.WHITE);
		board[6][3] = new Pawn(Player.WHITE);
		board[6][4] = new Pawn(Player.WHITE);
		board[6][5] = new Pawn(Player.WHITE);
		board[6][6] = new Pawn(Player.WHITE);
		board[6][7] = new Pawn(Player.WHITE);

		// place black pieces on initial board
		board[0][0] = new Rook(Player.BLACK);
		board[0][1] = new Knight(Player.BLACK);
		board[0][2] = new Bishop(Player.BLACK);
		board[0][4] = new Queen(Player.BLACK);
		board[0][3] = new King(Player.BLACK);
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

	/******************************************************************
	 * Method returns if move is valid
	 *
	 * @param move	move to test
	 * @return true or false
	 ******************************************************************/
	public boolean isValidMove(Move move) {
		boolean valid = false;	// instantiate and set valid to false


		if (board[move.fromRow][move.fromColumn] != null) {	// from space is occupied
			if (move.fromRow == move.toRow && move.fromColumn == move.toColumn) {	// tried to move to same spot
				return false;	// return false
			}
			if (board[move.toRow][move.toColumn] != null) {		// space moved to is occupied
				if (board[move.toRow][move.toColumn].player() == currentPlayer()) {	// space occupied by same player
					valid = false;	// set valid to false
				}
				if (board[move.fromRow][move.fromColumn].isValidMove(move, board)) {	// move is valid
					return true;	// return true
				}
			} else if (board[move.fromRow][move.fromColumn].isValidMove(move, board)) {	// move is to unoccupied space or space occupied by other player
				valid = true;	// set valid to true
			}
		}

		return valid;	// return value of valid
	}

	/******************************************************************
	 * Method moves chess pieces
	 *
	 * @param move	move to test
	 ******************************************************************/
	public void move(Move move) {
		// from space is occupied by a King
		if (board[move.fromRow][move.fromColumn] != null && pieceAt(move.fromRow, move.fromColumn).type().equals("King")) {
			if (pieceAt(move.fromRow, move.fromColumn).player() == Player.BLACK) {	// piece is BLACK king
				bKingRLoc = move.toRow;		// set bKingRLoc to toRow
				bKingCLoc = move.toColumn;	// set bKingCLoc to toColumn
			} else if (pieceAt(move.fromRow, move.fromColumn).player() == Player.WHITE) {	// piece is WHITE king
				wKingRLoc = move.toRow;		// set wKingRLoc to toRow
				wKingCLoc = move.toColumn;	// set wKingCLoc to toColumn
			}

		}
		board[move.toRow][move.toColumn] =  board[move.fromRow][move.fromColumn];	// set to space to what is at from space
		board[move.fromRow][move.fromColumn] = null;	// set from space to null
	}

	/******************************************************************
	 * Method returns true or false depending on if a player is in check
	 *
	 * @param p		player to check if in check
	 * @return true if player in check or false if not
	 ******************************************************************/
	public boolean inCheck(Player p) {		// whiteInCheck() and blackInCheck() used instead
		boolean valid = false;	// instantiate and set valid to false

		if (whiteInCheck()) {			// WHITE's king is in check
			valid = true;				// set valid to true
		} else if (blackInCheck()) {	// BLACK's king is in check
			valid = true;				// set valid to true
		}

		return valid;	// return value of valid
	}

	/******************************************************************
	 * Method marks the row and column that the White King is located
	 * on the board
	 *
	 * @param wR row
	 * @param wC column
	 ******************************************************************/
	public void setWhiteKingLocs(int wR, int wC) {
		wKingRLoc = wR;	// set wKingRLoc to wR
		wKingCLoc = wC;	// set wKingCLoc to wC
	}

	/******************************************************************
	 * Method marks the row and column that the Black King is located
	 * on the board
	 *
	 * @param bR row
	 * @param bC column
	 ******************************************************************/
	public void setBlackKingLocs(int bR, int bC) {
		bKingRLoc = bR;	// set bKingRLoc to bR
		bKingCLoc = bC;	// set bKingCLoc to bC
	}

	/******************************************************************
	 * Method checks to see if the game is complete depending on if
	 * black or white has been checkmated.
	 *
	 * @return true or false
	 ******************************************************************/
	public boolean isComplete() {	// checks for checkmate
		boolean valid = false;

		setCopyBoard();	// make copy of board

		if (blackInCheck()) {			// black king in check
			if (blackStillInCheck()) {	// no moves can be made to take king out of check
				valid = true;	// set valid to true
			}
		} else if (whiteInCheck()) {	// black king in check
			if (whiteStillInCheck()) {	// no moves can be made to take king out of check
				valid = true;	// set valid to true
			}
		}

		copyBoard();	// set board back to what it was before checking for checkmate

		return valid;	// return value of valid
	}

	/******************************************************************
	 * Helper method sets the board back to what it was before checkmate check
	 ******************************************************************/
	private void copyBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				setPiece(i, j, boardCopy[i][j]);	// set board back to what it was before checkmate check
			}
		}
	}

	/******************************************************************
	 * Helper method makes a copy of the current board
	 * Used to reset the current board back to what it was before checking
	 * 	for valid moves
	 ******************************************************************/
	private void setCopyBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				boardCopy[i][j] = board[i][j];		// make copy of current board
			}
		}
	}

	/******************************************************************
	 * Helper method checks to see if white king can get out of check,
	 * returns true unless white is no longer in check; in which case,
	 * it returns false.
	 *
	 * @return true or false
	 ******************************************************************/
	private boolean whiteStillInCheck () {		// checks to see if there is a way to get white king can get out of check
		boolean valid = true;	// true if white is still in check, false if not
		Move mTo;		// holds coordinates for piece to move to
		ArrayList<Move> checkMoves = new ArrayList<>();	// ArrayList to hold possible moves to get white king out of check

		for (int i = 0; i < 8; i++) {		// cycle rows to find white piece
			for (int j = 0; j < 8; j++) {	// cycle columns to find white piece
				if (board[i][j] != null && pieceAt(i,j).player() == Player.WHITE) {		// found white piece at i, j
					for (int x = 0; x < 8; x++) {		// cycle rows to find valid spot for found piece to move
						for (int y = 0; y < 8; y++) {	// cycle columns to find valid spot for found piece to move

							mTo = new Move(i, j, x, y);		// set mTo to move piece at i, j to x, y

							if (isValidMove(mTo)) {		// valid move found
								checkMoves.add(new Move(i, j, x, y));	// add move to checkMoves ArrayList
							}
						}
					}
				}
			}
		}

		for (Move m : checkMoves) {	// scan moves in checkMoves
			move(m);	// move current Move set to m
			if (whiteInCheck()) {	// white is in check
				move(new Move(m.toRow, m.toColumn, m.fromRow, m.fromColumn));	// reverse move(m)
				valid = true;	// set valid to true
			} else {	// white not in check
				move(new Move(m.toRow, m.toColumn, m.fromRow, m.fromColumn));	// reverse move(m)
				valid = false;	// set valid to false
				break;	// break out of for loop
			}

		}

		return valid;		// return valid (should always be true if it makes it here)
	}

	/******************************************************************
	 * Helper method checks to see if black king can get out of check,
	 * returns true unless white is no longer in check; in which case,
	 * it returns false.
	 *
	 * @return true or false
	 ******************************************************************/
	private boolean blackStillInCheck () {		// checks to see if there is a way to get black king out of check
		boolean valid = true;	// true if black is still in check, false if not
		Move mTo;		// holds coordinates for piece to move to
		ArrayList<Move> checkMoves = new ArrayList<>();

		for (int i = 0; i < 8; i++) {		// cycle rows to find black piece
			for (int j = 0; j < 8; j++) {	// cycle columns to find black piece
				if (board[i][j] != null && pieceAt(i,j).player() == Player.BLACK) {		// found black piece at i, j
					for (int x = 0; x < 8; x++) {		// cycle rows to find valid spot for found piece to move
						for (int y = 0; y < 8; y++) {	// cycle columns to find valid spot for found piece to move

							mTo = new Move(i, j, x, y);		// set mTo to move piece at i, j to x, y

							if (isValidMove(mTo)) {		// valid move found
								checkMoves.add(new Move(i, j, x, y));	// add Move to checkMoves ArrayList
							}
						}
					}
				}
			}
		}

		for (Move m : checkMoves) {	// scan Moves in checkMoves
			move(m);	// move current Move set to m
			if (blackInCheck()) {	// black is in check
				move(new Move(m.toRow, m.toColumn, m.fromRow, m.fromColumn));	// reverse move of m
				valid = true;	// set valid to true
			} else {	// black not in check
				move(new Move(m.toRow, m.toColumn, m.fromRow, m.fromColumn));	// reverse move of m
				valid = false;	// set valid to false
				break;	// break out of for loop
			}

		}

		return valid;		// return valid (should always be true if it makes it here)
	}

	/******************************************************************
	 * Method that checks to see if white king is in check,
	 * returns true unless white is not in check; in which case,
	 * it returns false.
	 *
	 * @return true or false
	 ******************************************************************/
	public boolean whiteInCheck() {		// checks to see if white king is in check
		boolean valid = false;	// true if white in check, false if not

		for (int i = 0; i < 8; i++) {		// cycle rows to find a black piece
			for (int j = 0; j < 8; j++) {	// cycle columns to find a black piece
				if (board[i][j] != null) {	// i, j is occupied
					if (pieceAt(i, j).player() == Player.BLACK) {	// black piece found at i, j
						if (isValidMove(new Move(i, j, wKingRLoc, wKingCLoc))) { // found black piece has a valid move to white king
							valid = true;	// set valid to true
							break;	// break out of for loops
						}
					}
				}
			}
		}

		return valid;	// return valid
	}

	/******************************************************************
	 * Method that checks to see if black king is in check,
	 * returns true unless white is not in check; in which case,
	 * it returns false.
	 *
	 * @return true or false
	 ******************************************************************/
	public boolean blackInCheck() {		// checks to see if black king is in check
		boolean valid = false;	// true if black in check, false if not

		for (int i = 0; i < 8; i++) {		// cycle rows to find a white piece
			for (int j = 0; j < 8; j++) {	// cycle columns to find a white piece
				if (board[i][j] != null) {	// i, j is occupied
					if (pieceAt(i, j).player() == Player.WHITE) {	// white piece found at i, j
						if (isValidMove(new Move(i, j, bKingRLoc, bKingCLoc))) { // found black piece has a valid move to white king
							valid = true;	// set valid to true
							break;	// break out of for loops
						}
					}
				}
			}
		}

		return valid;	// return valid
	}

	/******************************************************************
	 * Method returns the current player
	 *
	 * @return player black or white
	 ******************************************************************/
	public Player currentPlayer() {

		return player;	// return current player
	}

	/******************************************************************
	 * Method that sets the current player to WHITE
	 ******************************************************************/
	public void setPlayerWhite() {
		player = Player.WHITE;	// set current player to WHITE
	}

	/******************************************************************
	 * Method returns the number of rows for the board, set to 8
	 *
	 * @return 8
	 ******************************************************************/
	public int numRows() {
		return 8;	// return number of rows in board
	}

	/******************************************************************
	 * Method returns the number of columns for the board, set to 8
	 *
	 * @return 8
	 ******************************************************************/
	public int numColumns() {
		return 8;	// return number of columns in board
	}

	/******************************************************************
	 * Method returns chess piece at parameter row and column
	 *
	 * @param row		row to check
	 * @param column    column to check
	 * @return chess piece at row/column
	 ******************************************************************/
	public IChessPiece pieceAt(int row, int column) {

		return board[row][column];	// return piece occupying space at row/column
	}

	/******************************************************************
	 * Method sets the current player to WHITE if current is BLACK or
	 * 	BLACK if current player is WHITE
	 ******************************************************************/
	public void setNextPlayer() {
		player = player.next();	// set current player to the next player
	}

	/******************************************************************
	 * Method sets piece on the board at row, column
	 *
	 * @param row		row to place piece
	 * @param column	column to place piece
	 * @param piece		piece to place at row/column
	 ******************************************************************/
	public void setPiece(int row, int column, IChessPiece piece) {
		board[row][column] = piece;	// set piece at designated row/column
	}

	/******************************************************************
	 * Method returns the next move for the AI to make
	 *
	 * @return Move for AI to make
	 ******************************************************************/
	public Move AI() {
		/*
		 * Write a simple AI set of rules in the following order.
		 * a. Check to see if you are in check.
		 * 		i. If so, get out of check by moving the king or placing a piece to block the check
		 *
		 * b. Attempt to put opponent into check (or checkmate).
		 * 		i. Attempt to put opponent into check without losing your piece
		 *		ii. Perhaps you have won the game.
		 *
		 *c. Determine if any of your pieces are in danger,
		 *		i. Move them if you can.
		 *		ii. Attempt to protect that piece.
		 *
		 *d. Move a piece (pawns first) forward toward opponent king
		 *		i. check to see if that piece is in danger of being removed, if so, move a different piece.
		 */

		Move mTo = new Move();			// Move to use to test for valid moves
		Move moveToMake = new Move();	// Move that will be returned

		ArrayList<Move> movesForAI = new ArrayList<Move>();	// ArrayList to keep track of valid Moves AI could make

		setCopyBoard();	// make a copy of the current board
		if (!isComplete()) {		// black not in checkmate
			if (blackInCheck()) {   // black in check
				for (int r = 0; r < 8; r++) {        // cycle rows looking for black piece
					for (int c = 0; c < 8; c++) {    // cycle columns looking for black piece
						if (board[r][c] != null && board[r][c].player() == Player.BLACK) {    // found black piece
							for (int i = 0; i < 8; i++) {        // cycle rows for valid move
								for (int j = 0; j < 8; j++) {    // cycle columns for valid move
									if (isValidMove(new Move(r, c, i, j))) {    // found valid move
										mTo = new Move(r, c, i, j);        // set mTo to valid move

										move(mTo);    // move found piece to valid move
										if (!blackInCheck()) {    // valid move takes black out of check
											copyBoard();	// set board to what it was
											if (pieceAt(r, c).type().equals("King")) {	// piece at r, c is a King
												// set moveToMake to mTo and add to ArrayList
												moveToMake = new Move(mTo.fromRow, mTo.fromColumn, mTo.toRow, mTo.toColumn);
											} else if (pieceAt(r, c).type().equals("Queen")) {	// piece at r, c is a Queen
												// set moveToMake to mTo and add to ArrayList
												moveToMake = new Move(mTo.fromRow, mTo.fromColumn, mTo.toRow, mTo.toColumn);
											} else {	// piece at r, c any black piece other than a King or Queen
												copyBoard();
												return new Move(mTo.fromRow, mTo.fromColumn, mTo.toRow, mTo.toColumn);    // set moveToMake to mTo
											}
										}
										move(new Move(i, j, r, c)); // reverse mTo move
									}
								}
							}
						}
					}
				}
			} else {    // black not in check
				for (int r = 0; r < 8; r++) {        // cycle rows looking for black piece
					for (int c = 0; c < 8; c++) {    // cycle columns looking for black piece
						if (board[r][c] != null && board[r][c].player() == Player.BLACK) {    // found black piece
							for (int i = 0; i < 8; i++) {        // cycle rows for valid move
								for (int j = 0; j < 8; j++) {    // cycle columns for valid move
									mTo = new Move(r, c, i, j);        // set mTo to valid move
									if (isValidMove(mTo)) {	// mTo is a valid move that piece at r, c can make
										move(mTo);    // move found piece at r, c to i, j
										if (whiteInCheck()) {	// mTo put white in check
											copyBoard();	// set board to what it was
											return new Move(mTo.fromRow, mTo.fromColumn, mTo.toRow, mTo.toColumn);	// return Move that put white in check
										} else {	// mTo didn't put white in check
											copyBoard();	// set board to what it was
											if (board[i][j] != null && pieceAt(mTo.toRow, mTo.toColumn).player() == Player.WHITE) {	// black can take a white piece
												move(mTo);	// move piece at r, c to i, j
												if (!blackInCheck()) {	// mTo didn't make black put itself in check
													copyBoard();	// set board to what it was
													return new Move(mTo.fromRow, mTo.fromColumn, mTo.toRow, mTo.toColumn);	// return move that takes a white piece
												}
											}
											copyBoard();	// set board to what it was
											movesForAI.add(new Move(mTo.fromRow, mTo.fromColumn, mTo.toRow, mTo.toColumn));	// add mTo to an ArrayList
										}
									}
								}
							}
						}
					}
				}
			}
		}

		Random rand = new Random();		// instantiate Random
		if (movesForAI.size() > 0) {	// movesForAI ArrayList is populated
			int index = rand.nextInt(movesForAI.size());	// set index to random number from 0 to movesForAI.size() - 1

			// set moveToMake to random Move in movesForAI ArrayList
			moveToMake = new Move(movesForAI.get(index).fromRow, movesForAI.get(index).fromColumn, movesForAI.get(index).toRow, movesForAI.get(index).toColumn);
		}

		copyBoard();	// set board to what it was before checking for AI move
		return moveToMake;	// return moveToMake to tell what move black should make
	}
}
