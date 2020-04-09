import java.io.IOException;
import java.io.Serializable;

import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoRectangle;
import org.minueto.image.MinuetoText;
import org.minueto.window.MinuetoWindow;

public class Castle implements Serializable{

	public int health;
	public String message;

	public Castle(int health) throws IOException {
		this.health = health;
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
		
		if (health <= 0) {
			message = "Game Over Rookies\n";
		}
    	
    }
	public void IncreaseHealthIfThereIsFarmer() {
	
}
}
