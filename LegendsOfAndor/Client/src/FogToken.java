import org.minueto.image.MinuetoImage;

@SuppressWarnings("serial")
public class FogToken implements TileEntity{
	
	private MinuetoImage FogTokenImage;
		
	public int tile;
    public int tokenNumber; // this number indicate what will this token do.
	public FogToken(MinuetoImage FogTokenImage, int tile, int tokenNumber){
		this.FogTokenImage = FogTokenImage;
		this.tile = tile;
		this.tokenNumber = tokenNumber;
	}
	
	public MinuetoImage getImage() {
		// TODO Auto-generated method stub
		return this.FogTokenImage;
	}

	@Override
	public int getTile() {
		// TODO Auto-generated method stub
		return this.getTile();
	}

	@Override
	public void setTile(int tile) {
		// TODO Auto-generated method stub
		this.tile = tile;
	}
	
	
	

}
