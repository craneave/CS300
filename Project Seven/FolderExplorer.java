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
import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class uses recursion to find files from a directory in different ways, such as by type of
 * file, size, and name
 */
public class FolderExplorer {
  /**
   * Returns a list of the names of all files and directories in the the given folder
   * currentDirectory
   * 
   * @param currentDirectory of folder to be searched
   * @Throws NotDirectoryException with a description error message if the provided currentDirectory
   *         does not exist or if it is not a directory
   * @return arrayList of strings of the items in the given directory
   */
  public static ArrayList<String> getContents(File currentDirectory) throws NotDirectoryException {
    // initialize arraylist to store the contents of the folder
    ArrayList<String> contents = new ArrayList<String>();
    // if the directory does not exist or is not a directory then throw an exception
    if (!currentDirectory.exists() || !currentDirectory.isDirectory())
      throw new NotDirectoryException("This is not a directory");
    // a holder for the currentDirectory
    String[] holder = currentDirectory.list();
    for (int i = 0; i < holder.length; i++) {
      // goes through the directory, and adds them to the contents array
      contents.add(holder[i]);
    }
    // returns contents
    return contents;
  }

  /**
   * Recursive method that lists the names of all the files (not directories) in the given directory
   * and its sub-directories.
   * 
   * @param currentDirectory of folder to be searched
   * @Throws NotDirectoryException with a description error message if the provided currentDirectory
   *         does not exist or if it is not a directory
   * @return arrayList of strings of the items in the given directory
   */
  public static ArrayList<String> getDeepContents(File currentDirectory)
      throws NotDirectoryException {
    // initializes the contents array
    ArrayList<String> contents = new ArrayList<String>();
    // if the directory does not exist or is not a directory then throw an exception
    if (!currentDirectory.exists() || !currentDirectory.isDirectory())
      throw new NotDirectoryException("This is not a directory");
    // for loop that will go through all of the files inside of the directory passed
    for (File files : currentDirectory.listFiles()) {
      // checks if 'files' is a file
      if (files.isFile() == true) // if it is, then add it to the contents array
        contents.add(files.getName());
      else // if it is not, then recurse through the method, adding only files
        contents.addAll(getDeepContents(files));
    }
    // return contents
    return contents;
  }

  /**
   * Uses a helper method and decides if it is valid, and if so, returns it
   * 
   * @param currentDirectory of folder to be searched
   * @param fileName         of the file to be searched for
   * @Throws NoSuchElementException with a descriptive error message if the search operation returns
   *         with no results found (including the case if fileName is null or currentDirectory does
   *         not exist, or was not a directory)
   * @returns a string with the path of the given file looked for
   * 
   */
  public static String lookupByName(File currentDirectory, String fileName)
      throws NoSuchElementException {
    // these three if statements check if fileName is null, if currentDirectory exists, and if
    // any results were found from the recursion
    if (fileName == null)
      throw new NoSuchElementException("Name is null");
    if (currentDirectory.exists() == false)
      throw new NoSuchElementException("This directory doesnt exist");
    if (lookupByNameHelper(currentDirectory, fileName) == null)
      throw new NoSuchElementException("This file was not found");
    // if fileName was found, then return the string
    return lookupByNameHelper(currentDirectory, fileName);
  }

  /**
   * The helper method for the lookupByNameMethod(), this method handles all of the recursion and
   * stores the fileName path found into a string
   * 
   * @param currentDirectory of folder to be searched
   * @param fileName         of the file to be searched for
   * @returns a string with the path of the given file looked for
   * 
   */
  private static String lookupByNameHelper(File currentDirectory, String fileName) {
    // if currentDirectory is a directory then begin the for loop searching through the files
    if (currentDirectory.isDirectory()) {
      File[] arr = currentDirectory.listFiles();
      for (File f : arr) {
        // set a string named found to be equal to the return of recurring through the files
        String found = lookupByNameHelper(f, fileName);
        // once found is not null, that means the file was found, so return the file
        if (found != null)
          return found;
      }
    } else {
      // once currentDirectory is not a directory, check if it is the one we are looking for, and if
      // it is, then return the currentDirectory (file we are looking for) and its path
      if (currentDirectory.getName().equals(fileName))
        return currentDirectory.getPath();
    }
    // return null if found is never not null, meaning the file was never found
    return null;
  }

  /**
   * Recursive method that searches the given folder and its sub-directories for ALL files that
   * contain the given key in part of their name.
   * 
   * @param currentDirectory of folder to be searched
   * @param key              of the file to be searched for
   * @returns ArrayList of all the files with the given key
   * 
   */
  public static ArrayList<String> lookupByKey(File currentDirectory, String key) {
    // initialize contents array and the junk array
    ArrayList<String> contents = new ArrayList<String>();
    ArrayList<String> junk = new ArrayList<String>();
    // these if statements check if the currentDirectory is either not real, not a directory, or if
    // key is null
    if (currentDirectory.exists() == false)
      return contents;
    if (currentDirectory.isDirectory() == false)
      return contents;
    if (key == null)
      return contents;
    // begin for loop for searching through the files in the directory
    for (File files : currentDirectory.listFiles()) {
      // if the file is a file
      if (files.isFile() == true) {
        // then set variable index to the index in which this given file has the '.' positioned,
        // meaning that anything after the period is the type of file it is
        int index = files.getName().lastIndexOf('.');
        // if files substring from the period to the end of the string does not equal the key we are
        // searching for, then add the contents of that file to our junk array, this is to help
        // avoid errors, so I found that placing these extra files into a junk array can help
        if (files.getName().substring(index).equals(key) == false)
          junk.add(files.getName());
        else // if the file substring is equal to our key, then add this file to our contents array
          contents.add(files.getName());
      } else // if files is not a file, then keep adding the recursive contents to our contents
             // array
        contents.addAll(lookupByKey(files, key));
    }
    // return contents
    return contents;
  }

  /**
   * Recursive method that searches the given folder and its sub-directories for ALL files whose
   * size is within the given max and min values, inclusive
   * 
   * @param currentDirectory of folder to be searched
   * @param sizeMin          long of the min size searching for
   * @param sizeMin          long of the max size searching for
   * @returns ArrayList of all the files within the given range of file size
   * 
   */
  public static ArrayList<String> lookupBySize(File currentDirectory, long sizeMin, long sizeMax) {
    // initialize the two arrays again
    ArrayList<String> contents = new ArrayList<String>();
    ArrayList<String> junk = new ArrayList<String>();
    // if the current directory doesn't exist or if it is not a directory, then return the contents
    // array
    if (currentDirectory.exists() == false)
      return contents;
    if (currentDirectory.isDirectory() == false)
      return contents;
    // begin the loop through the files in the directory
    for (File files : currentDirectory.listFiles()) {
      // if files is a file
      if (files.isFile() == true) {
        // set the size of the file to variable size
        long size = files.length();
        // if the size of the file fits in the range given, then add the file to our contents array
        // list
        if (size >= sizeMin && size <= sizeMax)
          contents.add(files.getName());
        else // if not, then add the file to our junk array, again to just help avoid lose ends and
             // errors
          junk.add(files.getName());
      } else // if files is not a file, then recurse through the method again, adding the contents
             // to contents array list
        contents.addAll(lookupBySize(files, sizeMin, sizeMax));
    }
    // return contents
    return contents;
  }
}
