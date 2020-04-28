

import java.io.Serializable;

public class Bow implements Item, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4284917989365020330L;
	int Tile;

	public Bow(int tile){
		this.Tile = tile;
	}

	@Override
	public int getTile() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTile(int tile) {
		// TODO Auto-generated method stub
		
	}

}
