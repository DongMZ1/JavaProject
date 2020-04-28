import java.io.Serializable;

public class RuneStone implements Item, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4683280104664928120L;
	//private MinuetoImage goldImage;
	private int tile;
	
	public RuneStone(int tile) {
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
