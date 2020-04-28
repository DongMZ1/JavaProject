import java.io.Serializable;

public class Merchant implements TileEntity, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9127717617857352973L;
	int tile;

	public Merchant(int tile) {
		this.tile = tile ;
	}

	@Override
	public int getTile() {
		return this.tile;
	}

	@Override
	public void setTile(int tile) {
		this.tile = tile;
	}
	

}
