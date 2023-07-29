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
 * This class tests the implementation of TileMatching
 */
public class TileMatchingTester {
	/**
	 * Tests multiple different cases of the equals() method
	 * 
	 * @returns true if tests pass, otherwise fail
	 */
	public static boolean tileEqualsTester() {
		Tile test = new Tile(Color.BLACK);
		String obj = new String("Hey");
		Tile fake = new Tile(Color.BLUE);
		Tile real = new Tile(Color.BLACK);
		try {
			// Scenario one: test with a string object
			if (test.toString().equals(obj))
				return false;
			// Scenario two: test with a Tile with a different color
			if (test.equals(fake))
				return false;
			// Scenario three: test with a Tile of the same color
			if (!test.equals(real))
				return false;
			// Scenario four: test with a null object
			if (test.equals(null))
				return false;
			// Scenario five: test if the tile is equal to itself
			if (!test.equals(test))
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Tests multiple different cases of the TileListIterator class
	 * 
	 * @returns true if tests pass, otherwise fail
	 */
	public static boolean tileListIteratorTester() {
		Tile testerTile1 = new Tile(Color.BLACK);
		Tile testerTile2 = new Tile(Color.ORANGE);
		Tile testerTile3 = new Tile(Color.YELLOW);
		Node node1 = new Node(testerTile1);
		Node node2 = new Node(testerTile2, node1);
		Node node3 = new Node(testerTile3, node2);
		TileListIterator test = new TileListIterator(node3);
		TileListIterator test2 = new TileListIterator(null);
		// Scenario one: test hasNext() with correct implementation
		try {
			if (!test.hasNext())
				return false;
		} catch (Exception e) {
			return false;
		}
		// Scenario two: test hasNext() with correct implementation
		try {
			if (test2.hasNext())
				return false;
		} catch (Exception e) {
			return false;
		}
		// Scenario three: test next() with correct implementation (also test if it gets
		// the head on the first call)
		try {
			if (!test.next().equals(node3.getTile()))
				return false;
		} catch (Exception e) {
			return false;
		}
		// again
		try {
			if (!test.next().equals(node2.getTile()))
				return false;
		} catch (Exception e) {
			return false;
		}
		// Scenario four: test next() when there is a null
		try {
			test.next();
			test.next();
			return false;
		} catch (Exception e) {
		}

		return true;

	}

	/**
	 * Tests isEmpty and Size() methods
	 * 
	 * @returns true if tests pass, otherwise fail
	 */
	private static boolean isEmptyAndSizeTester() {
		Tile testerTile1 = new Tile(Color.BLACK);
		Tile testerTile2 = new Tile(Color.ORANGE);
		Tile testerTile3 = new Tile(Color.YELLOW);
		TileStack tile = new TileStack();
		TileStack tile2 = new TileStack();
		tile.push(testerTile1);
		tile.push(testerTile2);
		tile.push(testerTile3);
		// Scenario one: test if the isEmpty() method works with correct implementation
		if (tile.isEmpty())
			return false;
		// Scenario two: test if the isEmpty() method works when stack is empty
		if (!tile2.isEmpty())
			return false;

		// Scenario one: test if the size() method works with correct implementation
		if (tile.size() != 3)
			return false;
		// Scenario two: test if the size() method works when stack is empty
		if (tile2.size() != 0)
			return false;
		return true;
	}

	/**
	 * Tests push() and peek() methods
	 * 
	 * @returns true if tests pass, otherwise fail
	 */
	private static boolean pushAndPeekTester() {
		Tile testerTile1 = new Tile(Color.BLACK);
		Tile testerTile2 = new Tile(Color.ORANGE);
		Tile testerTile3 = new Tile(Color.YELLOW);
		TileStack tile = new TileStack();
		TileStack tile2 = new TileStack();
		try {
			tile.push(testerTile1);
			tile.push(testerTile2);
			tile.push(testerTile3);
			// Scenario one: test if the push() method works with correct implementation by
			// using the peek, this technically tests both
			if (tile.peek() != testerTile3)
				return false;
			// Scenario two: test if the peek() method works when stack is empty
			tile2.peek();
			return false;
		} catch (Exception e) {
			// if the exception is caught. do nothing
		}
		return true;
	}

	/**
	 * Tests the pop() method
	 * 
	 * @returns true if tests pass, otherwise fail
	 */
	private static boolean popTester() {
		Tile testerTile1 = new Tile(Color.BLACK);
		Tile testerTile2 = new Tile(Color.ORANGE);
		Tile testerTile3 = new Tile(Color.YELLOW);
		TileStack tile = new TileStack();
		TileStack tile2 = new TileStack();
		try {
			tile.push(testerTile1);
			tile.push(testerTile2);
			tile.push(testerTile3);
			tile.pop();
			// Scenario one: test if the push() method works with correct implementation by
			// using the pop, this technically tests both
			if (tile.peek() != testerTile2)
				return false;
			// Scenario two: test if the push() method works when stack is empty
			tile2.pop();
			return false;
		} catch (Exception e) {
			// if the exception is caught on correct implementation, do nothing
		}
		return true;
	}

	/**
	 * Tests iterator method()
	 * 
	 * @returns true if tests pass, otherwise fail
	 */
	private static boolean iteratorTester() {
		Tile testerTile1 = new Tile(Color.BLACK);
		Tile testerTile2 = new Tile(Color.ORANGE);
		Tile testerTile3 = new Tile(Color.YELLOW);
		Node node1 = new Node(testerTile1);
		Node node2 = new Node(testerTile2, node1);
		Node node3 = new Node(testerTile3, node2);
		TileStack tile = new TileStack();
		TileStack tile2 = new TileStack();
		TileListIterator test = new TileListIterator(node3);
		try {
			tile.push(testerTile1);
			tile.push(testerTile2);
			tile.push(testerTile3);
			// Scenario one: test if iterator method works correctly
			if (!tile.iterator().next().equals(test.next()))
				return false;
			// Scenario two: test if the iterator method works with an empty stack
			tile2.iterator();
			return false;
		} catch (Exception e) {
			// if the exception is caught on correct implementation, do nothing
		}
		return true;
	}

	/**
	 * Uses multiple helper methods to test the tileStack class
	 * 
	 * @returns true if tests pass, otherwise fail
	 */
	public static boolean tileStackTester() {
		if (!isEmptyAndSizeTester() || !pushAndPeekTester() || !popTester() || !iteratorTester())
			return false;
		return true;
	}

	/**
	 * Tests the tileMatchingGame class
	 * 
	 * @returns true if tests pass, otherwise fail
	 */
	public static boolean tileMatchingGameTester() {
		Tile testerTile1 = new Tile(Color.BLACK);
		Tile testerTile2 = new Tile(Color.ORANGE);
		Tile testerTile3 = new Tile(Color.YELLOW);
		Node node1 = new Node(testerTile1);
		Node node2 = new Node(testerTile2, node1);
		Node node3 = new Node(testerTile3, node2);
		TileStack tile = new TileStack();
		TileStack tile2 = new TileStack();
		TileMatchingGame tester = new TileMatchingGame(4);
		try {
			tester.dropTile(testerTile1, 0);
			// Scenario one: test if the drop tile method works
			if (!tester.column(0).equals(testerTile1.toString()))
				return false;
			// Scenario two: test adding two of the same color removes it
			tester.dropTile(testerTile1, 0);
			if (!tester.column(0).equals(""))
				return false;
			// Scenario three: test if clear column works
			tester.clearColumn(0);
			if (!tester.column(0).equals(""))
				return false;
			// Scenario four: test getColumnsNumber()
			if (tester.getColumnsNumber() != 4)
				return false;
			// Scenario five: check if restart game clears all the tiles, and check the
			// toString
			tester.dropTile(testerTile2, 2);
			tester.dropTile(testerTile3, 3);
			tester.restartGame();
			if (!tester.toString().equals("GAME COLUMNS:\n" + "0: \n" + "1: \n" + "2: \n" + "3: "))
				return false;
		} catch (Exception e) {
			// if any of those threw an exception then return false
			return false;
		}
		// Now we check the exceptions
		// Scenario one: clearColumn() when index is out of bounds
		try {
			tester.clearColumn(-2);
			return false;
		} catch (Exception e) {
		}
		// Scenario two: clearColumn() when index is out of bounds
		try {
			tester.column(100);
			return false;
		} catch (Exception e) {
		}
		// Scenario three: dropTile() when index is out of bounds
		try {
			tester.dropTile(testerTile3, 100);
			return false;
		} catch (Exception e) {
		}
		return true;
	}

	/**
	 * Tests the all the tests
	 * 
	 * @returns true if tests pass, otherwise fail
	 */
	public static boolean testAllTests() {
		if (!tileMatchingGameTester() || tileStackTester() || tileEqualsTester() || tileListIteratorTester())
			return false;
		return true;
	}

	/**
	 * The main method of the class
	 */
	public static void main(String[] args) {
		testAllTests();
	}
}
