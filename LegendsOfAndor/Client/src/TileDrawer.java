import org.minueto.window.MinuetoWindow;

import java.io.IOException;

public class TileDrawer {

	private static TileDrawer tileDrawer;
	private static Camera camera;
	private TileDrawer() throws IOException {
		camera = Camera.getInstance();
	}

	public static TileDrawer getInstance() throws IOException {
		if(tileDrawer == null)
			tileDrawer = new TileDrawer();
		return tileDrawer;
	}

	public void draw(Tile tile) {
		Coordinate coordinates;
		for(int i = 0; i < tile.tileEntities.size(); i++) {
			coordinates = camera.getPosOnScreen(tile.coords.get(0));
			Client.screen.draw(tile.tileEntities.get(i).getImage(), coordinates.getX(), coordinates.getY());
		}
	}
}
