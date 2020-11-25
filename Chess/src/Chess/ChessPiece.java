package Chess;

/**
 *	Abstract class to represent any type of ChessPiece
 *	Implements IChessPiece interface
 *  Used in Bishop, King, Knight, Pawn, Queen, and Rook classes
 */

public abstract class ChessPiece implements IChessPiece {

	private Player owner;

	/******************************************************************
	 * Default constructor assigns player to piece
	 ******************************************************************/
	protected ChessPiece(Player player) {
		this.owner = player;
	}

	/******************************************************************
	 * Method returns String type
	 ******************************************************************/
	public abstract String type();

	public Player player() {
		return owner;	// returns the player of piece
	}

	/******************************************************************
	 * Method returns true or false depending on if the move is valid
	 *
	 * @param move		// move to test
	 * @param board		// current board to test move on
	 * @return true if valid or false if invalid
	 ******************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		boolean valid = false;	// instantiate and set valid to false

		if (move.fromRow == move.toRow && move.fromColumn == move.toColumn) {	// tried to move to same spot

			return false; // return false
		}

		if (board[move.toRow][move.toColumn] != null) {		// space moved to is occupied
			if (board[move.toRow][move.toColumn].player() == owner) {	// space occupied by same player
				valid = false;	// set valid to false
			}
		}

		if (board[move.fromRow][move.fromColumn] != null) {	// space where piece originally at was null
			if (board[move.fromRow][move.fromColumn].isValidMove(move, board)) {	// move is valid
				valid = true;	// set valid to true
			}
		}

		return valid;	// return value of valid
	}
}
