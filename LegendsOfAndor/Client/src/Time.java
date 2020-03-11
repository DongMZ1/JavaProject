import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;

public class Time {
	MinuetoImage tokenImage;
	int time = 0;
	int x = 6020;
	int y = 240;
	private MinuetoWindow screen;
	Camera camera;
	
	public Time(MinuetoImage tokenImage, MinuetoWindow screen) throws IOException {
		this.tokenImage = tokenImage;
		this.screen = screen;
		this.camera = Camera.getInstance();
		
	}
	
	public void draw() {
		Coordinate coordinates = camera.getPosOnScreen(x,y);
		this.screen.draw(tokenImage,coordinates.getX(),coordinates.getY());
	}
	
	public void advance() {
		time++;
		x += 550;
	}
	
	public void reset() {
		time = 0;
		x = 6020;
		y = 240;
	}
	
}
