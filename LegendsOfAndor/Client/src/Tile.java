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
    private static MinuetoWindow screen;
    private ArrayList<Coordinate> coords;
    private ArrayList<TileEntity> tileEntities;
    private ArrayList<Character> tileCharacters;
    private Coordinate moveCoords;
    private int[] adjacentTiles;
    private int tileNumber;
    private static Camera camera;
    private int nextTile;
    private static final ArrayList<Tile> TILES = new ArrayList<Tile>();

    public Tile(int moveX, int moveY, int tileNumber, int[] adjacentTilesn, int nextTile) {
        this.coords = new ArrayList<>();
        this.moveCoords = new Coordinate(moveX, moveY);
//        this.screen = screen;
        this.tileEntities = new ArrayList<>();
        this.tileCharacters = new ArrayList<>();
        this.adjacentTiles = adjacentTiles;
        this.tileNumber = tileNumber;
        try {
			camera = Camera.getInstance();
		} catch (MinuetoFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.nextTile = nextTile;
    }
    
    static {
    	TILES.add(new Tile(1353, 1439, 0, new int[] {1, 2, 4, 5, 6, 7, 11}, 0));
    	TILES.add(new Tile(1450, 2225, 1, new int[] {0, 2, 3, 4}, 0));
    	TILES.add(new Tile(2043,2205, 2, new int[] {0, 1, 3, 6, 14}, 0));
    	TILES.add(new Tile(1800, 2685, 3, new int[] {1, 2, 4, 10, 14, 19, 20}, 1));
    	TILES.add(new Tile(994, 2408, 4, new int[] {0, 1, 3, 5, 20, 21}, 0));
    	TILES.add(new Tile(378, 2256, 5, new int[] {0, 4, 21}, 0));
    	TILES.add(new Tile(2725, 2079, 6, new int[] {0, 2, 11, 13, 14, 17}, 0));
    	TILES.add(new Tile(1289, 784, 7, new int[] {0, 8, 9, 11, 15}, 0));
    	TILES.add(new Tile(2095, 866, 8, new int[] {7, 9, 11}, 7));
    	TILES.add(new Tile(1551, 283, 9, new int[] {7, 8, 15}, 7));
    	TILES.add(new Tile(2028, 3074, 10, new int[] {3, 14, 18, 19}, 3));
    	TILES.add(new Tile(1024, 620, 11, new int[] {0, 6, 7, 8, 12, 13}, 0));
    	TILES.add(new Tile(1356, 660, 12, new int[] {11, 13}, 11));
    	TILES.add(new Tile(1656, 848, 13, new int[] {6, 11, 12, 16, 17}, 12));
    	TILES.add(new Tile(1112, 1236, 14, new int[] {2, 3, 6, 10, 17, 18}, 2));
    	TILES.add(new Tile(368, 104, 15, new int[] {7, 9}, 7));
    	TILES.add(new Tile(1872, 1176, 16, new int[] {13, 17, 36, 38, 32}, 13));
    	TILES.add(new Tile(1516, 1244, 17, new int[] {6, 14, 14, 16, 18, 36}, 6));
    	TILES.add(new Tile(1212, 1576, 18, new int[] {10, 14, 17, 19, 28, 36, 72}, 14));
    	TILES.add(new Tile(668, 1440, 19, new int[] {3, 10, 18, 20, 22, 23, 72}, 3));
    	TILES.add(new Tile(380, 1348, 20, new int[] {3, 4, 19, 21, 22}, 3));
    	TILES.add(new Tile(108, 1328, 21, new int[] {4, 5, 20, 22, 24}, 4));

    	
 
    }
    
    public static Tile get (int index) {
    	assert (index >= 0) && (index <= Constants.NUM_OF_TILES);
    	return TILES.get(index);
    }
    
    public static ArrayList<Tile> getAll () {
    	return TILES;
    }
    
    public void setScreen(MinuetoWindow screen) {
    	Tile.screen = screen;
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
//        Coordinate coordinates;
//        for(int i = 0; i < tileEntities.size(); i++) {
//            coordinates = camera.getPosOnScreen(coords.get(0));
//            Tile.screen.draw(tileEntities.get(i).getImage(), coordinates.getX(), coordinates.getY());
//        }
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
