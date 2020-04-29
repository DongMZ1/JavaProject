import java.io.Serializable;

import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.image.*;

public class Constants implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1159089522960529388L;
	//TODO Move Constants to Own Classes
    public static final int TOP_OF_BOARD = -6476;
    public static final int SIDE_OF_BOARD = -9861;
    public static final int BOTTOM_SCREEN_SPACE = 300;
    public static final int NUM_OF_TILES = 78;

    public static MinuetoImage Willpower = new MinuetoCircle(40, 40, MinuetoColor.BLUE, false);
    public static MinuetoImage strength = new MinuetoRectangle(25, 25, MinuetoColor.BLUE, false);

    public static final MinuetoImage MaleButton = new MinuetoRectangle(100, 100, new MinuetoColor(50, 50, 255), true);

    public static final double HERO_SCALE = 0.2;

}
