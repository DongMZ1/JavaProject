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

public class PickupOption extends Thread implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5824985243561826528L;
	String name;
	public PickupOption(String name) {
		this.name = name; 
	
	}
	
	
	public void run() {		
		@SuppressWarnings("unused")
		PickupOptionDefine win1 = new PickupOptionDefine(this.name);
	}
}

