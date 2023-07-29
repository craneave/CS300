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
 * This is meant to test the LinkedBookshelf and LinkedNode classes and all of their methods
 */
public class LinkedBookshelfTester {

  /**
   * This method is designed to test the testLinkedNode() method
   * 
   * @return boolean, true if test passes, otherwise fails
   */
  public static boolean testLinkedNode() {
    Book.resetGenerator();
    // two new tester int's
    int tester1 = 2;
    int tester2 = 4;
    // use both types of constructors
    LinkedNode<Integer> test = new LinkedNode<Integer>(tester1);
    LinkedNode<Integer> test2 = new LinkedNode<Integer>(tester2, test);
    // Scenario 1: test the constructor of the first test and the getData accessor at the same time
    if (!test.getData().equals(tester1)) {
      return false;
    }
    // Scenario 2: Check second constructor
    if (!test2.getData().equals(tester2) || test2.getNext() != test) {
      return false;
    }
    // Scenario 3: Test setNext mutator
    test.setNext(test2);
    // if the next variable is not changed by the previous statement, return false
    if (test.getNext() != test2 || !test2.getData().equals(tester2))
      return false;
    // if all pass, return true
    return true;
  }

  /**
   * This method is designed to test the testClear() method
   * 
   * @return boolean, true if test passes, otherwise fails
   */
  public static boolean testClear() {
    Book.resetGenerator();
    // new shelf
    LinkedBookshelf test = new LinkedBookshelf();
    // new book
    Book test2 = new Book("Hello", 340, "Crane", "Avery");
    // Scenario 1: Test clear when it is already clear
    test.clear();
    // validate by checking if an exception is thrown when getFirst() is called, because get first
    // is null
    try {
      test.getFirst();
      // return false if no exception is thrown
      return false;
    } catch (Exception e) {
    }
    // same here but with getLast()
    try {
      test.getLast();
      return false;
    } catch (Exception e) {
    }
    // making sure size is 0
    if (test.size() != 0)
      return false;
    // Scenario 2: add something then clear it
    test.appendBook(test2);
    // see if it will clear
    test.clear();
    // make sure getFirst() and getLast() are null
    try {
      test.getFirst();
      return false;
    } catch (Exception e) {
    }
    try {
      test.getLast();
      return false;
    } catch (Exception e) {
    }
    // make sure size is 0
    if (test.size() != 0)
      return false;
    return true;
  }

  /**
   * This method is designed to test the testAddBooks() method
   * 
   * @return boolean, true if test passes, otherwise fails
   */
  public static boolean testAddBooks() {
    Book.resetGenerator();
    // new shelf
    LinkedBookshelf test = new LinkedBookshelf();
    // new books
    Book test2 = new Book("Hello", 340, "Crane", "Avery");
    Book test3 = new Book("Joe", 120, "Smith", "Chad");
    // Scenario 1: Only one book is added, test to see if last and first are updated correctly
    test.appendBook(test2);
    try {
      if (!test.getLast().equals(test2) && !test.getFirst().equals(test2)) {
        return false;
      }
    } catch (Exception e) {
      // if size is 0, there will be an exception thrown, which we dont want, so return false if
      // exception is caught
      return false;
    }
    // Scenario 2: Add another book and see if first and last are updated correctly
    test.appendBook(test3);
    try {
      if (!test.getLast().equals(test3) && !test.getFirst().equals(test2))
        return false;
    } catch (Exception e) {
      // same reasoning here as earlier
      return false;
    }
    // Scenario 3: size is not correctly updated
    if (test.size() != 2)
      return false;
    return true;
  }

  /**
   * This method is designed to test the testSortBooks() method
   * 
   * @return boolean, true if test passes, otherwise fails
   */
  public static boolean testSortBooks() {
    Book.resetGenerator();
    // attribute to be sorted by
    Attribute a = Attribute.AUTHOR;
    // new shelf
    LinkedBookshelf test = new LinkedBookshelf();
    // create new books
    Book test2 = new Book("Hello", 340, "Crane", "Avery");
    Book test3 = new Book("Joe", 120, "Smith", "Chad");
    Book test4 = new Book("Books", 340, "Mann", "Kyle");
    Book test5 = new Book("Games", 10, "Smith", "John");
    // create the expected string if sorted with author
    String expected =
        "AUTHOR\n" + "0: \"Hello\", Crane, Avery (340)\n" + "2: \"Books\", Mann, Kyle (340)\n"
            + "1: \"Joe\", Smith, Chad (120)\n" + "3: \"Games\", Smith, John (10)";
    String expected2 = "AUTHOR\n" + "0: \"Hello\", Crane, Avery (340)";
    // add the books
    test.appendBook(test2);
    test.appendBook(test3);
    test.appendBook(test4);
    test.appendBook(test5);
    // Scenario 1: check if sort works with correct implementation
    try {
      LinkedBookshelf.sort(test, a);
    } catch (Exception e) {
      return false;
    }
    // if the test string equals expected then move on
    if (!test.toString().strip().equals(expected))
      return false;
    // reset sort
    test.clear();
    Book.resetGenerator();
    // Scenario 2: Check if sort works with one book
    test.appendBook(test2);
    try {
      LinkedBookshelf.sort(test, a);
    } catch (Exception e) {
      return false;
    }
    if (!test.toString().strip().equals(expected2))
      return false;
    return true;
  }

  /**
   * This method is designed to test the runAllTests() method
   * 
   * @return boolean, true if test passes, otherwise fails
   */
  public static boolean runAllTests() {
    Book.resetGenerator();
    // check all methods and if any are false, return false
    if (testLinkedNode() == false || testClear() == false || testAddBooks() == false
        || testSortBooks() == false)
      return false;
    // otherwise return true
    return true;

  }

  /**
   * The main method of the class
   */
  public static void main(String[] args) {
    // show the results of all the tests
    runAllTests();
  }

}
