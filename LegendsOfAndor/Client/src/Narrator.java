import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;
import java.io.Serializable;

public class Narrator implements Serializable{
	MinuetoImage tokenImage;
	int narrartorTrack = 1;
	int x = 12600;
	int y = 8200;
	private MinuetoWindow screen;
	Camera camera;
	
	public Narrator(MinuetoImage tokenImage, MinuetoWindow screen) throws IOException {
		this.tokenImage = tokenImage;
		this.screen = screen;
		this.camera = Camera.getInstance();
		
	}
	
	public void draw() {
		Coordinate coordinates = camera.getPosOnScreen(x,y);
		this.screen.draw(tokenImage,coordinates.getX(),coordinates.getY());
	}
	
	public void advance() {
		if(y > 600) {
          y = y -620;
          narrartorTrack ++;
		}
		
		
		if(narrartorTrack == 3) {
			Cards.DrawLegend2Card(2);
			Client.gameBoard.LegendCard2Index = 2;
		}

		if(narrartorTrack == 7) {
			Cards.DrawLegend2Card(3);
			Client.gameBoard.LegendCard2Index = 3;
		}
		
		if(narrartorTrack == 14) {
			Cards.DrawLegend2Card(4);
			Client.gameBoard.LegendCard2Index = 4;
		}
	}	

	
	public void reset() {
		narrartorTrack = 1;
		x = 12600;
		y = 8200;
	}
	
}