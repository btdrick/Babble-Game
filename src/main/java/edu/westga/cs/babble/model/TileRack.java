package edu.westga.cs.babble.model;


/**
 * A small collection of Tiles that holds the currently-drawn set of tiles.
 * @author lewisb
 *
 */
public class TileRack extends TileGroup {

	public static final int MAX_SIZE = 7;
	
	
	/**
	 * Accessor for the number of tiles we need to fully populate this rack
	 * 
	 * @return the number of missing tiles
	 */
	public int getNumberOfTilesNeeded() { 
		return MAX_SIZE - super.tiles().size(); 
	}
	
	@Override
	public void append(Tile tile) throws TileRackFullException {
		if (super.tiles().size() == MAX_SIZE) {
			throw new TileRackFullException();
		}
		
		super.append(tile);
	}
}
