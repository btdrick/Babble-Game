package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;

/**
 * Test class for testing TileGroup
 * remove method.
 * 
 * @author Brandon Drick
 * @version 08/28/2021
 */
public class TestTileGroupRemove {

	DummyTileGroup dummyTileGroup;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.dummyTileGroup = new DummyTileGroup();
	}
	
	@Test
	public void shouldNotAllowNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.dummyTileGroup.remove(null);
		});
	}
	
	@Test
	public void canNotRemoveFromEmptyTileGroup() {
		assertThrows(TileNotInGroupException.class, () -> {
			this.dummyTileGroup.remove(new Tile('A'));
		});
	}
	
	@Test
	public void canNotRemoveTileNotInTileGroup() {
		Tile testTile = new Tile('A');
		Tile dummyTile = new Tile('A');
		
		this.dummyTileGroup.append(testTile);
		
		assertThrows(TileNotInGroupException.class, () -> {
			this.dummyTileGroup.remove(dummyTile);
		});
	}

	@Test
	public void canRemoveOnlyTileInTileGroup() {
		try {
			Tile testTile = new Tile('A');
			
			this.dummyTileGroup.append(testTile);
			this.dummyTileGroup.remove(testTile);
			
			ObservableList<Tile> tiles = this.dummyTileGroup.tiles();
			assertFalse(tiles.contains(testTile));
		} catch (TileNotInGroupException tnige) {
			tnige.printStackTrace();
		}
	}
	
	@Test
	public void canRemoveFirstOfManyTilesFromTileGroup() {
		try {
			Tile tile1 = new Tile('A');
			Tile tile2 = new Tile('B');
			Tile tile3 = new Tile('C');
			
			ArrayList<Tile> testTiles = new ArrayList<Tile>(Arrays.asList(tile1, tile2, tile3));
			for (Tile current : testTiles) {
				this.dummyTileGroup.append(current);
			}
			
			this.dummyTileGroup.remove(tile1);
			
			ObservableList<Tile> tiles = this.dummyTileGroup.tiles();
			
			boolean removedProperTile = !tiles.contains(tile1) 
					&& tiles.contains(tile2) && tiles.contains(tile3);
			
			assertTrue(removedProperTile);
		} catch (TileNotInGroupException tnige) {
			tnige.printStackTrace();
		}
	}
	
	@Test
	public void canRemoveLastOfManyTilesFromTileGroup() {
		try {
			Tile tile1 = new Tile('A');
			Tile tile2 = new Tile('B');
			Tile tile3 = new Tile('C');
			ArrayList<Tile> testTiles = new ArrayList<Tile>(Arrays.asList(tile1, tile2, tile3));
			for (Tile current : testTiles) {
				this.dummyTileGroup.append(current);
			}
			
			this.dummyTileGroup.remove(tile3);
			
			ObservableList<Tile> tiles = this.dummyTileGroup.tiles();
			boolean removedProperTile = tiles.contains(tile1) 
					&& tiles.contains(tile2) && !tiles.contains(tile3);
			
			assertTrue(removedProperTile);
		} catch (TileNotInGroupException tnige) {
			tnige.printStackTrace();
		}
	}
	
	@Test
	public void canRemoveMiddleOfManyTilesFromTileGroup() {
		try {
			Tile tile1 = new Tile('A');
			Tile tile2 = new Tile('B');
			Tile tile3 = new Tile('C');
			ArrayList<Tile> testTiles = new ArrayList<Tile>(Arrays.asList(tile1, tile2, tile3));
			for (Tile current : testTiles) {
				this.dummyTileGroup.append(current);
			}
			
			this.dummyTileGroup.remove(tile2);
			
			ObservableList<Tile> tiles = this.dummyTileGroup.tiles();
			boolean removedProperTile = tiles.contains(tile1) 
					&& !tiles.contains(tile2) && tiles.contains(tile3);
			
			assertTrue(removedProperTile);
		} catch (TileNotInGroupException tnige) {
			tnige.printStackTrace();
		}
	}
	
	@Test
	public void canRemoveMultipleTilesFromTileGroup() {
		try {
			Tile tile1 = new Tile('A');
			Tile tile2 = new Tile('B');
			Tile tile3 = new Tile('C');
			ArrayList<Tile> testTiles = new ArrayList<Tile>(Arrays.asList(tile1, tile2, tile3));
			for (Tile current : testTiles) {
				this.dummyTileGroup.append(current);
			}
			
			this.dummyTileGroup.remove(tile1);
			this.dummyTileGroup.remove(tile2);
			
			ObservableList<Tile> tiles = this.dummyTileGroup.tiles();
			boolean removedProperTile = !tiles.contains(tile1) 
					&& !tiles.contains(tile2) && tiles.contains(tile3);
			
			assertTrue(removedProperTile);
		} catch (TileNotInGroupException tnige) {
			tnige.printStackTrace();
		}
	}
	
	@Test
	public void doesNotRemoveDuplicateTilesFromTileGroup() {
		try {
			Tile tile1 = new Tile('A');
			Tile tile2 = new Tile('B');
			Tile tile3 = new Tile('C');
			ArrayList<Tile> testTiles = new ArrayList<Tile>(Arrays.asList(tile1, tile2, tile3));
			for (Tile current : testTiles) {
				this.dummyTileGroup.append(current);
			}
			
			this.dummyTileGroup.remove(tile1);
			
			assertThrows(TileNotInGroupException.class, () -> {
				this.dummyTileGroup.remove(tile1);
			});
		} catch (TileNotInGroupException tnige) {
			tnige.printStackTrace();
		}
	}
}
