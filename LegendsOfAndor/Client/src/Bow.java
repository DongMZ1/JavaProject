import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class Bow implements Item{
	int Tile;
	static MinuetoImage image;
	
	public void Bowin(int tile) throws MinuetoFileException{
		Tile = tile;
		image = new MinuetoImageFile("images/wineskin.png");
	}
	
	
	@Override
	public MinuetoImage getImage() {
		// TODO Auto-generated method stub
		return null;
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
