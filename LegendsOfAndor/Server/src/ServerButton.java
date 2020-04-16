import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoRectangle;
import org.minueto.image.MinuetoText;
import org.minueto.window.MinuetoWindow;

public class ServerButton {

	private int x, y, width, height;
	private MinuetoImage buttonBackground;
	private MinuetoFont font;
	private MinuetoImage label;
	private MinuetoWindow screen;

	public ServerButton(int x, int y, int width, int height, String label, int fontSize, MinuetoWindow screen) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.font = new MinuetoFont("Arial",fontSize, true, false);
		this.label = new MinuetoText(label, font, MinuetoColor.BLACK);
		this.buttonBackground = new MinuetoRectangle(width, height, new MinuetoColor(211, 211, 211), true);
		this.screen = screen;
	}

	public void draw() {
		screen.draw(buttonBackground, x, y);
		screen.draw(label, x+20, y+20);
	}

	/**
	 * @param x
	 * @param y
	 * @return true if x & y are contained within the button. False otherwise
	 */
	public boolean isClicked(int x, int y) {
		return x > this.x && y > this.y &&
				x < this.x+width && y <this.y+height;
	}

}
