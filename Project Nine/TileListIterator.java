////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title:    P09 Tile Matching Game
//Course:   CS 300 Fall 2021
//
//Author:   Avery Crane
//Email:    adcrane@wisc.edu
//Lecturer:  Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
//Persons:    N/A
//Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * This class iterates through a tile list
 */
public class TileListIterator implements Iterator<Tile> {
	private Node node;

	/**
	 * Creates a new iterator to iterate through a list of tiles starting from its
	 * head, head is a reference to the head of the linked list of tiles
	 * 
	 * @param Node that represents the head
	 */
	public TileListIterator(Node head) {
		this.node = head;
	}

	/**
	 * Sees if the current node has a next thats not null
	 * 
	 * @return true of node has a next, false otherwise
	 */
	@Override
	public boolean hasNext() {
		// if nodes next is null return false, else return true
		if (node== null )
			return false;
		return true;
	}

	/**
	 * Gets the next of the current node
	 * 
	 * @return the tile of the next node
	 * @throws NoSuchElementException if hasNext() is empty
	 */
	@Override
	public Tile next() throws NoSuchElementException {
		if (!hasNext())
			throw new NoSuchElementException("Nothing Next");
		// set temp equal to current node
		Tile temp = node.getTile();
		// get next node
		node = node.getNext();
		return temp;
	}

}
