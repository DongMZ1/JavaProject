import org.minueto.image.MinuetoImage;

public class Merchant implements TileEntity {
	MinuetoImage merchantImage;
	int tile;
	@Override
	public Merchant(MinuetoImage merchantImage,int tile ) {
		this.merchantImage = merchantImage;
		this.tile = tile ;
	}
	public MinuetoImage getImage() {
		// TODO Auto-generated method stub
		return this.merchantImage;
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
