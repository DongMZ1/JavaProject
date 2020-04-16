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

public class ServerInterface extends Thread implements MinuetoFocusHandler, MinuetoMouseHandler {

	private boolean isFocused;

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

	private MinuetoWindow screen;
	private ServerButton newGameButton;
	private ServerButton loadGameButton;
	private ServerButton saveGameButton;

	private int currentScreen;
	private MinuetoEventQueue queue;

	public ServerInterface(String ipAddress) throws IOException {
		isFocused = true;
		screen = new MinuetoFrame(SCREEN_WIDTH, SCREEN_HEIGHT, true);
		screen.setVisible(true);
		currentScreen = UI_SAVE_LOAD;
		newGameButton = new ServerButton(250, 300, 300, 120, "New Game", 50, screen);
		loadGameButton = new ServerButton(730, 300, 300, 120, "Load Game", 50, screen);
		saveGameButton = new ServerButton(730, 300, 300, 120, "Save Game", 50, screen);
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
			screen.draw(ipAddressMessageBackground, 100, 150);
			screen.draw(ipAddressMessageBackground2, 440, 240);
			screen.draw(ipAddressMessage, 150, 175);
			screen.draw(ipAddress, 460, 245);
		}
		screen.render();
	}

	@Override
	public void run() {
		while(true) {
			draw();
		}
	}

	@Override
	public void handleGetFocus() { isFocused = true; }

	@Override
	public void handleLostFocus() { isFocused = false; }

	@Override
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
	@Override
	public void handleMouseRelease(int i, int i1, int i2) { }
	@Override
	public void handleMouseMove(int i, int i1) { }
}
