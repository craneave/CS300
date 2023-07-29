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
 * This class implements TankListener and is meant to be the parent class for all buttons
 */
public class Button implements TankListener {
  private static final int WIDTH = 85; // Width of this Button
  private static final int HEIGHT = 32; // Height of this Button
  protected static FishTank tank; // PApplet object where this button will be displayed
  private float x; // x-position of this button in the display window
  private float y; // y-position of this button in the display window
  protected String label; // text/label which represents this button

  /**
   * Creates a new Button at a given position within the display window and sets its label
   * 
   * @param label of the button
   * @param x     position of button
   * @param y     position of button
   */
  public Button(String label, float x, float y) {
    this.label = label;
    this.x = x;
    this.y = y;
  }

  /**
   * sets the he PApplet display window where this button is displayed and handled
   */
  public static void setProcessing(FishTank tank) {
    Button.tank = tank;
  }

  /**
   * Checks whether the mouse is over this button
   * 
   * @returns true if the mouse is over this button, false otherwise.
   */
  @Override
  public boolean isMouseOver() {
    // checks if the mouse is over this object
    return tank.mouseX >= x - WIDTH / 2 && tank.mouseX <= x + WIDTH / 2
        && tank.mouseY >= y - HEIGHT / 2 && tank.mouseY <= y + HEIGHT / 2;
  }

  /**
   * Draws the button to display window
   */
  @Override
  public void draw() {
    tank.stroke(0);// set line value to black
    // if the mouse is over this button, sets the fill color to dark gray.
    // Sets the fill color to light gray otherwise
    if (isMouseOver()) {
      tank.fill(100);
    } else {
      tank.fill(200);
    }
    // draw the button (rectangle with a centered text)
    tank.rect(x - WIDTH / 2.0f, y - HEIGHT / 2.0f, x + WIDTH / 2.0f, y + HEIGHT / 2.0f);
    tank.fill(0); // set the fill color to black
    tank.text(label, x, y); // display the text of the current button
  }

  /**
   * Implements the default behavior of this button when the mouse is pressed
   */
  @Override
  public void mousePressed() {
    // TODO if the mouse is over this button, print
    // "A button was pressed." to the console
    if (isMouseOver()) {
      System.out.println("A button was pressed");
    }
  }

  /**
   * Implements the default behavior of this button when the mouse is released
   */
  @Override
  public void mouseReleased() {
  }
}
