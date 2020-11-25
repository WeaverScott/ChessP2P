package Chess;

/**
 * Rook chess piece utilizing the ChessPiece class, which implements the IChessPiece interface
 *
 * Sets the player, type, and sets what a valid move for this piece is
 */

public class Rook extends ChessPiece {

	/******************************************************************
	 * Default constructor assigns player to rook
	 *
	 * @param player	player to set to piece
	 ******************************************************************/
	public Rook(Player player) {

		super(player);	// sets player for rook

	}

	/******************************************************************
	 * Method returns String type of "Rook"
	 *
	 * @return "Rook"
	 ******************************************************************/
	public String type() {

		return "Rook";	// sets piece type to Rook

	}

	/******************************************************************
	 * Method determines if the move is valid for a rook piece and returns
	 * true or false depending on if the move is valid
	 *
	 * @param move		move to test for validity
	 * @param board		current board to test move on
	 * @return true if valid move or false if invalid move
	 ******************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		boolean valid = false;	// instantiate and set valid to false
		int count = 0;	// count of pieces between Rook and the move to space

		if (move.fromRow == move.toRow) {	// moves left or right
			if (move.toColumn < move.fromColumn) { // moves left
				for (int i = move.toColumn; i < move.fromColumn; i++) {	// check each space in column between from and to
					if (board[move.fromRow][i] != null) {	// found a space that is occupied
						count += 1;	// add 1 to count
					}
				}
			} else {	// moves right
				for (int i = move.fromColumn + 1; i <= move.toColumn; i++) {	// check each space in column between from and to
					if (board[move.fromRow][i] != null) {	// found a space that is occupied
						count += 1;	// add 1 to count
					}
				}
			}

			// rook has a valid move to take a piece from the other player
			if (board[move.toRow][move.toColumn] != null && count == 1 && board[move.toRow][move.toColumn].player() == player().next()) {
				count = 0;	// set count to 0
			}
		} else if (move.fromColumn == move.toColumn) {	// moves up or down
			if (move.toRow < move.fromRow) {	// moves up
				for (int i = move.toRow; i < move.fromRow; i++) {	// check each space in row between from and to
					if (board[i][move.fromColumn] != null) {	// found space that is occupied
						count += 1;	// add 1 to count
					}
				}
			} else {	// moves down
				for (int i = move.fromRow + 1; i <= move.toRow; i++) {	// check each space in row between from and to
					if (board[i][move.fromColumn] != null) {	// found space that is occupied
						count += 1;	// add 1 to count
					}
				}
			}
			if (board[move.toRow][move.toColumn] != null) {	// move to space is occupied

				// rook has a valid move to take a piece from the other player
				if (count == 1 && board[move.toRow][move.toColumn].player() == player().next()) {
					count = 0;	// set count to 0
				}
			}

		} else {	// attempted to move diagonally
			count = 999;	// to set count to an invalid amount
		}

		if (count > 0) {	// at least 1 space is occupied between from and to
			valid = false;	// set valid to false
		} else {			// rook has a valid move to
			valid = true;	// set valid to true
		}

		return valid;	// return value of valid
	}
}
