import java.io.Serializable;
import java.util.Random;

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

public class PickupOptionDefine implements MinuetoKeyboardHandler,
MinuetoMouseHandler,
MinuetoWindowHandler, Serializable {

MinuetoFrame window;			// The Minueto window		
MinuetoEventQueue eventQueue;			// The Minueto queue that will hold
	// the events.
boolean closing;

String name;
			
public PickupOptionDefine(String name) {

MinuetoFont fontArial19;				// Font used to draw on the screen.
MinuetoImage imageText2, exitText;		// Instructions drawn on the screen.		// Background for this demo		
Random random = new Random();		
this.name = name;		
this.closing = false;
window = new MinuetoFrame(640, 480, true);
// Build the event queue.
eventQueue = new MinuetoEventQueue();
// Register the keyboard handler with the event queue.
window.registerKeyboardHandler(this, eventQueue);
// Register the mouse handler with the event queue.
window.registerMouseHandler(this, eventQueue);
// Register the window handler with the event queue.
window.registerWindowHandler(this, eventQueue);

// Disable the exit on close
window.exitOnClose(true);

// Initialize an Arial font of size 19, not bold and not italic.
fontArial19 = new MinuetoFont("Arial",19,false, false);

// Build images of the demo instructions.
imageText2 = new MinuetoText("press '1' to pickup Farmer, '2' to pickup Gold, '3' to pickup Willpower" ,fontArial19,MinuetoColor.BLUE);
exitText = new MinuetoText(" '4' to reveal a token, press Q to exit" ,fontArial19,MinuetoColor.BLUE);
// Show the game window.
window.setVisible(true);

window.setTitle(this.name);
window.setWindowPosition(random.nextInt(100),random.nextInt(100));

// Game/rendering loop
while(true) {

// Clear the window.
window.clear();

// Draw the background.
window.draw(imageText2, 0, 0);
window.draw(exitText, 0, 30);
// Handle all the events in the event queue.
while (eventQueue.hasNext()) {
eventQueue.handle();
if (closing) return;
}

// Render all graphics in the back buffer.
window.render();

// Give some breathing space to the other threads
Thread.yield();			
}		
}

/**
* Print out the key values when the user presses a key. Constants to
* identify key values are available in the MinuetoKeyboard class.
**/
public void handleKeyPress(int value) {


switch(value) {
case MinuetoKeyboard.KEY_1:
	Client.mainHero.pickupFarmer();
	this.closing = true;
	window.close();
	break;

case MinuetoKeyboard.KEY_2:
	Client.mainHero.pickupGold();
this.closing = true;
window.close();
break;

case MinuetoKeyboard.KEY_3:
	Client.mainHero.replenishWP();
this.closing = true;
window.close();
break;

case MinuetoKeyboard.KEY_4:
Client.mainHero.RevealFogToken();
this.closing = true;
window.close();
break;

case  MinuetoKeyboard.KEY_Q:
	this.closing = true;
	window.close();
}

}

/**
* Print out the key values when the user releases a key. Constants to
* identify key values are available in the MinuetoKeyboard class.
**/
public void handleKeyRelease(int value) {

}

/**
* Not used. Mostly used to implement typing stuff, such as text box.
*/
public void handleKeyType(char keyChar) {


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