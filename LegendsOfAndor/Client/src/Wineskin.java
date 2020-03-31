import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class Wineskin implements Item {

	MinuetoImage wineskin; 
	int tile;
	
	public Wineskin(int tile) throws MinuetoFileException {
		this.wineskin = new MinuetoImageFile("images/wineskin.png").scale(0.5, 0.5);
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
     return this.wineskin;
 }

}
