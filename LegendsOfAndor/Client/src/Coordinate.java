import java.io.Serializable;

public class Coordinate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7792525181697584490L;
	private int x;
	private int y;
	public Coordinate() {
		x = 0;
		y = 0;
	}
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}



}
