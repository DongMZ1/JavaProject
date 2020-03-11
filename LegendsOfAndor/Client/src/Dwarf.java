import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

import java.io.IOException;

public class Dwarf extends Hero{

	// dwarfs can buy SP in dwarf mine for one gold each
		
	public Dwarf(MinuetoImage heroImage, int tile, boolean mainHero) throws IOException {
		super(heroImage, tile, mainHero);
		// TODO Auto-generated constructor stub
		dice = new DwarfDice();

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
