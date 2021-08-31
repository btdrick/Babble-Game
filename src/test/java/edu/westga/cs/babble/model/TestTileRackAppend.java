package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing the TileRack
 * model class append method
 * 
 * @author Brandon Drick
 * @version 08/28/2021
 */
public class TestTileRackAppend {

	TileRack tileRack;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.tileRack = new TileRack();
	}

	@Test
	public void shouldNotAppendToFullRack() {
		for (int index = 0; index < 7; index++) {
			this.tileRack.append(new Tile('A'));
		}
		assertThrows(TileRackFullException.class, () -> {
			this.tileRack.append(new Tile('A'));
		});
	}
}
