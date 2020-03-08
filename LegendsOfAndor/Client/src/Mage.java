import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

public class Mage extends Hero{

	//special ability: change dice result, implemented in fight class
	
	public Mage(MinuetoImage heroImage, int tile, boolean mainHero) throws MinuetoFileException {
		super(heroImage, tile, mainHero);
		// TODO Auto-generated constructor stub
	}
	
	Dice dice = new MageDice();

}
