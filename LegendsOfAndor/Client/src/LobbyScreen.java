import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.handlers.MinuetoMouse;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;
import org.minueto.image.MinuetoText;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;

public class LobbyScreen implements Inputtable {
    //TODO Clean LobbyScreen class
    private boolean isEasy;
    private MinuetoWindow screen;
    private String lobbyName = "Hex 13";
    private MinuetoFont toggleFont = new MinuetoFont("Times New Roman",100, true, false);
    private MinuetoFont startFont = new MinuetoFont("Times New Roman",70, true, false);
    private MinuetoFont playerPlateFont = new MinuetoFont("Times New Roman",20, false, false);
    private MinuetoImage background = new MinuetoImageFile("images/LobbyBackground.jpg").crop(0, 200, 1920, 1080);
    private MinuetoImage tavernName = new MinuetoText(lobbyName + " Tavern", toggleFont, MinuetoColor.BLACK);
    private MinuetoImage easy = new MinuetoText("EASY", toggleFont, MinuetoColor.GREEN);
    private MinuetoImage hard = new MinuetoText("HARD", toggleFont, MinuetoColor.RED);
    private MinuetoImage start = new MinuetoText("Start Game", startFont, MinuetoColor.BLACK);
    private MinuetoImage empty = new MinuetoText("EMPTY", toggleFont, MinuetoColor.BLACK);
    private MinuetoImage player1 = new MinuetoText("Jamie", toggleFont, MinuetoColor.BLACK);
    private MinuetoImage maleLogo = new MinuetoImageFile("images/MaleSymbol.png").scale(0.2, 0.2);
    private MinuetoImage femaleLogo = new MinuetoImageFile("images/FemaleSymbol.png");
    private MinuetoImage warriorThumbnail = new MinuetoImageFile("images/Heroes/WarriorMaleThumbnail.png");
    private MinuetoImage archerThumbnail = new MinuetoImageFile("images/Heroes/ArcherMaleThumbnail.png");
    private MinuetoImage mageThumbnail = new MinuetoImageFile("images/Heroes/MageMaleThumbnail.png");
    private MinuetoImage dwarfThumbnail = new MinuetoImageFile("images/Heroes/DwarfMaleThumbnail.png");
    private int currentChar = 0;
    private static GameStatus gameStatus;
    private static TextBox textBox;
    public LobbyScreen(MinuetoWindow screen) throws IOException {
        this.screen = screen;
        this.isEasy = false;
        gameStatus = GameStatus.getInstance();
        textBox = TextBox.getInstance();
    }

    public void draw() {
        screen.draw(background, 0, 0);
        screen.draw(tavernName, 20, 15);
        screen.draw(Constants.difficultyToggle, gameStatus.screenWidth - Constants.DIFFICULTY_WIDTH, 0);
        screen.draw(Constants.difficultyToggle, textBox.getWidth() + 300, gameStatus.screenHeight - Constants.DIFFICULTY_HEIGHT + 50);
        screen.draw(start, textBox.getWidth() + 325, gameStatus.screenHeight - Constants.DIFFICULTY_HEIGHT + 60);
        for(int i = 0; i < 4; i++) {
            screen.draw(Constants.playerPlate, textBox.getWidth() + 50, 200 + i * (Constants.PLAYER_HEIGHT + 50));
            if(i == 0) {
                screen.draw(player1, textBox.getWidth() + 75, 225);
            }
            else
                screen.draw(empty, textBox.getWidth() + 75, 225 + i * (Constants.PLAYER_HEIGHT + 50));
        }
        screen.draw(Constants.MaleButton, textBox.getWidth() + 500, 225);
        screen.draw(warriorThumbnail, textBox.getWidth() + 650, 225);
        screen.draw(archerThumbnail, textBox.getWidth() + 800, 225);
        screen.draw(dwarfThumbnail, textBox.getWidth() + 950, 225);
        screen.draw(mageThumbnail, textBox.getWidth() + 1100, 225);
        screen.draw(Constants.chooseCharHighlight, textBox.getWidth() + 650 + (currentChar * 150), 225);
        screen.draw(maleLogo, textBox.getWidth() + 515, 240);
        if(isEasy)
            screen.draw(easy, gameStatus.screenWidth - (int) (Constants.DIFFICULTY_WIDTH / 1.2), 15);
        else
            screen.draw(hard, gameStatus.screenWidth - (int) (Constants.DIFFICULTY_WIDTH / 1.15), 15);
    }

    /**
     * @param x1 Bottom left x coordinate
     * @param y1 Bottom left y coordinate
     * @param x2 Top right x coordinate
     * @param y2 Top right y coordinate
     * @param clickedX x coordinate of click
     * @param clickedY y coordinate of click
     * @return true if (clickedX, clickedY) falls inside box. False otherwise
     */
    public boolean isClicked(int x1, int y1, int x2, int y2, int clickedX, int clickedY) {
        return clickedX > x1 && clickedX < x2 && clickedY < y1 && clickedY > y2;
    }

    public void handleKeyPress(int i) { }
    public void handleKeyRelease(int i) {}
    public void handleKeyType(char c) {

    }
    public void handleMousePress(int x, int y, int button) {
        if(isClicked(textBox.getWidth() + 325, gameStatus.screenHeight, textBox.getWidth() + 325 + Constants.DIFFICULTY_WIDTH,
                        gameStatus.screenHeight - Constants.DIFFICULTY_HEIGHT + 60, x, y)) {
            gameStatus.focus = gameStatus.FOCUS_ON_GAMESCREEN;
            gameStatus.currentScreen = gameStatus.GAME_SCREEN;
        }
        else if(isClicked(0, gameStatus.screenHeight, textBox.getWidth(), gameStatus.screenHeight - textBox.getHeight(), x, y)) {
            gameStatus.focus = gameStatus.FOCUS_ON_TEXTBOX;
        }
        else if(button == MinuetoMouse.MOUSE_BUTTON_LEFT && isClicked(gameStatus.screenWidth - Constants.DIFFICULTY_WIDTH,
                Constants.DIFFICULTY_HEIGHT, gameStatus.screenWidth, 0, x, y))
        isEasy = !isEasy;
        else if(button == MinuetoMouse.MOUSE_BUTTON_LEFT && isClicked(textBox.getWidth() + 650,
                325, textBox.getWidth() + 750, 225, x, y))
            currentChar = 0;
        else if(button == MinuetoMouse.MOUSE_BUTTON_LEFT && isClicked(textBox.getWidth() + 800,
                325, textBox.getWidth() + 900, 225, x, y))
            currentChar = 1;
        else if(button == MinuetoMouse.MOUSE_BUTTON_LEFT && isClicked(textBox.getWidth() + 950,
                325, textBox.getWidth() + 1050, 225, x, y))
            currentChar = 2;
        else if(button == MinuetoMouse.MOUSE_BUTTON_LEFT && isClicked(textBox.getWidth() + 1100,
                325, textBox.getWidth() + 1200, 225, x, y))
            currentChar = 3;
    }
    public void handleMouseRelease(int x, int y, int button) {

    }
    public void handleMouseMove(int x, int y) {

    }
    public void handleMouseWheelRotate(int rotation) {

    }
}
