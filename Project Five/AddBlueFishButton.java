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
 * This class extends button and adds a blue fish
 */
public class AddBlueFishButton extends Button {
  /**
   * Constructor that creates the add blue button
   * 
   * @param x position
   * @param y position
   */
  public AddBlueFishButton(float x, float y) {
    super("Add Blue", x, y);
  }

  /**
   * Implements the default behavior of this button when the mouse is pressed
   */
  @Override
  public void mousePressed() {
    //if mouse is pressed, add a blue fish
    if (isMouseOver()) {
      tank.addObject(new BlueFish());
    }
  }
}
