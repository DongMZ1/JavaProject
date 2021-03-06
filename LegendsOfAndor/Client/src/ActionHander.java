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

public class ActionHander implements MinuetoKeyboardHandler,
MinuetoMouseHandler,
MinuetoWindowHandler, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1083404940507375295L;
	MinuetoWindow window;			// The Minueto window
	MinuetoEventQueue eventQueue;
	boolean closing;

   
  
	public ActionHander() {

		
		MinuetoFont fontArial19;				// Font used to draw on the screen.
		MinuetoImage imageText = null, imageText1 = null, imageText2 = null, exitText;
		
		
		// Create a 640 by 480 window
		window = new MinuetoFrame(640, 800, true);
		// Build the event queue.
		eventQueue = new MinuetoEventQueue();
		
		// Register the keyboard handler with the event queue.
		window.registerKeyboardHandler(this, eventQueue);

		fontArial19 = new MinuetoFont("Arial",19,false, false);
		
		
		
		
			imageText = new MinuetoText("Press 1 to use Wineskin; Press 2 to use Telescope to reveal adjacent Fogtoken" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("Press 3 to use Falcon for Trade" ,fontArial19,MinuetoColor.BLUE);
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
		switch(value) {
		case MinuetoKeyboard.KEY_1:
			Client.getMainHero().UseWineSkinForMove();
			InputThread.updateVariable();
			this.closing = true;
			window.close();
			break;
		case MinuetoKeyboard.KEY_2:
			// if u have telescope, then u can use it to reveal adjacent fogtoken
			if(Client.getMainHero().getTelescope() > 0) {
				for(Item item: Client.getMainHero().items) {
					if(item instanceof Telescope) {
						Client.getMainHero().items.remove(item);
						InputThread.updateVariable();
						break;
					}
				}
			TelescopeForViewFogtokenHandler t1 = new TelescopeForViewFogtokenHandler();
			}
			this.closing = true;
			window.close();
			break;
		case MinuetoKeyboard.KEY_3:
			if(Client.getMainHero().getFalcon() > 0) {
				for(Item i: Client.getMainHero().items) {
					if(i instanceof Falcon) {
						Client.getMainHero().items.remove(i);
						break;
					}
				}
				
				SelectHeroHandler s1 = new SelectHeroHandler();
				UseFalconForTrade u1 = new UseFalconForTrade();
			}
			this.closing = true;
			window.close();
			break;
	//	case MinuetoKeyboard.KEY_4:
	//		Client.getMainHero().UseMedicalHerbForWP();
	//		InputThread.updateVariable();
	//		this.closing = true;
	//		window.close();
	//		break;
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



