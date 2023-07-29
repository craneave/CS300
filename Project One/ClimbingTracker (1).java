//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Program 1: Climbing Tracker
// Course: CS 300 Fall 2021
//
// Author: Avery Crane
// Email: adcrane@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources:
//////////////// https://stackoverflow.com/questions/8410294/why-does-printlnarray-have-strange-output-ljava-lang-string3e25a5
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This class uses multiple methods for tracking rock climbing grades and previous progress
 */
public class ClimbingTracker {
  /**
   * Determines whether a grade is valid and adds it to the history if there is space
   * 
   * @return the number of "send" climbs including the newly added one
   */
  public static int sendClimb(String[] send, int numSend, String grade) {
    int t = 0;
    // checking if grade is a valid input, and if so assigning t to the number of the grade
    if (grade.substring(1).equals("V")) {
      t = Integer.parseInt(grade.substring(1));
    }
    // determining if the number is valid in the grade, and if there is space in the array
    if ((grade.substring(0, 1).equals("V")) && (t >= 0 && t <= 7) && (numSend < send.length)) {
      // adding the grade to the empty ending position of the array and adding it to the total
      // number of fails
      send[numSend] = grade;
      return numSend + 1;
    }
    // will return only the original number of sends if the grade is not valid or if there is no
    // space
    return numSend;
  }

  /**
   * Determines whether a grade is valid and adds it to the history if there is space
   * 
   * @return the number of "fail" climbs including the newly added one
   */
  public static int failClimb(String[] fail, int numFail, String grade) {
    int t = 0;
    // checking if grade is a valid input, and if so assigning t to the number of the grade
    if (grade.substring(1).equals("V")) {
      t = Integer.parseInt(grade.substring(1));
    }
    // determining if the number is valid in the grade, and if there is space in the array
    if ((grade.substring(0, 1).equals("V")) && (t >= 0 && t <= 7) && (numFail < fail.length)) {
      // adding the grade to the empty ending position of the array and adding it to the total
      // number of fails
      fail[numFail] = grade;
      return numFail + 1;
    }
    // will return only the original number of sends if the grade is not valid or if there is no
    // space
    return numFail;
  }

  /**
   * Takes the info of grades in the array, and finds the average based on the length of the history
   * wanted
   * 
   * @return the average of the grade levels
   */
  public static String getStats(String[] send, int numSend, String[] fail, int numFail,
      int historyLength) {
    String average = null;
    // the tracker variable will track what grades to average, and the counter counts how many to
    // average
    int sendTracker = 0;
    int sendCounter = 0;
    int failCounter = 0;
    int failTracker = 0;
    double sendTotal = 0.0;
    double sendAverage = 0.0;
    double failTotal = 0.0;
    double failAverage = 0.0;
    // if the history length is larger than the amount of grades in the array then tracker is set to
    // the num otherwise tracker is equal to history length in correspondence to the end of the
    // array
    if (historyLength > numSend) {
      sendTracker = 0;
    } else {
      sendTracker = numSend - historyLength;
    }
    if (historyLength > numFail) {
      failTracker = 0;
    } else {
      failTracker = numFail - historyLength;
    }
    // for both of these for loops, it takes the number out of the grade, adds it to the total, and
    // the counter goes up because its another number being averaged
    for (int x = numSend - 1; x >= sendTracker; x--) {
      sendCounter = 1 + sendCounter;
      int sendIn = Integer.parseInt(send[x].substring(1));
      sendTotal = sendTotal + sendIn;
    }
    for (int x = numFail - 1; x >= failTracker; x--) {
      failCounter = 1 + failCounter;
      int failIn = Integer.parseInt(fail[x].substring(1));
      failTotal = failTotal + failIn;
    }
    // the totals of both are being set to the average variables
    sendAverage = sendTotal / sendCounter;
    failAverage = failTotal / failCounter;
    // history length is tested to see if either send or fail are 0, and will set the string
    // "average" to what's appropriate
    if (historyLength == 0 || numSend == 0 && numFail == 0) {
      average = "Send: --\nFail: --";
    }
    if (historyLength != 0 && numSend == 0 && numFail != 0) {
      average = "Send: --\nFail: " + failAverage;
    }
    if (historyLength != 0 && numSend != 0 && numFail == 0) {
      average = "Send: " + sendAverage + "\nFail: --";
    }
    if (historyLength != 0 && numSend != 0 && numFail != 0) {
      average = "Send: " + sendAverage + "\nFail: " + failAverage;
    }
    return average;
  }

  /**
   * Takes the info of grades in the array, and determines how many time each grade was send or fail
   * 
   * @return a string of grades sent or failed
   */
  public static String getHistogram(String[] send, int numSend, String[] fail, int numFail) {
    // individually initializing all variables to their corresponding grades, so that the "+" and
    // "-" can be added to them, and also initializes the final string
    String finalString = "";
    String countZero = "V0:";
    String countOne = "\nV1:";
    String countTwo = "\nV2:";
    String countThree = "\nV3:";
    String countFour = "\nV4:";
    String countFive = "\nV5:";
    String countSix = "\nV6:";
    String countSeven = "\nV7:";
    // the loop will check every string in the array, and then find the individual grade numbers,
    // and then a "-" is added to the string with the equal grade (ex. if grade is V0, then " -" is
    // added to countZero), this is done for all numbers for every grade in the array
    for (int x = 0; x <= fail.length - 1; x++) {
      // checks if numFail is a number above 0, and if not will skip the string section
      if (numFail > 0) {
        // checks if x of the array is = to null, and if it is, will skip the string section
        if (fail[x] != null) {
          if (fail[x].substring(1).equals("0")) {
            countZero += " -";
          }
          if (fail[x].substring(1).equals("1")) {
            countOne += " -";
          }
          if (fail[x].substring(1).equals("2")) {
            countTwo += " -";
          }
          if (fail[x].substring(1).equals("3")) {
            countThree += " -";
          }
          if (fail[x].substring(1).equals("4")) {
            countFour += " -";
          }
          if (fail[x].substring(1).equals("5")) {
            countFive += " -";
          }
          if (fail[x].substring(1).equals("6")) {
            countSix += " -";
          }
          if (fail[x].substring(1).equals("7")) {
            countSeven += " -";
          }
        }
      }
    }
    // same thing being done here as the last loop just for send instead of fail
    for (int x = 0; x <= send.length - 1; x++) {
      // checks if numSend is a number above 0, and if not will skip the string section
      if (numSend > 0) {
        // checks if x of the array is = to null, and if it is, will skip the string section
        if (send[x] != null) {
          if (send[x].substring(1).equals("0")) {
            countZero += " +";
          }
          if (send[x].substring(1).equals("1")) {
            countOne += " +";
          }
          if (send[x].substring(1).equals("2")) {
            countTwo += " +";
          }
          if (send[x].substring(1).equals("3")) {
            countThree += " +";
          }
          if (send[x].substring(1).equals("4")) {
            countFour += " +";
          }
          if (send[x].substring(1).equals("5")) {
            countFive += " +";
          }
          if (send[x].substring(1).equals("6")) {
            countSix += " +";
          }
          if (send[x].substring(1).equals("7")) {
            countSeven += " +";
          }
        }
      }
    }
    // checking if numSend and numFail are both 0
    if (numSend == 0 && numFail == 0) {
      finalString = "Error: no data to display";
    }
    // this section works backwards from 7 to 0 to see if any of the initialized count variables
    // still has only 4 characters, and if so, will only start the final string with the largest
    // grade with a send or fail
    else if (countSeven.length() != 4) {
      finalString = countZero + countOne + countTwo + countThree + countFour + countFive + countSix
          + countSeven;
    } else if (countSix.length() != 4) {
      finalString = countZero + countOne + countTwo + countThree + countFour + countFive + countSix;
    } else if (countFive.length() != 4) {
      finalString = countZero + countOne + countTwo + countThree + countFour + countFive;
    } else if (countFour.length() != 4) {
      finalString = countZero + countOne + countTwo + countThree + countFour;
    } else if (countThree.length() != 4) {
      finalString = countZero + countOne + countTwo + countThree;
    } else if (countTwo.length() != 4) {
      finalString = countZero + countOne + countTwo;
    } else if (countOne.length() != 4) {
      finalString = countZero + countOne;
    } else if (countZero.length() != 4) {
      finalString = countZero;
    }
    // returning the final string
    return finalString;
  }
}