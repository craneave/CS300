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
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class uses multiple methods for setting up the information to be used to set fish onto the
 * screen, drag them, and make them swim, all under the users control
 */

public class Fish {
  // PApplet object that represents the graphic interface of the JunglePark application
  private static PApplet processing;
  // PImage object that represents the background image
  private PImage image;
  // float x position of fish
  private float x;
  // float y position of fish
  private float y;
  // swimming speed of the fish
  private int speed;
  // for if fish is dragging
  private boolean isDragging;
  // for if fish is swimming
  private boolean isSwimming;
  // stores the old x-position of the mouse
  private static int oldMouseX;
  // stores the old y-position of the mouse
  private static int oldMouseY;

  /**
   * Creates a new fish object located at a specific (x, y) position of the display window
   * 
   * @param processing        a PApplet object that represents the display window of the Fish Tank
   *                          application
   * @param x                 a reference to the x position of object
   * @param y                 a reference to the y position of object
   * @param speed             a reference to the swim speed
   * @param fishImageFileName a reference to which decoration
   */
  public Fish(PApplet processing, float x, float y, int speed, String fishImageFileName) {
    // processing PApplet object that represents the display window
    this.processing = processing;
    // x x-position of the image of this fish in the display window
    this.x = x;
    // y y-position of the image of this fish in the display window
    this.y = y;
    // speed the swimming speed of this fish
    this.speed = speed;
    // fishImageFileName file name of the image of the fish to be created
    this.image = processing.loadImage(fishImageFileName);
  }

  /**
   * Creates a new fish object positioned at the center of the display window.
   * 
   * @param processing a PApplet object that represents the display window of the Fish Tank
   *                   application
   */
  public Fish(PApplet processing) {
    // processing PApplet object that represents the display window
    this.processing = processing;
    // This constructor sets the image instance field to a PImage whose file name is "images" +
    // File.separator + "orange.png"
    this.image = processing.loadImage("images" + File.separator + "orange.png");
    // Sets speed instance field to 5
    speed = 5;
    // Sets the x and y position of the fish to the center of the display window
    x = processing.width / 2;
    y = processing.height / 2;
    // The created fish won't be dragging nor swimming.
    isDragging = false;
    isSwimming = false;
  }

  /**
   * Getter method of fish image
   * 
   * @returns image of the fish
   */
  public PImage getImage() {
    // getter of the image instance field
    return image;
  }

  /**
   * Getter method of x position
   * 
   * @returns x position of the fish in the window
   */
  public float getPositionX() {
    // getter of the x-position of this fish
    return x;
  }

  /**
   * Getter method of y position
   * 
   * @returns y position of the fish in the window
   */
  public float getPositionY() {
    // getter of the y-position of this fish
    return y;
  }

  /**
   * Moves this fish with dx dy
   * 
   */
  public void move(int dx, int dy) {
    // adds dx move to the x-position of this fish
    x += dx;
    // adds dy move to the y-position of this fish
    y += dy;
  }

  /**
   * Checks if fish is being dragged
   * 
   * @returns true if fish is being dragged, otherwise false
   */
  public boolean isDragging() {
    // a getter for the isDragging instance field
    return isDragging;
  }

  /**
   * Starts dragging this fish and sets old mouse positions to the current ones
   * 
   */
  public void startDragging() {
    // sets oldMouseX data field to the current x-position of the mouse
    Fish.oldMouseX = processing.mouseX;
    // sets oldMouseY data field to the current y-position of the mouse
    Fish.oldMouseY = processing.mouseY;
    // sets the isDragging data field of this fish to true
    isDragging = true;
  }

  /**
   * Stops dragging this fish
   * 
   */
  public void stopDragging() {
    // sets the isDragging data field of this fish to false
    isDragging = false;
  }

  /**
   * Starts the swimming for the fish
   * 
   */
  public void startSwimming() {
    // stops dragging the fish
    isDragging = false;
    // sets the isSwimming instance field to true
    isSwimming = true;
  }

  /**
   * Stops swimming for the fish
   * 
   */
  public void stopSwimming() {
    // Sets the isSwimming instance field of this fish to false
    isSwimming = false;
  }

  /**
   * Moves horizontally the fish one speed step from left to right *
   * 
   */
  public void swim() {
    // If the fish reaches the end of any sized screen, restart it back to the other side
    x = (x + speed) % processing.width;
    // draw the fish
    processing.image(image, x, y);
  }

  /**
   * Draws he fish and will account for dragging and swimming
   * 
   */
  public void draw() {
    int dx = 0;
    int dy = 0;

    // checks if fish is swimming, if so use swim method
    if (isSwimming) {
      swim();
    }
    // checks if fish is dragging, if so set dx and dy to the current mouse position minus the old
    if (isDragging() == true) {
      dx = processing.mouseX - oldMouseX;
      dy = processing.mouseY - oldMouseY;
      // send dx dy through the move method
      move(dx, dy);
      // reinitialize oldMouseX and oldMouseY to the last position
      oldMouseX = (int) x;
      oldMouseY = (int) y;
    }
    // draw the fish
    processing.image(image, x, y);

  }

  /**
   * Checks if the mouse is over a given fish whose reference is provided as input parameter
   * 
   * @param fish reference to a given fish object
   * @return true if the mouse is over the given fish object (i.e. over the image of the fish),
   *         false otherwise
   */
  public boolean isMouseOver() {
    int fishWidth = this.getImage().width;
    int fishHeight = this.getImage().height;

    // checks if the mouse is over the provided fish
    return processing.mouseX >= this.getPositionX() - fishWidth / 2
        && processing.mouseX <= this.getPositionX() + fishWidth / 2
        && processing.mouseY >= this.getPositionY() - fishHeight / 2
        && processing.mouseY <= this.getPositionY() + fishHeight / 2;
  }
}
