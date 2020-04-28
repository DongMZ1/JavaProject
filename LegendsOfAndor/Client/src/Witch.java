import java.io.Serializable;

public class Witch implements TileEntity, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2196967333559463320L;
	int tile;

	public Witch(int tile) {
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
