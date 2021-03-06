import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

import java.io.IOException;

public class Archer extends Hero{
	//special ability: in fight, archer can decide when to stop rolling dice. An archer can also attack monster from adjacent space.
	//the features mentioned above are implemented in fight class
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3760727839441370599L;

	public Archer(int tile) throws IOException {
		super(tile);
		diceList.add(new Dice(new int[]{1, 2, 3, 4, 5, 6}));
		diceList.add(new Dice(new int[]{1, 2, 3, 4, 5, 6}));
		diceList.add(new Dice(new int[]{1, 2, 3, 4, 5, 6}));
		diceList.add(new Dice(new int[]{1, 2, 3, 4, 5, 6}));
	}
	
	
}
