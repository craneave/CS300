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
 * This class is the node creator for the book shelf
 */
public class LinkedNode<T> {
  private T data;
  private LinkedNode<T> next;

  /**
   * The constructor for only data given, and sets next to null
   * 
   * @param T data given of book
   */
  public LinkedNode(T data) {
    this.data = data;
    this.next = null;
  }

  /**
   * The constructor for data and next given
   * 
   * @param T          data given of book
   * @param LinkedNode next
   */
  public LinkedNode(T data, LinkedNode<T> next) {
    this.data = data;
    this.next = next;
  }

  /**
   * The getter method for the next of the node
   */
  public LinkedNode<T> getNext() {
    // if next is null, return this method as a null
    if (next == null)
      return null;
    return next;
  }

  /**
   * The getter method for the data of the node
   */
  public T getData() {
    return data;
  }

  /**
   * This method creates a string for the given nodes data
   * 
   * @Override
   */
  public String toString() {
    // create a new string, and take the data from this node, and convert to string
    String s = this.data.toString();
    return s;
  }

  /**
   * This method sets the next of the node to the given next value
   * 
   * @param LinkedNode next for the node
   */
  public void setNext(LinkedNode<T> next) {
    this.next = next;
  }
}
