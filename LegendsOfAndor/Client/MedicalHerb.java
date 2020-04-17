import java.io.Serializable;

public class MedicalHerb implements Item, Serializable {
	//private MinuetoImage goldImage;
	private int tile;
	
	public MedicalHerb(int tile) {
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
