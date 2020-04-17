import java.io.Serializable;

public class Witch implements TileEntity, Serializable {
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
