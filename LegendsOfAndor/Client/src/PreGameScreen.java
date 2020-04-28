import java.io.IOException;

public class PreGameScreen implements Inputtable {

	boolean isConnected;

	public static LobbyScreen lobbyScreen;
	public static ConnectionScreen connectionScreen;
	private static PreGameScreen preGameScreen;

	public static PreGameScreen getInstance() throws IOException {
		if(preGameScreen == null)
			preGameScreen = new PreGameScreen();
		return preGameScreen;
	}

	private PreGameScreen() throws IOException {
		isConnected = false;
		connectionScreen = ConnectionScreen.getInstance();
		lobbyScreen = LobbyScreen.getInstance();

	}

	public void draw() {
		if(!isConnected) {
			connectionScreen.draw();
		}
		else
			lobbyScreen.draw();
	}

	public void handleKeyPress(int key) {
		if(isConnected)
			lobbyScreen.handleKeyPress(key);
		else
			connectionScreen.handleKeyPress(key);
	}
	public void handleKeyRelease(int key) {
		if(isConnected)
			lobbyScreen.handleKeyRelease(key);
		else
			connectionScreen.handleKeyRelease(key);
	}
	public void handleKeyType(char c) {
		if(isConnected)
			lobbyScreen.handleKeyType(c);
		else
			connectionScreen.handleKeyType(c);
	}
	public void handleMousePress(int x, int y, int button) {
		if(isConnected)
			lobbyScreen.handleMousePress(x, y, button);
		else
			connectionScreen.handleMousePress(x, y, button);
	}
	public void handleMouseRelease(int x, int y, int button) {
		if(isConnected)
			lobbyScreen.handleMouseRelease(x, y, button);
		else
			connectionScreen.handleMouseRelease(x, y, button);
	}
	public void handleMouseMove(int x, int y) {
		if(isConnected)
			lobbyScreen.handleMouseMove(x, y);
		else
			connectionScreen.handleMouseMove(x, y);
	}
	public void handleMouseWheelRotate(int rotation) {
		if(isConnected)
			lobbyScreen.handleMouseWheelRotate(rotation);
		else
			connectionScreen.handleMouseWheelRotate(rotation);
	}
}
