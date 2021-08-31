package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing the PlayedWord
 * model class clear method
 * 
 * @author Brandon Drick
 * @version 08/28/2021
 */
public class TestPlayedWordClear {

	PlayedWord playedWord;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.playedWord = new PlayedWord();
	}

	@Test
	public void shouldClearEmptyWord() {
		assertEquals(0, this.playedWord.getScore());
		this.playedWord.clear();
		assertEquals(0, this.playedWord.getScore());
	}
	
	@Test
	public void shouldClearWordWithOneTile() {
		this.playedWord.append(new Tile('A'));
		this.playedWord.clear();
		assertEquals(0, this.playedWord.getScore());
	}
	
	@Test
	public void shouldClearWordWithManyTiles() {
		for (int index = 0; index < 10; index++) {
			this.playedWord.append(new Tile('E'));
		}
		
		this.playedWord.clear();
		assertEquals(0, this.playedWord.getScore());
	}
}
