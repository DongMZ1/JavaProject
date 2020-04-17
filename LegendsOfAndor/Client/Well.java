import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

import java.io.IOException;

public class Well implements TileEntity{

	private int tile;
	

	private boolean isEmpty; //Boolean shows whether the well is empty
	
	//constructor 
	public Well(int assignedTile) throws IOException {
		setWellEmptiness(false);
		tile = assignedTile;
	}	

	public void replenishWell() {
		if(isEmpty) {
			setWellEmptiness(false);
		}
	}

	public int emptiedByHero(Hero hero) {
		//will point returned to specific hero class 
		int wp;
		if(!this.isEmpty && hero instanceof Warrior) {
			setWellEmptiness(true);
			wp = 5;
		}
		
		else if(!this.isEmpty) {
			setWellEmptiness(true);
			wp = 3;			
		}
		
		else {
			wp = 0;
		}
		return wp;
	}

	//UPDATE
	public void setWellEmptiness(boolean a){
		isEmpty = a;
	}

	public boolean isEmpty() {
		return this.isEmpty;
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
