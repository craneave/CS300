/////////////////////////////////////////////////////////////////////////////////
//
// Title: Program 8: Linked Sorting
// Course: CS 300 Fall 2021
//
// Author: Avery Crane
// Email: adcrane@wisc.edu
// Lecturer: Hobbes LeGault
//
////////////////////////////////////////////////////////////////////////////////
//
// Persons: NONE
// Online Sources: NONE
////////////////
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This class is the bookshelf for all of the books to be added, cleared, or sorted
 */
public class LinkedBookshelf {

  private LinkedNode<Book> front;
  private LinkedNode<Book> back;
  private int size;
  private Attribute sortedBy;

  /**
   * This is the constructor for the class, setting front, back to null, size to 0, and sortedBy to
   * default ID
   */
  public LinkedBookshelf() {
    front = null;
    back = null;
    size = 0;
    sortedBy = Attribute.ID;
  }

  /**
   * Getter method for the size of the shelf
   */
  public int size() {
    return size;
  }

  /**
   * 
   * This method decides whether the shelf is empty or not
   * 
   * @return true if shelf is empty
   */
  public boolean isEmpty() {
    // if size is 0 then is empty is true
    if (size() == 0)
      return true;
    else
      return false;
  }

  /**
   * This method decides whether the shelf is empty or not
   * 
   * @throws NullPointerException if size of shelf is 0
   * @return string representation of the shelf
   * @Override parent toString method
   */
  public String toString() throws NullPointerException {
    // create a new string which starts with what it is being sortedBy and adds a newline
    String total = sortedBy.toString() + "\n";
    // new book node which is set to the front node
    LinkedNode<Book> curr = front;
    // if the size of the shelf is 0, throw new NullPointerException
    if (size == 0)
      throw new NullPointerException();
    // while curr is not a null
    while (curr != null) {
      // total is going to equal the book data curr is on, plus a newline
      total += curr.getData() + "\n";
      // then increment curr until it runs through the whole shelf
      curr = curr.getNext();
    }
    // return the final string
    return total;
  }

  /**
   * This method searches for the node at the given index
   * 
   * @throws IndexOutOfBoundsException if the index is less than 0 or more than what's on the shelf
   * @return the node at said index
   */
  public LinkedNode<Book> getNode(int index) throws IndexOutOfBoundsException {
    int count = 0;
    // if the index given is out of range, throw a new IndexOutOfBoundsException
    if (index < 0 || index > size() - 1) {
      throw new IndexOutOfBoundsException("Index out of range");
    } else {
      // make curr the front
      LinkedNode<Book> curr = front;
      // while the next of the front is not null, and count is less than index
      while (front.getNext() != null && count < index) {
        // increment count closer to index
        count++;
        // curr will be set to the next node
        curr = curr.getNext();
      }
      // the while loop will kick out at the index of the node we are searching for
      return curr;
    }

  }

  /**
   * This method searches for the node at the given index, then gives the data of it
   * 
   * @throws IndexOutOfBoundsException if the index is less than 0 or more than what's on the shelf
   * @return the node's data at said index
   */
  public Book get(int index) throws IndexOutOfBoundsException {
    int count = 0;
    // if the index given is out of range, throw a new IndexOutOfBoundsException
    if (index < 0 || index > size() - 1) {
      throw new IndexOutOfBoundsException("Index out of range");
    } else {
      // make curr the front
      LinkedNode<Book> curr = front;
      // while the next of the front is not null, and count is less than index
      while (front.getNext() != null && count < index) {
        // increment count closer to index
        count++;
        // curr will be set to the next node
        curr = curr.getNext();
      }
      // the while loop will kick out at the index of the node we are searching for, and then get
      // the data of it
      return curr.getData();
    }

  }

  /**
   * This method gets the first book on the shelf
   * 
   * @throws NullPointerException if size is 0 or if front is null
   * @return the first book on the shelf
   */
  public Book getFirst() throws NullPointerException {
    // if either size is 0 or the front are null, throw null pointer exception
    if (size == 0 || front == null)
      throw new NullPointerException();
    return front.getData();
  }

  /**
   * This method gets the last book on the shelf
   * 
   * @throws NullPointerException if size is 0 or if back is null
   * @return the back book on the shelf
   */
  public Book getLast() throws NullPointerException {
    // if either size is 0 or the back are null, throw null pointer exception
    if (size == 0 || back == null)
      throw new NullPointerException();
    return back.getData();
  }

  /**
   * This method clears the shelf and sets front, back to null, and size to 0
   */
  public void clear() {
    front = null;
    back = null;
    size = 0;
  }

  /**
   * This method adds a book to the end of the shelf
   * 
   * @param Book to be added
   */
  public void appendBook(Book toAdd) {
    // new book node to be added
    LinkedNode<Book> newBook = new LinkedNode<Book>(toAdd);
    // if the shelf is empty, then make the new node the front and back, and increase size
    if (isEmpty()) {
      front = newBook;
      back = newBook;
      size++;
    } else {
      // if not, then set the new node to the back, and set the previous backs next node, to the new
      // node, then increase size
      back.setNext(newBook);
      back = newBook;
      size++;
    }
  }

  /**
   * This will insert a book into an already sorted shelf
   * 
   * @param Book to be added
   */
  public void insertBook(Book toAdd) {
    // new node for the new book
    LinkedNode<Book> newBook = new LinkedNode<Book>(toAdd);
    // if the new book is greater than the back book on the shelf, then the new book node becomes
    // the back
    if (toAdd.compareTo(get(size() - 1), sortedBy) > 0) {
      back.setNext(newBook);
      back = newBook;
      size++;
    } // if the new node is less than the first node, then new book becomes the front
    else if (toAdd.compareTo(get(0), sortedBy) < 0) {
      newBook.setNext(front);
      front = newBook;
      size++;
    } else {
      // run through the sorted list backwards, starting at the largest object
      for (int x = size() - 1; x >= 0; x--) {
        // if the new book is greater than the book iterated on the shelf
        if (toAdd.compareTo(get(x), sortedBy) > 0) {
          // then new book nodes next value is set to that current nodes next value
          newBook.setNext(getNode(x).getNext());
          // then that current nodes next value is set equal to the new node
          getNode(x).setNext(newBook);
          // increase size
          size++;
        }
      }
    }
  }

  /**
   * This method will take an unsorted shelf, and use insertBook to sort the entire shelf
   * 
   * @param LinkedBookshelf shelf to be sorted
   * @param Attribute       to be sorted by
   * @throws NullPointerException if the size of the shelf to be sorted is 0
   */
  public static void sort(LinkedBookshelf b, Attribute sortedBy) throws NullPointerException {
    // if the size of the shelf is empty, then throw NullPointerException
    if (b.size() == 0)
      throw new NullPointerException();
    // set the sortedBy variable to the desired one entered
    b.sortedBy = sortedBy;
    // create two new shelfs
    LinkedBookshelf temp = new LinkedBookshelf();
    LinkedBookshelf first = new LinkedBookshelf();
    // this shelf will hold only the first book, or in other words, the sorted section of insertion
    // sort
    first.appendBook(b.get(0));
    // Iterate through the nodes starting with the second one on the shelf
    for (int x = 1; x < b.size(); x++) {
      // this shelf is going to hold every book after the first one
      temp.appendBook(b.get(x));
    }
    // now clear the original shelf
    b.clear();
    // and add the first book from it back to it
    b.appendBook(first.get(0));
    // now iterate through the remaining books on the temp shelf, and send each one through the
    // insertBook() method
    for (int x = 0; x < temp.size(); x++) {
      b.insertBook(temp.get(x));
    }
  }
}
