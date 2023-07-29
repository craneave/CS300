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
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class uses multiple methods for placing decorations on an aquarium background, allowing the
 * user to move them
 */
public class Decoration {
  // PApplet object that represents the graphic interface of the JunglePark application
  static private PApplet processing;
  // PImage object that represents the decoration image
  private PImage image;
  // float that represents the x position of decoration
  private float x;
  // float that represents the y position of decoration
  private float y;
  // int that represents the x position of old mouse position
  static private int oldMouseX;
  // int that represents the y position of old mouse position
  static private int oldMouseY;
  // boolean that represents if the decoration is dragging
  private boolean isDragging;

  /**
   * Defines the initial environment properties of this application
   * 
   * @param processing    a reference to the graphic display window of this application
   * @param x             a reference to the x position of object
   * @param y             a reference to the y position of object
   * @param imageFileName a reference to which decoration
   */
  public Decoration(PApplet processing, float x, float y, String imageFileName) {
    // processing: PApplet reference to the display window of the Fish Tank
    // application
    this.processing = processing;
    // x: x-position of this decoration object
    this.x = x;
    // y: y-position of this decoration object
    this.y = y;
    // imageFileName: filename of the image to be loaded for this object
    this.image = processing.loadImage(imageFileName);
  }

  /**
   * PImage getter method
   * 
   * @return decoration image
   */
  public PImage getImage() {
    return image;
  }

  /**
   * Getter of x position
   * 
   * @return the x-position of this decoration object
   */
  public float getPositionX() {
    return x;
  }

  /**
   * Getter of y position
   * 
   * @return the y-position of this decoration object
   */
  public float getPositionY() {
    return y;
  }

  /**
   * Checks whether this decoration object is being dragged
   * 
   * @return true if the object is being dragged, false otherwise
   */
  public boolean isDragging() {
    if (isDragging == true)
      return true;
    else
      return false;
  }

  /**
   * Starts dragging this decoration object also sets the oldMouseX and oldMouseY to the current
   * position of the mouse
   * 
   */
  public void startDragging() {
    // sets oldMouseX data field to the current x-position of the mouse
    oldMouseX = (int) x;
    // sets oldMouseY data field to the current y-position of the mouse
    oldMouseY = (int) y;
    // sets the isDragging data field of this fish to true
    isDragging = true;
  }

  /**
   * Stops the dragging, sets is dragging to false
   * 
   */
  public void stopDragging() {
    // sets the isDragging data field of this fish to false
    isDragging = false;
  }

  /**
   * Checks if the mouse is over a given decoration whose reference is provided as input parameter
   * 
   * @return true if the mouse is over the given decoration object (i.e. over the image of the
   *         decoration), false otherwise
   */
  public boolean isMouseOver() {
    int decorWidth = this.getImage().width;
    int decorHeight = this.getImage().height;

    // checks if the mouse is over the provided fish
    return processing.mouseX >= this.getPositionX() - decorWidth / 2
        && processing.mouseX <= this.getPositionX() + decorWidth / 2
        && processing.mouseY >= this.getPositionY() - decorHeight / 2
        && processing.mouseY <= this.getPositionY() + decorHeight / 2;
  }

  /**
   * Moves this decoration with dx and dy
   * 
   * @param x value of fish
   * @param y value of fish
   * 
   */
  public void move(int dx, int dy) {
    // adds dx move to the x-position of this fish
    x += dx;
    // adds dy move to the y-position of this fish
    y += dy;
  }

  /**
   * Draws this decoration object to the display window. This method sets also the position of this
   * object to follow the moves of the mouse if it is being dragged
   * 
   */
  public void draw() {
    int dx = 0;
    int dy = 0;

    // checks if dragging is true
    if (isDragging() == true) {
      // sets dx-dy to the current position of the mouse, minus the old position
      dx = processing.mouseX - oldMouseX;
      dy = processing.mouseY - oldMouseY;
      // uses dx and dy for the move method to move decoration
      move(dx, dy);
      // reinitializes oldMouseX and oldMouseY to x and y
      oldMouseX = (int) x;
      oldMouseY = (int) y;
    }
    // draws the image at x and y coordinates
    processing.image(image, x, y);
  }
}
