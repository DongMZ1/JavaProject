import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class Wineskin implements Item {

	int tile;
	
	public Wineskin(int tile) throws MinuetoFileException {
		this.tile = tile;
		// TODO Auto-generated constructor stub
	}
	 public void setTile(int tile) {
	        this.tile = tile;
	    }
	    public int getTile() {
	        return this.tile;
	    }

}
