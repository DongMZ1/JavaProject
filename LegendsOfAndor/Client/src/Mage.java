import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

import java.io.IOException;

public class Mage extends Hero{

	//special ability: change dice result, implemented in fight class
	
	public Mage(int tile, boolean mainHero) throws IOException {
		super(tile, mainHero);
		dice = new MageDice();
	}
	
	
}
