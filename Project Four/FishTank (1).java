/////////////////////////////////////////////////////////////////////////////////
//
// Title: Program 4: Fish Tank
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
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class uses multiple methods for placing decorations and fish on an aquarium background,
 * allowing the user to move them and for the fish to swim, controlled by user
 */

public class FishTank {
  // PApplet object which represents the graphic interface of the Fish Tank application
  private static PApplet processing;
  // PImage object which represents the background image
  private static PImage backgroundImage;
  // array storing the current fishes present in the tank
  private static Fish[] fishes;
  // Generator of random numbers
  private static Random randGen;
  // circular indexed array of fish images names
  private static String[] images =
      new String[] {"orange.png", "blue.png", "yellow.png", "black.png"};
  // index of next fish image index in the circular array images
  private static int nextImageIndex;
  // Speed at which the fish can be set to swim at
  private static int fishSpeed;
  // array storing the decoration objects present in the tank
  private static Decoration[] decorations;

  /**
   * Defines initial environment properties such as screen size and to load background images and
   * fonts as the program starts. It also initializes all data fields.
   * 
   * @param processingObj a PApplet object that represents the display window of the Fish Tank
   *                      application
   */
  public static void setup(PApplet processingObj) {
    processing = processingObj;
    backgroundImage = processing.loadImage("images" + File.separator + "background.png");
    fishes = new Fish[8];
    randGen = new Random();
    fishSpeed = 5;
    decorations = new Decoration[4];
    decorations[0] = new Decoration(processing, 430, 60, "images" + File.separator + "flower.png");
    decorations[1] = new Decoration(processing, 580, 470, "images" + File.separator + "log.png");
    decorations[2] = new Decoration(processing, 65, 520, "images" + File.separator + "shell.png");
    decorations[3] = new Decoration(processing, 280, 535, "images" + File.separator + "ship.png");
  }


  /**
   * Continuously draws and updates the application display window
   * 
   */
  public static void draw() {
    // clear the display window by drawing the background image
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    // traverse the fishes array and draw each of the fish present in the tank
    for (int i = 0; i < fishes.length; i++) {
      if (i < 4) {
        decorations[i].draw();
      }
      if (fishes[i] != null)
        fishes[i].draw();
    }
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    // traverse the fishes array and start dragging a fish if the mouse is over it
    for (int i = 0; i < fishes.length; i++) {
      if (fishes[i] != null && (fishes[i]).isMouseOver()) {
        fishes[i].startDragging();
        // only the fish at the lowest index will start dragging if there are fishes overlapping
        break;
      }
    }
    for (int i = 0; i < decorations.length; i++) {
      if (decorations[i] != null && (decorations[i]).isMouseOver()) {
        decorations[i].startDragging();
        // only the decoration at the lowest index will start dragging if there are fishes
        // overlapping
        break;
      }
    }
  }

  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    // traverse the fishes array and stop dragging any fish
    for (int i = 0; i < fishes.length; i++) {
      if (fishes[i] != null)
        fishes[i].stopDragging();
      if (i < 4) {
        if (decorations[i] != null)
          decorations[i].stopDragging();
      }
    }
  }

  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {
    switch (Character.toUpperCase(processing.key)) {
      // add a new fish if the maximum numbers of fish allowed to be present in the tank is not
      // reached
      case 'F':
        for (int i = 0; i < fishes.length; i++) {
          if (fishes[i] == null) {
            fishes[i] = new Fish(processing, (float) randGen.nextInt(processing.width),
                (float) randGen.nextInt(processing.height), fishSpeed,
                "images" + File.separator + images[FishTank.nextImageIndex]);
            nextImageIndex = (nextImageIndex + 1) % images.length;
            System.out.println(nextImageIndex);
            break;
          }
        }
        break;
      // delete the clicked fish if any
      case 'R':
        for (int i = 0; i < fishes.length; i++) {
          if (fishes[i] != null && (fishes[i]).isMouseOver()) {
            fishes[i] = null;
            break;
          }
        }
        break;
      // when 'S' is pressed, any fishes on the screen will begin to swim
      case 'S':
        for (int i = 0; i < fishes.length; i++) {
          if (fishes[i] != null) {
            fishes[i].startSwimming();
          }
        }
        break;
      // when 'X' is pressed, any fishes on the screen will stop swimming
      case 'X':
        for (int i = 0; i < fishes.length; i++) {
          if (fishes[i] != null) {
            fishes[i].stopSwimming();
          }
        }
    }
  }

  /**
   * This main method starts the application
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    // starts the application
    Utility.startApplication();
  }
}
