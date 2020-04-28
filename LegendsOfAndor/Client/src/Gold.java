import java.io.Serializable;

public class Gold implements Item, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5964844813214796596L;
	//private MinuetoImage goldImage;
	private int tile;
	
	public Gold(int tile) {
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
