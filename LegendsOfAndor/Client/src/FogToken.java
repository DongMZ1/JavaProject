import org.minueto.image.MinuetoImage;

@SuppressWarnings("serial")
public class FogToken implements TileEntity{
		
	public int tile;
    public int tokenNumber; // this number indicate what will this token do.
	public FogToken(int tile, int tokenNumber){
		this.tile = tile;
		this.tokenNumber = tokenNumber;
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
