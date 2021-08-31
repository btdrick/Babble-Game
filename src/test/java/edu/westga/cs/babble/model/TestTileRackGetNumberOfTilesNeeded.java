package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing the TileRack
 * model class getNumberOfTilesNeeded method
 * 
 * @author Brandon Drick
 * @version 08/28/2021
 */
public class TestTileRackGetNumberOfTilesNeeded {

	TileRack tileRack;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.tileRack = new TileRack();
	}
	
	@Test
	public void emptyTileRackShouldNeedMaxSizeNumberOfTiles() {
		assertEquals(7, this.tileRack.getNumberOfTilesNeeded());
	}
	
	@Test
	public void tileRackWithOneTileShouldNeedMaxSizeMinusOneTiles() {
		this.tileRack.append(new Tile('A'));
		assertEquals(6, this.tileRack.getNumberOfTilesNeeded());
	}
	
	@Test
	public void tileRackWithSeveralTilesShouldNeedSomeTiles() {
		for (int index = 0; index < 5; index++) {
			this.tileRack.append(new Tile('A'));
		}
		assertEquals(2, this.tileRack.getNumberOfTilesNeeded());
	}
	
	@Test
	public void fullRackNeedsZeroTiles() {
		for (int index = 0; index < 7; index++) {
			this.tileRack.append(new Tile('A'));
		}
		assertEquals(0, this.tileRack.getNumberOfTilesNeeded());
	}

}
