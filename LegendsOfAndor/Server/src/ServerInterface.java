import org.minueto.MinuetoColor;
import org.minueto.MinuetoEventQueue;
import org.minueto.handlers.*;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoRectangle;
import org.minueto.image.MinuetoText;
import org.minueto.window.MinuetoFrame;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;
import java.util.ArrayList;

public class ServerInterface extends Thread implements MinuetoFocusHandler, MinuetoMouseHandler, MinuetoKeyboardHandler, MinuetoMouseWheelHandler {

	private boolean isFocused;
	private int selectedSave;
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
	private MinuetoText saveNameDisplay;

	private MinuetoWindow screen;
	private ServerButton newGameButton;
	private ServerButton loadGameButton;
	private ServerButton selectGameButton;
	private ServerButton selectGameButton2;
	private ServerButton backToMain;
	private ServerButton backToMain2;
	private ServerButton backToMain3;
	private ServerButton backToMain4;
	private ServerButton saveGameButton;

	private ArrayList<ServerButton> saveFiles;

	private int currentScreen;
	private MinuetoEventQueue queue;

	public ServerInterface(String ipAddress) throws IOException {
		selectedSave = -1;
		isFocused = true;
		saveName = "";
		saveNameDisplay = new MinuetoText(saveName, font, MinuetoColor.BLACK);
		screen = new MinuetoFrame(SCREEN_WIDTH, SCREEN_HEIGHT, true);
		screen.setVisible(true);
		currentScreen = UI_SAVE_LOAD;
		newGameButton = new ServerButton(250, 310, 300, 100, "New Game", 50, screen);
		loadGameButton = new ServerButton(730, 310, 300, 100, "Load Game", 50, screen);

		selectGameButton = new ServerButton(50, 100, 200, 65, "Start", 50, screen);
		selectGameButton2 = new ServerButton(50, 165, 200, 100, "Game", 50, screen);
		backToMain = new ServerButton(50, 300, 200, 65, "Return", 50, screen);
		backToMain2 = new ServerButton(50, 365, 200, 65, "to", 50, screen);
		backToMain3 = new ServerButton(50, 430, 200, 65, "Main", 50, screen);
		backToMain4 = new ServerButton(50, 495, 200, 100, "Menu", 50, screen);


		saveGameButton = new ServerButton(490, 500, 300, 100, "Save Game", 50, screen);
		this.ipAddress = new MinuetoText(ipAddress, ipFont, MinuetoColor.BLACK);

		saveFiles = new ArrayList<>();
		String[] saves = Server.getSaveFiles();
		for(int i = 0; i < saves.length; i++)
			saveFiles.add(new ServerButton(300, 100 + (i*100), 880, 75, saves[i], 35, screen));

		queue = new MinuetoEventQueue();
		screen.registerMouseHandler(this, queue);
		screen.registerFocusHandler(this, queue);
		screen.registerKeyboardHandler(this, queue);
		screen.registerMouseWheelHandler(this, queue);
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
			selectGameButton2.draw();
			selectGameButton.draw();
			backToMain4.draw();
			backToMain3.draw();
			backToMain2.draw();
			backToMain.draw();
			for(ServerButton button : saveFiles)
				button.draw();
		}
		else if(currentScreen == UI_RUNNING) {
			screen.draw(ipAddressMessageBackground, 100, 100);
			screen.draw(ipAddressMessageBackground2, 440, 190);
			screen.draw(ipAddressMessage, 150, 125);
			screen.draw(ipAddress, 460, 195);
			screen.draw(saveNameBackground, 100, 375);
			screen.draw(saveNameMessage, 110, 385);
			screen.draw(saveNameTextBox, 380, 385);
			screen.draw(saveNameDisplay, 390, 385);
			saveGameButton.draw();
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
					for (int i = 0; i < saveFiles.size(); i++) {
						if (saveFiles.get(i).isClicked(x, y)) {
							if (selectedSave >= 0)
								saveFiles.get(selectedSave).deselect();
							saveFiles.get(i).select();
							selectedSave = i;
						}
					}
					if (selectGameButton.isClicked(x, y) || selectGameButton2.isClicked(x, y)) {
						if (selectedSave != -1) {
							Server.loadGame(saveFiles.get(selectedSave).getLabelText());
							currentScreen = UI_RUNNING;
							new Server.ServerRunner().start();
						}
					}
					else if(backToMain.isClicked(x, y) || backToMain2.isClicked(x, y) || backToMain3.isClicked(x, y) || backToMain4.isClicked(x, y)) {
						currentScreen = UI_SAVE_LOAD;
						if(selectedSave != -1) {
							saveFiles.get(selectedSave).deselect();
							selectedSave = -1;
						}
					}
				}
				else if(button == MinuetoMouse.MOUSE_BUTTON_LEFT && currentScreen == UI_RUNNING) {
					if(saveGameButton.isClicked(x, y)) {
						Server.saveGame(saveName);
						saveName = "";
						saveNameDisplay = new MinuetoText(saveName, font, MinuetoColor.BLACK);
					}
				}
			}
		} catch (IOException | ClassNotFoundException e) {}
	}
	public void handleMouseRelease(int i, int i1, int i2) { }
	public void handleMouseMove(int i, int i1) { }

	public void handleKeyPress(int i) {}

	public void handleKeyRelease(int i) {}

	public void handleKeyType(char c) {
		if(isFocused && currentScreen == UI_RUNNING) {
			if(c == MinuetoKeyboard.KEY_BACKSPACE) {
				if (saveName.length() > 0) {
					saveName = saveName.substring(0, saveName.length() - 1);
					saveNameDisplay = new MinuetoText(saveName, font, MinuetoColor.BLACK);
				}
			}
			else {
				this.saveName += c;
				saveNameDisplay = new MinuetoText(saveName, font, MinuetoColor.BLACK);
			}
		}
	}
	public void handleMouseWheelRotate(int i) {
		if(isFocused && currentScreen == UI_SELECT_SAVE)
			for(ServerButton button : saveFiles)
				button.changeY(-i * 4);
	}
}
