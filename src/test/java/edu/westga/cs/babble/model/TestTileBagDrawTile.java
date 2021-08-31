package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;
import static java.util.Map.entry;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing the TileBag
 * model class
 * 
 * @author Brandon Drick
 * @version 08/27/2021
 */
public class TestTileBagDrawTile {

	TileBag tileBag;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.tileBag = new TileBag();
	}
	
	@Test
	public void canDrawAllTiles() {
		ArrayList<Tile> tilePile = new ArrayList<Tile>();
		
		try {
			for (int index = 0; index < 98; index++) {
				Tile drawnTile = this.tileBag.drawTile();
				tilePile.add(drawnTile);
			}
		} catch (EmptyTileBagException etbe) {
			etbe.printStackTrace();
		}
		
		assertEquals(98, tilePile.size());
	}
	
	@Test
	public void canNotDrawTooManyTiles() {
		assertThrows(EmptyTileBagException.class, () -> {
			for (int index = 0; index < 99; index++) {
				this.tileBag.drawTile();
			}
		});
	}
	
	@Test
	public void hasProperTileDistribution() {
		ArrayList<Character> tileBagContents = new ArrayList<Character>();
		for (int index = 0; index < 98; index++) {
			try {
				tileBagContents.add(this.tileBag.drawTile().getLetter());
			} catch (EmptyTileBagException etbe) {
				etbe.printStackTrace();
			}
		}
		
		Map<Character, Integer> tileBagCharacterMap = this.getTileBagCharacterMap();
		for (Entry<Character, Integer> entry : tileBagCharacterMap.entrySet()) {
			Character letter = entry.getKey();
			int setNumber = entry.getValue();
			
			long numberOfLetters = tileBagContents.stream()
					.filter(character -> character.equals(letter)).count();
			
			assertEquals(setNumber, numberOfLetters);
		}
		
	}
	
	private Map<Character, Integer> getTileBagCharacterMap() {
		Map<Character, Integer> tileBagCharacterMap = 
				Map.ofEntries(entry('A', 9), entry('B', 2), entry ('C', 2),
						entry('D', 4), entry('E', 12), entry('F', 2), entry('G', 3),
						entry('H', 2), entry('I', 9), entry('J', 1), entry('K', 1),
						entry('L', 4), entry('M', 2), entry('N', 6), entry('O', 8),
						entry('P', 2), entry('Q', 1), entry('R', 6), entry('S', 4), 
						entry('T', 6), entry('U', 4), entry('V', 2), entry('W', 2),
						entry('X', 1), entry('Y', 2), entry('Z', 1));
		
		return tileBagCharacterMap;
	}
}
