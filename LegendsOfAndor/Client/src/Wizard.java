import java.io.IOException;

import org.minueto.image.MinuetoImage;

public class Wizard extends Hero{

	public Wizard(int tile, boolean mainHero) throws IOException {
		super(tile, mainHero);
		diceList.add(new Dice(new int[] {1,2,3,4,5,6}));
	}

}
