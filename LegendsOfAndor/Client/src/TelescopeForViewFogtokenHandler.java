
import java.io.Serializable;

import org.minueto.MinuetoColor;
import org.minueto.MinuetoEventQueue;
import org.minueto.handlers.MinuetoKeyboard;
import org.minueto.handlers.MinuetoKeyboardHandler;
import org.minueto.handlers.MinuetoMouseHandler;
import org.minueto.handlers.MinuetoWindowHandler;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoText;
import org.minueto.window.MinuetoFrame;
import org.minueto.window.MinuetoWindow;

public class TelescopeForViewFogtokenHandler implements MinuetoKeyboardHandler,
MinuetoMouseHandler,
MinuetoWindowHandler, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6175940386785934069L;
	MinuetoWindow window;			// The Minueto window
	MinuetoEventQueue eventQueue;
	boolean closing;
	final int ASCIINUMBERS[] = {48,49,50,51,52,53,54,55,56,57};
    int tileToreveal;
  
	public TelescopeForViewFogtokenHandler() {

		MinuetoFont fontArial19;				// Font used to draw on the screen.
		MinuetoImage imageText = null, imageText1 = null, imageText2 = null, exitText;
		
		
		// Create a 640 by 480 window
		window = new MinuetoFrame(640, 480, true);
		// Build the event queue.
		eventQueue = new MinuetoEventQueue();
		
		// Register the keyboard handler with the event queue.
		window.registerKeyboardHandler(this, eventQueue);

		fontArial19 = new MinuetoFont("Arial",19,false, false);
		
		
		
		
			imageText = new MinuetoText("Type adjancent tile + a space for review" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);


		
		
		exitText = new MinuetoText("Press 'q' to quit." ,fontArial19,MinuetoColor.BLUE);

		// Show the game window.
		window.setVisible(true);
	
		// Game/rendering loop
		while(true) {
		
			// Clear the window.
			window.clear();
			
			// Draw the instructions.
			window.draw(imageText, 0, 0);
			window.draw(imageText1, 0, 60);
			window.draw(imageText2, 0, 120);
			window.draw(exitText, 0, 450);
			
			// Handle all the events in the event queue.
			while (eventQueue.hasNext()) {
				eventQueue.handle();
				if (closing) return;
				}
			
			// Render all graphics in the back buffer.
			window.render();
			Thread.yield();		
		}		
	}

	@Override
	public void handleKeyPress(int value) {
		
		
	}

	@Override
	public void handleKeyRelease(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKeyType(char c) {
		// TODO Auto-generated method stub
		if( c == ' ') {
			System.out.println(tileToreveal);
			int[] adjacentTile = Tile.get(Client.getMainHero().tile).getAdjacentTiles();
			for(int i = 0; i < adjacentTile.length; i++) {
				if(adjacentTile[i] == tileToreveal) {
					Client.getMainHero().FilpFogTokenForPreviewOnly(tileToreveal);
				}
			}
			tileToreveal = 0;
		}else {
		for (int i = 0; i <10; i++) {
			if (c == ASCIINUMBERS[i]) {
				tileToreveal *= 10;
				tileToreveal += i;
			}
		}
		}
		if( c == 'q') {
			this.closing = true;
			window.close();
		}
	}
	
	/**
	* Print out the key values when the user releases a key. Constants to
	* identify key values are available in the MinuetoKeyboard class.
	**/


	/**
	* Not used. Mostly used to implement typing stuff, such as text box.
	*/


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
	public void handleMousePress(int x, int y, int button) {
	// TODO Auto-generated method stub

	}

	@Override
	public void handleMouseRelease(int x, int y, int button) {
	// TODO Auto-generated method stub

	}

	@Override
	public void handleMouseMove(int x, int y) {
	// TODO Auto-generated method stub

	}

}
