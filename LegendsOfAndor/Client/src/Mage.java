import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

import java.io.IOException;

public class Mage extends Hero{

	//special ability: change dice result, implemented in fight class
	
	public Mage(MinuetoImage heroImage, int tile, boolean mainHero) throws IOException {
		super(heroImage, tile, mainHero);
		// TODO Auto-generated constructor stub
		dice = new MageDice();
	}
	
	
}
