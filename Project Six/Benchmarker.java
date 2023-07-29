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
 * This class uses the other classes to compare the speeds of the two different methods of finding
 * the password
 */
public class Benchmarker {
  /**
   * The main method of this class
   */
  public static void main(String[] args) {
    System.out.println(race(4, 1));
  }

  /**
   * This method is designed to time the speed of the brute force method
   * 
   * @param ph, an object of the PasswordHacker class
   * @return time elapsed to complete brute force
   */
  public static long timeBruteForce(PasswordHacker ph) {
    long time;
    // takes time before method
    time = System.currentTimeMillis();
    // applies brute force method
    ph.bruteForce();
    // takes the time after, and subtracts it to find time elapsed
    time = System.currentTimeMillis() - time;
    return time;
  }

  /**
   * This method is designed to time the speed of the hack method
   * 
   * @param ph, an object of the PasswordHacker class
   * @return time elapsed to complete hack method
   */
  public static long timeHack(PasswordHacker ph) {
    long time;
    // takes time before method
    time = System.currentTimeMillis();
    // applies hack method
    ph.hack();
    // takes the time after, and subtracts it to find time elapsed
    time = System.currentTimeMillis() - time;
    return time;
  }

  /**
   * This method is designed to take the time of both of the two methods, and compare them together
   * over a certain amount of attempts, and finds the average
   * 
   * @param passwordLength of the password
   * @param numRuns        the number of runs to compare
   * @return total, the string of the race results
   */
  public static String race(int passwordLength, int numRuns) {
    long totalBrute = 0;
    long totalHack = 0;
    // runs through the numRuns requested
    for (int x = 0; x < numRuns; x++) {
      // makes a new passwordHacker
      PasswordHacker pass = new PasswordHacker(passwordLength);
      // adds the time to a total variable
      totalBrute += timeBruteForce(pass);
      totalHack += timeHack(pass);
    }
    // takes the average of the times
    float averageBrute = totalBrute / numRuns;
    float averageHack = totalHack / numRuns;
    // adds them to a string to be printed out
    String total = "Brute force " + passwordLength + ": " + String.valueOf(averageBrute) + "\nHack "
        + passwordLength + ": " + String.valueOf(averageHack);
    return total;
  }
}
