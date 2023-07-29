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

import java.util.ArrayList;
import java.io.File;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class uses multiple methods for placing decorations and fish on an aquarium background,
 * allowing the user to move them and for the fish to swim, controlled by user
 */

public class FishTank extends PApplet {
  private PImage backgroundImage; // PImage object which represents the background image
  protected ArrayList<TankListener> objects; // list storing interactive objects
  protected Random randGen; // Generator of random numbers
  private TankObject flower;
  private TankObject log;
  private TankObject shell;
  private TankObject ship;

  /**
   * The main method of the program which is used to start the PApplet main
   */
  public static void main(String[] args) {
    // Auto-generated method stub
    PApplet.main("FishTank"); // do not add any other statement to the main method
    // The PApplet.main() method takes a String input parameter which represents
    // the name of your PApplet class.
  }


  /**
   * sets the size of this PApplet to 800 width x 600 height
   */
  @Override
  public void settings() {
    size(800, 600);
  }

  /**
   * Defines initial environment properties such as screen size and loads the background image and
   * fonts as the program starts. It also initializes all data fields. The above IS NOT a javadoc
   * style method header!
   */
  @Override
  public void setup() {
    // Set and display the title of the display window
    this.getSurface().setTitle("Fish Tank 3000");
    // Set the location from which images are drawn to CENTER
    this.imageMode(PApplet.CENTER);
    // Set the location from which rectangles are drawn.
    this.rectMode(PApplet.CORNERS);
    // Confirms that our Processing program is focused, meaning that it is active and will accept
    // mouse or keyboard input.
    this.focused = true;
    // sets the text alignment to center
    this.textAlign(PApplet.CENTER, PApplet.CENTER);
    // load the background image and store the loaded image to backgroundImage
    this.backgroundImage = this.loadImage("images/background.png");
    // create an empty array list of objects
    this.objects = new ArrayList<TankListener>();
    // set randGen to the reference of a new Random objects
    this.randGen = new Random();
    // Button processing set to the PApplet processing
    Button.setProcessing(this);
    // TankObject processing set to the PApplet processing
    TankObject.setProcessing(this);
    // creating new decorations
    flower = new TankObject(430, 60, "images" + File.separator + "flower.png");
    log = new TankObject(580, 470, "images" + File.separator + "log.png");
    shell = new TankObject(65, 520, "images" + File.separator + "shell.png");
    ship = new TankObject(280, 535, "images" + File.separator + "ship.png");
    // adding the decorations to the objects array list
    objects.add(flower);
    objects.add(log);
    objects.add(ship);
    objects.add(shell);
    // creating two new black fish
    BlackFish one = new BlackFish(log, flower);
    BlackFish two = new BlackFish(shell, flower);
    // adding them to array list
    objects.add(one);
    objects.add(two);
    // creating the four buttons
    AddBlueFishButton blue = new AddBlueFishButton(43, 16);
    AddOrangeFishButton orange = new AddOrangeFishButton(129, 16);
    AddYellowFishButton yellow = new AddYellowFishButton(215, 16);
    ClearTankButton clear = new ClearTankButton(301, 16);
    // adding them to the array list
    objects.add(blue);
    objects.add(orange);
    objects.add(yellow);
    objects.add(clear);
  }

  /**
   * Constantly draws the background to the screen along with the objects
   */
  @Override
  public void draw() {
    image(backgroundImage, width / 2, height / 2);
    // traverse the fishes array and draw each of the objects present in the tank
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).draw();
    }
  }

  /**
   * If the mouse is pressed and is over, then the object wll drag
   */
  @Override
  public void mousePressed() {
    // traverse the fishes array and start dragging a fish if the mouse is over it
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i).isMouseOver()) {
        objects.get(i).mousePressed();
        // only the fish at the lowest index will start dragging if there are fishes overlapping
        break;
      }
    }
  }

  /**
   * If mouse is released then dragging stops
   */
  @Override
  public void mouseReleased() {
    // traverse the objects list and call each object's mouseReleased() method
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).mouseReleased();
    }
  }

  /**
   * adds an instance of TankListener passed as input to the objects array list
   */
  public void addObject(TankListener object) {
    objects.add(object);
  }

  /**
   * The callback method for anytime a key is pressed
   */
  @Override
  public void keyPressed() {
    switch (Character.toUpperCase(key)) {
      case 'O': // add a new fish
        for (int i = 0; i < objects.size() - 1; i++) {
          objects.add(i, new Fish());
          break;
        }
        break;
      case 'Y': // add a yellow fish
        for (int i = 0; i < objects.size() - 1; i++) {
          objects.add(i, new Fish(2, "images/yellow.png"));
          break;
        }
        break;
      case 'R': // delete the fish being dragged if any
        for (int i = 0; i < objects.size() - 1; i++) {
          if (objects.get(i).isMouseOver()) {
            objects.remove(i);
          }
        }
        break;
      case 'S': // start swimming
        for (int i = 0; i < objects.size(); i++) {
          if (objects.get(i) instanceof Fish) {
            Fish fish = (Fish) objects.get(i);
            fish.startSwimming();
          }
        }
        break;
      case 'X': // stop swimming
        for (int i = 0; i < objects.size(); i++) {
          if (objects.get(i) instanceof Fish) {
            Fish fish = (Fish) objects.get(i);
            fish.stopSwimming();
          }
        }
        break;
      case 'C': // clear all fish
        clear();
        break;
      case 'B': // adds a blue fish
        for (int i = 0; i < objects.size(); i++) {
          objects.add(i, new BlueFish());
          break;
        }
        break;
    }
  }

  /**
   * Removes instances of the class Fish from this tank
   */
  public void clear() {
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i) instanceof Fish) {
        objects.remove(i);
        i--;
      }
    }
  }
}
