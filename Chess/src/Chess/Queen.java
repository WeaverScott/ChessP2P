package Chess;

/**
 * Queen chess piece utilizing the ChessPiece class, which implements the IChessPiece interface
 *
 * Sets the player, type, and sets what a valid move for this piece is
 */

public class Queen extends ChessPiece {

	/******************************************************************
	 * Default constructor assigns player to queen piece
	 *
	 * @param player	// sets player for piece
	 ******************************************************************/
	public Queen(Player player) {
		super(player);

	}

	/******************************************************************
	 * Method returns String type of "Queen"
	 *
	 * @return "Queen"
	 ******************************************************************/
	public String type() {
		return "Queen";		// sets type Queen to piece

	}

	/******************************************************************
	 * Method determines if the move is valid for a Queen piece and returns
	 * true or false depending on if the move is valid
	 *
	 * @param move		move to be tested
	 * @param board		current board move will be tested on
	 * @return true if valid move or false if invalid move
	 ******************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		Bishop move1 = new Bishop(board[move.fromRow][move.fromColumn].player());	// instantiate a Bishop piece
		Rook move2 = new Rook(board[move.fromRow][move.fromColumn].player());		// instantiate a Rook Piece
		return (move1.isValidMove(move, board) || move2.isValidMove(move, board));	// validity of move by using Bishop and Rook's isValid
	}
}
