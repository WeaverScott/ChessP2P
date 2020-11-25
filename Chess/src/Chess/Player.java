package Chess;
/**
 * Determines which player is next depending on the current player
 */

public enum Player {
	BLACK, WHITE;

	/**
	 * Return the {@code Player} whose turn is next.
	 *
	 * @return the {@code Player} whose turn is next
	 */
	public Player next() {
		if (this == BLACK) {	// current player is BLACK
			return WHITE;		// return WHITE
		} else {				// current player is WHITE
			return BLACK;		// return BLACK
		}
	}
}