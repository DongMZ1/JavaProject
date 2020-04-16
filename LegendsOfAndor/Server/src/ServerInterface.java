import org.minueto.MinuetoColor;
import org.minueto.MinuetoEventQueue;
import org.minueto.handlers.MinuetoFocusHandler;
import org.minueto.handlers.MinuetoMouse;
import org.minueto.handlers.MinuetoMouseHandler;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoRectangle;
import org.minueto.image.MinuetoText;
import org.minueto.window.MinuetoFrame;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;
import java.util.ArrayList;

public class ServerInterface extends Thread implements MinuetoFocusHandler, MinuetoMouseHandler {

	private boolean isFocused;
	private boolean isTyping;

	private static final int UI_SAVE_LOAD = 0;
	private static final int UI_SELECT_SAVE = 1;
	private static final int UI_RUNNING = 2;
	private static final int SCREEN_WIDTH = 1280;
	private static final int SCREEN_HEIGHT = 720;

	private static final MinuetoFont font = new MinuetoFont("Arial",35, true, false);
	private static final MinuetoFont ipFont = new MinuetoFont("Arial",60, true, false);
	private static final MinuetoImage background = new MinuetoRectangle(SCREEN_WIDTH, SCREEN_HEIGHT, MinuetoColor.BLACK, true);

	private static final MinuetoImage ipAddressMessageBackground = new MinuetoRectangle(1080, 200, new MinuetoColor(211, 211, 211), true);
	private static final MinuetoImage ipAddressMessageBackground2 = new MinuetoRectangle(400, 80, MinuetoColor.WHITE, true);
	private static final MinuetoText ipAddressMessage = new MinuetoText("The game server is now running on the following ip address:", font, MinuetoColor.BLACK);
	private MinuetoText ipAddress;

	private static final MinuetoImage saveNameBackground = new MinuetoRectangle(1080, 60, new MinuetoColor(211, 211, 211), true);
	private static final MinuetoImage saveNameTextBox = new MinuetoRectangle(780, 40, MinuetoColor.WHITE, true);
	private static final MinuetoText saveNameMessage = new MinuetoText("Save File Name:", font, MinuetoColor.BLACK);
	private String saveName;


	private MinuetoWindow screen;
	private ServerButton newGameButton;
	private ServerButton loadGameButton;
	private ServerButton saveGameButton;

	private ArrayList<ServerButton> saveFiles;

	private int currentScreen;
	private MinuetoEventQueue queue;

	public ServerInterface(String ipAddress) throws IOException {
		isFocused = true;
		isTyping = false;
		saveName = "";
		screen = new MinuetoFrame(SCREEN_WIDTH, SCREEN_HEIGHT, true);
		screen.setVisible(true);
		currentScreen = UI_SAVE_LOAD;
		newGameButton = new ServerButton(250, 310, 300, 100, "New Game", 50, screen);
		loadGameButton = new ServerButton(730, 310, 300, 100, "Load Game", 50, screen);
		saveGameButton = new ServerButton(730, 310, 300, 100, "Save Game", 50, screen);
		this.ipAddress = new MinuetoText(ipAddress, ipFont, MinuetoColor.BLACK);

		queue = new MinuetoEventQueue();
		screen.registerMouseHandler(this, queue);
		screen.registerFocusHandler(this, queue);
	}

	public void draw() {
		screen.draw(background, 0, 0);

		while(queue.hasNext())
			queue.handle();

		if(currentScreen == UI_SAVE_LOAD) {
			newGameButton.draw();
			loadGameButton.draw();
		}
		else if(currentScreen == UI_SELECT_SAVE) {

		}
		else if(currentScreen == UI_RUNNING) {
			screen.draw(ipAddressMessageBackground, 100, 100);
			screen.draw(ipAddressMessageBackground2, 440, 190);
			screen.draw(ipAddressMessage, 150, 125);
			screen.draw(ipAddress, 460, 195);
			screen.draw(saveNameBackground, 100, 400);
			screen.draw(saveNameMessage, 110, 410);
			screen.draw(saveNameTextBox, 380, 410);

		}
		screen.render();
	}

	@Override
	public void run() {
		while(true) {
			draw();
		}
	}

	public void handleGetFocus() { isFocused = true; }

	public void handleLostFocus() { isFocused = false; }

	public void handleMousePress(int x, int y, int button) {
		try {
			if (isFocused) {
				if (button == MinuetoMouse.MOUSE_BUTTON_LEFT && currentScreen == UI_SAVE_LOAD) {
					if (newGameButton.isClicked(x, y)) {
						Server.createNewGame();
						currentScreen = UI_RUNNING;
						new Server.ServerRunner().start();
					} else if (loadGameButton.isClicked(x, y)) {
						currentScreen = UI_SELECT_SAVE;
					}
				}
				else if(button == MinuetoMouse.MOUSE_BUTTON_LEFT && currentScreen == UI_SELECT_SAVE) {

				}
				else if(button == MinuetoMouse.MOUSE_BUTTON_LEFT && currentScreen == UI_RUNNING) {

				}
			}
		} catch (IOException e) {}
	}
	public void handleMouseRelease(int i, int i1, int i2) { }
	public void handleMouseMove(int i, int i1) { }
}
