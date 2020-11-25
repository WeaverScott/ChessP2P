package Chess;

/**
 * Pawn chess piece utilizing the ChessPiece class, which implements the IChessPiece interface
 *
 * Sets the player, type, and sets what a valid move for this piece is
 */

public class Pawn extends ChessPiece {

	/******************************************************************
	 * Default constructor assigns player to pawn piece
	 *
	 * @param player	Player to assign to pawn
	 ******************************************************************/
	public Pawn(Player player) {

		super(player);

	}

	/******************************************************************
	 * Method returns String type of "Pawn"
	 *
	 * @return "Pawn"
	 ******************************************************************/
	public String type() {
		return "Pawn";	// sets piece type to Pawn
	}

	/******************************************************************
	 * Method determines if the move is valid for a pawn piece and returns
	 * true or false depending on if the move is valid
	 *
	 * @param move		move to test for validity
	 * @param board		current board to test move on
	 * @return true if valid move or false if invalid move
	 ******************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = false;	// instantiate and set valid to false

		if (board[move.fromRow][move.fromColumn].player() == Player.WHITE) {	// piece at origin of move is white
			if (board[move.toRow][move.toColumn] == null) {		// move to space is empty

				// pawn is at its starting position and can move up one or two spaces
				if (move.fromRow == 6 && (move.toRow == 5 || move.toRow == 4) && (move.toColumn == move.fromColumn)) {
					if (move.toRow == 4 && board[5][move.toColumn] != null) {	// pawn tried to jump a piece to move up two on first move
						valid = false;	// set valid to false
					} else {	// no piece blocking pawn from moving up two on first move
						valid = true;	// set valid to true
					}

					// pawn tries to move up one space to an empty space
				} else if (move.fromRow > 0 && (move.toRow == (move.fromRow - 1)) && (move.toColumn == move.fromColumn)) {
					valid = true;	// set valid to true
				}
			} else {	// pawn tried to move to an occupied space
				if (move.toRow == (move.fromRow - 1)) {		// move up one space
					if ((move.toColumn == (move.fromColumn - 1)) || (move.toColumn == (move.fromColumn + 1))) {	// move is up-left or up-right
						if (board[move.toRow][move.toColumn].player() == player().next()) {		// piece at moved to space is of player black
							valid = true;	// set valid to true
						}
					}
				}
			}
		}

		if (board[move.fromRow][move.fromColumn].player() == Player.BLACK) {	// piece at origin of move is black
			if (board[move.toRow][move.toColumn] == null) {		// move to space is empty

				// pawn is at its starting position and can move down one or two spaces
				if (move.fromRow == 1 && (move.toRow == 2 || move.toRow == 3) && (move.toColumn == move.fromColumn)) {
					if (move.toRow == 3 && board[2][move.toColumn] != null) {	// pawn tried to jump a piece to move down two on first move
						valid = false;	// set valid to false
					} else {	// no piece blocking pawn from move down two on first move
						valid = true;	// set valid to true
					}

					// pawn tries to move down one space to an empty space
				} else if (move.fromRow < 7 && (move.toRow == (move.fromRow + 1)) && (move.toColumn == move.fromColumn)) {
					valid = true;	// set valid to true
				}
			} else {	// pawn tried to move to an occupied space
				if (move.toRow == (move.fromRow + 1)) {		// move down one space
					if ((move.toColumn == (move.fromColumn + 1)) || (move.toColumn == (move.fromColumn - 1))) {	// move is down-right or down-left
						if (board[move.toRow][move.toColumn].player() == player().next()) {		// piece at moved to space is of player white
							valid = true;	// set valid to true
						}
					}
				}
			}
		}

		return valid;	// return value of valid
	}
}
