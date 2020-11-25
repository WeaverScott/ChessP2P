package Chess;

/**
 * King chess piece utilizing the ChessPiece class, which implements the IChessPiece interface
 *
 * Sets the player, type, and sets what a valid move for this piece is
 */

public class King extends ChessPiece {

	/******************************************************************
	 * Default constructor assigns player to king piece
	 ******************************************************************/
	public King(Player player) {
		super(player);
	}

	/******************************************************************
	 * Method returns String type of "King"
	 *
	 * @return "King"
	 ******************************************************************/
	public String type() {

		return "King";	// set type of piece to King
	}

	/******************************************************************
	 * Method determines if the move is valid for a king piece and returns
	 * true or false depending on if the move is valid
	 *
	 * @param move		move to test
	 * @param board		current board to test piece on
	 * @return true if valid move or false if invalid move
	 ******************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = false;		// instantiate and set valid to false

		// to space is either empty or occupied by a piece of the other player
		if (board[move.toRow][move.toColumn] == null || board[move.toRow][move.toColumn].player() == player().next()) {

			// to space is 1 space in any direction surrounding this piece
			if ((move.toRow == (move.fromRow - 1)) || (move.toRow == (move.fromRow + 1)) ||
					(move.toColumn == (move.fromColumn - 1)) || (move.toColumn == (move.fromColumn + 1))) {
				valid = true;	// set valid to true

				// piece tried to move 2 or more spaces in any direction
				if ((move.toRow - move.fromRow) > 1 || (move.toRow - move.fromRow) < -1 ||
						(move.toColumn - move.fromColumn) > 1 || (move.toColumn - move.fromColumn) < -1) {
					valid = false;	// set valid to false
				}
			}
		}
		return valid;	// return value of valid
	}
}
