import java.io.Serializable;

public class Helm implements Item, Serializable {
	//private MinuetoImage goldImage;
	private int tile;
	
	public Helm(int tile) {
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
