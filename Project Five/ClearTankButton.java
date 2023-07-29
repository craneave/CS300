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
 * This class extends button and clears fish
 */
public class ClearTankButton extends Button {
  /**
   * Constructor that creates the clear button
   * 
   * @param x position
   * @param y position
   */
  public ClearTankButton(float x, float y) {
    super("clear", x, y);
  }

  /**
   * Implements the default behavior of this button when the mouse is pressed
   */
  @Override
  public void mousePressed() {
    // if mouse is pressed, delete all fish
    if (isMouseOver()) {
      tank.clear();
    }
  }
}
