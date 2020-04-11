import java.io.Serializable;

public class PrinceThorald implements Character, Serializable {
    public int tile;
 
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
