import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;
import java.io.Serializable;

public class Narrator implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8376814387488014369L;
	int narrartorTrack;
	int x;
	int y;
	
	public Narrator() {
		x = 12600;
		y = 8820;
		narrartorTrack = 0;
	}
	

	
	public void advance() throws IOException {
		
		if(Client.gameStatus.MedicalHerbInCastle && Client.gameStatus.SkralTowerDefeated && Client.gameScreenDrawer.gameScreen.castle.health >= 0) {
			Cards.DrawLegend2Card(4);
		}
		
		if(y > 600) {
          y = y -620;
          narrartorTrack ++;
		}
		//set rune stone cards for draw
		if(narrartorTrack == Client.gameStatus.WhenToDrawRuneStoneLegendCard) {
			Cards.DrawLegend2Card(100);
			Client.gameStatus.legend2CardIndex = Client.gameStatus.WhenToDrawRuneStoneLegendCard;
			
		}
		
		if(narrartorTrack == 1) {
			Cards.DrawLegend2Card(1);
			Client.gameStatus.legend2CardIndex = 1;
		}
		
		if(narrartorTrack == 3) {
			Cards.DrawLegend2Card(2);
			Client.gameStatus.legend2CardIndex = 2;
		}

		if(narrartorTrack == 7) {
			Cards.DrawLegend2Card(3);
			Client.gameStatus.legend2CardIndex = 3;
		}
		
		if(narrartorTrack == 14) {
			Cards.DrawLegend2Card(4);
			Client.gameStatus.legend2CardIndex = 4;
		}
		InputThread.updateVariable();
	}	

	
	public void reset() {
		narrartorTrack = 1;
		x = 12600;
		y = 8820;
	}
	
}
