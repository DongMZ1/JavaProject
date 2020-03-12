import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class Gold implements Item{
	private static MinuetoImage goldImage; 
	private int tile;
	
	public Gold(int tile) throws MinuetoFileException {
		this.goldImage = new MinuetoImageFile("images/gold.png");
		this.tile = tile;
	}
	
	//UPDATE
	public void setTile(int tile) {
		this.tile = tile;
	}
	public int getTile() {
		return this.tile;
	}
	public MinuetoImage getImage() {
		return this.goldImage;
	}

}
