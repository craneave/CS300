///////////////////////////////////////////////////////////////////////////////
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
 * This class tests all classes and methods for this project
 */
public class BinaryBookshelfTester {
  /**
   * Tests the generic class TreeNode
   * 
   * @returns true if tests pass, false otherwise
   */
  public static boolean testTreeNode() {
    // new tester int's
    int tester1 = 2;
    int tester2 = 10;
    int tester3 = 6;
    // use both types of constructors
    TreeNode<Integer> test = new TreeNode<Integer>(tester1);
    TreeNode<Integer> test2 = new TreeNode<Integer>(tester2);
    TreeNode<Integer> test3 = new TreeNode<Integer>(tester3, test, test2);
    // Scenario 1: test the first constructor
    if (!test.getData().equals(tester1))
      return false;
    if (test.getLeft() != null)
      return false;
    if (test.getRight() != null)
      return false;
    if (!test.toString().equals("2"))
      return false;
    // Scenario 2: Check the setter methods
    test2.setLeft(test);
    if (test2.getLeft() == null)
      return false;
    if (!test2.getLeft().equals(test))
      return false;
    if (test2.getRight() != null)
      return false;
    test2.setLeft(null);
    if (test2.getLeft() != null)
      return false;
    // Scenario 3: Test the second constructor
    if (!test3.getLeft().equals(test))
      return false;
    if (!test3.getRight().equals(test2))
      return false;
    // if all pass, return true
    return true;
  }

  /**
   * This method ensures that the basic methods of a BinaryBookshelf are valid, before any Books
   * have been added to the shelf.
   * 
   * @returns true if tests pass, false otherwise
   */
  public static boolean testEmptyTree() {

    // SCENARIO 1: INVALID INPUTS
    // test 1: empty attribute array
    try {
      Attribute[] attributeTester1 = new Attribute[4];
      BinaryBookshelf shelfTest1 = new BinaryBookshelf(attributeTester1);
      return false;
    } catch (Exception e) {
    }
    // test 2: attribute size other than four
    try {
      Attribute[] attributeTester2 = new Attribute[6];
      BinaryBookshelf shelfTest2 = new BinaryBookshelf(attributeTester2);
      return false;
    } catch (Exception e) {
    }
    // test 3: attribute with same elements
    try {
      Attribute[] attributeTester3 = new Attribute[4];
      attributeTester3[0] = Attribute.AUTHOR;
      attributeTester3[1] = Attribute.ID;
      attributeTester3[2] = Attribute.PAGECOUNT;
      attributeTester3[3] = Attribute.AUTHOR;
      BinaryBookshelf shelfTest3 = new BinaryBookshelf(attributeTester3);
      return false;
    } catch (Exception e) {
    }
    // test 4: something other than AUTHOR at index 0
    try {
      Attribute[] attributeTester4 = new Attribute[4];
      attributeTester4[0] = Attribute.ID;
      attributeTester4[1] = Attribute.AUTHOR;
      attributeTester4[2] = Attribute.PAGECOUNT;
      attributeTester4[3] = Attribute.TITLE;
      BinaryBookshelf shelfTest4 = new BinaryBookshelf(attributeTester4);
      return false;
    } catch (Exception e) {
    }

    // SCENARIO 2: VALID INPUTS
    // test 1: valid inputs
    try {
      Attribute[] attributeTester5 = new Attribute[4];
      attributeTester5[0] = Attribute.AUTHOR;
      attributeTester5[1] = Attribute.ID;
      attributeTester5[2] = Attribute.PAGECOUNT;
      attributeTester5[3] = Attribute.TITLE;
      BinaryBookshelf shelfTest5 = new BinaryBookshelf(attributeTester5);
      Book dummyBook = new Book("Hello", 90);
      String author = "John";
      String a = "1:" + attributeTester5[0] + " 2:" + attributeTester5[1] + " 3:"
          + attributeTester5[2] + " 4:" + attributeTester5[3];
      BinaryBookshelf shelfTest6 = new BinaryBookshelf(attributeTester5);
      // test 2: test size(), isEmpty(), getRoot(), and toString() on empty shelf
      if (shelfTest6.size() != 0)
        return false;
      if (!shelfTest6.isEmpty())
        return false;
      if (shelfTest6.toString() != "")
        return false;
      if (shelfTest6.getRoot() != null)
        return false;
      // test 3: test attributeOrder()
      if (!shelfTest6.getAttributeOrder().strip().equals(a))
        return false;
      // test 4: test contains()
      if (shelfTest6.contains(dummyBook))
        return false;
      // test 5: test getBooksByAuthor()
      if (!shelfTest6.getBooksByAuthor(author).isEmpty())
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * This method ensures that the BinaryBookshelf insertBook() method works as expected
   * 
   * @returns true if tests pass, false otherwise
   */
  public static boolean testInsertBook() {

    // SCENARIO 1: INSERTING INTO EMPTY TREE
    try {
      Attribute[] attributeTester = new Attribute[4];
      attributeTester[0] = Attribute.AUTHOR;
      attributeTester[1] = Attribute.TITLE;
      attributeTester[2] = Attribute.ID;
      attributeTester[3] = Attribute.PAGECOUNT;
      Book dummyBook = new Book("Children Of The Corn", 340, "King", "Stephen");
      BinaryBookshelf shelfTest = new BinaryBookshelf(attributeTester);
      // test 1: Insert book and should no longer be empty
      if (!shelfTest.isEmpty() || shelfTest.size() != 0)
        return false;
      shelfTest.insertBook(dummyBook);
      if (shelfTest.isEmpty() || shelfTest.size() != 1)
        return false;
      // test 2: test getRoot() now
      if (!shelfTest.getRoot().toString().equals(dummyBook.toString()))
        return false;


      // SCENARIO 2: INSERTING A UNIQUE SMALLER VALUE INTO NON EMPTY
      // test 1: new book with alphabetically earlier name and make sure it becomes the left child
      Book dummyBook2 = new Book("Oliver Twist", 540, "Dickens", "Charles");
      shelfTest.insertBook(dummyBook2);
      if (!shelfTest.getRoot().getLeft().toString().equals(dummyBook2.toString())
          || shelfTest.size() != 2)
        return false;

      // SCENARIO 3: INSERTING VALUE WITH SHARED AUTHOR ATTRIBUTE
      Book dummyBook3 = new Book("IT", 600, "King", "Stephen");
      shelfTest.insertBook(dummyBook3);
      if (!shelfTest.getRoot().getRight().toString().equals(dummyBook3.toString())
          || shelfTest.size() != 3)
        return false;
    } catch (Exception e) {
      return false;
    }

    // SCENARIO 4: DUPLICATE VALUE
    try {
      Attribute[] attributeTester = new Attribute[4];
      attributeTester[0] = Attribute.AUTHOR;
      attributeTester[1] = Attribute.TITLE;
      attributeTester[2] = Attribute.ID;
      attributeTester[3] = Attribute.PAGECOUNT;
      Book dummyBook = new Book("Children Of The Corn", 340, "King", "Stephen");
      Book dummyBook2 = new Book("Children Of The Corn", 340, "King", "Stephen");
      BinaryBookshelf shelfTest = new BinaryBookshelf(attributeTester);
      shelfTest.insertBook(dummyBook);
      shelfTest.insertBook(dummyBook);
      return false;
    } catch (Exception e) {
    }
    return true;
  }

  /**
   * This method ensures that the BinaryBookshelf contains() method works as expected
   * 
   * @returns true if tests pass, false otherwise
   */
  public static boolean testContains() {
    // SCENARIO 1: NON-RECURSIVE CASE
    try {
      Attribute[] attributeTester = new Attribute[4];
      attributeTester[0] = Attribute.AUTHOR;
      attributeTester[1] = Attribute.TITLE;
      attributeTester[2] = Attribute.ID;
      attributeTester[3] = Attribute.PAGECOUNT;
      Book dummyBook = new Book("Children Of The Corn", 340, "King", "Stephen");
      Book dummyBook2 = new Book("IT", 600, "King", "Stephen");
      BinaryBookshelf shelfTest = new BinaryBookshelf(attributeTester);
      // add book
      shelfTest.insertBook(dummyBook);
      // check for book
      if (!shelfTest.contains(dummyBook))
        return false;
      // check for non existent book
      if (shelfTest.contains(dummyBook2))
        return false;
    } catch (Exception e) {
      return false;
    }
    // SCENARIO 2: RECURSIVE-CASE
    try {
      Attribute[] attributeTester = new Attribute[4];
      attributeTester[0] = Attribute.AUTHOR;
      attributeTester[1] = Attribute.TITLE;
      attributeTester[2] = Attribute.PAGECOUNT;
      attributeTester[3] = Attribute.ID;
      Book dummyBook = new Book("Children Of The Corn", 340, "King", "Stephen");
      Book dummyBook2 = new Book("IT", 600, "King", "Stephen");
      Book dummyBook3 = new Book("Oliver Twist", 340, "Dickens", "Charles");
      Book dummyBook4 = new Book("IT", 900, "King", "Stephen");
      Book dummyBook5 = new Book("Doctor Sleep", 340, "King", "Stephen");
      Book dummyBook6 = new Book("Cujo", 560, "King", "Stephen");

      BinaryBookshelf shelfTest = new BinaryBookshelf(attributeTester);
      TreeNode<Book> dummyNode2 = new TreeNode<Book>(dummyBook2);
      TreeNode<Book> dummyNode3 = new TreeNode<Book>(dummyBook3);
      TreeNode<Book> dummyNode4 = new TreeNode<Book>(dummyBook4);
      TreeNode<Book> dummyNode5 = new TreeNode<Book>(dummyBook5);
      // add book
      shelfTest.insertBook(dummyBook);
      // copy and add left/right values
      shelfTest.getRoot().setLeft(dummyNode3);
      shelfTest.getRoot().setRight(dummyNode2);
      // set the left and right of dummy node 2
      // dummyNode2 gets a right value of dummyNode4 because they are equal in author and title, but
      // dummyNode4 is greater in page count
      dummyNode2.setRight(dummyNode4);
      // dummyNode2 gets the left value of dummyNode5 because they have the same author value, but
      // dummyNode5 has a lesser title value than dummyNode2, but greater than the root
      dummyNode2.setLeft(dummyNode5);
      // test 1: check if the book at the root is contained in shelf
      if (!shelfTest.contains(dummyBook))
        return false;
      // test 2: check for a leaf node
      if (!shelfTest.contains(dummyBook3))
        return false;
      // test 3: check for a internal node
      if (!shelfTest.contains(dummyBook2))
        return false;
      // test 4: check for a non existent node
      if (shelfTest.contains(dummyBook6))
        return false;

    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * This method ensures that the BinaryBookshelf getBooksByAuthor() method works as expected.
   * 
   * @returns true if tests pass, false otherwise
   */
  public static boolean testGetBooksByAuthor() {
    // SCENARIO 1: NON-RECURSIVE CASE
    try {
      Attribute[] attributeTester = new Attribute[4];
      attributeTester[0] = Attribute.AUTHOR;
      attributeTester[1] = Attribute.TITLE;
      attributeTester[2] = Attribute.ID;
      attributeTester[3] = Attribute.PAGECOUNT;
      Book dummyBook = new Book("Children Of The Corn", 340, "King", "Stephen");
      String author = "King, Stephen";
      String badAuthor = "Hello";
      BinaryBookshelf shelfTest = new BinaryBookshelf(attributeTester);
      // add book
      shelfTest.insertBook(dummyBook);
      // check for book author
      if (shelfTest.getBooksByAuthor(author).size() != 1)
        return false;
      // check for non existent book author
      if (shelfTest.getBooksByAuthor(badAuthor).size() != 0)
        return false;
    } catch (Exception e) {
      return false;
    }
    // SCENARIO 2: RECURSIVE-CASE
    try {
      Attribute[] attributeTester = new Attribute[4];
      attributeTester[0] = Attribute.AUTHOR;
      attributeTester[1] = Attribute.TITLE;
      attributeTester[2] = Attribute.PAGECOUNT;
      attributeTester[3] = Attribute.ID;
      Book dummyBook = new Book("Children Of The Corn", 340, "King", "Stephen");
      Book dummyBook2 = new Book("IT", 600, "King", "Stephen");
      Book dummyBook3 = new Book("Oliver Twist", 340, "Dickens", "Charles");
      Book dummyBook4 = new Book("IT", 900, "King", "Stephen");
      Book dummyBook5 = new Book("Doctor Sleep", 340, "King", "Stephen");
      String author1 = "Dickens, Charles";
      String author2 = "King, Stephen";
      String author3 = "Hello";
      BinaryBookshelf shelfTest = new BinaryBookshelf(attributeTester);
      TreeNode<Book> dummyNode2 = new TreeNode<Book>(dummyBook2);
      TreeNode<Book> dummyNode3 = new TreeNode<Book>(dummyBook3);
      TreeNode<Book> dummyNode4 = new TreeNode<Book>(dummyBook4);
      TreeNode<Book> dummyNode5 = new TreeNode<Book>(dummyBook5);
      // add book
      shelfTest.insertBook(dummyBook);
      // copy and add left/right values
      shelfTest.getRoot().setLeft(dummyNode3);
      shelfTest.getRoot().setRight(dummyNode2);
      // set the left and right of dummy node 2
      // dummyNode2 gets a right value of dummyNode4 because they are equal in author and title, but
      // dummyNode4 is greater in page count
      dummyNode2.setRight(dummyNode4);
      // dummyNode2 gets the left value of dummyNode5 because they have the same author value, but
      // dummyNode5 has a lesser title value than dummyNode2, but greater than the root
      dummyNode2.setLeft(dummyNode5);
      // test 1: get one author
      if (shelfTest.getBooksByAuthor(author1).size() != 1)
        return false;
      // test 2: multiple books
      if (shelfTest.getBooksByAuthor(author2).size() != 4)
        return false;
      // test 3: wrong author
      if (shelfTest.getBooksByAuthor(author3).size() != 0)
        return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * This method tests all tests
   * 
   * @returns true if tests pass, false otherwise
   */
  public static boolean runAllTests() {
    return testTreeNode() && testEmptyTree() && testInsertBook() && testContains()
        && testGetBooksByAuthor();
  }

  /**
   * Main method of this program
   * 
   */
  public static void main(String[] args) {
    System.out.println(runAllTests());
  }
}
