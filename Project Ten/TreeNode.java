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
 * This class creates a tree node with a left and right value
 */
public class TreeNode<T> {
  private T data;
  private TreeNode<T> left;
  private TreeNode<T> right;

  /**
   * The constructor for only data given, and sets left/right to null
   * 
   * @param T data of node
   */
  public TreeNode(T data) {
    this.data = data;
    this.left = null;
    this.right = null;
  }

  /**
   * The constructor for data and next given
   * 
   * @param T        data of node
   * @param TreeNode left value
   * @param TreeNode right
   */
  public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  /**
   * The getter method for the left of the node
   * 
   * @returns TreeNode<T> left of the node
   */
  public TreeNode<T> getLeft() {
    // if left is null, return this method as a null
    if (left == null)
      return null;
    return left;
  }

  /**
   * The getter method for the right of the node
   * 
   * @returns TreeNode<T> right of the node
   */
  public TreeNode<T> getRight() {
    // if right is null, return this method as a null
    if (right == null)
      return null;
    return right;
  }

  /**
   * The getter method for the data of the node
   * 
   * @returns T data
   */
  public T getData() {
    return data;
  }

  /**
   * This method creates a string for the given nodes data
   * 
   * @returns String form of the data
   * @Override
   */
  public String toString() {
    // create a new string, and take the data from this node, and convert to string
    String s = this.data.toString();
    return s;
  }

  /**
   * This method sets the left of the node to the given next value
   * 
   * @param TreeNode left for the node
   */
  public void setLeft(TreeNode<T> left) {
    this.left = left;
  }

  /**
   * This method sets the right of the node to the given next value
   * 
   * @param TreeNode right for the node
   */
  public void setRight(TreeNode<T> right) {
    this.right = right;
  }

}
