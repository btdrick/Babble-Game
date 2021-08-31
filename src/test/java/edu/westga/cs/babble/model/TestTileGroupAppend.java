package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;

/**
 * Test class for testing TileGroup
 * append method.
 * 
 * @author Brandon Drick
 * @version 08/28/2021
 */
public class TestTileGroupAppend {

	DummyTileGroup dummyTileGroup;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.dummyTileGroup = new DummyTileGroup();
	}
	
	@Test
	public void shouldNotAllowNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.dummyTileGroup.append(null);
		});
	}
	
	@Test
	public void emptyGroupShouldBeEmpty() {
		ObservableList<Tile> tiles = this.dummyTileGroup.tiles();
		assertEquals(0, tiles.size());
	}
	
	@Test
	public void shouldHaveOneTileInTileGroup() {
		this.dummyTileGroup.append(new Tile('A'));
		ObservableList<Tile> tiles = this.dummyTileGroup.tiles();
		assertEquals(1, tiles.size());
		assertEquals('A', tiles.get(0).getLetter());
	}
	
	@Test
	public void shouldHaveManyTilesInTileGroup() {
		Tile tile1 = new Tile('A');
		Tile tile2 = new Tile('B');
		Tile tile3 = new Tile('C');
		
		this.dummyTileGroup.append(tile1);
		this.dummyTileGroup.append(tile2);
		this.dummyTileGroup.append(tile3);
		
		ObservableList<Tile> tiles = this.dummyTileGroup.tiles();

		assertEquals(3, tiles.size());
		assertEquals('A', tiles.get(0).getLetter());
		assertEquals('B', tiles.get(1).getLetter());
		assertEquals('C', tiles.get(2).getLetter());
	}
	
	@Test
	public void shouldHaveManyTilesIncludingDuplicatesInTileGroup() {
		Tile tile1 = new Tile('A');
		Tile tile2 = new Tile('A');
		Tile tile3 = new Tile('C');
		
		this.dummyTileGroup.append(tile1);
		this.dummyTileGroup.append(tile2);
		this.dummyTileGroup.append(tile3);
		
		ObservableList<Tile> tiles = this.dummyTileGroup.tiles();

		assertEquals(3, tiles.size());
		assertEquals('A', tiles.get(0).getLetter());
		assertEquals('A', tiles.get(1).getLetter());
		assertEquals('C', tiles.get(2).getLetter());
	}
	
	@Test
	public void canNotAddSameTileTwice() {
		Tile tile1 = new Tile('A');
		this.dummyTileGroup.append(tile1);
		
		assertThrows(IllegalArgumentException.class, () -> {
			this.dummyTileGroup.append(tile1);
		});
	}
}
