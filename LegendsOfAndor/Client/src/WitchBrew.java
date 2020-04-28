import java.io.Serializable;

public class WitchBrew implements Item, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8323501784942334255L;
	//private MinuetoImage goldImage;
	private int tile;
	
	public WitchBrew(int tile) {
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
