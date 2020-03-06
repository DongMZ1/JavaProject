import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class Gold implements Item{
	MinuetoImage goldImage; 
	int tile;
	public Gold(int tile) throws MinuetoFileException {
		this.goldImage = new MinuetoImageFile("images/gold.png");
;
		this.tile = tile;
		// TODO Auto-generated constructor stub
	}
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
