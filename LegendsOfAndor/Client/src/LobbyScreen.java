import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.handlers.MinuetoKeyboard;
import org.minueto.handlers.MinuetoMouse;
import org.minueto.image.*;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class LobbyScreen implements Inputtable, Serializable {

	private static final long serialVersionUID = -5178509001230895367L;
	public boolean isEasy;

    private int currentChar = 0;
    public static GameStatus gameStatus;
    public boolean readyToStart;
    MinuetoImage background;
    private MinuetoImage difficultyToggle;
    private static LobbyScreen lobbyScreen;
    private static MinuetoFont startFont;
    private static MinuetoText startText;

    static MinuetoFont toggleFont = new MinuetoFont("Times New Roman",60, true, false);
    static MinuetoImage easy = new MinuetoText("EASY", toggleFont, MinuetoColor.GREEN);
    static MinuetoImage hard = new MinuetoText("HARD", toggleFont, MinuetoColor.RED);

    public ArrayList<LobbyPlayer> players;
    public static LobbyScreen getInstance() throws IOException {
        if(lobbyScreen == null)
            lobbyScreen = new LobbyScreen();
        return lobbyScreen;
    }

    private LobbyScreen() throws IOException {
        this.isEasy = false;
        gameStatus = GameStatus.getInstance();

        players = new ArrayList<>();
        this.background = new MinuetoImageFile("images/LobbyBackground.jpg").crop(0, 200, 1920, 1080);
        this.difficultyToggle = new MinuetoRectangle(400, 150, new MinuetoColor(230, 230, 230), true);
        this.startFont = new MinuetoFont("Times New Roman",45, true, false);
        this.startText = new MinuetoText("Start Game", startFont, MinuetoColor.BLACK);
        readyToStart = false;

    }

    public void draw() {
    	Client.screen.draw(background, 0, 0);
        Client.screen.draw(difficultyToggle, gameStatus.screenWidth - 400, gameStatus.screenHeight - 100);
        Client.screen.draw(difficultyToggle, 300, gameStatus.screenHeight - 100);
        Client.screen.draw(startText, 325, gameStatus.screenHeight - 90);
        for(int i = 0; i < players.size(); i++) {
            players.get(i).draw();
        }
        if(isEasy)
            Client.screen.draw(easy, gameStatus.screenWidth - (int) (400 / 1.2), gameStatus.screenHeight - 90);
        else
            Client.screen.draw(hard, gameStatus.screenWidth - (int) (400 / 1.15), gameStatus.screenHeight - 90);
    }

    public void handleKeyPress(int i) { }
    public void handleKeyRelease(int i) {}
    public void handleKeyType(char c) {

    }
    public void handleMousePress(int x, int y, int button) {
        if(x > 100) {
            if(y > 50 && y < 150 && Client.playerNum==1)
                players.get(0).handleMousePress(x,y,button);
            else if(y > 175 && y < 275 && players.size()>1 && Client.playerNum==2)
                players.get(1).handleMousePress(x,y,button);
            else if(y > 300 && y < 400 && players.size()>2 && Client.playerNum==3)
                players.get(2).handleMousePress(x,y,button);
            else if(y > 425 && y < 525 && players.size()>3 && Client.playerNum==4)
                players.get(3).handleMousePress(x,y,button);
        }

        if(y > gameStatus.screenHeight - 100) {
            if(x > 300 && x < 700) {
                readyToStart = true;
                InputThread.sendString("r " + "1");
            }
            else if(x > gameStatus.screenWidth-400 && x < gameStatus.screenWidth) {
                InputThread.sendString("d 1");
            }
        }
    }
    public void handleMouseRelease(int x, int y, int button) {

    }
    public void handleMouseMove(int x, int y) {

    }
    public void handleMouseWheelRotate(int rotation) {

    }
}
