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
 * This class extends Fish and creates/swims a blue fish
 */
public class BlueFish extends Fish {
  /**
   * Constructor of BlueFish and will create a blue fish in the Fish class with a speed of 2
   */
  public BlueFish() {
    super(2, "images/blue.png");
  }

  /**
   * Moves the fish from right to left, and resets it on other side of screen if it reaches 0 or less
   */
  @Override
  public void swim() {
    setX((getX() - speed()));
    if (getX() <= 0) {
      setX(getX() + tank.width);
    }
  }
}
