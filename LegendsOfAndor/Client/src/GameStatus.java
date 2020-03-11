import org.minueto.MinuetoFileException;
import org.minueto.window.MinuetoFrame;
import org.minueto.window.MinuetoFullscreen;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class GameStatus {

	//Basic network code init
	String serverAddress = "127.0.0.1";

	Socket socket = new Socket(serverAddress, 59001);
	Scanner in = new Scanner(socket.getInputStream());
	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

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
    
	int screenWidth = 1280;
	int screenHeight = 720;
	int focus;
	int currentScreen;
	MinuetoWindow screen;
	UIStatus ui = UIStatus.NONE;
	FightStatus fight = FightStatus.NONE;

	private GameStatus() throws IOException {
		focus = FOCUS_ON_GAMESCREEN;
		currentScreen = GAME_SCREEN;
		screen = new MinuetoFrame(screenWidth, screenHeight, true);

	}

	public static GameStatus getInstance() throws IOException {
		if(gameStatus == null)
			gameStatus = new GameStatus();
		return gameStatus;
	}

	public void handle() {
		String input;
		String variable;
		String value;
		while(in.hasNextLine()) {
			input = in.nextLine();
			variable = input.split(",")[0];
			value = input.split(",")[1];
			switch(variable){
				case "message": System.out.println(value);
			}
		}
	}

	public void updateVariable(String variable, String value) {
		out.println(variable + "," + value);
	}
}
