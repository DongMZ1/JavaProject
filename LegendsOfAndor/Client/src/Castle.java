import java.io.IOException;
import java.io.Serializable;

import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoRectangle;
import org.minueto.image.MinuetoText;
import org.minueto.window.MinuetoWindow;

public class Castle implements Serializable{

	private int health;
	private transient MinuetoWindow screen;
	private transient MinuetoImage upperBlock = new MinuetoRectangle(270, 500, MinuetoColor.WHITE, true);
	private transient MinuetoFont font = new MinuetoFont("Helvetica",28, false, false);
	private String message;
	Camera camera;
	
	public Castle(int health, MinuetoWindow window) throws IOException {
		this.health = health;
		screen = window;
		this.camera = Camera.getInstance();
		message = "Castle Health: " + String.valueOf(health);
	}
	
	public void draw() {
		
		Coordinate coordinates = camera.getPosOnScreen(80,400);
		screen.draw(upperBlock, coordinates.getX(),coordinates.getY());
        screen.draw(new MinuetoText(message, font, MinuetoColor.BLACK), coordinates.getX(), coordinates.getY());
	}
	
	public void damage(Monster m) {
    	//TODO
    	// Should decrease shield count of castle
		if (m.getClass() == Gor.class) {
			//UPDATE
			System.out.println("Damaged castle by Gor");
			health--;
		}
		else {
			
		}
		
		if (health == 0) {
			message = "Game Over Rookies\n";
		}
    	
    }
	public void IncreaseHealthIfThereIsFarmer() {
		// assume tile 0 is the place of castle
		// if the farmer was dropped at tile 0, then farmer will appear at the tileentities list, heath++ then remove the farmer
		for(TileEntity t: Tile.get(0).getTileEntities()) {
			if(t instanceof Farmer) {
				//UPDATE
				health++;
				Tile.get(0).getTileEntities().remove(t);
			}
			
		}
	}
}
