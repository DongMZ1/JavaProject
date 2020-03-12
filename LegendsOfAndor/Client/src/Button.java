import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoRectangle;
import org.minueto.image.MinuetoText;

import java.io.IOException;
import java.io.Serializable;

public class Button implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 3950552043413552193L;
	private Coordinate coordinate;
    private int height, width;
    private static MinuetoText label;
    private boolean clickable;
    private static MinuetoRectangle clickableButtonBackground;
    private static MinuetoRectangle unclickableButtonBackground;
    private static MinuetoFont font;
    private GameStatus gameStatus;

    public Button(Coordinate coordinate, int height, int width, String plabel, boolean clickable) throws IOException {
        this.coordinate = coordinate;
        this.height = height;
        this.width = width;
        font = new MinuetoFont("Arial",20, true, false);
        label = new MinuetoText(plabel, font, MinuetoColor.BLACK);
        this.clickable = clickable;
        clickableButtonBackground = new MinuetoRectangle(width, height, new MinuetoColor(211, 211, 211), true);
        unclickableButtonBackground = new MinuetoRectangle(width, height, new MinuetoColor(225, 225, 225), true);
        this.gameStatus = GameStatus.getInstance();
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public int getHeight() {
        return this.height;
    }
    public int getWidth() {
        return this.width;
    }
    public void setLabel(String newLabel) {
        label = new MinuetoText(newLabel, font, MinuetoColor.BLACK);
    }

    public void draw() {
        if(clickable)
            Client.screen.draw(clickableButtonBackground, coordinate.getX(), coordinate.getY());
        else
            Client.screen.draw(unclickableButtonBackground, coordinate.getX(), coordinate.getY());
        Client.screen.draw(label, coordinate.getX() + 30, coordinate.getY() + 15);
    }

    public boolean isClickable() {
        return clickable;
    }
    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }
    /**
     * @param x
     * @param y
     * @return true if x & y are contained within the button. False otherwise
     */
    public boolean isClicked(int x, int y) {
        return x > coordinate.getX() && y > coordinate.getY() &&
                x < coordinate.getX()+width && y < coordinate.getY()+height;
    }
}
