//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P09 Tile Matching Game
// Course:   CS 300 Fall 2021
//
// Author:   Avery Crane
// Email:    adcrane@wisc.edu
// Lecturer:  Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:    N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This class runs the workings of the game
 */
public class TileMatchingGame {
	private TileStack[] columns;

	/**
	 * Constructor for the TileMatchinGame class, initializes the columns
	 * 
	 * @param the number of columns
	 */
	public TileMatchingGame(int columnCount) {
		// set each new column location to an empty tile stack
		this.columns = new TileStack[columnCount];
		for (int x = 0; x <= columnCount - 1; x++) {
			TileStack t = new TileStack();
			columns[x] = t;
		}
	}

	/**
	 * Clears the columns
	 * 
	 * @param the index of column to clear
	 * @throws IndexOutOfBoundsException when index is out of bounds
	 */
	public void clearColumn(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > columns.length)
			throw new IllegalArgumentException();
		// go through the column and pop each tile
		for (int x = 0; x < columns[index].size(); x++) {
			columns[index].pop();
		}
	}

	/**
	 * Restarts the game
	 */
	public void restartGame() {
		for (int x = 0; x <= columns.length - 1; x++) {
			clearColumn(x);
		}
	}

	/**
	 * Returns a string representation of the stack of tiles at a given column index
	 * 
	 * @param the index of column to print
	 * @throws IndexOutOfBoundsException when index is out of bounds
	 */
	public String column(int index) throws IllegalArgumentException {
		// check for exception
		if (index < 0 || index > columns.length)
			throw new IllegalArgumentException();
		String total = "";
		// if column is empty
		if (!columns[index].isEmpty()) {
			// make a new iterator
			TileListIterator counter = (TileListIterator) columns[index].iterator();
			for (int x = 0; x < columns[index].size(); x++) {
				// if the tile hasnext() is true
				if (counter.hasNext()) {
					// total is equal to the next tostring()
					total += counter.next().toString();
				}
			}
		}
		return total;
	}

	/**
	 * Drops a tile at the top of the stack located at the given column index.
	 * 
	 * @param the index of column to drop on
	 * @param the tile to be dropped
	 * @throws IndexOutOfBoundsException when index is out of bounds
	 */
	public void dropTile(Tile tile, int index) throws IllegalArgumentException {
		if (index < 0 || index > columns.length)
			throw new IllegalArgumentException();
		// if column at index is not empty
		if (!columns[index].isEmpty()) {
			// if the colors are equal
			if (tile.getColor().equals(columns[index].peek().getColor())) {
				// pop the current node
				columns[index].pop();
			} else {
				// otherwise push it onto the column
				columns[index].push(tile);
			}
		} else {
			// if columns is empty then just add the tile
			columns[index].push(tile);
		}
	}

	/**
	 * Gets the length of the columns
	 * 
	 * @returns int of length of column
	 */
	public int getColumnsNumber() {
		return columns.length;
	}

	/**
	 * Gets the string value for the game to run
	 * 
	 * @returns string of the game columns and what is in them
	 */
	@Override
	public String toString() {
		String total = "GAME COLUMNS:";
		// go through the columns and call column() to add to each column
		for (int x = 0; x <= getColumnsNumber() - 1; x++) {
			total += "\n" + x + ": ";
			total += column(x);
		}
		return total;
	}
}
