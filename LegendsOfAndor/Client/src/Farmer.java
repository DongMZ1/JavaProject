import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class Farmer implements TileEntity{
	
	MinuetoImage farmerImage;
	int tile;
	Hero hero = null;
	boolean isguided = false;
	public Farmer(MinuetoImage farmerImage, int tile) throws MinuetoFileException {
		this.farmerImage = farmerImage;
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
        return this.farmerImage;
    }
	
	public void isGuidedBy(Hero hero) {
		//if a farmer is not guided by a hero
		if(!isguided) {
			isguided = true;
		    this.hero = hero;
		}
	}
	
	public void isDropped() {
		if(isguided) {
		this.isguided = false;
		this.hero = null;
	}
	
	}
}
