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

public class EventCardForRevealFogTokenHandler implements MinuetoKeyboardHandler,
MinuetoMouseHandler,
MinuetoWindowHandler{
	MinuetoWindow window;			// The Minueto window
	MinuetoEventQueue eventQueue;
	boolean closing;

   
  
	public EventCardForRevealFogTokenHandler() {

		
		MinuetoFont fontArial19;				// Font used to draw on the screen.
		MinuetoImage imageText = null, imageText1 = null, imageText2 = null, imageText3 = null, exitText;
		
		
		// Create a 640 by 480 window
		window = new MinuetoFrame(640, 480, true);
		// Build the event queue.
		eventQueue = new MinuetoEventQueue();
		
		// Register the keyboard handler with the event queue.
		window.registerKeyboardHandler(this, eventQueue);

		fontArial19 = new MinuetoFont("Arial",19,false, false);
		
		
		
		
			imageText = new MinuetoText("1 for Tile 8, 2 for Tile 11, 3 for Tile 12, 4 for Tile 13" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("5 for Tile 49, 6 for Tile 16, 7 for Tile 32, 8 for Tile 46 " ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("9 for Tile 44, 0 for Tile 42, Q for Tile 48, W for Tile 47" ,fontArial19,MinuetoColor.BLUE);
            imageText3 = new MinuetoText("E for Tile 56, R for Tile 63, T for Tile 64" ,fontArial19,MinuetoColor.BLUE);

		
		
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
			window.draw(imageText3, 0, 180);
			
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
		switch(value) {
		case MinuetoKeyboard.KEY_1:
			Client.mainHero.FilpFogTokenForPreviewOnly(8);
			this.closing = true;
			window.close();
			break;
		case  MinuetoKeyboard.KEY_2:
			Client.mainHero.FilpFogTokenForPreviewOnly(11);
			this.closing = true;
			window.close();
			break;
		case  MinuetoKeyboard.KEY_3:
			Client.mainHero.FilpFogTokenForPreviewOnly(12);
			this.closing = true;
			window.close();
			break;
		case  MinuetoKeyboard.KEY_4:
			Client.mainHero.FilpFogTokenForPreviewOnly(13);
			this.closing = true;
			window.close();
			break;
		case  MinuetoKeyboard.KEY_5:
			Client.mainHero.FilpFogTokenForPreviewOnly(49);
			this.closing = true;
			window.close();
			break;
		case  MinuetoKeyboard.KEY_6:
			Client.mainHero.FilpFogTokenForPreviewOnly(16);
			this.closing = true;
			window.close();
			break;
		case  MinuetoKeyboard.KEY_7:
			Client.mainHero.FilpFogTokenForPreviewOnly(32);
			this.closing = true;
			window.close();
			break;
		case  MinuetoKeyboard.KEY_8:
			Client.mainHero.FilpFogTokenForPreviewOnly(46);
			this.closing = true;
			window.close();
			break;
		case  MinuetoKeyboard.KEY_9:
			Client.mainHero.FilpFogTokenForPreviewOnly(44);
			this.closing = true;
			window.close();
			break;
		case  MinuetoKeyboard.KEY_0:
			Client.mainHero.FilpFogTokenForPreviewOnly(42);
			this.closing = true;
			window.close();
			break;
		case  MinuetoKeyboard.KEY_Q:
			Client.mainHero.FilpFogTokenForPreviewOnly(48);
			this.closing = true;
			window.close();
			break;
		case  MinuetoKeyboard.KEY_W:
			Client.mainHero.FilpFogTokenForPreviewOnly(47);
			this.closing = true;
			window.close();
			break;
		case  MinuetoKeyboard.KEY_E:
			Client.mainHero.FilpFogTokenForPreviewOnly(56);
			this.closing = true;
			window.close();
			break;
		case  MinuetoKeyboard.KEY_R:
			Client.mainHero.FilpFogTokenForPreviewOnly(63);
			this.closing = true;
			window.close();
			break;
		case  MinuetoKeyboard.KEY_T:
			Client.mainHero.FilpFogTokenForPreviewOnly(64);
			this.closing = true;
			window.close();
			break;
		}
		}
		
		


	@Override
	public void handleKeyRelease(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKeyType(char keyChar) {
		// TODO Auto-generated method stub
		
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



