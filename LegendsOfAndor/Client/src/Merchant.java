import org.minueto.image.MinuetoImage;

public class Merchant implements TileEntity {
	MinuetoImage merchantImage;
	int tile;
	
	public Merchant(int tile) {
		this.tile = tile ;
	}

	@Override
	public int getTile() {
		// TODO Auto-generated method stub
		return this.tile;
	}

	@Override
	public void setTile(int tile) {
		this.tile = tile;
		// TODO Auto-generated method stub

	}
	

}
