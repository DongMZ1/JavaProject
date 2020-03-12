import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

import java.io.IOException;

public class Well implements TileEntity{

	transient MinuetoImage wellImage;
	private int tile;
	

	private boolean IsEmpty; //Boolean shows whether the well is empty
	
	//constructor 
	public Well(MinuetoImage image, int assignedTile) throws IOException {
		wellImage = image;
		setWellEmptiness(false);
		tile = assignedTile;
	}	

	public void replenishWell() throws MinuetoFileException {
		if(IsEmpty) {
			setWellEmptiness(false);
			setImage(new MinuetoImageFile("images/Well.png"));
		}
	}

	public int emptiedByHero(Hero hero) throws MinuetoFileException {
		//will point returned to specific hero class 
		int wp;
		if(!this.IsEmpty && hero instanceof Warrior) {
			setWellEmptiness(true);
			wp = 5;
		}
		
		else if(!this.IsEmpty) {
			setWellEmptiness(true);
			wp = 3;			
		}
		
		else {
			wp = 0;
		}
		
		setImage(new MinuetoImageFile("images/emptyWell.png"));
		return wp;
	}
	
	private void setImage(MinuetoImage image) {
		wellImage = image;
	}
	
	//UPDATE
	public void setWellEmptiness(boolean a){
		IsEmpty = a;
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
