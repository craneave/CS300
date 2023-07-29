import java.util.ArrayList;

/////////////////////////////////////////////////////////////////////////////////
//
// Title: Program 10: Binary Bookshelf
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
 * This class is a binary search tree version of our sorted Bookshelf
 */
public class BinaryBookshelf {
  private TreeNode<Book> root;
  private int size;
  private Attribute[] sortList;

  /**
   * This is the constructor for the class, setting root to null, size to 0, and sortedList to
   * parameter
   * 
   * @param Attribute[] of what to sort by
   * @throws IllegalArgumentException if the sortList array is not a valid entry
   */
  public BinaryBookshelf(Attribute[] sortList) throws IllegalArgumentException {
    // checks if sortList is null, or is greater than or less than 4, or if the first Index is not
    // author, then throw a new IllegalArgumentException
    if (sortList == null || sortList.length > 4 || sortList.length < 4
        || sortList[0] != Attribute.AUTHOR)
      throw new IllegalArgumentException("Invalid attribute array");
    // loop through the array and compare each element with the rest of the list to check if any are
    // the same
    for (int x = 0; x < sortList.length; x++) {
      for (int y = x + 1; y < sortList.length; y++) {
        if (sortList[x] == sortList[y])
          throw new IllegalArgumentException("Invalid attribute array LOOPS");
      }
    }
    // otherwise just construct the shelf
    this.size = 0;
    this.root = null;
    this.sortList = sortList;
  }

  /**
   * Getter method for the size of the shelf
   * 
   * @returns int size of shelf
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
   * This method clears the shelf and sets root to null, and size to 0
   */
  public void clear() {
    root = null;
    size = 0;
  }

  /**
   * Searches for the input book in the bookshelf.
   * 
   * @param Book book to be searched
   * @returns true if its found, false otherwise
   */
  public boolean contains(Book book) {
    return containsHelper(book, root);
  }

  /**
   * Recursive helper method; searches for the input book in the subtree rooted at current, uses
   * compareToHelper to compare based off of the sortList array
   * 
   * @param Book           book to be searched
   * @param TreeNode<Book> of current node
   * @returns true if its found, false otherwise
   */
  protected boolean containsHelper(Book book, TreeNode<Book> current) {
    // if the node being searched for is null, return false, base case
    if (current == null)
      return false;
    // if the book being searched for is greater than the current node, then recurse right
    if (compareToHelper(book, current.getData()) > 0)
      return containsHelper(book, current.getRight());
    // if the book being searched for is less than the current node, then recurse left
    if (compareToHelper(book, current.getData()) < 0)
      return containsHelper(book, current.getLeft());
    // if they are exactly equal, return true
    if (compareToHelper(book, current.getData()) == 0)
      return true;
    return false;
  }

  /**
   * helper method to compare two Book objects according to the sortList of attributes
   * 
   * @param Book the first book
   * @param Book the second book
   * @returns a negative value if one < two, a positive value if one > two, and 0 if they are equal
   */
  protected int compareToHelper(Book one, Book two) {
    // go through each attribute and compare to the other book
    for (int x = 0; x < sortList.length; x++) {
      // if this book at attribute x is greater than the other books attribute x, then return a
      // positive
      if (one.compareTo(two, sortList[x]) > 0)
        return 1;
      // if this book at attribute x is less than the other books attribute x, then return a
      // negative
      else if (one.compareTo(two, sortList[x]) < 0)
        return -1;
    }
    // if nothing is returned then return 0 because they must be equal
    return 0;
  }

  /**
   * Provides a String formatted list of the attributes in the order in which they are used
   * 
   * @returns a String formatted list of the attribute
   */
  public String getAttributeOrder() {
    String s = "";
    // go through the array and toString() each value with the correct number in front of it
    for (int x = 0; x < sortList.length; x++)
      s += x + 1 + ":" + sortList[x].toString() + " ";
    return s;
  }

  /**
   * Creates a list of books on the shelf written by a given author
   * 
   * @param String name of the author
   * @returns a list of books in the bookshelf written by the given author
   */
  public ArrayList<Book> getBooksByAuthor(String authorName) {
    if (authorName.equals(null))
      return null;
    return getBooksByAuthorHelper(authorName, root);
  }

  /**
   * Recursive helper method
   * 
   * @param String   name of the author
   * @param TreeNode of the current node to be searched
   * @returns a list of books in the subtree rooted at current which were written by the given
   *          author
   */
  protected ArrayList<Book> getBooksByAuthorHelper(String authorName, TreeNode<Book> current) {
    ArrayList<Book> list = new ArrayList<Book>();
    // if the node being searched for is null, return list, base case
    if (current == null)
      return list;
    // if author is found, add to list
    if (current.getData().getAuthor().equals(authorName))
      list.add(current.getData());
    // then go down the left and right sides of tree adding the list
    list.addAll(getBooksByAuthorHelper(authorName, current.getLeft()));
    list.addAll(getBooksByAuthorHelper(authorName, current.getRight()));
    return list;
  }

  /**
   * Adds a new Book to the BST in sorted order, relative to this BST's sortList attributes
   * 
   * @param Book book to be added
   * @throws IllegalArgumentException if the book is already in BST
   */
  public void insertBook(Book book) throws IllegalArgumentException {
    if (root == null) {
      root = new TreeNode<Book>(book);
      size++;
    } else {
      insertBookHelper(book, root);
    }
  }

  /**
   * Adds a new Book to the BST in sorted order, relative to this BST's sortList attributes
   * 
   * @param Book           book to be added
   * @param TreeNode<Book> current node in recursion
   * @throws IllegalArgumentException if the book is already in BST
   */
  protected void insertBookHelper(Book book, TreeNode<Book> current)
      throws IllegalArgumentException {
    // compare book and the current book if book is greater then move forward
    if (compareToHelper(book, current.getData()) > 0) {
      // is the right child null?
      if (current.getRight() == null) {
        current.setRight(new TreeNode<Book>(book));
        size++;
      } else
        insertBookHelper(book, current.getRight());
    }
    // compare book and the current book, if book is less than then move forward
    else if (compareToHelper(book, current.getData()) < 0) {
      // is the left child null?
      if (current.getLeft() == null) {
        current.setLeft(new TreeNode<Book>(book));
        size++;
      } else
        insertBookHelper(book, current.getLeft());
    } else {
      // no duplicates
      throw new IllegalArgumentException("No duplicate values!");
    }
  }

  /**
   * A shallow copy of the root node so that test tree structures may be constructed manually rather
   * than by using the insertBook() method
   * 
   * @returns TreeNode<Book> root of the tree
   */
  protected TreeNode<Book> getRoot() {
    return root;
  }

  /**
   * Creates and returns an in-order traversal of the BST
   * 
   * @returns String version of the BST
   */
  @Override
  public String toString() {
    if (root == null)
      return "";
    return toStringHelper(root);
  }

  /**
   * Recursive helper method of toString()
   * 
   * @param TreeNode<Book> current node
   * @returns String representation of the subtree rooted at the current node
   */
  protected String toStringHelper(TreeNode<Book> current) {
    String s = "";
    // if current is null then end that search
    if (current == null)
      return "";
    // add the result of the getLeft() search and the getRight() searches
    s += toStringHelper(current.getLeft());
    // get the book data and create a new line
    s += current.toString() + "\n";
    s += toStringHelper(current.getRight());
    return s;
  }
}

