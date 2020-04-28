import java.io.Serializable;

@SuppressWarnings("serial")
public class FogToken implements TileEntity, Serializable {
		
	public int tile;
    public int tokenNumber; // this number indicate what will this token do.
	public FogToken() {
		tile = 0;
		tokenNumber = 0;
	}
	public FogToken(int tile, int tokenNumber){
		this.tile = tile;
		this.tokenNumber = tokenNumber;
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
