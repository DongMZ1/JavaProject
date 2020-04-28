
import java.io.Serializable;

public class Shield implements Item, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4938974285687148033L;
	//private MinuetoImage goldImage;
	private int tile;
	
	public Shield(int tile) {
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
