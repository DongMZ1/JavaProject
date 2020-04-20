import java.io.Serializable;

import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class Wineskin implements Item , Serializable{

	int tile;
	
	public Wineskin(int tile){
		this.tile = tile;
	}
	 public void setTile(int tile) {
	        this.tile = tile;
	    }
	    public int getTile() {
	        return this.tile;
	    }

}
