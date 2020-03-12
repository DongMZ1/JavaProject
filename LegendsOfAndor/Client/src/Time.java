import org.minueto.image.MinuetoImage;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;
import java.io.Serializable;

public class Time implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4888251730179638475L;
	/**
	 * 
	 */
	static MinuetoImage tokenImage;
	int time = 0;
	int x = 6020;
	int y = 240;
<<<<<<< Updated upstream
	private static MinuetoWindow screen;
=======
	static MinuetoWindow screen;
>>>>>>> Stashed changes
	static Camera camera;
	
	public Time(MinuetoImage ptokenImage, MinuetoWindow pscreen) throws IOException {
		tokenImage = ptokenImage;
		screen = pscreen;
		camera = Camera.getInstance();
		
	}
	
	public void draw() {
		Coordinate coordinates = camera.getPosOnScreen(x,y);
		screen.draw(tokenImage,coordinates.getX(),coordinates.getY());
	}
	
	public void advance() {
		time++;
		x += 550;
	}
	
	public boolean left() {
		if (time < 7) return true;
		else return false;
	}
	
	public void reset() {
		time = 0;
		x = 6020;
		y = 240;
	}
	
}
