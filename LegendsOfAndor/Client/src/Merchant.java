import org.minueto.image.MinuetoImage;

public class Merchant implements TileEntity {
	static MinuetoImage merchantImage;
	int tile;
	
	public Merchant(MinuetoImage merchantImage,int tile ) {
		this.merchantImage = merchantImage;
		this.tile = tile ;
	}
	@Override
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
