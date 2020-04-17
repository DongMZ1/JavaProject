import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.handlers.MinuetoMouse;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;
import org.minueto.image.MinuetoText;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;
import java.io.Serializable;

public class LobbyScreen implements Inputtable, Serializable {
    //TODO Clean LobbyScreen class
    private boolean isEasy;

    private int currentChar = 0;
    public static GameStatus gameStatus;
    private static TextBox textBox;
    int ghettoText;
    public LobbyScreen() throws IOException {
        this.isEasy = false;
        gameStatus = GameStatus.getInstance();
        textBox = TextBox.getInstance();
        ghettoText = (int) (textBox.getWidth()*1.5);
    }

    public void draw() {
    	drawScale(Constants.background, 0, 0);
    	drawScale(Constants.tavernName, 20, 15);
    	drawScale(Constants.difficultyToggle, gameStatus.screenWidth - Constants.DIFFICULTY_WIDTH, 0);
    	drawScale(Constants.difficultyToggle, ghettoText + 300, gameStatus.screenHeight - Constants.DIFFICULTY_HEIGHT + 50);
    	drawScale(Constants.start, ghettoText + 325, 3*(gameStatus.screenHeight)/2 - 100);
        for(int i = 0; i < 4; i++) {
        	drawScale(Constants.playerPlate, ghettoText + 75, 200 + i * (Constants.PLAYER_HEIGHT + 50));
            if(i == 0) {
            	drawScale(Constants.player1, ghettoText + 100, 225);
            }
            else
            	drawScale(Constants.empty, ghettoText + 100, 225 + i * (Constants.PLAYER_HEIGHT + 50));
        }
        drawScale(Constants.MaleButton, ghettoText + 500, 225);
        drawScale(Constants.warriorThumbnail, ghettoText + 650, 225);
        drawScale(Constants.archerThumbnail, ghettoText + 800, 225);
        drawScale(Constants.dwarfThumbnail, ghettoText + 950, 225);
        drawScale(Constants.mageThumbnail, ghettoText + 1100, 225);
        drawScale(Constants.chooseCharHighlight, ghettoText + 650 + (currentChar * 150), 225);
        drawScale(Constants.maleLogo, ghettoText + 515, 240);
        if(isEasy)
        	drawScale(Constants.easy, gameStatus.screenWidth - (int) (Constants.DIFFICULTY_WIDTH / 1.2), 15);
        else
        	drawScale(Constants.hard, gameStatus.screenWidth - (int) (Constants.DIFFICULTY_WIDTH / 1.15), 15);
    }
    
    //Could add int scale instead of 1.5
    public void drawScale(MinuetoImage image, int x, int y) {
    	Client.screen.draw(image,(int) (x/1.5), (int) (y/1.5));
    }
    
    public boolean clickedScale (int x1, int y1, int x2, int y2, int clickedX, int clickedY) {
    	return isClicked((int) (x1/1.5), (int) (y1/1.5), (int) (x2/1.5), (int) (y2/1.5), clickedX, clickedY);
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
    	System.out.println("X: " + x + "Y: " + y);
        if(isClicked(660,700,900,650, x, y)) {
            gameStatus.focus = gameStatus.FOCUS_ON_GAMESCREEN;
            gameStatus.currentScreen = gameStatus.GAME_SCREEN;
        }
        else if(isClicked(0, gameStatus.screenHeight, textBox.getWidth(), gameStatus.screenHeight - textBox.getHeight(), x, y)) {
            gameStatus.focus = gameStatus.FOCUS_ON_TEXTBOX;
        }
        else if(button == MinuetoMouse.MOUSE_BUTTON_LEFT && clickedScale(gameStatus.screenWidth - Constants.DIFFICULTY_WIDTH,
                Constants.DIFFICULTY_HEIGHT, gameStatus.screenWidth, 0, x, y))
        isEasy = !isEasy;
        else if(button == MinuetoMouse.MOUSE_BUTTON_LEFT && clickedScale(ghettoText + 650,
                325, ghettoText + 750, 225, x, y))
            currentChar = 0;
        else if(button == MinuetoMouse.MOUSE_BUTTON_LEFT && clickedScale(ghettoText + 800,
                325, ghettoText + 900, 225, x, y))
            currentChar = 1;
        else if(button == MinuetoMouse.MOUSE_BUTTON_LEFT && clickedScale(ghettoText + 950,
                325, ghettoText + 1050, 225, x, y))
            currentChar = 2;
        else if(button == MinuetoMouse.MOUSE_BUTTON_LEFT && clickedScale(ghettoText + 1100,
                325, ghettoText + 1200, 225, x, y))
            currentChar = 3;
    }
    public void handleMouseRelease(int x, int y, int button) {

    }
    public void handleMouseMove(int x, int y) {

    }
    public void handleMouseWheelRotate(int rotation) {

    }
}
