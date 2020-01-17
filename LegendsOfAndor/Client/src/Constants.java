import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoCircle;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoRectangle;

public class Constants {
    //TODO Move Constants to Own Classes
    public static final int TOP_OF_BOARD = -6476;
    public static final int SIDE_OF_BOARD = -9861;
    public static final int BOTTOM_SCREEN_SPACE = 300;

    public static MinuetoImage Willpower = new MinuetoCircle(40, 40, MinuetoColor.BLUE, false);
    public static MinuetoImage strength = new MinuetoRectangle(25, 25, MinuetoColor.BLUE, false);

    public static final int DIFFICULTY_WIDTH = 400;
    public static final int DIFFICULTY_HEIGHT = 150;
    public static final MinuetoImage difficultyToggle = new MinuetoRectangle(DIFFICULTY_WIDTH, DIFFICULTY_HEIGHT, new MinuetoColor(230, 230, 230), true);

    public static final int PLAYER_WIDTH = 1200;
    public static final int PLAYER_HEIGHT = 150;
    public static final MinuetoImage playerPlate = new MinuetoRectangle(PLAYER_WIDTH, PLAYER_HEIGHT, new MinuetoColor(230, 230, 230), true);
    public static final MinuetoImage MaleButton = new MinuetoRectangle(100, 100, new MinuetoColor(50, 50, 255), true);
    public static final MinuetoImage chooseCharHighlight = new MinuetoRectangle(100, 100, MinuetoColor.RED, false);

    public static final double HERO_SCALE = 0.2;
}
