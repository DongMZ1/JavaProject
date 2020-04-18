import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.minueto.MinuetoColor;
import org.minueto.MinuetoEventQueue;
import org.minueto.MinuetoFileException;
import org.minueto.handlers.MinuetoKeyboard;
import org.minueto.handlers.MinuetoKeyboardHandler;
import org.minueto.handlers.MinuetoWindowHandler;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;
import org.minueto.image.MinuetoText;
import org.minueto.window.MinuetoFrame;

public class DiceHandler extends Thread implements Serializable{
	
	ArrayList<Integer> rolls;
	
	public DiceHandler(ArrayList<Integer> Rolls) {
		rolls = Rolls;
	}
	
	
	private class DiceImage implements Serializable, MinuetoWindowHandler, MinuetoKeyboardHandler{
		MinuetoFrame window;			
		MinuetoEventQueue eventQueue;
		MinuetoFont fontArial19 = new MinuetoFont("Arial",25,false, false);
		MinuetoImage text = new MinuetoText("press any key to close this window" ,fontArial19,MinuetoColor.BLUE);
		boolean closing;
		
		ArrayList<MinuetoImage> diceList = new ArrayList<MinuetoImage>(
				Arrays.asList(
						new MinuetoImageFile("images/Heroes/Dice/1.png").scale(300/ 671.0, 300/ 671.0),
						new MinuetoImageFile("images/Heroes/Dice/2.png").scale(300/ 671.0, 300/ 671.0),
						new MinuetoImageFile("images/Heroes/Dice/3.png").scale(300/ 671.0, 300/ 671.0),
						new MinuetoImageFile("images/Heroes/Dice/4.png").scale(300/ 671.0, 300/ 671.0),
						new MinuetoImageFile("images/Heroes/Dice/5.png").scale(300/ 671.0, 300/ 671.0),
						new MinuetoImageFile("images/Heroes/Dice/6.png").scale(300/ 671.0, 300/ 671.0)));
		
		
		public DiceImage() throws MinuetoFileException {

			closing = false;
			Random random = new Random();	
			window = new MinuetoFrame(640, 480, true);
			eventQueue = new MinuetoEventQueue();
			window.registerWindowHandler(this, eventQueue);
			window.exitOnClose(false);
			window.registerKeyboardHandler(this, eventQueue);
			window.setVisible(true);

			//window.setTitle(this.name);
			window.setWindowPosition(random.nextInt(100),random.nextInt(100));

			// Game/rendering loop
			while(true) {

				window.clear();
				window.draw(text, 0, 30);
				drawDice();
				
			// Handle all the events in the event queue.
				while (eventQueue.hasNext()) {
					eventQueue.handle();
					if (closing) {
						return;
					}
					
				}
				window.render();
				Thread.yield();
			}

		}
		
		private void drawDice() {

			
			for(int i = 0; i < rolls.size(); i++) {
				window.draw(diceList.get(rolls.get(i) - 1), 100 * i, 100);
			}
		}
		
		
		@Override
		public void handleGetFocus() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void handleLostFocus() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void handleQuitRequest() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void handleMinimizeWindow() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void handleRestoreWindow() {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void handleKeyPress(int value) {
			closing = true;
			window.close();
		}


		@Override
		public void handleKeyRelease(int value) {
			
		}


		@Override
		public void handleKeyType(char keyChar) {
			closing = true;
			window.close();
			
		}

	}
	
	
	public void run() {		
		try {
			@SuppressWarnings("unused")
			DiceImage dice = new DiceImage();
		} catch (MinuetoFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
