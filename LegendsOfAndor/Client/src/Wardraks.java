import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

import java.io.IOException;

public class Wardraks extends Monster {

	public Wardraks(int tile) throws IOException {
		super(tile);
		this.health = 7;
		this.strength = 10;
	}

}
