import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

import java.io.IOException;
import java.io.Serializable;

public class Dwarf extends Hero implements Serializable {

	// dwarfs can buy SP in dwarf mine for one gold each
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -8340230892698155216L;

	public Dwarf(int tile) throws IOException {
		super(tile);
		diceList.add(new Dice(new int[] {1,2,3,4,5,6}));
		diceList.add(new Dice(new int[] {1,2,3,4,5,6}));

	}

	public void Buy_SP_from_dwarf_mine() {		
		// check for remaining gold number and hero position
		// there's only one dwarf mine in game, which is located on tile 71
		
		if (getGoldNm() > 1 && tile == 71) {

			int counter = 0;
			for (Item i : items) {

				// if one gold removed, then add one strength point
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
			
	}//end of function
	
}
