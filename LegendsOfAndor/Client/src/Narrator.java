import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;
import java.io.Serializable;

public class Narrator implements Serializable{
	int narrartorTrack;
	int x;
	int y;
	
	public Narrator() {
		x = 12600;
		y = 8820;
		narrartorTrack = 0;
	}
	

	
	public void advance() {
		if(y > 600) {
          y = y -620;
          narrartorTrack ++;
		}
		
		if(narrartorTrack == 1) {
			Cards.DrawLegend2Card(1);
			GameStatus.gameStatus.legend2CardIndex = 1;
		}
		
		if(narrartorTrack == 3) {
			Cards.DrawLegend2Card(2);
			GameStatus.gameStatus.legend2CardIndex = 2;
		}

		if(narrartorTrack == 7) {
			Cards.DrawLegend2Card(3);
			GameStatus.gameStatus.legend2CardIndex = 3;
		}
		
		if(narrartorTrack == 14) {
			Cards.DrawLegend2Card(4);
			GameStatus.gameStatus.legend2CardIndex = 4;
		}
		
	}	

	
	public void reset() {
		narrartorTrack = 1;
		x = 12600;
		y = 8820;
	}
	
}