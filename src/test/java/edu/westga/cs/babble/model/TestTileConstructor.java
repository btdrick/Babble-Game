package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Class for testing the Tile
 * model class
 * 
 * @author Brandon Drick
 * @version 08/27/2021
 */
public class TestTileConstructor {
	
	@Test
	public void shouldNotAllowNonLetters() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Tile('0');
		});
	}

	@Test
	public void shouldReturn1PointForTileA() {
		Tile testTile = new Tile('A');
		assertEquals(1, testTile.getPointValue());
	}
	
	@Test
	public void shouldReturn2PointsForTileD() {
		Tile testTile = new Tile('D');
		assertEquals(2, testTile.getPointValue());
	}
	
	@Test
	public void shouldReturn3PointsForTileM() {
		Tile testTile = new Tile('m');
		assertEquals(3, testTile.getPointValue());
	}
	
	@Test
	public void shouldReturn4PointsForTileY() {
		Tile testTile = new Tile('y');
		assertEquals(4, testTile.getPointValue());
	}
	
	@Test
	public void shouldReturn5PointsForTileK() {
		Tile testTile = new Tile('k');
		assertEquals(5, testTile.getPointValue());
	}
	
	@Test
	public void shouldReturn8PointsForTileX() {
		Tile testTile = new Tile('x');
		assertEquals(8, testTile.getPointValue());
	}
	
	@Test
	public void shouldReturn10PointsForTileZ() {
		Tile testTile = new Tile('z');
		assertEquals(10, testTile.getPointValue());
	}

}
