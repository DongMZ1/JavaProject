import org.minueto.MinuetoColor;
import org.minueto.handlers.MinuetoKeyboard;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoRectangle;
import org.minueto.image.MinuetoText;

import javax.swing.*;

public class ConnectionScreen {

	private static final int SCREEN_WIDTH = 1280;
	private static final int SCREEN_HEIGHT = 720;

	private static final MinuetoFont font = new MinuetoFont("Arial",60, true, false);
	private static final MinuetoFont ipFont = new MinuetoFont("Arial",60, true, false);
	private static final MinuetoImage background = new MinuetoRectangle(SCREEN_WIDTH, SCREEN_HEIGHT, MinuetoColor.BLACK, true);
	private static final MinuetoImage ipAddressMessageBackground = new MinuetoRectangle(1080, 100, new MinuetoColor(211, 211, 211), true);
	private static final MinuetoImage ipAddressMessageBackground2 = new MinuetoRectangle(400, 80, MinuetoColor.WHITE, true);
	private static final MinuetoText ipAddressMessage = new MinuetoText("Connect to ip address:", font, MinuetoColor.BLACK);
	public String ipAddress;
	private MinuetoText ipAddressDisplay;

	private static ConnectionScreen connectionScreen;

	public static ConnectionScreen getInstance() {
		if(connectionScreen == null)
			connectionScreen = new ConnectionScreen();
		return connectionScreen;
	}

	private ConnectionScreen() {
		ipAddress = "";
		ipAddressDisplay = new MinuetoText(ipAddress, ipFont, MinuetoColor.BLACK);
	}

	public void draw() {
		Client.screen.draw(background,0,0);
		Client.screen.draw(ipAddressMessageBackground, 100, 260);
		Client.screen.draw(ipAddressMessageBackground2, 770, 270);
		Client.screen.draw(ipAddressMessage, 100, 270);
		Client.screen.draw(ipAddressDisplay, 770, 270);
	}

	public void handleKeyPress(int key) { }
	public void handleKeyRelease(int key) { }
	public void handleKeyType(char c) {
		if((c >= MinuetoKeyboard.KEY_0 && c <= MinuetoKeyboard.KEY_9) || c == 46) {
			ipAddress += c;
			ipAddressDisplay = new MinuetoText(ipAddress, ipFont, MinuetoColor.BLACK);
		}
		if(c == MinuetoKeyboard.KEY_ENTER) {
			PreGameScreen.connect();
		}
		if(c == MinuetoKeyboard.KEY_BACKSPACE) {
			if (ipAddress.length() > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.length() - 1);
				ipAddressDisplay = new MinuetoText(ipAddress, ipFont, MinuetoColor.BLACK);
			}
		}
	}
	public void handleMousePress(int x, int y, int button) { }
	public void handleMouseRelease(int x, int y, int button) { }
	public void handleMouseMove(int x, int y) { }
	public void handleMouseWheelRotate(int rotation) { }
}
