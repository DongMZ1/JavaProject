import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoRectangle;
import org.minueto.image.MinuetoText;

import java.io.IOException;

public class CastleDrawer {

	private MinuetoImage upperBlock = new MinuetoRectangle(270, 500, MinuetoColor.WHITE, true);
	private MinuetoFont font = new MinuetoFont("Helvetica",28, false, false);
	private static CastleDrawer castleDrawer;
	private static Camera camera;
	private CastleDrawer() throws IOException {
		camera = Camera.getInstance();
	}

	public static CastleDrawer getInstance() throws IOException {
		if(castleDrawer == null)
			castleDrawer = new CastleDrawer();
		return castleDrawer;
	}

	public void draw(Castle castle) {
		if(castle.health <= 0) {
			castle.message = "Game Over Rookies\n";
		}else {
			castle.message = "Castle Health: " + String.valueOf(castle.health);
		}
		Coordinate coordinates = camera.getPosOnScreen(80,400);
		Client.screen.draw(upperBlock, coordinates.getX(),coordinates.getY());
		Client.screen.draw(new MinuetoText(castle.message, font, MinuetoColor.BLACK), coordinates.getX(), coordinates.getY());
		InputThread.updateVariable();
	}
}
