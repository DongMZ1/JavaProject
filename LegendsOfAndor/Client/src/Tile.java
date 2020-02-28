import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoCircle;
import org.minueto.image.MinuetoImage;
import org.minueto.window.MinuetoFullscreen;
import org.minueto.window.MinuetoWindow;

import java.util.ArrayList;
import java.util.HashMap;

public class Tile {
    // TODO Make Tile Class Flyweight
    // TODO Make accessor to get tile from index
    private MinuetoWindow screen;
    private ArrayList<Coordinate> coords;
    private ArrayList<TileEntity> tileEntities;
    private ArrayList<Character> tileCharacters;
    private Coordinate moveCoords;
    private int[] adjacentTiles;
    private int tileNumber;
    private static Camera camera;
    private int nextTile;
    public Tile(int moveX, int moveY, MinuetoWindow screen, int tileNumber, int[] adjacentTiles) throws MinuetoFileException {
        this.coords = new ArrayList<>();
        this.moveCoords = new Coordinate(moveX, moveY);
        this.screen = screen;
        this.tileEntities = new ArrayList<>();
        this.tileCharacters = new ArrayList<>();
        this.adjacentTiles = adjacentTiles;
        this.tileNumber = tileNumber;
        camera = Camera.getInstance();
    }
    public void setup(int x, int y, int next) {
        coords.add(new Coordinate(x, y));
        this.nextTile = next;
    }

    public void addTileEntity(TileEntity tileEntity) {
        tileEntities.add(tileEntity);
        if (tileEntity instanceof Character) {
        	tileCharacters.add((Character) tileEntity);
        	        }
    }

    public boolean containsTileEntity(TileEntity tileEntity) {
        return tileEntities.contains(tileEntity);
    }

    public void removeTileEntity(TileEntity tileEntity) {
        tileEntities.remove(tileEntity);
        if (tileEntity instanceof Character) {
        	tileCharacters.remove((Character) tileEntity);
        	
        }
    }
    
    public ArrayList<TileEntity> getTileEntities(){
    	return tileEntities;
    }
    
    public ArrayList<Character> getTileCharacters(){
    	return tileCharacters;
    }

    public void draw() {
        Coordinate coordinates;
        for(int i = 0; i < tileEntities.size(); i++) {
            coordinates = camera.getPosOnScreen(coords.get(0));
            this.screen.draw(tileEntities.get(i).getImage(), coordinates.getX(), coordinates.getY());
        }
    }
    public boolean isAdjacent(int adjacentTile) {
        for(int tileNum : adjacentTiles)
            if(adjacentTile == tileNum)
                return true;
        return false;
    }
    public int getMoveX() {
        return this.moveCoords.getX() - 25;
    }
    public int getMoveY() {
        return this.moveCoords.getY() - 25;
    }
	public int getNextTile() {
		return nextTile;
	}
}
