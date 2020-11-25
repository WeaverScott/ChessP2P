package Chess;

/**
 * Knight chess piece utilizing the ChessPiece class, which implements the IChessPiece interface
 *
 * Sets the player, type, and sets what a valid move for this piece is
 */

public class Knight extends ChessPiece {

	/******************************************************************
	 * Default constructor assigns player to king piece
	 ******************************************************************/
	public Knight(Player player) {
		super(player);
	}

	/******************************************************************
	 * Method returns String type of "Knight"
	 *
	 * @return "Knight"
	 ******************************************************************/
	public String type() {
		return "Knight";	// set piece type to Knight
	}

	/******************************************************************
	 * Method determines if the move is valid for a knight piece and returns
	 * true or false depending on if the move is valid
	 *
	 * @param move		move to test
	 * @param board		current board to test move on
	 * @return true if valid move or false if invalid move
	 ******************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board){

		boolean valid = false;	// instantiate and set valid to false

		// Move's toRow is +/- 2 and toColumn is +/- 1 or toRow is +/- 1 and toColumn is +/- 2 from its from row/column
		if (((Math.abs(move.toColumn - move.fromColumn) == 2) && (Math.abs(move.toRow - move.fromRow) == 1)) ||
				((Math.abs(move.toColumn - move.fromColumn) == 1) && (Math.abs(move.toRow - move.fromRow) == 2))) {

			// move to space is either empty or occupied by a piece of the other player
			if (board[move.toRow][move.toColumn] == null || (board[move.toRow][move.toColumn].player() != board[move.fromRow][move.fromColumn].player())) {
				valid = true;	// set valid to true
			}

		}
		return valid;	// return value of valid
	}
}
