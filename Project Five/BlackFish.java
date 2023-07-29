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
 * This class extends Fish and creates/swims a black fish
 */
public class BlackFish extends Fish {
  private TankObject source;
  private TankObject destination;
  private TankObject temp;

  /**
   * Black fish constructor that takes a source object and a destination object for the fish to move
   * 
   * @param source     object
   * @param desination object for black fish to swim to
   */
  public BlackFish(TankObject source, TankObject destination) {
    super(2, "images/black.png");
    this.source = source;
    this.destination = destination;
  }

  /**
   * makes one speed move towards destination
   */
  public void moveTowardsDestination() {
    // find dx and dy
    float dx = destination.getX() - this.getX();
    float dy = destination.getY() - this.getY();
    // find distance and convert it to an int
    int distance = (int) Math.sqrt((dx * dx + dy * dy));
    // find the new x and y of the fish
    float newX = this.getX() + (speed() * dx) / distance;
    float newY = this.getY() + (speed() * dy) / distance;
    // set the fish to the new points
    setX(newX);
    setY(newY);
  }

  /**
   * decides if object is over
   * 
   * @return true if this black fish is over another tank object, and false otherwise
   */
  public boolean isOver(TankObject other) {
    return this.getX() < other.getX() + other.image.width
        && this.getX() + this.image.width > other.getX()
        && this.getY() < other.getY() + other.image.height
        && this.getY() + this.image.height > other.getY();
  }

  /**
   * swims the fish and will switch objects once arrived
   */
  @Override
  public void swim() {
    // move the fish towards its destination
    moveTowardsDestination();
    // if destination is reached (meaning this fish is over its destination,
    // switch source and destination
    if (isOver(destination) == true) {
      temp = destination;
      destination = source;
      source = temp;
    }
  }
}
