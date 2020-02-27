import org.minueto.MinuetoFileException;
import org.minueto.window.MinuetoFrame;
import org.minueto.window.MinuetoFullscreen;
import org.minueto.window.MinuetoWindow;

import java.util.ArrayList;

public class GameStatus {

	private static GameStatus gameStatus;

    public static final int FOCUS_ON_LOBBY = 0;
    public static final int FOCUS_ON_GAMESCREEN = 1;
    public static final int FOCUS_ON_TEXTBOX = 2;

    public static final int LOBBY_SCREEN = 0;
    public static final int GAME_SCREEN = 1;

	int screenWidth = 1280;
	int screenHeight = 720;
	int focus;
	int currentScreen;
	MinuetoWindow screen;
	Status current = Status.NONE;

	private GameStatus() throws MinuetoFileException {
		focus = FOCUS_ON_GAMESCREEN;
		currentScreen = GAME_SCREEN;
		screen = new MinuetoFrame(screenWidth, screenHeight, true);

	}

	public static GameStatus getInstance() throws MinuetoFileException {
		if(gameStatus == null)
			gameStatus = new GameStatus();
		return gameStatus;
	}
	
	
}
