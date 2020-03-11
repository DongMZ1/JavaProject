import org.minueto.MinuetoEventQueue;
import org.minueto.MinuetoFileException;
import org.minueto.handlers.MinuetoKeyboardHandler;
import org.minueto.handlers.MinuetoMouseHandler;
import org.minueto.handlers.MinuetoMouseWheelHandler;
import org.minueto.window.MinuetoFullscreen;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;
import java.util.ArrayList;

public class InputHandler implements MinuetoKeyboardHandler, MinuetoMouseHandler, MinuetoMouseWheelHandler {

	private static InputHandler inputHandler;

	private static GameStatus gameStatus;
	private ArrayList<Inputtable> inputs;
	private MinuetoEventQueue queue;
	private InputHandler() throws IOException {
		inputs = new ArrayList<>();
		queue = new MinuetoEventQueue();
		gameStatus = GameStatus.getInstance();

		gameStatus.screen.registerKeyboardHandler(this, queue);
		gameStatus.screen.registerMouseHandler(this, queue);
		gameStatus.screen.registerMouseWheelHandler(this, queue);
	}

	public static InputHandler getInputHandler() throws IOException {
		if(inputHandler == null)
			inputHandler = new InputHandler();
		return inputHandler;
	}

	public void addInput(Inputtable input) {
		inputs.add(input);
		
	}
	
	public void removeInput(Inputtable input) {
		inputs.remove(input);
	}
	
	public void handleQueue() {
		while(queue.hasNext())
			queue.handle();
	}

	public void handleKeyPress(int i) {
		inputs.get(gameStatus.focus).handleKeyPress(i);
	}

	public void handleKeyRelease(int i) {
		inputs.get(gameStatus.focus).handleKeyRelease(i);
	}

	public void handleKeyType(char c) {
		inputs.get(gameStatus.focus).handleKeyType(c);
	}

	public void handleMousePress(int i, int i1, int i2) {
		inputs.get(gameStatus.focus).handleMousePress(i, i1, i2);
		
	}

	public void handleMouseRelease(int i, int i1, int i2) {
		inputs.get(gameStatus.focus).handleMouseRelease(i, i1, i2);
	}

	public void handleMouseMove(int i, int i1) {
		inputs.get(gameStatus.focus).handleMouseMove(i, i1);
	}

	public void handleMouseWheelRotate(int i) {
//		inputs.get(gameStatus.focus).handleMouseWheelRotate(i);
	}
}
