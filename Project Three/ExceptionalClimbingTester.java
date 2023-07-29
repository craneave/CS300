//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Program 3: Exceptional Climbing
// Course: CS 300 Fall 2021
//
// Author: Avery Crane
// Email: adcrane@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources:https://stackoverflow.com
//
///////////////////////////////////////////////////////////////////////////////
import java.util.zip.DataFormatException;

/**
 * This class uses multiple methods to test the methods tracking rock climbing grades and previous
 * progress
 */
public class ExceptionalClimbingTester {
  /**
   * This is the main, and it tests if all the tests are true
   */
  public static void main(String[] args) {

    runAllTests();
  }

  /**
   * Tests the sendClimb method in four different ways
   * 
   * @return true if the tests pass, and fail if they do not
   */
  public static boolean testSendClimb() {
    // declare different types of ways to test the method with what the expected output should
    // be
    String[] sendArray = new String[] {"V3", null,};
    int testSendNum = 1;
    String testSendGrade = "V4";
    String[] sendArray2 = new String[] {"V1", null, "V2"};
    int testSendNum2 = 2;
    String testSendGrade2 = "V6";
    String[] sendArray3 = new String[] {"V1", "V3", null};
    int testSendNum3 = 2;
    String testSendGrade3 = "V9";
    String[] sendArray4 = new String[] {"V1"};
    int testSendNum4 = 1;
    String testSendGrade4 = "V2";
    // trying each different scenario individually, and if it catches the specific exception it
    // should be throwing, returns true, but if it catches something unexpected, returns false
    try {
      ExceptionalClimbing.sendClimb(sendArray, testSendNum, testSendGrade);
    }
    // catching any exception, and returning false because its not the specific exception (if any)
    // that is expected
    catch (Exception e) {
      return false;
    }
    try {
      ExceptionalClimbing.sendClimb(sendArray3, testSendNum3, testSendGrade3);
    } catch (IllegalArgumentException ill) {
      // testing if the message equals what its supposed to, and returning false if not
      if (!ill.getMessage().equals(testSendGrade3 + " is not a valid grade")) {
        return false;
      }
      return true;
    } catch (Exception e) {
      return false;
    }
    try {
      ExceptionalClimbing.sendClimb(sendArray4, testSendNum4, testSendGrade4);
    } catch (IllegalArgumentException ill) {
      // testing if the message equals what its supposed to, and returning false if not
      if (!ill.getMessage()
          .equals("cannot add new value to full length " + sendArray4.length + " array")) {
        return false;
      }
      return true;
    } catch (Exception e) {
      return false;
    }
    try {
      ExceptionalClimbing.sendClimb(sendArray2, testSendNum2, testSendGrade2);
    } catch (DataFormatException exc) {
      // testing if the message equals what its supposed to, and returning false if not
      if (!exc.getMessage().equals("invalid oversized array")) {
        return false;
      }
      return true;
    } catch (Exception e) {
      return false;
    }
    return false;
  }

  public static boolean testFailClimb() {
    // declare the different types of way to test the method with what the expected output should
    // be
    String[] failArray = new String[] {"V3", null,};
    int testFailNum = 1;
    String testFailGrade = "V4";
    String[] failArray2 = new String[] {"V1", null, "V2"};
    int testFailNum2 = 2;
    String testFailGrade2 = "V6";
    String[] failArray3 = new String[] {"V1", "V3"};
    int testFailNum3 = 2;
    String testFailGrade3 = "V6";
    String[] failArray4 = new String[] {"V1", null};
    int testFailNum4 = -2;
    String testFailGrade4 = "V9";
    // trying each different scenario individually, and if it catches the specific exception it
    // should be throwing, returns true, but if it catches something unexpected, returns false
    try {
      ExceptionalClimbing.failClimb(failArray, testFailNum, testFailGrade);
    } // catching any exception, and returning false because its not the specific exception (if any)
    // that is expected
    catch (Exception e) {
      return false;
    }
    try {
      ExceptionalClimbing.failClimb(failArray4, testFailNum4, testFailGrade4);
    } catch (IllegalArgumentException ill) {
      // testing if the message equals what its supposed to, and returning false if not
      if (!ill.getMessage().equals(testFailGrade4 + " is not a valid grade")) {
        return false;
      }
      return true;
    } catch (Exception e) {
      return false;
    }
    try {
      ExceptionalClimbing.failClimb(failArray3, testFailNum3, testFailGrade3);
    } catch (IllegalArgumentException ill) {
      // testing if the message equals what its supposed to, and returning false if not
      if (!ill.getMessage()
          .equals("cannot add new value to full length " + failArray3.length + " array")) {
        return false;
      }
      return true;
    } catch (Exception e) {
      return false;
    }
    try {
      ExceptionalClimbing.failClimb(failArray2, testFailNum2, testFailGrade2);
    } catch (DataFormatException exc) {
      // testing if the message equals what its supposed to, and returning false if not
      if (!exc.getMessage().equals("invalid oversized array")) {
        return false;
      }
      return true;
    } catch (Exception e) {
      return false;
    }

    return false;
  }

  /**
   * Tests the getStats method in three different ways
   * 
   * @return true if the tests pass, and fail if they do not
   */
  public static boolean testGetStats() {
    // declare the different types of way to test the method with what the expected output should
    // be
    String[] sendArray = new String[] {"V1", "V2", null, null};
    String[] failArray = new String[] {"V2", "V2", null, null};
    int numSend = 2;
    int numFail = 2;
    int historyLength = 1;
    String[] sendArray2 = new String[] {null, null};
    String[] failArray2 = new String[] {null, null};
    int numSend2 = 0;
    int numFail2 = 0;
    int historyLength2 = 2;
    String[] sendArray3 = new String[] {"V1", null};
    String[] failArray3 = new String[] {"V5", "V4"};
    int numSend3 = 1;
    int numFail3 = 2;
    int historyLength3 = -8;
    // trying each different scenario individually, and if it catches the specific exception it
    // should be throwing, returns true, but if it catches something unexpected, returns false
    try {
      ExceptionalClimbing.getStats(sendArray, numSend, failArray, numFail, historyLength);
    } // catching any exception, and returning false because its not the specific exception (if any)
    // that is expected
    catch (Exception exc) {
      return false;
    }
    try {
      ExceptionalClimbing.getStats(sendArray2, numSend2, failArray2, numFail2, historyLength2);
    } catch (RuntimeException run) {
      // testing if the message equals what its supposed to, and returning false if not
      if (!run.getMessage().equals("no climbs provided")) {
        return false;
      }
      return true;
    } catch (Exception exc) {
      return false;
    }
    try {
      ExceptionalClimbing.getStats(sendArray3, numSend3, failArray3, numFail3, historyLength3);
    } catch (IllegalArgumentException ill) {
      // testing if the message equals what its supposed to, and returning false if not
      if (!ill.getMessage().equals(historyLength3 + " is not a valid history length")) {
        return false;
      }
      return true;
    } catch (Exception exc) {
      return false;
    }
    return false;
  }

  /**
   * Tests the getHistogram method in three different ways
   * 
   * @return true if the tests pass, and fail if they do not
   */
  public static boolean testGetHistogram() {
    // declare the different types of way to test the method with what the expected output should
    // be
    String[] sendArray = new String[] {"V2", "V3", null};
    String[] failArray = new String[] {"V5", "V5", "V4"};
    int numSend = 2;
    int numFail = 3;
    String[] sendArray2 = new String[] {null};
    String[] failArray2 = new String[] {null};
    int numSend2 = 0;
    int numFail2 = 0;
    // trying each different scenario individually, and if it catches the specific exception it
    // should be throwing, returns true, but if it catches something unexpected, returns false
    try {
      ExceptionalClimbing.getHistogram(sendArray, numSend, failArray, numFail);
    }
    // catching any exception, and returning false because its not the specific exception (if any)
    // that is expected
    catch (Exception exc) {
      return false;
    }
    try {
      ExceptionalClimbing.getHistogram(sendArray2, numSend2, failArray2, numFail2);
    } catch (RuntimeException run) {
      // testing if the message equals what its supposed to, and returning false if not
      if (!run.getMessage().equals("no climbs provided")) {
        return false;
      }
      return true;
    } catch (Exception exc) {
      return false;
    }
    return false;
  }

  /**
   * Tests all of the test methods
   * 
   * @return true if the test methods all pass, and fail if they do not
   */
  public static boolean runAllTests() {
    // tests if they are all true, and if so returns true otherwise false
    if (testSendClimb() == true && testFailClimb() == true && testGetStats() == true
        && testGetHistogram() == true) {
      return true;
    }
    return false;
  }

}
