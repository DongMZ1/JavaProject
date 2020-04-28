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
	

	
	public void advance() throws IOException {
		if(y > 600) {
          y = y -620;
          narrartorTrack ++;
		}
		
		if(GameStatus.gameStatus.SkralTowerDefeated && GameStatus.gameStatus.MedicalHerbInCastle) {
			Cards.DrawLegend2Card(4);
			GameStatus.gameStatus.legend2CardIndex = 4;
		}
		//set rune stone cards for draw
		if(narrartorTrack == GameStatus.gameStatus.WhenToDrawRuneStoneLegendCard) {
			Cards.DrawLegend2Card(100);
			GameStatus.gameStatus.legend2CardIndex = GameStatus.gameStatus.WhenToDrawRuneStoneLegendCard;
			
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
		InputThread.updateVariable();
	}	

	
	public void reset() {
		narrartorTrack = 1;
		x = 12600;
		y = 8820;
	}
	
}