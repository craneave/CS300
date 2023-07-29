/////////////////////////////////////////////////////////////////////////////////
//
// Title: Program 11: Assignment Planner
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

import java.util.NoSuchElementException;

/**
 * Tester class for the AssignmentQueue implementation of PriorityQueueADT
 */
public class AssignmentQueueTester {
  /**
   * Tests the functionality of the constructor for AssignmentQueue Should implement at least the
   * following tests:
   *
   * - Calling the AssignmentQueue with an invalid capacity should throw an IllegalArgumentException
   * - Calling the AssignmentQueue with a valid capacity should not throw any errors, and should
   * result in a new AssignmentQueue which is empty, and has size 0
   *
   * @return true if the constructor of AssignmentQueue functions properly
   * @see AssignmentQueue#AssignmentQueue(int)
   */
  public static boolean testConstructor() {
    // test 1: test with an invalid capacity
    try {
      AssignmentQueue test = new AssignmentQueue(-2);
      // return false if an exception is not caught
      return false;
    } catch (IllegalArgumentException e) {
    } catch (Exception e) {
      return false;
    }
    // test 2: test with a valid capacity
    try {
      AssignmentQueue test = new AssignmentQueue(8);
      // make sure it is empty
      if (!test.isEmpty())
        return false;
      // make sure size is 0
      if (test.size() != 0)
        return false;
    } catch (Exception e) {
      // no exceptions should be caught
      return false;
    }
    return true;
  }

  /**
   * Tests the functionality of the enqueue() and peek() methods Should implement at least the
   * following tests:
   *
   * - Calling peek on an empty queue should throw a NoSuchElementException 
   * - Calling enqueue on a queue which is empty should add the Assignment, and increment the size of the queue 
   * - Calling enqueue on a non-empty queue should add the Assignment at the proper position, and increment the size of the queue 
   * - Calling peek on a non-empty queue should always return the Assignment with the earliest due date 
   * - Calling enqueue on a full queue should throw an IllegalStateException 
   * - Calling enqueue with a null Assignment should throw a NullPointerException
   * 
   * @return true if AssignmentQueue.enqueue() and AssignmentQueue.peek() function properly
   */
  public static boolean testEnqueue() {
    // test 1: peak() on a empty queue
    try {
      AssignmentQueue test = new AssignmentQueue(8);
      test.peek();
      return false;
    } catch (Exception e) {
    }
    // test 2: enqueue() on a empty queue
    try {
      AssignmentQueue test = new AssignmentQueue(8);
      Assignment testAssignment = new Assignment("CS300 P11", 12, 6, 22);
      test.enqueue(testAssignment);
      // check if its empty
      if (test.isEmpty())
        return false;
      // check size
      if (test.size() != 1)
        return false;
    } catch (Exception e) {
      return false;
    }
    // test 3: adding with enqueue to a non empty queue
    try {
      AssignmentQueue test1 = new AssignmentQueue(8);
      Assignment testAssignment1 = new Assignment("CS300 P11", 12, 04, 24);
      Assignment testAssignment2 = new Assignment("CS300 P10", 1, 2, 12);
      Assignment testAssignment3 = new Assignment("CS300 P09", 12, 06, 1);
      Assignment testAssignment4 = new Assignment("CS300 P08", 12, 02, 4);
      Assignment testAssignment5 = new Assignment("CS300 P07", 1, 01, 21);
      test1.enqueue(testAssignment1);
      test1.enqueue(testAssignment2);
      test1.enqueue(testAssignment3);
      test1.enqueue(testAssignment4);
      test1.enqueue(testAssignment5);
      // check if its empty
      if (test1.isEmpty())
        return false;
      // check size
      if (test1.size() != 5)
        return false;
      // check if they are in the right position, and check peek()
      if (!test1.peek().equals(testAssignment5))
        return false;
    } catch (Exception e) {
      return false;
    }
    // test 4: adding to an already full queue
    try {
      AssignmentQueue test1 = new AssignmentQueue(5);
      Assignment testAssignment1 = new Assignment("CS300 P11", 12, 04, 24);
      Assignment testAssignment2 = new Assignment("CS300 P10", 1, 2, 12);
      Assignment testAssignment3 = new Assignment("CS300 P09", 12, 06, 1);
      Assignment testAssignment4 = new Assignment("CS300 P08", 12, 02, 4);
      Assignment testAssignment5 = new Assignment("CS300 P07", 1, 01, 21);
      Assignment testAssignment6 = new Assignment("CS300 P06", 6, 21, 1);

      test1.enqueue(testAssignment1);
      test1.enqueue(testAssignment2);
      test1.enqueue(testAssignment3);
      test1.enqueue(testAssignment4);
      test1.enqueue(testAssignment5);
      test1.enqueue(testAssignment6);
      return false;
    } catch (IllegalStateException e) {
    } catch (Exception e) {
      return false;
    }
    // test 5: enqueue with null assignment
    try {
      AssignmentQueue test1 = new AssignmentQueue(2);
      Assignment testAssignment1 = null;
      test1.enqueue(testAssignment1);
      return false;
    } catch (NullPointerException e) {
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Tests the functionality of dequeue() and peek() methods. The peek() method must return without
   * removing the assignment with the highest priority in the queue. The dequeue() method must
   * remove, and return the assignment with the highest priority in the queue. The size must be
   * decremented by one, each time the dequeue() method is successfully called. Try to check the
   * edge cases (calling peek and dequeue on an empty queue, and calling dequeue on a queue of size
   * one). For normal cases, try to consider dequeuing from a queue whose size is at least 6. Try to
   * consider cases where percolate-down recurses on left and right.
   * 
   * @return true if AssignmentQueue.dequeue() and AssignmentQueue.peek() function properly
   */
  public static boolean testDequeuePeek() {
    // test 1: peek() does not remove anything and dequeue works on regular cases
    try {
      AssignmentQueue test1 = new AssignmentQueue(8);
      Assignment testAssignment1 = new Assignment("CS300 P11", 12, 04, 24);
      Assignment testAssignment2 = new Assignment("CS300 P10", 1, 2, 12);
      Assignment testAssignment3 = new Assignment("CS300 P09", 12, 06, 1);
      Assignment testAssignment4 = new Assignment("CS300 P08", 12, 02, 4);
      Assignment testAssignment5 = new Assignment("CS300 P07", 1, 01, 21);
      Assignment testAssignment6 = new Assignment("CS300 P06", 12, 21, 1);
      test1.enqueue(testAssignment1);
      test1.enqueue(testAssignment2);
      test1.enqueue(testAssignment3);
      test1.enqueue(testAssignment4);
      test1.enqueue(testAssignment5);
      test1.enqueue(testAssignment6);
      // check if they are in the right position, and check peek()
      if (!test1.peek().equals(testAssignment5))
        return false;
      // check the dequeue
      if (!test1.dequeue().equals(testAssignment5))
        return false;
      // check the size now
      if (test1.size() != 5)
        return false;
      // dequeue again
      if (!test1.dequeue().equals(testAssignment2))
        return false;
      // dequeue again
      if (!test1.dequeue().equals(testAssignment4))
        return false;
      // check the size now
      if (test1.size() != 3)
        return false;
      // dequeue again
      if (!test1.dequeue().equals(testAssignment1))
        return false;
      // dequeue again
      if (!test1.dequeue().equals(testAssignment3))
        return false;
      // dequeue with a queue with one thing
      if (!test1.dequeue().equals(testAssignment6))
        return false;
    } catch (Exception e) {
      return false;
    }
    // Test 2: dequeue edge case
    try {
      AssignmentQueue test1 = new AssignmentQueue(8);
      // test dequeue on an empty queue
      test1.dequeue();
      return false;
    } catch (NoSuchElementException e) {
    } catch (Exception e) {
      return false;
    }
    // Test 3: peek edge case
    try {
      AssignmentQueue test1 = new AssignmentQueue(8);
      // test dequeue on an empty queue
      test1.peek();
      return false;
    } catch (NoSuchElementException e) {
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Tests the functionality of the clear() method Should implement at least the following
   * scenarios: 
   * - clear can be called on an empty queue with no errors 
   * - clear can be called on a non-empty queue with no errors 
   * - After calling clear, the queue should contain no Assignments
   *
   * @return true if AssignmentQueue.clear() functions properly
   */
  public static boolean testClear() {
    // test 1: check clear on full queue
    try {
      AssignmentQueue test1 = new AssignmentQueue(8);
      Assignment testAssignment1 = new Assignment("CS300 P11", 12, 04, 24);
      Assignment testAssignment2 = new Assignment("CS300 P10", 1, 2, 12);
      Assignment testAssignment3 = new Assignment("CS300 P09", 12, 06, 1);
      Assignment testAssignment4 = new Assignment("CS300 P08", 12, 02, 4);
      Assignment testAssignment5 = new Assignment("CS300 P07", 1, 01, 21);
      Assignment testAssignment6 = new Assignment("CS300 P06", 12, 21, 1);
      test1.enqueue(testAssignment1);
      test1.enqueue(testAssignment2);
      test1.enqueue(testAssignment3);
      test1.enqueue(testAssignment4);
      test1.enqueue(testAssignment5);
      test1.enqueue(testAssignment6);
      // check clear on a full queue
      test1.clear();
      // check if empty
      if (!test1.isEmpty())
        return false;
      // check size
      if (test1.size() != 0)
        return false;
      // peek throws exception because it is empty
      test1.peek();
      return false;
    } catch (NoSuchElementException e) {
    } catch (Exception e) {
      return false;
    }
    // test 2: check clear on empty queue
    try {
      AssignmentQueue test1 = new AssignmentQueue(8);
      // check clear on a empty queue
      test1.clear();
      // check if empty
      if (!test1.isEmpty())
        return false;
      // check size
      if (test1.size() != 0)
        return false;
      // peek throws exception because it is empty
      test1.peek();
      return false;
    } catch (NoSuchElementException e) {
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Tests all the methods of the AssignmentQueue class
   * 
   */
  public static boolean runAllTests() {
    if (!testConstructor() || !testEnqueue() || !testDequeuePeek() || !testClear())
      return false;
    return true;
  }

  /**
   * Main method
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    runAllTests();
  }
}
