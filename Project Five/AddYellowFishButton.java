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
 * This class extends button and adds a yellow fish
 */
public class AddYellowFishButton extends Button {
  /**
   * Constructor that creates the add yellow button
   * 
   * @param x position
   * @param y position
   */
  public AddYellowFishButton(float x, float y) {
    super("Add Yellow", x, y);
  }

  /**
   * Implements the default behavior of this button when the mouse is pressed
   */
  @Override
  public void mousePressed() {
    //if mouse pressed then make a new yellow fish
    if (isMouseOver()) {
      tank.addObject(new Fish(2, "images/yellow.png"));
    }
  }
}
