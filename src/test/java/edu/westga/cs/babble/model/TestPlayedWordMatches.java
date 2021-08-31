package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing the PlayedWord
 * model class matches method
 * 
 * @author Brandon Drick
 * @version 08/28/2021
 */
public class TestPlayedWordMatches {

	PlayedWord playedWord;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.playedWord = new PlayedWord();
	}
	
	@Test
	public void hasTilesForAMultipleLetterWord() {
		this.playedWord.append(new Tile('E'));
		this.playedWord.append(new Tile('X'));
		this.playedWord.append(new Tile('T'));
		this.playedWord.append(new Tile('R'));
		this.playedWord.append(new Tile('A'));
		
		assertTrue(this.playedWord.matches("EXTRA"));
	}
	
	@Test
	public void hasTilesForASingleLetterWord() {
		this.playedWord.append(new Tile('A'));
		
		assertTrue(this.playedWord.matches("A"));
	}
	
	@Test
	public void cannotMatchWordWhenTilesAreScrambled() {
		this.playedWord.append(new Tile('T'));
		this.playedWord.append(new Tile('E'));
		this.playedWord.append(new Tile('X'));
		this.playedWord.append(new Tile('A'));
		this.playedWord.append(new Tile('R'));
		
		assertFalse(this.playedWord.matches("EXTRA"));
	}
	
	@Test
	public void cannotMatchWordIfInsufficientTiles() {
		this.playedWord.append(new Tile('E'));
		this.playedWord.append(new Tile('X'));
		this.playedWord.append(new Tile('T'));
		this.playedWord.append(new Tile('R'));
		
		assertFalse(this.playedWord.matches("EXTRA"));
	}
	
	@Test
	public void canMatchWordWithDuplicateLetters() {
		this.playedWord.append(new Tile('B'));
		this.playedWord.append(new Tile('A'));
		this.playedWord.append(new Tile('L'));
		this.playedWord.append(new Tile('L'));
		
		assertTrue(this.playedWord.matches("BALL"));
	}
	
	@Test
	public void nonEmptyWordShouldNotMatchEmptyText() {
		this.playedWord.append(new Tile('B'));
		this.playedWord.append(new Tile('A'));
		this.playedWord.append(new Tile('L'));
		this.playedWord.append(new Tile('L'));
		
		assertFalse(this.playedWord.matches(""));
	}
	
	@Test
	public void emptyWordShouldNotMatchEmptyText() {
		assertFalse(this.playedWord.matches(""));
	}
	
	@Test
	public void shouldNotAllowNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.playedWord.matches(null);
		});
	}

}
