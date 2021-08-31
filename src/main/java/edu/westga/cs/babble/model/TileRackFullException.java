package edu.westga.cs.babble.model;

/**
 * Thrown by {@link TileRack#append(Tile)} if an attempt is made to append to an already-full rack.
 * 
 * @author lewisb
 * @version fall 2021
 */
public class TileRackFullException extends RuntimeException {
	private static final long serialVersionUID = 6089566925880861187L;

}
