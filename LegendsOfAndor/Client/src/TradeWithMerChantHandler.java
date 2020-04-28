
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

public class TradeWithMerChantHandler implements MinuetoKeyboardHandler,
MinuetoMouseHandler,
MinuetoWindowHandler, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7319846447371168902L;
	MinuetoWindow window;			// The Minueto window
	MinuetoEventQueue eventQueue;
	boolean closing;

   
  
	public TradeWithMerChantHandler() {

		
		MinuetoFont fontArial19;				// Font used to draw on the screen.
		MinuetoImage imageText = null, imageText1 = null, imageText2 = null, imageText3 = null, imageText4 = null, exitText;
		
		
		// Create a 640 by 480 window
		window = new MinuetoFrame(800, 800, true);
		// Build the event queue.
		eventQueue = new MinuetoEventQueue();
		
		// Register the keyboard handler with the event queue.
		window.registerKeyboardHandler(this, eventQueue);

		fontArial19 = new MinuetoFont("Arial",19,false, false);
		
		
		
		
			imageText = new MinuetoText("Press 1 to Trade two Gold for Strength point; Press 2 to Trade two Gold for A Bow;" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("Press 3 to Trade two Gold for Wineskin; Press 4 to Trade two Gold for Falcon;" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("Press 5 to trade two Gold for Helm; Press 6 to trade two Gold for Shield;" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("Press 7 to trade (number of hero + 1) for Witchbrew at Witch;" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("Press 8 to trade two Gold for Telescope;" ,fontArial19,MinuetoColor.BLUE);
		
		
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
			window.draw(imageText3, 0, 180);
			window.draw(imageText4, 0, 210);
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
		switch(value) {
		case MinuetoKeyboard.KEY_1:
			Client.getMainHero().Buy2SPfor2Gold();
			InputThread.updateVariable();
			this.closing = true;
			window.close();
			break;
		case MinuetoKeyboard.KEY_2:
			Client.getMainHero().BuyBowFor2Gold();
			InputThread.updateVariable();
			this.closing = true;
			window.close();
			break;
		case MinuetoKeyboard.KEY_3:
			Client.getMainHero().BuyWineskinFor2Gold();
			InputThread.updateVariable();
			this.closing = true;
			window.close();
			break;
		case MinuetoKeyboard.KEY_4:
			Client.getMainHero().BuyFalconFor2Gold();
			InputThread.updateVariable();
			this.closing = true;
			window.close();
			break;
		case MinuetoKeyboard.KEY_5:
			Client.getMainHero().BuyHelmFor2Gold();
			InputThread.updateVariable();
			this.closing = true;
			window.close();
			break;
		case MinuetoKeyboard.KEY_6:
			Client.getMainHero().BuyShieldFor2Gold();
			InputThread.updateVariable();
			this.closing = true;
			window.close();
			break;
		case MinuetoKeyboard.KEY_7:
			Client.getMainHero().BuyWitchBrewForGold();
			InputThread.updateVariable();
			this.closing = true;
			window.close();
			break;
		case MinuetoKeyboard.KEY_8:
			Client.getMainHero().BuyTelescopeFor2Gold();
			InputThread.updateVariable();
			this.closing = true;
			window.close();
			break;
		case  MinuetoKeyboard.KEY_Q:
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


