import java.io.IOException;

import org.minueto.image.MinuetoImage;

public class Wizard extends Hero{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7602006500691237341L;

	public Wizard(int tile) throws IOException {
		super(tile);
		diceList.add(new Dice(new int[] {1,2,3,4,5,6}));
	}

}
