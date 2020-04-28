import java.io.Serializable;

public class PrinceThorald implements Character, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 921499384106716628L;
	public int tile;
 
	public PrinceThorald(int tile) {
		this.tile = tile;
	}

	@Override
	public int getTile() {
		// TODO Auto-generated method stub
		return this.tile;
	}

	@Override
	public void setTile(int tile) {
		this.tile = tile;
		
	}

}
