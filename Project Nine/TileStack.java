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
import java.util.EmptyStackException;
import java.util.Iterator;
/**
 * This class creates a stack of tiles
 */
public class TileStack implements Iterable<Tile>, StackADT<Tile> {
	private Node top;
	private int size;

	/**
	 * Creates a new stack
	 */
	public TileStack() {
		this.top = null;
		this.size = 0;
	}

	/**
	 * Returns an iterator to iterate through this stack starting from its top
	 * 
	 * @return Iterator to iterate through stack
	 * @throws EmptyStackException when the stack is empty
	 */
	@Override
	public Iterator<Tile> iterator() throws EmptyStackException{
		if(isEmpty())
			throw new EmptyStackException();
		TileListIterator iterator = new TileListIterator(top);
		return iterator;
	}

	/**
	 * Pushes the provided tile at top of this stack
	 * 
	 * @param Tile of the element to be pushed
	 */
	@Override
	public void push(Tile element) {
		// if stack is empty then set top equal to new node
		if (isEmpty()) {
			Node elem = new Node(element);
			top = elem;
			size++;
		} else {
			// otherwise set the new elements next to the top, then set the top to the new
			// node
			Node elem = new Node(element, top);
			top = elem;
			size++;
		}
	}

	/**
	 * Removes and returns the tile at the top of this stack
	 * 
	 * @return Tile at the top of the stack and then removes it
	 * @throws EmptyStackException when the stack is empty
	 */
	@Override
	public Tile pop() throws EmptyStackException{
		// call the size method to see if an exception is thrown
		if(isEmpty())
			throw new EmptyStackException();
		// set temp to the tile of the current top
		Tile temp = top.getTile();
		// set top equal to tops next
		top = top.getNext();
		// reduce size
		size--;
		// then return temp, which is the Tile we popped
		return temp;
	}

	/**
	 * Returns the tile at the top of this stack
	 * 
	 * @return Tile at the top of the stack
	 * @throws EmptyStackException when the stack is empty
	 */
	@Override
	public Tile peek() throws EmptyStackException{
		// if is empty, then return null
		if (isEmpty())
			throw new EmptyStackException();
		//return the top tile
		return top.getTile();
	}

	/**
	 * Check whether this stack is empty
	 * 
	 */
	@Override
	public boolean isEmpty() {
		// if size is less than or equal to 0 then return true
		if (size() == 0)
			return true;
		return false;
	}

	/**
	 * Get the size of the stack
	 * 
	 * @return size of stack
	 */
	@Override
	public int size() {
		return size;
	}
}
