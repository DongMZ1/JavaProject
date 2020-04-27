import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

import java.io.IOException;

public class Gor extends Monster {

	public Gor(int tile) throws IOException {
		super(tile);
		this.health = 4;
		this.strength = 4;
	}

}
