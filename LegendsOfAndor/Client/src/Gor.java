import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

import java.io.IOException;

public class Gor extends Monster {

	/**
	 * 
	 */
	private static final long serialVersionUID = -709501381963345088L;

	public Gor(int tile) throws IOException {
		super(tile);
		this.health = 4;
		this.strength = 4;
	}

}
