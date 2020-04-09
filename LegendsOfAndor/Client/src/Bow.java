import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

import java.io.Serializable;

public class Bow implements Item, Serializable {
	int Tile;

	public void Bow(int tile) throws MinuetoFileException{
		Tile = tile;
	}

	@Override
	public int getTile() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTile(int tile) {
		// TODO Auto-generated method stub
		
	}

}
