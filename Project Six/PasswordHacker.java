/////////////////////////////////////////////////////////////////////////////////
//
// Title: Program 6: Benchmarking Hacks
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
 * This class represents the password hacker for the program, uses different ways for opening the
 * box and getting the password
 */
public class PasswordHacker {
  private LockBox toPick;
  private int passwordLength;

  /**
   * Constructor for the PasswordHacker class, builds a new lock box with given password length
   * 
   * @param passwordLength of the password
   */
  public PasswordHacker(int passwordLength) {
    this.passwordLength = passwordLength;
    toPick = new LockBox(passwordLength);
  }

  /**
   * Hack method which accesses the password and uses it
   * 
   * Complexity: O(1) A complexity of O(1) is due to the face that no matter how large the password
   * is, the hack method is able to access it in the same way, not causing it to change due to
   * password size, making it constant
   */
  public void hack() {
    toPick.reset();
    toPick.authenticate(toPick.hackMe());
  }

  /**
   * Brute force method which grinds its way through all possibly password possibilities
   * 
   * Complexity: O(N) The brute force method has a complexity of O(N) because as the password length
   * begins to increase, the method has to work harder and harder making it exponentially increasing
   * in complexity, causing this to be linear
   */
  public void bruteForce() {
    int count = 0;
    toPick.reset();
    // if the box is open, try to guess
    while (toPick.isOpen() == false) {
      // authenticate the guess by using the count variable
      toPick.authenticate(generateGuess(count));
      // increase count
      count++;
    }
  }

  /**
   * Generates a guess by inching one by one through each possible password
   * 
   * @param count, the number of times guessed
   * @return guess, the string form of the guess
   */
  public String generateGuess(int count) {
    String guess = "";
    // while guess is less that password length, add zeros
    while (guess.length() < passwordLength)
      guess += "0";
    // add the guess to end of the zeros
    guess += String.valueOf(count);
    // cuts off the zeros that are not needed
    guess = guess.substring(guess.length() - passwordLength, guess.length());
    return guess;
  }
}
