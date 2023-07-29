import java.util.Random;

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
 * This class represents the lock box, and uses multiple methods to help set up the uses of it
 */
public class LockBox {
  protected static Random randGen;
  private String password;
  private boolean isOpen;

  /**
   * The constructor for the lock box class
   * 
   * @param passwordLength is the length of the password being created
   * @throws IllegalArgumentException if passwordLength is 0
   */
  public LockBox(int passwordLength) throws IllegalArgumentException {
    // randGen set back to null for a different random num
    randGen = null;
    // if randGen is null, create a new lock, then if password is negative, throw an Illegal
    // Argument Exception
    if (randGen == null) {
      if (passwordLength > 0) {
        // initializing temp and password strings
        String temp = "";
        password = "";
        // initializes ranGen
        randGen = new Random();
        // temp is equal to random number
        temp = String.valueOf(randGen.nextInt(10000));
        // adds 0s to the password string
        while (password.length() + temp.length() < passwordLength) {
          password += "0";
        }
        // adds the random number to the front of the password string
        password += temp;
      } else {
        // throw exception
        throw new IllegalArgumentException("Invalid Password Length");
      }
    }
  }

  /**
   * The method for checking if the guess given is equal to the password
   * 
   * @param guess of the password
   */
  public void authenticate(String guess) {
    if (guess.equals(password)) {
      isOpen = true;
    }
  }

  /**
   * The getter method of the password
   * 
   * @return password
   */
  public String hackMe() {
    return password;
  }

  /**
   * The getter method on if lock box is open or not
   * 
   * @return isOpen
   */
  public boolean isOpen() {
    return isOpen;
  }
  /**
   * Resets the lock box
   * 
   */
  public void reset() {
    isOpen = false;
  }
}
