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
 * This class extends Tank Object and is used to place fish into the tank
 */
public class Fish extends TankObject {
  private int speed;
  private boolean isSwimming;

  /**
   * Fish Constructor that references its parent class to create a fish
   * 
   * @throws IllegalArgumentException if speed is negative
   * @param speed             of the fish
   * @param fishImageFileName or the file name of the fish
   */
  public Fish(int speed, String fishImageFileName) throws IllegalArgumentException {
    super(TankObject.tank.randGen.nextInt(tank.width), TankObject.tank.randGen.nextInt(tank.height),
        fishImageFileName);
    if (speed < 0) {
      throw new IllegalArgumentException("Warning: speed cannot be negative");
    } else {
      this.speed = speed;
    }
  }

  /**
   * Fish Constructor that creates an orange fish with a speed of 5
   */
  public Fish() {
    this(5, "images/orange.png");
  }

  /**
   * This method sets the position of this fish to follow the mouse moves if it is dragging, calls
   * its swim() method if it is swimming, and draws it to the display window.
   */
  @Override
  public void draw() {
    // if the fish is swimming, call its swim() method
    if (isSwimming()) {
      swim();
    }
    if (isDragging()) {
      super.draw();
    }
    // draw the fish at its current position
    tank.image(this.image, getX(), getY());
  }

  /**
   * Checks if fish is swimming
   */
  public boolean isSwimming() {
    return isSwimming;
  }

  /**
   * Starts swimming
   */
  public void startSwimming() {
    isSwimming = true;
  }

  /**
   * Stops swimming
   */
  public void stopSwimming() {
    isSwimming = false;
  }

  /**
   * Gets speed
   * 
   * @return speed of fish
   */
  public int speed() {
    return speed;
  }

  /**
   * Moves horizontally the fish one speed step from left to right.
   */
  public void swim() {
    setX((getX() + speed) % tank.width);
  }
}
