/////////////////////////////////////////////////////////////////////////////////
//
// Title: Program 2: Fish Tank
// Course: CS 300 Fall 2021
//
// Author: Avery Crane
// Email: adcrane@wisc.edu
// Lecturer: Hobbes LeGault
//
////////////////////////////////////////////////////////////////////////////////
//
// Persons: NONE
// Online Sources:
//////////////// https://stackoverflow.com/questions/8410294/why-does-printlnarray-have-strange-output-ljava-lang-string3e25a5
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class uses multiple methods for placing fish on an aquarium background, allowing the user to
 * add, remove, and drag fish
 */
public class FishTank {
  // PApplet object that represents the graphic interface of the JunglePark application
  private static PApplet processing;
  // PImage object that represents the background image
  private static PImage backgroundImage;
  // perfect size array storing the different fish present in the fish tank. These fish can be of
  // different species.
  private static Fish[] fishes;
  // Generator of random numbers
  private static Random randGen;

  public static void main(String[] args) {
    Utility.startApplication();
  }

  /**
   * Defines the initial environment properties of this application
   * 
   * @param processingObj a reference to the graphic display window of this application
   */
  public static void setup(PApplet processingObj) {
    // Initializes the Random object
    FishTank.randGen = new Random();
    // Sets processing to the processingObj
    processing = processingObj;
    // Creates the fish array and sets the size to 8
    fishes = new Fish[8];
    // load the image of the background
    backgroundImage = processing.loadImage("images/background.png");
  }

  /**
   * Draws and updates the application display window. This callback method called in an infinite
   * loop.
   */
  public static void draw() {
    // Draw the background image at the center of the screen
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    for (int i = 0; i < fishes.length; i++) {
      if (fishes[i] != null)
        fishes[i].draw();
    }
  }

  /**
   * Checks if the mouse is over a specific Fish whose reference is provided as input parameter
   *
   * @param Fish reference to a specific fish
   * @return true if the mouse is over the specific Fish object (i.e. over the image of the Fish),
   *         false otherwise
   */
  public static boolean isMouseOver(Fish fish) {
    // Set the width and height of the image to w and h
    int w = fish.getImage().width;
    int h = fish.getImage().height;
    // Set the position of the fish's coordinates to x and y
    float x = fish.getPositionX();
    float y = fish.getPositionY();
    // Sets the mouses coordinates to mouseX and mouseY
    float mouseX = processing.mouseX;
    float mouseY = processing.mouseY;
    // Sets the left and right bounds of the x and y axis by taking the center of the image and
    // accounting for half of the width in either direction, then setting it to a bound
    float rightBoundX = x + (w / 2);
    float rightBoundY = y + (h / 2);
    float leftBoundX = x - (w / 2);
    float leftBoundY = y - (h / 2);
    // Checks if the mouses coordinates are within the bounds created, and if so, then isMouseOver
    // is true, else false
    if (mouseX >= leftBoundX && mouseX <= rightBoundX && mouseY >= leftBoundY
        && mouseY <= rightBoundY) {
      return true;
    }
    return false;
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    // Runs through all of the fishes, and will set the appropriate fish setDragging() to true
    for (int i = 0; i < fishes.length; i++) {
      if (fishes[i] != null) {
        if (isMouseOver(fishes[i]) == true) {
          fishes[i].setDragging(true);
          // kick out of loop once the lowest fish is found so multiple cannot be dragged
          i = 8;
        }
      }
    }
  }

  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    // Runs through all of the fishes in the array and will set dragging to false on the appropriate
    // fish
    for (int i = 0; i < fishes.length; i++) {
      if (fishes[i] != null) {
        fishes[i].setDragging(false);
      }
    }
  }

  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {
    float x = 0;
    float y = 0;
    // generates a random x-position of type float within the width of the display window
    x = (float) randGen.nextInt(processing.width);
    // generates a random y-position of type float within the height of the display window
    y = (float) randGen.nextInt(processing.height);
    // Runs through all of the fishes in the array
    for (int i = 0; i < fishes.length; i++) {
      // Will test if the array spot i is a null
      if (fishes[i] == null) {
        // If it is a null, and the key f was pressed, then a new fish image will be added to that i
        // position in the array
        if (processing.key == 'f' || processing.key == 'F') {
          fishes[i] = new Fish(processing, processing.width / 2, processing.height / 2);
          fishes[i].setPositionX(x);
          fishes[i].setPositionY(y);
          // Kick out of the loop by setting i to 8
          i = 8;
        }
        // If it was not a null and or the r key was pressed, and the mouse is hovering over a fish,
        // then that fish being hovered over will be set to null, removing it from the screen
      } else if ((processing.key == 'r' || processing.key == 'R') && isMouseOver(fishes[i])) {
        fishes[i] = null;
        // Kick out of the loop by setting i to 8
        i = 8;
      }
    }
  }
}
