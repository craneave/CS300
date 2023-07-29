//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P09 Tile Matching Game
// Course:   CS 300 Fall 2021
//
// Author:   Avery Crane
// Email:    adcrane@wisc.edu
// Lecturer:  Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:    N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models a Tile of a specific color
 *
 */
/**
 * @author mouna
 *
 */
public class Tile {
	private Color color; // color of this Tile

	/**
	 * Creates a Tile with a specific color
	 * 
	 * @param color color to be assigned to this tile
	 */
	public Tile(Color color) {
		this.color = color;
	}

	/**
	 * Gets the color of this tile
	 * 
	 * @return the color of this tile
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Returns a string representation of this tile
	 * 
	 * @return the color of this tile as a string
	 */
	@Override
	public String toString() {
		return color.name() + " ";
	}

	/**
	 * Checks whether this tile equals to the other object provided as input
	 * 
	 * @param Object of the other tile
	 * @return true if other is a Tile and has the same color as this tile
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		// check if other is an instance of tile
		if (other instanceof Tile == false)
			return false;
		// cast other to a tile
		Tile o = (Tile) other;
		// check the color
		if (o.color == this.color)
			return true;
		return false;
	}
}