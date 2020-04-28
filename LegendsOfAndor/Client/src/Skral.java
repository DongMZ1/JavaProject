import java.io.IOException;

public class Skral extends Monster {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1524210835598396550L;

	public Skral(int tile) throws IOException {
		super(tile);
		this.health = 6;
		this.strength = 6;
	}
}
