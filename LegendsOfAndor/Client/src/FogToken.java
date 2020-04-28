import java.io.Serializable;


public class FogToken implements TileEntity, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3838112663007106688L;
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
