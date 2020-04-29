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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8780641316623632521L;
	ArrayList<Integer> rolls;
	
	public DiceHandler(ArrayList<Integer> Rolls) {
		rolls = (ArrayList<Integer>) Rolls.clone();
	}

	private class DiceImage implements Serializable, MinuetoWindowHandler, MinuetoKeyboardHandler{
		/**
		 * 
		 */
		private static final long serialVersionUID = -7293312381040281519L;
		MinuetoFrame window;			
		MinuetoEventQueue eventQueue;
		MinuetoFont fontArial19 = new MinuetoFont("Arial",25,false, false);
		MinuetoImage text = new MinuetoText("press any key to close this window" ,fontArial19,MinuetoColor.BLUE);
		boolean closing;
		int indicator;
		ArrayList<MinuetoImage> acList;
		
		final ArrayList<MinuetoImage> heroDice = new ArrayList<MinuetoImage>(
				Arrays.asList(
						new MinuetoImageFile("images/Heroes/Dice/1.png").scale(300/ 671.0, 300/ 671.0),
						new MinuetoImageFile("images/Heroes/Dice/2.png").scale(300/ 671.0, 300/ 671.0),
						new MinuetoImageFile("images/Heroes/Dice/3.png").scale(300/ 671.0, 300/ 671.0),
						new MinuetoImageFile("images/Heroes/Dice/4.png").scale(300/ 671.0, 300/ 671.0),
						new MinuetoImageFile("images/Heroes/Dice/5.png").scale(300/ 671.0, 300/ 671.0),
						new MinuetoImageFile("images/Heroes/Dice/6.png").scale(300/ 671.0, 300/ 671.0)));
		
		final ArrayList<MinuetoImage> blackDice = new ArrayList<MinuetoImage>(
				Arrays.asList(
						new MinuetoImageFile("images/Heroes/Dice/6.JPG").scale(100/ 671.0, 100/ 671.0),
						new MinuetoImageFile("images/Heroes/Dice/7.JPG").scale(100/ 671.0, 100/ 671.0),
						new MinuetoImageFile("images/Heroes/Dice/8.JPG").scale(100/ 671.0, 100/ 671.0),
						new MinuetoImageFile("images/Heroes/Dice/9.JPG").scale(100/ 671.0, 100/ 671.0),
						new MinuetoImageFile("images/Heroes/Dice/10.JPG").scale(100/ 671.0, 100/ 671.0),
						new MinuetoImageFile("images/Heroes/Dice/11.JPG").scale(100/ 671.0, 100/ 671.0)));
		
		final ArrayList<MinuetoImage> monsterDice = new ArrayList<MinuetoImage>(
				Arrays.asList(
						new MinuetoImageFile("images/Monsters/Dice/1.png").scale(300/ 671.0, 300/ 671.0),
						new MinuetoImageFile("images/Monsters/Dice/2.png").scale(300/ 671.0, 300/ 671.0),
						new MinuetoImageFile("images/Monsters/Dice/3.png").scale(300/ 671.0, 300/ 671.0),
						new MinuetoImageFile("images/Monsters/Dice/4.png").scale(300/ 671.0, 300/ 671.0),
						new MinuetoImageFile("images/Monsters/Dice/5.png").scale(300/ 671.0, 300/ 671.0),
						new MinuetoImageFile("images/Monsters/Dice/6.png").scale(300/ 671.0, 300/ 671.0)));
		
		
		public DiceImage() throws MinuetoFileException {
			
			indicator = rolls.get(rolls.size() - 1);
			rolls.remove(rolls.size() - 1);
			
			if(indicator == -3) {
				acList = monsterDice;
			}
			
			else if(indicator == -2) {
				acList = blackDice;
			}
			
			else {
				acList = heroDice;
			}
			
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
				if(indicator == -2) {
					window.draw(acList.get(rolls.get(i) - 6), 100 * i, 100);
				}
				
				else {
					window.draw(acList.get(rolls.get(i) - 1), 100 * i, 100);
				}
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
