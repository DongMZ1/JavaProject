import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

public class Dwarf extends Hero{

	// dwarfs can buy SP in dwarf mine for one gold each
		
	public Dwarf(MinuetoImage heroImage, int tile, boolean mainHero) throws MinuetoFileException {
		super(heroImage, tile, mainHero);
		// TODO Auto-generated constructor stub
	}

	public void Buy_SP_from_dwarf_mine() {
		
		// firstly, find out if the dwarf has more than one gold to trade
		if (getGoldNm() > 1) {
			// Second check if there is dwarf mine
			for (TileEntity t : Tile.get(tile).getTileEntities()) {
				if (t instanceof DwarfMine) {
					// if there is a merchant then trade, set a counter to remove two gold from
					// items list of hero
					int counter = 0;
					for (Item i : items) {

						// if we already remove two gold, then add one strength point, otherwise continue remove
						// gold object of items list
						if (counter == 1) {
							sp += 1;
							break;
						}
						
						if (i instanceof Gold) {
							this.items.remove(i);
							counter++;
						}
					}

				}
			}
		}
	}//end of function
	
}
