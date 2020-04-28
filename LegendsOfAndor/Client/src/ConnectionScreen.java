import org.minueto.MinuetoColor;
import org.minueto.handlers.MinuetoKeyboard;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoRectangle;
import org.minueto.image.MinuetoText;

public class ConnectionScreen {

	private static final MinuetoFont font = new MinuetoFont("Arial",35, true, false);
	private static final MinuetoFont ipFont = new MinuetoFont("Arial",60, true, false);
	private static final MinuetoImage ipAddressMessageBackground = new MinuetoRectangle(1080, 200, new MinuetoColor(211, 211, 211), true);
	private static final MinuetoImage ipAddressMessageBackground2 = new MinuetoRectangle(400, 80, MinuetoColor.WHITE, true);
	private static final MinuetoText ipAddressMessage = new MinuetoText("The game server is now running on the following ip address:", font, MinuetoColor.BLACK);
	private String ipAddress;
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

	}

	public void handleKeyPress(int key) { }
	public void handleKeyRelease(int key) { }
	public void handleKeyType(char c) {
		if((c >= MinuetoKeyboard.KEY_0 && c <= MinuetoKeyboard.KEY_9) || c == 46) {
			ipAddress += c;
			ipAddressDisplay = new MinuetoText(ipAddress, ipFont, MinuetoColor.BLACK);
		}
		if(c == MinuetoKeyboard.KEY_ENTER) {

		}
	}
	public void handleMousePress(int x, int y, int button) { }
	public void handleMouseRelease(int x, int y, int button) { }
	public void handleMouseMove(int x, int y) { }
	public void handleMouseWheelRotate(int rotation) { }
}
