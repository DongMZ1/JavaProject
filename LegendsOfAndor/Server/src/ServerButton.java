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
	private MinuetoImage highlight;
	private MinuetoImage highlight2;
	private boolean highlighted;
	private String labelText;

	public ServerButton(int x, int y, int width, int height, String label, int fontSize, MinuetoWindow screen) {
		labelText = label;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.font = new MinuetoFont("Arial",fontSize, true, false);
		this.label = new MinuetoText(label, font, MinuetoColor.BLACK);
		this.buttonBackground = new MinuetoRectangle(width, height, new MinuetoColor(211, 211, 211), true);
		this.highlight = new MinuetoRectangle(width, height, MinuetoColor.RED, false);
		this.highlight2 = new MinuetoRectangle(width-2, height-2, MinuetoColor.RED, false);
		this.screen = screen;
		this.highlighted = false;
	}

	public void draw() {
		screen.draw(buttonBackground, x, y);
		screen.draw(label, x+20, y+20);
		if(highlighted) {
			screen.draw(highlight, x, y);
			screen.draw(highlight2, x+1, y+1);
		}
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

	public void changeY(int i) {
		y += i;
	}

	public void select() {
		highlighted = true;
	}
	public void deselect() {
		highlighted = false;
	}

	public String getLabelText() {
		return labelText;
	}

}
