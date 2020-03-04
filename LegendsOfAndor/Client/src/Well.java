import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

public class Well implements TileEntity{

	MinuetoImage wellImage;
	private int tile;
	GameStatus gameStatus;
	private boolean IsEmpty; //Boolean shows whether the well is empty
	
	//constructor 
	public Well(MinuetoImage image, int assignedTile) throws MinuetoFileException {
		wellImage = image;
		IsEmpty = false;
		tile = assignedTile;
		gameStatus = GameStatus.getInstance();

	}	
	
	//replenish the well in a new day
	public void replenishWell() {
		if(!IsEmpty) {
			IsEmpty = false;
		}
	}
	
	//this function's called when a hero intends to drink from the well
	public int emptiedByHero(Hero hero) {
		//will point returned to specific hero class 
		int wp;
		
		if(!this.IsEmpty && hero instanceof Warrior) {
			this.IsEmpty = true;
			wp = 5;
		}
		
		else if(!this.IsEmpty) {
			this.IsEmpty = true;
			wp = 3;			
		}
		
		else {
			wp = 0;
		}
		
		return wp;
	}
	
	@Override
	public MinuetoImage getImage() {
		
		return wellImage;
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
