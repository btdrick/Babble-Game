package edu.westga.cs.babble.model;

/**
 * Data class for a letter tile with a point value
 * @author lewisb
 *
 */
public class Tile {
	private char uppercaseLetter;
	
	/**
	 * Creates a new Tile with the given letter.  Requires that letter be a valid letter.
	 * @param letter a letter A-Z or a-z
	 */
	public Tile(char letter) {
		if(!Character.isAlphabetic(letter)) {
			throw new IllegalArgumentException("letter must be between A and Z");
		}
		
		this.uppercaseLetter = Character.toUpperCase(letter);
	}
	
	/**
	 * Accessor for this Tile's letter
	 * @return the letter
	 */
	public char getLetter() { 
		return this.uppercaseLetter; 
	}
	
	/**
	 * Accessor this this Tile's point value
	 * @return the points this Tile is worth
	 */
	public int getPointValue() { 
		String ltr = "" + this.uppercaseLetter;
		String onePointLetters = "EAIONRTLSU";
		String twoPointLetters = "DG";
		String threePointLetters = "BCMP";
		String fourPointLetters = "FHVWY";
		String fivePointLetters = "K";
		String eightPointLetters = "JX";
		// String tenPointLetters = "QZ";
		
		if (onePointLetters.contains(ltr)) {
			return 1;
		}
		else if (twoPointLetters.contains(ltr)) {
			return 2;
		}
		else if (threePointLetters.contains(ltr)) {
			return 3;
		}
		else if (fourPointLetters.contains(ltr)) {
			return 4;
		}
		else if (fivePointLetters.contains(ltr)) {
			return 5;
		}
		else if (eightPointLetters.contains(ltr)) {
			return 8;
		}
		else {
			return 10;
		}
	}
}
