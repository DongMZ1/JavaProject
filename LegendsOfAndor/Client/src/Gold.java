import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

public class Gold implements TileEntity{
	MinuetoImage goldImage; 
	int tile;
	public Gold(MinuetoImage goldImage, int tile) throws MinuetoFileException {
		this.goldImage = goldImage;
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
