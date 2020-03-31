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

public class LoseWPDistributerHander implements MinuetoKeyboardHandler,
MinuetoMouseHandler,
MinuetoWindowHandler{
	MinuetoWindow window;			// The Minueto window
	MinuetoEventQueue eventQueue;
	boolean closing;

   
  
	public LoseWPDistributerHander() {

		
		MinuetoFont fontArial19;				// Font used to draw on the screen.
		MinuetoImage imageText = null, imageText1 = null, imageText2 = null, exitText;
		
		
		// Create a 640 by 480 window
		window = new MinuetoFrame(640, 480, true);
		// Build the event queue.
		eventQueue = new MinuetoEventQueue();
		
		// Register the keyboard handler with the event queue.
		window.registerKeyboardHandler(this, eventQueue);
		fontArial19 = new MinuetoFont("Arial",19,false, false);
		
		
		
		
			imageText = new MinuetoText("By press the Number:" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("1 for Let player1 to lose one Willpower, 2 for player2 to lose one Willpower" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("3 for Let player3 to lose one Willpower, 4 for player4 to lose one Willpower" ,fontArial19,MinuetoColor.BLUE);


		
		
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
			try{Client.gameBoard.tm.heroes.get(0).wp = Client.gameBoard.tm.heroes.get(0).wp -1;}catch(ArrayIndexOutOfBoundsException exception) {
			    
			}
			this.closing = true;
			window.close();
			break;
		case  MinuetoKeyboard.KEY_2:
try{Client.gameBoard.tm.heroes.get(1).wp = Client.gameBoard.tm.heroes.get(1).wp -1;}catch(ArrayIndexOutOfBoundsException exception) {
			    
			}
			this.closing = true;
			window.close();
			break;
		case  MinuetoKeyboard.KEY_3:
try{Client.gameBoard.tm.heroes.get(2).wp = Client.gameBoard.tm.heroes.get(2).wp -1;}catch(ArrayIndexOutOfBoundsException exception) {
			    
			}
			this.closing = true;
			window.close();
			break;
		case  MinuetoKeyboard.KEY_4:
try{Client.gameBoard.tm.heroes.get(3).wp = Client.gameBoard.tm.heroes.get(3).wp -1;}catch(ArrayIndexOutOfBoundsException exception) {
			    
			}
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


