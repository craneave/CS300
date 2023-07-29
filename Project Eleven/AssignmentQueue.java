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
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array-based heap implementation of a priority queue containing Assignments. Guarantees the
 * min-heap invariant, so that the Assignment at the root should have the earliest due date, and
 * children always have a due date after or at the same time as their parent. The root of a
 * non-empty queue is always at index 0 of this array-heap.
 */
public class AssignmentQueue implements PriorityQueueADT<Assignment>, Iterable<Assignment> {
  private Assignment[] queue; // array min-heap of assignments representing this priority queue
  private int size; // size of this priority queue

  /**
   * Creates a new empty AssignmentQueue with the given capacity
   * 
   * @param capacity Capacity of this AssignmentQueue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer
   */
  public AssignmentQueue(int capacity) throws IllegalArgumentException {
    // if capacity is less that 0 than throw a new IllegalArgumentException
    if (capacity < 0)
      throw new IllegalArgumentException("Must be a positive integer");
    // set size to 0 and initilize queue with length capacity
    this.size = 0;
    this.queue = new Assignment[capacity];
  }

  /**
   * Checks whether this AssignmentQueue is empty
   * 
   * @return {@code true} if this AssignmentQueue is empty
   */
  @Override
  public boolean isEmpty() {
    // if size is 0, return true, else false
    if (size() == 0)
      return true;
    return false;
  }

  /**
   * Returns the size of this AssignmentQueue
   * 
   * @return the size of this AssignmentQueue
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Returns the capacity of this AssignmentQueue
   * 
   * @return the capacity of this AssignmentQueue
   */
  public int capacity() {
    return this.queue.length;
  }


  /**
   * Removes all elements from this AssignmentQueue
   */
  @Override
  public void clear() {
    // reinitialize queue
    this.queue = new Assignment[this.queue.length];
    // set size to 0
    this.size = 0;
  }

  /**
   * Returns the Assignment at the root of this AssignmentQueue, i.e. the Assignment with the
   * earliest due date.
   * 
   * @return the Assignment in this AssignmentQueue with the earliest due date
   * @throws NoSuchElementException if this AssignmentQueue is empty
   */
  @Override
  public Assignment peek() throws NoSuchElementException {
    // if the queue is empty throw exception
    if (isEmpty())
      throw new NoSuchElementException("This queue is empty!");
    // otherwise return the first element which will be the earliest due date
    return this.queue[0];
  }

  /**
   * Adds the given Assignment to this AssignmentQueue at the correct position based on the min-heap
   * ordering. This queue should maintain the min-heap invariant, so that the Assignment at each
   * index has an earlier or equivalent due-date than the Assignments in its child nodes.
   * Assignments should be compared using the Assignment.compareTo() method.
   * 
   * @param e Assignment to add to this AssignmentQueue
   * @throws NullPointerException  if the given Assignment is null
   * @throws IllegalStateException with a descriptive error message if this AssignmentQueue is full
   */
  @Override
  public void enqueue(Assignment e) throws IllegalStateException, NullPointerException {
    // if the queue is full, throw exception
    if (size() == this.queue.length)
      throw new IllegalStateException("The queue is full!");
    // if the given assignment is null, throw NullPointerException
    if (e == null)
      throw new NullPointerException("The given assignmnet is null!");
    // add new data if the queue is empty
    if (isEmpty()) {
      this.queue[0] = e;
      size++;
      return;
    }
    this.queue[size()] = e;
    size++;

    percolateUp(size() - 1);
  }

  /**
   * Removes and returns the Assignment at the root of this AssignmentQueue, i.e. the Assignment
   * with the earliest due date.
   * 
   * @return the Assignment in this AssignmentQueue with the earliest due date
   * @throws NoSuchElementException with a descriptive error message if this AssignmentQueue is
   *                                empty
   */
  @Override
  public Assignment dequeue() throws NoSuchElementException {
    if (isEmpty())
      throw new NoSuchElementException("This queue is empty!");
    Assignment best = this.queue[0];
    if (size() > 1) {
      this.queue[0] = this.queue[size() - 1];
      this.queue[size() - 1] = null;
      size--;
    } else {
      Assignment temp = this.queue[0];
      this.queue[0] = null;
      size--;
      return temp;
    }
    percolateDown(0);
    return best;
  }

  /**
   * Recursive implementation of percolateDown() method. Restores the min-heap invariant of a given
   * subtree by percolating its root down the tree. If the element at the given index does not
   * violate the min-heap invariant (it is due before its children), then this method does not
   * modify the heap. Otherwise, if there is a heap violation, then swap the element with the
   * correct child and continue percolating the element down the heap.
   * 
   * @param i index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateDown(int i) {
    // TODO provide the worst-case runtime complexity of this method assuming that the problem size
    // N is the size of this queue
    // Time complexity: O(log(N))

    // the parentIndex of the queue
    int parentIndex = i;
    // find the left and right by multiplying by two, then adding one for left, and two for right
    int left = (2 * parentIndex) + 1;
    int right = (2 * parentIndex) + 2;
    // if the left index is greater than or equal to the length of the queue, then we are done
    if (left >= size())
      // this is a leaf
      return;
    // if the parentIndex is less than the left index
    else if (this.queue[parentIndex].compareTo(this.queue[left]) > 0) {
      // if the rightIndex is greater than or equal to the length, then we swap the parent with the
      // left, and we are done
      if (right >= size()) {
        swap(parentIndex, left);
        return;
        // otherwise then continue
      } else {
        // if left index is greater than right, then larger is left, otherwise it is right
        int larger = (this.queue[left].compareTo(this.queue[right]) < 0) ? left : right;
        // swap the parent index with which ever was larger
        swap(parentIndex, larger);
        // recurse back through the method with larger being the new index
        percolateDown(larger);
      }
    }
    // else if right is less than the size, and parentIndex is less than right
    else if (right < size() && this.queue[parentIndex].compareTo(this.queue[right]) > 0) {
      // then swap them
      swap(parentIndex, right);
      // and recurse back through with right as the new index
      percolateDown(right);
    }
    // if none of that, then we are done
    else
      return;
  }

  /**
   * Recursive implementation of percolateUp() method. Restores the min-heap invariant of the tree
   * by percolating a leaf up the tree. If the element at the given index does not violate the
   * min-heap invariant (it occurs after its parent), then this method does not modify the heap.
   * Otherwise, if there is a heap violation, swap the element with its parent and continue
   * percolating the element up the heap.
   * 
   * @param i index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateUp(int i) {
    // TODO provide the worst-case runtime complexity of this method assuming that the problem size
    // N is the size of this queue
    // Time complexity: O(log(N))
    // the index of the newData in the queue
    int newDataIndex = i;
    if (newDataIndex == 0)
      return;
    // to find the parent index of the given index you must subtract 1 and divide by two with
    // integer division
    int parentIndex = (newDataIndex - 1) / 2;
    // if the assignment at the data index is greater than the parentIndex
    if (this.queue[newDataIndex].compareTo(this.queue[parentIndex]) < 0) {
      // swap the assignment with its parent
      swap(newDataIndex, parentIndex);
      // recurse back through with the
      percolateUp(parentIndex);
    } else
      return;
  }

  /**
   * Returns a deep copy of this AssignmentQueue containing all of its elements in the same order.
   * This method does not return the deepest copy, meaning that you do not need to duplicate
   * assignments. Only the instance of the heap (including the array and its size) will be
   * duplicated.
   * 
   * @return a deep copy of this AssignmentQueue. The returned new assignment queue has the same
   *         length and size as this queue.
   */
  public AssignmentQueue deepCopy() {
    AssignmentQueue copy = new AssignmentQueue(queue.length);
    for (int x = 0; x < this.queue.length; x++) {
      copy.queue[x] = this.queue[x];
    }
    copy.size = size();
    return copy;
  }

  /**
   *    * Returns a String representing this AssignmentQueue, where each element (assignment) of the
   *    * queue is listed on a separate line, in order from earliest to latest.    *     * @return a
   * String representing this AssignmentQueue    
   */
  public String toString() {
    StringBuilder val = new StringBuilder();

    for (int i = 0; i < size; i++) {
      val.append(queue[i]).append("\n");
    }
    return val.toString();
  }

  /**
   * Private helper method that will swap two items
   * 
   * @param index1 to be swapped
   * @param index2 to be swapped
   */
  private void swap(int index1, int index2) {
    Assignment temp = this.queue[index1];
    this.queue[index1] = this.queue[index2];
    this.queue[index2] = temp;
  }

  /**
   * Returns an Iterator for this AssignmentQueue which proceeds from the earliest to the latest
   * Assignment in the queue.
   * 
   * @see AssignmentIterator
   * @return an Iterator for this AssignmentQueue
   */
  @Override
  public Iterator<Assignment> iterator() {
    return new AssignmentIterator(this);
  }
}
