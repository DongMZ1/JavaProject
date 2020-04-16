import org.minueto.MinuetoFileException;
import org.minueto.window.MinuetoFrame;
import org.minueto.window.MinuetoFullscreen;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class GameStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7976870154680099891L;

	private static GameStatus gameStatus;

    public static final int FOCUS_ON_LOBBY = 0;
    public static final int FOCUS_ON_GAMESCREEN = 1;
    public static final int FOCUS_ON_TEXTBOX = 2;
    public static final int FOCUS_ON_FIGHT = 3;
    public static final int FOCUS_ON_COLLABORATIVE = 4;

    public static final int LOBBY_SCREEN = 0;
    public static final int GAME_SCREEN = 1;
    public static final int FIGHT_SCREEN = 3;
    public static final int COLLABORATIVE_SCREEN = 4;

    public static int lastFocused = 0;

	public int legend2CardIndex = 1;
	public boolean Legend2ModeIsEasy = false;
    public int EventCardIndex = 1;
	int screenWidth = 1280;
	int screenHeight = 720;
	int focus;
	public int getFocus() {
		return focus;
	}
	//UPDATE
	public void setFocus(int focus) {
		this.focus = focus;
	}

	int currentScreen;
	public int getCurrentScreen() {
		return currentScreen;
	}
	//UPDATE
	public void setCurrentScreen(int currentScreen) {
		this.currentScreen = currentScreen;
	}

	UIStatus ui = UIStatus.NONE;
	FightStatus fight = FightStatus.NONE;

	public FightStatus getFight() {
		return fight;
	}
	//UPDATE
	public void setFight(FightStatus fight) {
		this.fight = fight;
	}

	private GameStatus() throws IOException {
		focus = FOCUS_ON_LOBBY;
		currentScreen = LOBBY_SCREEN;

	}

	public static GameStatus getInstance() throws IOException {
		if(gameStatus == null)
			gameStatus = new GameStatus();
		return gameStatus;
	}

}
