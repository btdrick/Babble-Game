package edu.westga.cs.babble.model;

import java.util.*;

/**
 * Container for Tiles, allows drawing random Tiles.  When created, it self-populates with a Scrabble-like set of tiles.
 * @author lewisb
 *
 */
public class TileBag {

	private ArrayList<Tile> tiles;
	private Random rand;
	
	/**
	 * Creates a new, populated TileBag
	 */
	public TileBag()
	{
		this.rand = new Random();
		this.tiles = new ArrayList<Tile>();
		this.populateWithScrabbleTiles();
	}
	
	/**
	 * Brute-force fills the bag with a set of tiles with the Scrabble points and letter distribution (ignoring the blank tiles)
	 */
	private void populateWithScrabbleTiles()
	{
		// 1-pt tiles: E, A, I, O, N, R, T, L, S, U
		for(int i=0; i<12; i++) tiles.add(new Tile('E'));
		for(int i=0; i< 9; i++) tiles.add(new Tile('A'));
		for(int i=0; i< 9; i++) tiles.add(new Tile('I'));
		for(int i=0; i< 8; i++) tiles.add(new Tile('O'));
		for(int i=0; i< 6; i++) tiles.add(new Tile('N'));
		for(int i=0; i< 6; i++) tiles.add(new Tile('R'));
		for(int i=0; i< 6; i++) tiles.add(new Tile('T'));
		for(int i=0; i< 4; i++) tiles.add(new Tile('L'));
		for(int i=0; i< 4; i++) tiles.add(new Tile('S'));
		for(int i=0; i< 4; i++) tiles.add(new Tile('U'));
		
		// 2-pt tiles: D, G
		for(int i=0; i<4; i++) tiles.add(new Tile('D'));
		for(int i=0; i<3; i++) tiles.add(new Tile('G'));
		
		// 3 pt tiles: B, C, M, P
		for(int i=0; i<2; i++) tiles.add(new Tile('B'));
		for(int i=0; i<2; i++) tiles.add(new Tile('C'));
		for(int i=0; i<2; i++) tiles.add(new Tile('M'));
		for(int i=0; i<2; i++) tiles.add(new Tile('P'));
		
		// 4 pt tiles: F, H, V, W, Y
		for(int i=0; i<2; i++) tiles.add(new Tile('F'));
		for(int i=0; i<2; i++) tiles.add(new Tile('H'));
		for(int i=0; i<2; i++) tiles.add(new Tile('V'));
		for(int i=0; i<2; i++) tiles.add(new Tile('W'));
		for(int i=0; i<2; i++) tiles.add(new Tile('Y'));
		
		// 5-pt tiles: K
		tiles.add(new Tile('K'));
		
		// 8-pt tiles: J, X
		tiles.add(new Tile('J'));
		tiles.add(new Tile('X'));
		
		// 10-pt tiles: Q, Z
		tiles.add(new Tile('Q'));
		tiles.add(new Tile('Z'));
	}
	
	/**
	 * Answers the existential question of "is this bag empty?"
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty()
	{
		return this.tiles.isEmpty();
	}
	
	/**
	 * Draws and removes a random tile from the bag
	 * @return the removed tile
	 * @throws EmptyTileBagException if the bag is empty
	 */
	public Tile drawTile() throws EmptyTileBagException
	{
		if(isEmpty())
		{
			throw new EmptyTileBagException();
		}
		
		int size = tiles.size();
		int index = rand.nextInt(size);
		Tile patsy = tiles.remove(index);
		return patsy;
	}
}
