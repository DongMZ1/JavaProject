import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

import java.io.IOException;

public class Mage extends Hero{

	//special ability: change dice result, implemented in fight class
	
	public Mage(int tile) throws IOException {
		super(tile);
		diceList.add(new Dice(new int[] {1,2,3,4,5,6}));
	}
	
	
}
