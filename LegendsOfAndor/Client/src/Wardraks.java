import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

import java.io.IOException;

public class Wardraks extends Monster {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1990966408294611957L;

	public Wardraks(int tile) throws IOException {
		super(tile);
		this.health = 7;
		this.strength = 10;
	}

}
