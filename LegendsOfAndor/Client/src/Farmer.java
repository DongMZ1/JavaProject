import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class Farmer implements TileEntity{

	private MinuetoImage farmerImage;
	private int tile;
	private Hero hero = null;
	private boolean guided = false;
	
	public boolean getGuided() {
		return guided;
	}

	//UPDATE
	public void setGuided(boolean guided) {
		this.guided = guided;
	}


	public Farmer(MinuetoImage farmerImage, int tile) throws MinuetoFileException {
		this.farmerImage = farmerImage;
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
		return this.farmerImage;
	}

	public void isGuidedBy(Hero hero) {
		if(!guided) {
			setGuided(true);
			this.hero = hero;
		}
	}

	public void isDropped() {
		if(guided) {
			setGuided(false);
			this.hero = null;
		}

	}
}
