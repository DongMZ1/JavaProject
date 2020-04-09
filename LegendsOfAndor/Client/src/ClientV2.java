import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.minueto.MinuetoColor;
import org.minueto.MinuetoEventQueue;
import org.minueto.handlers.MinuetoKeyboardHandler;
import org.minueto.handlers.MinuetoMouseHandler;
import org.minueto.handlers.MinuetoMouseWheelHandler;
import org.minueto.handlers.MinuetoWindowHandler;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;
import org.minueto.window.*;
/*
public class ClientV2 {

	static MinuetoWindow window;			// main window (1280 x 720), everything goes in here
	static MinuetoImageFile board;			// original 9861 x 6476 board
	static MinuetoImage scaledBoard;		// for zooming in and out
	static MinuetoEventQueue eventQueue;	// mouse press, mouse wheel, mouse move, keyboard, ...
	static int xMouse;						// x coordinate in window
	static int yMouse;						// y coordinate in window
	static double scaleFactorX;				// required to scale down board
	static double scaleFactorY;
	static int rotation = 0;				// to control sensitivity of mouse wheel
	
	
	public static void main(String[] args) throws Exception {

		
		// new fixed 1280*720 window (or maybe user input width and height?)
		window = new MinuetoFrame(1280, 720, false);
//		MapStore.initData();
		
		
		// load board image file
		board = new MinuetoImageFile("images/LegendsOfAndorBoard.jpg");
		

		// base scale factors (to fit board in 1280 * 720 window)
		scaleFactorX = (double) window.getWidth()/board.getWidth();
		scaleFactorY = (double) window.getHeight() / board.getHeight();

		// scaled down to get board to fit the window
		scaledBoard = board.scale(scaleFactorX, scaleFactorY);

		// Build the event queue.
		eventQueue = new MinuetoEventQueue();

		// Register anonymous keyboard handler with the event queue.
		window.registerKeyboardHandler(new MinuetoKeyboardHandler() {
			@Override
			public void handleKeyPress(int value) {	
			}
			@Override
			public void handleKeyRelease(int value) {}
			@Override
			public void handleKeyType(char keyChar) {
			}
		}, eventQueue);
		// Register anonymous mouse handler with the event queue.
		window.registerMouseHandler(new MinuetoMouseHandler() {
			@Override
			public void handleMousePress(int x, int y, int button) {
				if (button == 1) {
		//			System.out.println(x + " " + y);
					System.out.println("clicked on tile " + MapStore.MAPDATA[(int) (x / 26.6)][(int) (y / 22.5)]);
				}
			}
			@Override
			public void handleMouseRelease(int x, int y, int button) {
			}
			@Override
			public void handleMouseMove(int x, int y) {	
				xMouse = x;
				yMouse = y;
			}

		}, eventQueue);
		// Register anonymous window handler with the event queue.
		window.registerWindowHandler(new MinuetoWindowHandler() {
			@Override
			public void handleGetFocus() {
			}
			@Override
			public void handleLostFocus() {
			}
			@Override
			public void handleQuitRequest() {
			}
			@Override
			public void handleMinimizeWindow() {
			}
			@Override
			public void handleRestoreWindow() {
			}
		}, eventQueue);
		//Register anonymous mouse wheel handle with the event queue. 
		window.registerMouseWheelHandler(new MinuetoMouseWheelHandler() {

			@Override
			public void handleMouseWheelRotate(int rotate) {

				rotation += rotate;

				// scroll sensitivity, adjust between 3 and 7
				if (rotation == 5 || rotation == -5) {
					// zooming: 3*3 areas cropped from board, scaled down to window size
					if (rotate < 0) {
						// not 100% zoomed in
						if (scaledBoard.getHeight() < board.getHeight() && scaledBoard.getWidth() < board.getWidth()) {
							window.clear();
							//	scaledBoard = board.scale((double) 1/3, (double) 1/3);
							scaledBoard = board.crop((xMouse / 426) * 3287, (yMouse / 240) * 2158, 3287, 2158).scale(scaleFactorX * 3, scaleFactorY * 3);
						}
					}
					// zooming out achieved by scaling down board to window size
					else if (rotate > 0) {
						// not 100% zoomed out
						if (scaledBoard.getHeight() >= window.getHeight() || scaledBoard.getWidth() >= window.getWidth()) {
							window.clear();
							scaledBoard = board.scale(scaleFactorX, scaleFactorY);
						}

					}
					rotation = 0;
				}
			}
		}, eventQueue);
		
		// event the game window.
		window.setVisible(true);

		// Game/rendering loop
		while (true) {

			// Clear the window.
			window.clear();

			// Draw the background.
			window.draw(scaledBoard, 0, 0);

			// Handle all the events in the event queue.
			while (eventQueue.hasNext()) {
				eventQueue.handle();
			}

			// Render all graphics in the back buffer.
			window.render();
		}

	}


}


class InputThread extends Thread{
//	private GameStatus gameStatus;
//	private LobbyScreen lobbyScreen;
//	private TextBox textBox;

	//Basic network code init
	static String serverAddress = "0.0.0.0";

	static Socket socket;
	static ObjectInputStream in;
	static ObjectOutputStream out;
	Object o;

	static {
		try {
			socket = new Socket(serverAddress, 59001);
			OutputStream pee = socket.getOutputStream();
			out = new ObjectOutputStream(pee);
			InputStream poop = socket.getInputStream();
			in = new ObjectInputStream(poop);


		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public InputThread(GameScreenDrawer gameScreenDrawer) {
	}

	public void run() {

		try {
			while (true) {
				Object input = in.readObject();
				System.out.println(input);
			}
		} catch(Exception e) {}
	}

}
*/
