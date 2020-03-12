
import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.image.*;

public class Constants {
    //TODO Move Constants to Own Classes
    public static final int TOP_OF_BOARD = -6476;
    public static final int SIDE_OF_BOARD = -9861;
    public static final int BOTTOM_SCREEN_SPACE = 300;
    public static final int NUM_OF_TILES = 78;

    public static MinuetoImage Willpower = new MinuetoCircle(40, 40, MinuetoColor.BLUE, false);
    public static MinuetoImage strength = new MinuetoRectangle(25, 25, MinuetoColor.BLUE, false);

    public static final int DIFFICULTY_WIDTH = 400;
    public static final int DIFFICULTY_HEIGHT = 150;
    public static final MinuetoImage difficultyToggle = new MinuetoRectangle(DIFFICULTY_WIDTH, DIFFICULTY_HEIGHT, new MinuetoColor(230, 230, 230), true);

    public static final int PLAYER_WIDTH = (2*1200)/3;
    public static final int PLAYER_HEIGHT = (2*150)/3;
    public static final MinuetoImage playerPlate = new MinuetoRectangle(PLAYER_WIDTH, PLAYER_HEIGHT, new MinuetoColor(230, 230, 230), true);
    public static final MinuetoImage MaleButton = new MinuetoRectangle(100, 100, new MinuetoColor(50, 50, 255), true);
    public static final MinuetoImage chooseCharHighlight = new MinuetoRectangle(100, 100, MinuetoColor.RED, false);
    public static final double HERO_SCALE = 0.2;
    public static final MinuetoFont FONT = new MinuetoFont("Arial",20, true, false);
    public static final MinuetoImage BACKGROUND = new MinuetoRectangle(12000, 9000, MinuetoColor.BLACK, true);


    static String lobbyName = "Hex 13";
    static MinuetoFont toggleFont = new MinuetoFont("Times New Roman",60, true, false);
    static MinuetoFont startFont = new MinuetoFont("Times New Roman",45, true, false);
    static MinuetoFont playerPlateFont = new MinuetoFont("Times New Roman",20, false, false);
    static MinuetoImage background;
    static MinuetoImage easy = new MinuetoText("EASY", toggleFont, MinuetoColor.GREEN);
    static MinuetoImage hard = new MinuetoText("HARD", toggleFont, MinuetoColor.RED);
    static MinuetoImage start = new MinuetoText("Start Game", startFont, MinuetoColor.BLACK);
    static MinuetoImage empty = new MinuetoText("EMPTY", toggleFont, MinuetoColor.BLACK);
    static MinuetoImage player1 = new MinuetoText("Jamie", toggleFont, MinuetoColor.BLACK);
    static MinuetoImage maleLogo;
    static MinuetoImage femaleLogo;
    static MinuetoImage warriorThumbnail;
    static MinuetoImage archerThumbnail;
    static MinuetoImage mageThumbnail;
    static MinuetoImage dwarfThumbnail;
    static MinuetoImage tavernName = new MinuetoText(lobbyName + " Tavern", toggleFont, MinuetoColor.BLACK);
    static {
        try {
            background = new MinuetoImageFile("images/LobbyBackground.jpg").crop(0, 200, 1920, 1080);
            dwarfThumbnail = new MinuetoImageFile("images/Heroes/DwarfMaleThumbnail.png");
            mageThumbnail = new MinuetoImageFile("images/Heroes/MageMaleThumbnail.png");
            archerThumbnail = new MinuetoImageFile("images/Heroes/ArcherMaleThumbnail.png");
            warriorThumbnail = new MinuetoImageFile("images/Heroes/WarriorMaleThumbnail.png");
            femaleLogo = new MinuetoImageFile("images/FemaleSymbol.png");
            maleLogo = new MinuetoImageFile("images/MaleSymbol.png").scale(0.2, 0.2);
        } catch (MinuetoFileException e) {
            e.printStackTrace();
        }
    }

    public Constants() throws MinuetoFileException {
    }
}
