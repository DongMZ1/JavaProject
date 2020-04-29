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

    static String lobbyName = "Hex 13";
    static MinuetoFont toggleFont = new MinuetoFont("Times New Roman",60, true, false);
    static MinuetoImage easy = new MinuetoText("EASY", toggleFont, MinuetoColor.GREEN);
    static MinuetoImage hard = new MinuetoText("HARD", toggleFont, MinuetoColor.RED);
    static MinuetoImage empty = new MinuetoText("EMPTY", toggleFont, MinuetoColor.BLACK);
    static MinuetoImage player1 = new MinuetoText("Jamie", toggleFont, MinuetoColor.BLACK);
    static MinuetoImage tavernName = new MinuetoText(lobbyName + " Tavern", toggleFont, MinuetoColor.BLACK);

    public Constants() throws MinuetoFileException {
    }
}
