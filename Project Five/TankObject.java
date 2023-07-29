import processing.core.PImage;

/////////////////////////////////////////////////////////////////////////////////
//
// Title: Program 5: Fish Tank
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
 * This class implements TankListener and other classes extend this one to help with a tank ojbects
 * properties
 */
public class TankObject implements TankListener {
  protected static FishTank tank; // PApplet object which represents
  // the display window
  protected PImage image; // image of this tank object
  private float x; // x-position of this tank in the display window
  private float y; // y-position of this tank in the display window
  private boolean isDragging; // indicates whether this tank object
  // is being dragged or not
  private static int oldMouseX; // old x-position of the mouse
  private static int oldMouseY; // old y-position of the mouse

  /**
   * Constructor for TankObject
   * 
   * @param x             a float of the x position
   * @param y             a float of the y position
   * @param imageFileName a string of the file name
   */
  public TankObject(float x, float y, String imageFileName) {
    this.x = x;
    this.y = y;
    image = tank.loadImage(imageFileName);
  }

  // Sets the PApplet graphic display window for all TankObjects
  public static void setProcessing(FishTank tank) {
    TankObject.tank = tank;
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
   * x position
   * 
   * @returns x position of object
   */
  public float getX() {
    return x;
  }

  /**
   * y position
   * 
   * @returns y position of object
   */
  public float getY() {
    return y;
  }

  /**
   * sets x position
   */
  public void setX(float x) {
    this.x = x;

  }

  /**
   * sets y position
   */
  public void setY(float y) {
    this.y = y;
  }

  /**
   * image getter
   * 
   * @returns image
   */
  public PImage getImage() {
    return image;
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
   * Draws tank objects onto the screen and if it is dragging will compensate for that
   */
  @Override
  public void draw() {
    // if this object is dragging, set its position to follow the mouse moves
    if (this.isDragging) {
      int dx = tank.mouseX - oldMouseX;
      int dy = tank.mouseY - oldMouseY;
      move(dx, dy);
      oldMouseX = tank.mouseX;
      oldMouseY = tank.mouseY;
    }

    // draw this object at its current position
    tank.image(this.image, this.x, y);

  }

  /**
   * If the mouse is pressed, then the object at that position will startDragging()
   */
  @Override
  public void mousePressed() {
    for (int i = 0; i < tank.objects.size(); i++) {
      if (tank.objects.get(i).isMouseOver()) {
        startDragging();
      }

    }
  }


  /**
   * If mouse is released, then object stops dragging
   */
  @Override
  public void mouseReleased() {
    for (int i = 0; i < tank.objects.size(); i++)
      stopDragging();
  }

  /**
   * If the mouse is over the object, then this will be true
   * 
   * @return true if mouse is over image
   */
  @Override
  public boolean isMouseOver() {
    // checks if the mouse is over this object
    return tank.mouseX >= x - image.width / 2 && tank.mouseX <= x + image.width / 2
        && tank.mouseY >= y - image.height / 2 && tank.mouseY <= y + image.height / 2;
  }

}
