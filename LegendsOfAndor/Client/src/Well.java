import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

public class Well implements TileEntity{

	MinuetoImage wellImage;
	int tile;
	boolean IsEmpty; //Boolean shows whether the well is empty
	
	public void replenishWell() {
		if(!IsEmpty) {
			IsEmpty = false;
		}
	}
	
	public int emptiedByHero() {
		if(!this.IsEmpty) {
			this.IsEmpty = true;
			return 3;
		}
		
		else {
			return 0;
		}
	}
	
	
	public Well(int tile) throws MinuetoFileException {
		
		IsEmpty = false;
		this.tile = tile;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public MinuetoImage getImage() {
		//No well image
		return null;
	}

	@Override
	public int getTile() {
		return this.tile;
	}

	@Override
	public void setTile(int tile) {
		this.tile = tile;
	}

}
