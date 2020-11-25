package Chess;

/**
 * Bishop chess piece utilizing the ChessPiece class, which implements the IChessPiece interface
 *
 * Sets the player, type, and sets what a valid move for this piece is
 */

public class Bishop extends ChessPiece {

	/******************************************************************
	 * Default constructor assigns player to bishop
	 ******************************************************************/
	public Bishop(Player player) {
		super(player);
	}

	/******************************************************************
	 * Method returns String type of "Bishop"
	 *
	 * @return String "Bishop"
	 ******************************************************************/
	public String type() {
		return "Bishop";	// sets type for piece to Bishop
	}

	/******************************************************************
	 * Method returns true or false depending on if the move is valid
	 * for the Bishop piece
	 *
	 * @param move		move to test
	 * @param board		current board to test move on
	 * @return true if valid or false if invalid
	 ******************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		boolean valid = false;	// instantiate and set valid to false
		int count = 0;			// counter for number of occupied spaces between from and to
		int colIndex = 0;		// index to check columns

		if (move.toRow != move.fromRow && move.toColumn != move.fromColumn) {	// to is not the same as from

			if (Math.abs(move.toRow - move.fromRow) == Math.abs(move.toColumn - move.fromColumn)) {	// tried to move to space on a diagonal
				if (move.toRow > move.fromRow && move.toColumn > move.fromColumn) {    // down-right
					colIndex = move.fromColumn + 1;	// set colIndex to (fromColumn + 1)
					for (int i = move.fromRow + 1; i <= move.toRow; i++) {	// scan rows from (fromRow + 1) to toRow
						if (board[i][colIndex] != null) {	// space between from and to is occupied
							count += 1;	// add 1 to count
						}
						colIndex += 1;	// increment colIndex by 1
					}

				} else if (move.toRow < move.fromRow && move.toColumn > move.fromColumn) {    // up-right
					colIndex = move.fromColumn + 1;		// set colIndex to (fromColumn + 1)
					for (int i = move.fromRow - 1; i >= move.toRow; i--) {	// scan rows from toRow to (fromRow - 1)
						if (board[i][colIndex] != null) {	// space between from and to is occupied
							count += 1;	// add 1 to count
						}
						colIndex += 1;	// increment colIndex by
					}

				} else if (move.toRow < move.fromRow && move.toColumn < move.fromColumn) {    // up-left
					colIndex = move.fromColumn - 1;	// set colIndex to (fromColumn + 1)
					for (int i = move.fromRow - 1; i >= move.toRow; i--) {	// scan rows from toRow to (fromRow - 1)
						if (board[i][colIndex] != null) {	// space between from and to is occupied
							count += 1;	// add 1 to count
						}
						colIndex -= 1;	// increment colIndex by
					}

				} else if (move.toRow > move.fromRow && move.toColumn < move.fromColumn) {    // down-left
					colIndex = move.fromColumn - 1;	// set colIndex to (fromColumn + 1)
					for (int i = move.fromRow + 1; i <= move.toRow; i++) {	// scan rows from (fromRow + 1) to toRow
						if (board[i][colIndex] != null) {	// space between from and to is occupied
							count += 1;	// add 1 to count
						}
						colIndex -= 1;	// increment colIndex by
					}

				} else {    // attempted to move up/down/right/left
					count = 999;    // to set count to an invalid amount
				}




				if (count > 0) {	// 1 or more occupied spaces between from and to
					valid = false;	// set valid to false
				} else {			// no occupied between from and to
					valid = true;	// set valid to true
				}

				if (board[move.toRow][move.toColumn] != null) {	// to space is occupied
					if (count == 1 && (board[move.toRow][move.toColumn].player() == player().next())) {	// to space occupied by other player
						valid = true;	// set valid to true
					}
				}
			} else {	// tried to move up, down, left, or right
				valid = false;	// set valid to false
			}
		}
		return valid;	// return value of valid
	}
}
