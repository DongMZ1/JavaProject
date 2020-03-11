import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoCircle;
import org.minueto.image.MinuetoImage;
import org.minueto.window.MinuetoFullscreen;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    private ArrayList<Grid> grids = new ArrayList<>();
    
    public ArrayList<Grid> getGrids() {
		return grids;
	}

	private static final ArrayList<Tile> TILES = new ArrayList<Tile>();

    public Tile(int moveX, int moveY, int tileNumber, int[] adjacentTiles, int nextTile) {
        this.coords = new ArrayList<>();
        coords.add(new Coordinate(moveX, moveY));
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.nextTile = nextTile;
    }
    
    public void addGrids(Grid...grids) {
    	this.grids.addAll(Arrays.asList(grids));
    }
    
    public void mapTilesToGrides() {
    	for (Grid grid : grids)
    		grid.setTile(this);
    	
    }
       
    static {
    	TILES.add(new Tile(1480, 1836, 0, new int[] {1, 2, 4, 5, 6, 7, 11}, 0));
    	TILES.add(new Tile(1952, 2720, 1, new int[] {0, 2, 3, 4}, 0));
    	TILES.add(new Tile(2476, 2732, 2, new int[] {0, 1, 3, 6, 14}, 0));
    	TILES.add(new Tile(2300, 3484, 3, new int[] {1, 2, 4, 10, 14, 19, 20}, 1));
    	TILES.add(new Tile(1460, 2936, 4, new int[] {0, 1, 3, 5, 20, 21}, 0));
    	TILES.add(new Tile(252, 2716, 5, new int[] {0, 4, 21}, 0));
    	TILES.add(new Tile(3732, 2660, 6, new int[] {0, 2, 11, 13, 14, 17}, 0));
    	TILES.add(new Tile(1432, 756, 7, new int[] {0, 8, 9, 11, 15}, 0));
    	TILES.add(new Tile(2552, 924, 8, new int[] {7, 9, 11}, 7));
    	TILES.add(new Tile(2324, 260, 9, new int[] {7, 8, 15}, 7));
    	TILES.add(new Tile(2704, 3976, 10, new int[] {3, 14, 18, 19}, 3));
    	TILES.add(new Tile(3108, 1636, 11, new int[] {0, 6, 7, 8, 12, 13}, 0));
    	TILES.add(new Tile(3976, 1848, 12, new int[] {11, 13}, 11));
    	TILES.add(new Tile(4916, 2288, 13, new int[] {6, 11, 12, 16, 17}, 12));
    	TILES.add(new Tile(3428, 3324, 14, new int[] {2, 3, 6, 10, 17, 18}, 2));
    	TILES.add(new Tile(1128, 152, 15, new int[] {7, 9}, 7));
    	TILES.add(new Tile(5552, 3216, 16, new int[] {13, 17, 36, 38, 32}, 13));
    	TILES.add(new Tile(4344, 3436, 17, new int[] {6, 14, 14, 16, 18, 36}, 6));
    	TILES.add(new Tile(3384, 4516, 18, new int[] {10, 14, 17, 19, 28, 36, 72}, 14));
    	TILES.add(new Tile(1956, 4128, 19, new int[] {3, 10, 18, 20, 22, 23, 72}, 3));
    	TILES.add(new Tile(1064, 3780, 20, new int[] {3, 4, 19, 21, 22}, 3));
    	TILES.add(new Tile(164, 3608, 21, new int[] {4, 5, 20, 22, 24}, 4));   	
    	TILES.add(new Tile(1032, 4788, 22, new int[] {19, 20, 21, 23, 24, 72}, 10));
    	TILES.add(new Tile(1652, 5628, 23, new int[] {19, 22, 24, 25, 31, 34, 35, 72}, 19));
    	TILES.add(new Tile(316, 5240, 24, new int[] {20, 21, 2, 23, 25}, 21));
    	TILES.add(new Tile(400, 6432, 25, new int[] {23, 24, 26, 27, 31}, 24));
    	TILES.add(new Tile(72, 2596, 26, new int[] {25, 27}, 25));
    	TILES.add(new Tile(280, 2552, 27, new int[] {25, 6, 31}, 25));
    	TILES.add(new Tile(1516, 1836, 28, new int[] {18, 29, 36, 38, 72}, 18));
    	TILES.add(new Tile(1180, 2016, 29, new int[] {28, 30, 34, 72}, 28));   	
    	TILES.add(new Tile(1040, 2300, 30, new int[] {29, 33, 34, 35}, 29));
    	TILES.add(new Tile(440, 2408, 31, new int[] {23, 25, 27, 33, 35}, 23));
    	TILES.add(new Tile(2200, 1272, 32, new int[] {16, 38}, 16));
    	TILES.add(new Tile(740, 2452, 33, new int[] {30, 31, 35}, 30));
    	TILES.add(new Tile(828, 2024, 34, new int[] {23, 29, 30, 35, 72}, 23));
    	TILES.add(new Tile(604, 2216, 35, new int[] {23, 30, 31, 33, 34}, 23));
    	TILES.add(new Tile(1676, 1556, 36, new int[] {16, 17, 18, 28, 38}, 16));
    	TILES.add(new Tile(1112, 2684, 37, new int[] {41}, 41));   	
    	TILES.add(new Tile(2060, 1632, 38, new int[] {16, 28, 32, 36, 39}, 16));
    	TILES.add(new Tile(2428, 1908, 39, new int[] {38, 40, 42, 43}, 38));
    	TILES.add(new Tile(1932, 2060, 40, new int[] {39, 41}, 39));
    	TILES.add(new Tile(1568, 2300, 41, new int[] {37, 40}, 40)); 	
    	TILES.add(new Tile(2612, 1632, 42, new int[] {39, 43, 44, 45}, 39));
    	TILES.add(new Tile(2876, 1860, 43, new int[] {39, 42, 44, 45, 71}, 39));   	
    	TILES.add(new Tile(2744, 1472, 44, new int[] {42, 43, 45, 46}, 42));
    	TILES.add(new Tile(3140, 1484, 45, new int[] {43, 44, 46, 64, 65}, 43));
    	TILES.add(new Tile(2816, 1244, 46, new int[] {44, 45, 47, 64}, 44));
    	TILES.add(new Tile(2816, 1244, 47, new int[] {48, 53, 54, 56}, 46));
    	TILES.add(new Tile(2292, 836, 48, new int[] {16, 47, 49, 50, 51, 53}, 16));
    	TILES.add(new Tile(1784, 552, 49, new int[] {48, 50}, 48));
    	TILES.add(new Tile(2080, 436, 50, new int[] {48, 49, 51, 52}, 48));
    	TILES.add(new Tile(2492, 544, 51, new int[] {48, 50, 52, 53, 55}, 48));   	
    	TILES.add(new Tile(2312, 324, 52, new int[] {50, 51, 55}, 50));
    	TILES.add(new Tile(2676, 684, 53, new int[] {47, 48, 51, 54, 55}, 47));
    	TILES.add(new Tile(2928, 624, 54, new int[] {47, 53, 55, 56, 57}, 47));
    	TILES.add(new Tile(2732, 284, 55, new int[] {51, 52, 53, 54, 57}, 51));   	
    	TILES.add(new Tile(3096, 904, 56, new int[] {47, 54, 57, 63}, 47));
    	TILES.add(new Tile(3280, 476, 57, new int[] {54, 55, 56, 58, 59, 63}, 54));   	
    	TILES.add(new Tile(2828, 652, 58, new int[] {57, 59, 60, 61, 62, 63}, 57));
    	TILES.add(new Tile(3632, 320, 59, new int[] {57, 58, 60}, 57));
    	TILES.add(new Tile(3980, 300, 60, new int[] {58, 59, 62}, 59));
    	TILES.add(new Tile(3992, 1096, 61, new int[] {58, 62, 63, 64}, 58));
    	TILES.add(new Tile(4100, 676, 62, new int[] {58, 60, 61}, 58));
    	TILES.add(new Tile(3568, 896, 63, new int[] {56, 57, 58, 61, 64}, 56));
    	TILES.add(new Tile(3376, 1216, 64, new int[] {45, 46, 61, 63, 65}, 45));
    	TILES.add(new Tile(3512, 1496, 65, new int[] {45, 64, 66}, 45));   	
    	TILES.add(new Tile(3740, 1840, 66, new int[] {65, 67}, 65));
    	TILES.add(new Tile(3736, 2040, 67, new int[] {66, 68}, 66));
    	TILES.add(new Tile(3660, 2196, 68, new int[] {67, 69}, 67));
    	TILES.add(new Tile(3476, 2320, 69, new int[] {68, 70}, 68)); 	
    	TILES.add(new Tile(3404, 2484, 70, new int[] {69, 81}, 69));
    	TILES.add(new Tile(1932, 3188, 71, new int[] {43}, 43));
    	TILES.add(new Tile(968, 1728, 72, new int[] {18, 19, 23, 28, 29, 34}, 18));    	
    	TILES.add(new Tile(3896, 2572, 80, new int[] {}, 80));
    	TILES.add(new Tile(3368, 2640, 81, new int[] {70, 80}, 70));
    	TILES.add(new Tile(3140, 2736, 82, new int[] {81, 84}, 81)); 	
    	TILES.add(new Tile(4032, 1520, 83, new int[] {}, 83));
    	TILES.add(new Tile(2928, 2508, 84, new int[] {82}, 84)); 		
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
        Coordinate coordinates;
        for(int i = 0; i < tileEntities.size(); i++) {
            coordinates = camera.getPosOnScreen(coords.get(0));
            Tile.screen.draw(tileEntities.get(i).getImage(), coordinates.getX(), coordinates.getY());
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

	public int[] getAdjacentTiles() {
		return adjacentTiles;
	}
}
