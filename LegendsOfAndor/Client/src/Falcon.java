import java.io.Serializable;

public class Falcon implements Item, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8950167645195494056L;
	//private MinuetoImage goldImage;
	private int tile;
	
	public Falcon(int tile) {
		//this.goldImage = new MinuetoImageFile("images/gold.png");
		this.tile = tile;
	}
	
	//UPDATE
	public void setTile(int tile) {
		this.tile = tile;
	}
	public int getTile() {
		return this.tile;
	}
	//public MinuetoImage getImage() {return this.goldImage;}

}
