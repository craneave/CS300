
/////////////////////////////////////////////////////////////////////////////////
//
// Title: Program 7: Folder Explorer
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
import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This is meant to test the folder explorer class and all of its methods
 */
public class FolderExplorerTester {
  /**
   * This method is designed to test the getContents() method
   * 
   * @param folder to be dug into
   * @return boolean, true if test passes, otherwise fails
   */
  public static boolean testGetContents(File folder) {
    try {
      // Scenario 1
      // list the basic contents of the cs300 folder
      ArrayList<String> listContent = FolderExplorer.getContents(folder);
      // expected output must contain "exams preparation", "grades",
      // "lecture notes", "programs", "reading notes", "syllabus.txt",
      // and "todo.txt" only.
      String[] contents = new String[] {"exams preparation", "grades", "lecture notes", "programs",
          "reading notes", "syllabus.txt", "todo.txt"};
      List<String> expectedList = Arrays.asList(contents);
      // check the size and the contents of the output
      if (listContent.size() != 7) {
        System.out.println("Problem detected: cs300 folder must contain 7 elements.");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++) {
        if (!listContent.contains(expectedList.get(i))) {
          System.out.println("Problem detected: " + expectedList.get(i)
              + " is missing from the output of the list contents of cs300 folder.");
          return false;
        }
      }
      // Scenario 2 - list the contents of the grades folder
      File f = new File(folder.getPath() + File.separator + "grades");
      listContent = FolderExplorer.getContents(f);
      if (listContent.size() != 0) {
        System.out.println("Problem detected: grades folder must be empty.");
        return false;
      }
      // Scenario 3 - list the contents of the p02 folder
      f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p02");
      listContent = FolderExplorer.getContents(f);
      if (listContent.size() != 1 || !listContent.contains("FishTank.java")) {
        System.out.println(
            "Problem detected: p02 folder must contain only " + "one file named FishTank.java.");
        return false;
      }

      // Scenario 4 - List the contents of a file
      f = new File(folder.getPath() + File.separator + "todo.txt");
      try {
        listContent = FolderExplorer.getContents(f);
        System.out.println("Problem detected: Your FolderExplorer.getContents() must "
            + "throw a NotDirectoryException if it is provided an input which is not"
            + " a directory.");
        return false;
      } catch (NotDirectoryException e) { // catch only the expected exception
        // no problem detected
      }
      // Scenario 5 - List the contents of not found directory/file
      f = new File(folder.getPath() + File.separator + "music.txt");
      try {
        listContent = FolderExplorer.getContents(f);
        System.out.println("Problem detected: Your FolderExplorer.getContents() must "
            + "throw a NotDirectoryException if the provided File does not exist.");
        return false;
      } catch (NotDirectoryException e) {
        // behavior expected
      }
    } catch (Exception e) {
      System.out.println("Problem detected: Your FolderExplorer.getContents() has thrown"
          + " a non expected exception.");
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * This method is designed to test the base case of the getDeepContents() method
   * 
   * @param folder to be dug into
   * @return boolean, true if test passes, otherwise fails
   */
  public static boolean testDeepGetContentsBaseCase(File folder) {
    // Only scenario, test base case
    try {
      File f = new File(folder.getPath() + File.separator + "syllabus.txt");
      FolderExplorer.getDeepContents(f);
    } catch (NotDirectoryException e) {
      return true;
    }
    System.out.println(
        "Problem detected: Your FolderExplorer.getDeepContents() did not throw an exception for an empty folder");
    return false;
  }

  /**
   * This method is designed to test the recursive case of getDeepContents() method
   * 
   * @param folder to be dug into
   * @return boolean, true if test passes, otherwise fails
   */
  public static boolean testDeepListRecursiveCase(File folder) {
    File g = new File(folder.getPath() + File.separator + "syllabus.txt");
    if (!testDeepGetContentsBaseCase(g)) {
      return false;
    }
    // Scenario 1 - Check if exception is thrown when its not supposed to
    try {
      File f = new File(folder.getPath() + File.separator + "programs");
      FolderExplorer.getDeepContents(f);
    } catch (NotDirectoryException e) {
      System.out.println(
          "Problem detected: Your FolderExplorer.getDeepContents() did  throw an exception for a non empty folder");
      return false;
    }

    // Scenario 2 - check if the contents returned on a folder, return what is expected
    try {
      File f = new File(folder.getPath() + File.separator + "programs");
      ArrayList<String> listContent = FolderExplorer.getDeepContents(f);
      // expected output must contain "ClimbingTracker.java", "ClimbingTrackerTester.java",
      // "FishTank.java", "ExceptionalClimbing.java", "ExceptionalClimbingTester.java",
      // "Program01.pdf", "Program02.pdf", "Program03.pdf"
      String[] contents = new String[] {"ClimbingTracker.java", "ClimbingTrackerTester.java",
          "FishTank.java", "ExceptionalClimbing.java", "ExceptionalClimbingTester.java",
          "Program01.pdf", "Program02.pdf", "Program03.pdf"};
      List<String> expectedList = Arrays.asList(contents);
      // check the size and the contents of the output
      if (listContent.size() != 8) {
        System.out.println("Problem detected: cs300 folder must contain 7 elements.");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++) {
        if (!listContent.contains(expectedList.get(i))) {
          System.out.println("Problem detected: " + expectedList.get(i)
              + " is missing from the output of the list contents of cs300 folder.");
          return false;
        }
      }
    } catch (NotDirectoryException e) {
      System.out.println(
          "Problem detected: Your FolderExplorer.getDeepContents() did  throw an exception for a non empty folder");
      return false;
    }
    return true;
  }

  /**
   * This method is designed to test the lookupByFileName() method
   * 
   * @param folder to be dug into
   * @return boolean, true if test passes, otherwise fails
   */
  public static boolean testLookupByFileName(File folder) {
    // Scenario 1 - Test a regular file
    try {
      if (FolderExplorer.lookupByName(folder, "Recursion.txt")
          .equals("cs300\\lecture notes\\unit3\\Recursion.txt") == false) {
        return false;
      }
    } catch (NoSuchElementException e) {
      return false;
    }
    // Scenario 2 - Test a null file name
    try {
      if (!FolderExplorer.lookupByName(folder, null).equals(""))
        return false;
    } catch (NoSuchElementException e1) {
    }
    // Scenario 3 - Test a no result found
    try {
      FolderExplorer.lookupByName(folder, "Yo");
      return false;
    } catch (NoSuchElementException e) {
    }
    // Scenario 4 - Test an invalid directory
    try {
      if (!FolderExplorer.lookupByName(new File("cs200"), ".pdf").equals(""))
        return false;
    } catch (NoSuchElementException e) {
    }
    return true;
  }

  /**
   * This method is designed to test the bse case of the lookupByKey() method
   * 
   * @param folder to be dug into
   * @return boolean, true if test passes, otherwise fails
   */
  public static boolean testLookupByKeyBaseCase(File folder) {
    // Scenario 1 - Test a regular file
    // Should be a size three array because there are three pdf's in cs300 folder
    ArrayList<String> listContent = FolderExplorer.lookupByKey(folder, ".pdf");
    if (listContent.size() != 3) {
      return false;
    }
    // Scenario 2 - Test a null file name
    ArrayList<String> listContent2 = new ArrayList<String>();
    if (!FolderExplorer.lookupByKey(folder, null).equals(listContent2))
      return false;

    // Scenario 3 - Test a no result found
    if (!FolderExplorer.lookupByKey(folder, "Yo").equals(listContent2))
      return false;

    // Scenario 4 - Test an invalid directory
    if (!FolderExplorer.lookupByKey(new File("cs200"), ".pdf").equals(listContent2))
      return false;

    return true;
  }

  /**
   * This is the main method of the program which prints out the results of all the tests
   * 
   */
  public static void main(String[] args) {
    System.out.println("testGetContents: " + testGetContents(new File("cs300")));
    System.out
        .println("testDeepGetContentsBaseCase: " + testDeepGetContentsBaseCase(new File("cs300")));
    System.out
        .println("testDeepListRecursiveCase: " + testDeepListRecursiveCase(new File("cs300")));
    System.out.println("testLookUpByFileName: " + testLookupByFileName(new File("cs300")));
    System.out.println("testLookupByKeyBaseCase: " + testLookupByKeyBaseCase(new File("cs300")));
  }
}
