package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing the PlayedWord
 * model class getScore method
 * 
 * @author Brandon Drick
 * @version 08/28/2021
 */
public class TestPlayedWordGetScore {

	PlayedWord playedWord;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.playedWord = new PlayedWord();
	}
	
	@Test
	public void emptyWordShouldHaveScoreOfZero() {
		assertEquals(0, this.playedWord.getScore());
	}
	
	@Test
	public void scoreAOneTileWord() {
		this.playedWord.append(new Tile('E'));
		assertEquals(1, this.playedWord.getScore());
	}
	
	@Test
	public void scoreAWordWithMultipleDifferingTiles() {
		this.playedWord.append(new Tile('E'));
		this.playedWord.append(new Tile('X'));
		this.playedWord.append(new Tile('T'));
		this.playedWord.append(new Tile('R'));
		this.playedWord.append(new Tile('A'));
		
		assertEquals(12, this.playedWord.getScore());
	}
	
	@Test
	public void scoreAWordContainingDuplicateTiles() {
		this.playedWord.append(new Tile('E'));
		this.playedWord.append(new Tile('E'));
		
		assertEquals(2, this.playedWord.getScore());
	}
}
