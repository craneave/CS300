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
 * This class extends button and adds an orange fish
 */
public class AddOrangeFishButton extends Button {
  /**
   * Constructor that creates the add orange button
   * 
   * @param x position
   * @param y position
   */
  public AddOrangeFishButton(float x, float y) {
    super("Add Orange", x, y);
  }

  /**
   * Implements the default behavior of this button when the mouse is pressed
   */
  @Override
  public void mousePressed() {
    // if mouse pressed, add a new orange fish
    if (isMouseOver()) {
      tank.addObject(new Fish());
    }
  }
}
