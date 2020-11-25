package Chess;

public class Move {
	public int fromRow, fromColumn, toRow, toColumn;

	/******************************************************************
	 * Default constructor for move
	 * Sets move to fromRow: 0, fromColumn: 0, toRow: 0, toColumn: 0
	 ******************************************************************/
	public Move(){
	}

	/******************************************************************
	 * Constructor to create a new Move and set its from/to row and column
	 *
	 * @param fromRow		// origin row of piece
	 * @param fromColumn	// origin column of piece
	 * @param toRow			// row piece is trying to move to
	 * @param toColumn		// column piece is trying to move to
	 ******************************************************************/
	public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
		this.fromRow = fromRow;			// set this Move's fromRow
		this.fromColumn = fromColumn;	// set this Move's fromColumn
		this.toRow = toRow;				// set this Move's toRow
		this.toColumn = toColumn;		// set this Move's toColumn
	}

	/******************************************************************
	 * Method returns a String stating the move that was made from row col
	 * to to row col.
	 * @return String
	 ******************************************************************/
	@Override
	public String toString() {
		return "Move [fromRow=" + fromRow + ", fromColumn=" + fromColumn + ", toRow=" + toRow + ", toColumn=" + toColumn
				+ "]";
	}


}
