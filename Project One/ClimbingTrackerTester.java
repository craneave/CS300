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
 * This class uses multiple methods to test the methods tracking rock climbing grades and previous
 * progress
 */
public class ClimbingTrackerTester {
  /**
   * This is the main, and it tests if all the tests are true
   */
  public static void main(String[] args) {

    runAllTests();
  }

  /**
   * Tests the sendClimb method in three different ways
   * 
   * @return true if the tests pass, and fail if they do not
   */
  public static boolean testSendClimb() {
    // declare three different types of way to test the method with what the expected output should
    // be
    String[] sendArray = new String[] {"V1", "V2", null, null};
    int testSendNum = 2;
    String testSendGrade = "V0";
    int sendExpected = 3;
    String[] sendArray2 = new String[] {"V1", "V2"};
    int testSendNum2 = 2;
    String testSendGrade2 = "V6";
    int sendExpected2 = 2;
    String[] sendArray3 = new String[] {"V6", "V2", "V3", null, null};
    int testSendNum3 = 3;
    String testSendGrade3 = "NOT VALID";
    int sendExpected3 = 3;
    // send all three methods through and if they all come back as expected, then return true
    if (ClimbingTracker.sendClimb(sendArray, testSendNum, testSendGrade) == sendExpected
        && ClimbingTracker.sendClimb(sendArray2, testSendNum2, testSendGrade2) == sendExpected2
        && ClimbingTracker.sendClimb(sendArray3, testSendNum3, testSendGrade3) == sendExpected3) {
      return true;
    }

    return false;
  }

  /**
   * Tests the failClimb method in three different ways
   * 
   * @return true if the tests pass, and fail if they do not
   */
  public static boolean testFailClimb() {
    // declare three different types of way to test the method with what the expected output should
    // be
    String[] failArray = new String[] {};
    int testFailNum = 0;
    String testFailGrade = "V0";
    int failExpected = 0;
    String[] failArray2 = new String[] {"V2", "V3", "V6", null, null};
    int testFailNum2 = 3;
    String testFailGrade2 = "V4";
    int failExpected2 = 4;
    String[] failArray3 = new String[] {"V2", "V3", "V1", null, null, null};
    int testFailNum3 = 3;
    String testFailGrade3 = "NOT VALID";
    int failExpected3 = 3;
    // send all three methods through and if they all come back as expected, then return true
    if (ClimbingTracker.failClimb(failArray, testFailNum, testFailGrade) == failExpected
        && ClimbingTracker.failClimb(failArray2, testFailNum2, testFailGrade2) == failExpected2
        && ClimbingTracker.failClimb(failArray3, testFailNum3, testFailGrade3) == failExpected3) {
      return true;
    }

    return false;
  }

  /**
   * Tests the getHistogram method in three different ways
   * 
   * @return true if the tests pass, and fail if they do not
   */
  public static boolean testGetHistogram() {
    // declare three different types of way to test the method with what the expected output should
    // be
    String[] sendArray = new String[] {"V0", "V0", "V0", "V0", "V0", null, null};
    String[] failArray = new String[] {"V0", "V0", "V0", null};
    int numSend = 5;
    int numFail = 3;
    String[] sendArray2 = new String[] {"V2", "V2", "V2", "V2", "V2", null, null};
    String[] failArray2 = new String[] {"V2", "V2", null, null};
    int numSend2 = 5;
    int numFail2 = 2;
    String[] sendArray3 = new String[] {};
    String[] failArray3 = new String[] {};
    int numSend3 = 0;
    int numFail3 = 0;
    String[] sendArray4 = new String[] {"V1", null, null, null, null};
    String[] failArray4 = new String[] {"V1"};
    int numSend4 = 1;
    int numFail4 = 0;
    // send all three methods through and if they all come back as expected, then return true
    if (ClimbingTracker.getHistogram(sendArray, numSend, failArray, numFail)
        .equals("V0: - - - + + + + +")
        && ClimbingTracker.getHistogram(sendArray2, numSend2, failArray2, numFail2)
            .equals("V0:\nV1:\nV2: - - + + + + +")
        && ClimbingTracker.getHistogram(sendArray3, numSend3, failArray3, numFail3)
            .equals("Error: no data to display")
        && ClimbingTracker.getHistogram(sendArray4, numSend4, failArray4, numFail4)
            .equals("V0:\nV1: +")) {
      return true;
    }

    return false;

  }

  /**
   * Tests the getStats method in three different ways
   * 
   * @return true if the tests pass, and fail if they do not
   */
  public static boolean testGetStats() {
    // declare three different types of way to test the method with what the expected output should
    // be
    String[] sendArray = new String[] {"V6", "V5", "V2", "V3", "V2", null, null};
    String[] failArray = new String[] {"V2", "V7", "V2", "V5", "V3", null, null};
    int numSend = 5;
    int numFail = 5;
    int historyLength = 2;
    String[] sendArray2 = new String[] {"V6", "V3", "V0", "V3", null, null, null};
    String[] failArray2 = new String[] {null, null};
    int numSend2 = 4;
    int numFail2 = 0;
    int historyLength2 = 2;
    String[] sendArray3 = new String[] {"V0", "V1", "V7", "V7", "V5", "V2"};
    String[] failArray3 = new String[] {"V1", "V2", "V0", "V2"};
    int numSend3 = 6;
    int numFail3 = 4;
    int historyLength3 = 2;
    String[] sendArray4 = new String[] {};
    String[] failArray4 = new String[] {};
    int numSend4 = 0;
    int numFail4 = 0;
    int historyLength4 = 0;
    // send all methods through and if they all come back as expected, then return true
    if (ClimbingTracker.getStats(sendArray, numSend, failArray, numFail, historyLength)
        .equals("Send: " + 2.5 + "\nFail: " + 4.0)
        && ClimbingTracker.getStats(sendArray2, numSend2, failArray2, numFail2, historyLength2)
            .equals("Send: " + 1.5 + "\nFail: " + "--")
        && ClimbingTracker.getStats(sendArray3, numSend3, failArray3, numFail3, historyLength3)
            .equals("Send: " + 3.5 + "\nFail: " + 1.0)
        && ClimbingTracker.getStats(sendArray4, numSend4, failArray4, numFail4, historyLength4)
            .equals("Send: --\nFail: --")) {
      return true;
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


