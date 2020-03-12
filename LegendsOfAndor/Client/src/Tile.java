import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoCircle;
import org.minueto.image.MinuetoImage;
import org.minueto.window.MinuetoFullscreen;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Tile implements Serializable{
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
    	TILES.add(new Tile(5552, 3216, 16, new int[] {13, 17, 36, 38, 32, 48}, 13));
    	TILES.add(new Tile(4344, 3436, 17, new int[] {6, 14, 14, 16, 18, 36}, 6));
    	TILES.add(new Tile(3384, 4516, 18, new int[] {10, 14, 17, 19, 28, 36, 72}, 14));
    	TILES.add(new Tile(1956, 4128, 19, new int[] {3, 10, 18, 20, 22, 23, 72}, 3));
    	TILES.add(new Tile(1064, 3780, 20, new int[] {3, 4, 19, 21, 22}, 3));
    	TILES.add(new Tile(164, 3608, 21, new int[] {4, 5, 20, 22, 24}, 4));   	
    	TILES.add(new Tile(1032, 4788, 22, new int[] {19, 20, 21, 23, 24, 72}, 10));
    	TILES.add(new Tile(1652, 5628, 23, new int[] {19, 22, 24, 25, 31, 34, 35, 72}, 19));
    	TILES.add(new Tile(316, 5240, 24, new int[] {20, 21, 2, 23, 25}, 21));
    	TILES.add(new Tile(400, 6432, 25, new int[] {23, 24, 26, 27, 31}, 24));
    	TILES.add(new Tile(212, 7320, 26, new int[] {25, 27}, 25));
    	TILES.add(new Tile(812, 7260, 27, new int[] {25, 6, 31}, 25));
    	TILES.add(new Tile(4132, 5360, 28, new int[] {18, 29, 36, 38, 72}, 18));
    	TILES.add(new Tile(3544, 5864, 29, new int[] {28, 30, 34, 72}, 28));   	
    	TILES.add(new Tile(3248, 6568, 30, new int[] {29, 33, 34, 35}, 29));
    	TILES.add(new Tile(1252, 7036, 31, new int[] {23, 25, 27, 33, 35}, 23));
    	TILES.add(new Tile(6532, 3528, 32, new int[] {16, 38}, 16));
    	TILES.add(new Tile(2080, 7156, 33, new int[] {30, 31, 35}, 30));
    	TILES.add(new Tile(2416, 5880, 34, new int[] {23, 29, 30, 35, 72}, 23));
    	TILES.add(new Tile(1764, 6384, 35, new int[] {23, 30, 31, 33, 34}, 23));
    	TILES.add(new Tile(4880, 4520, 36, new int[] {16, 17, 18, 28, 38}, 16));
    	TILES.add(new Tile(3640, 7628, 37, new int[] {41}, 41));   	
    	TILES.add(new Tile(6312, 4728, 38, new int[] {16, 28, 32, 36, 39}, 16));
    	TILES.add(new Tile(7488, 5604, 39, new int[] {38, 40, 42, 43}, 38));
    	TILES.add(new Tile(5716, 6028, 40, new int[] {39, 41}, 39));
    	TILES.add(new Tile(4548, 6544, 41, new int[] {37, 40}, 40)); 	
    	TILES.add(new Tile(7668, 4656, 42, new int[] {39, 43, 44, 45}, 39));
    	TILES.add(new Tile(8624, 5280, 43, new int[] {39, 42, 44, 45, 71}, 39));   	
    	TILES.add(new Tile(7912, 4148, 44, new int[] {42, 43, 45, 46}, 42));
    	TILES.add(new Tile(9440, 4260, 45, new int[] {43, 44, 46, 64, 65}, 43));
    	TILES.add(new Tile(8028, 3544, 46, new int[] {44, 45, 47, 64}, 44));
    	TILES.add(new Tile(7980, 2600, 47, new int[] {46, 48, 53, 54, 56}, 46));
    	TILES.add(new Tile(6644, 2184, 48, new int[] {16, 47, 49, 50, 51, 53}, 16));
    	TILES.add(new Tile(5356, 1492, 49, new int[] {48, 50}, 48));
    	TILES.add(new Tile(6280, 1192, 50, new int[] {48, 49, 51, 52}, 48));
    	TILES.add(new Tile(7632, 1476, 51, new int[] {48, 50, 52, 53, 55}, 48));   	
    	TILES.add(new Tile(6768, 736, 52, new int[] {50, 51, 55}, 50));
    	TILES.add(new Tile(8076, 1800, 53, new int[] {47, 48, 51, 54, 55}, 47));
    	TILES.add(new Tile(8852, 1752, 54, new int[] {47, 53, 55, 56, 57}, 47));
    	TILES.add(new Tile(8104, 632, 55, new int[] {51, 52, 53, 54, 57}, 51));   	
    	TILES.add(new Tile(9372, 2548, 56, new int[] {47, 54, 57, 63}, 47));
    	TILES.add(new Tile(9956, 1780, 57, new int[] {54, 55, 56, 58, 59, 63}, 54));   	
    	TILES.add(new Tile(11500, 1836, 58, new int[] {57, 59, 60, 61, 62, 63}, 57));
    	TILES.add(new Tile(10800, 804, 59, new int[] {57, 58, 60}, 57));
    	TILES.add(new Tile(12052, 916, 60, new int[] {58, 59, 62}, 59));
    	TILES.add(new Tile(11972, 3156, 61, new int[] {58, 62, 63, 64}, 58));
    	TILES.add(new Tile(12252, 1916, 62, new int[] {58, 60, 61}, 58));
    	TILES.add(new Tile(10376, 2552, 63, new int[] {56, 57, 58, 61, 64}, 56));
    	TILES.add(new Tile(9564, 3424, 64, new int[] {45, 46, 61, 63, 65}, 45));
    	TILES.add(new Tile(10332, 4388, 65, new int[] {45, 64, 66}, 45));   	
    	TILES.add(new Tile(11104, 5224, 66, new int[] {65, 67}, 65));
    	TILES.add(new Tile(11124, 5928, 67, new int[] {66, 68}, 66));
    	TILES.add(new Tile(10940, 6480, 68, new int[] {67, 69}, 67));
    	TILES.add(new Tile(10248, 6856, 69, new int[] {68, 70}, 68)); 	
    	TILES.add(new Tile(10272, 7376, 70, new int[] {69, 81}, 69));
    	TILES.add(new Tile(9660, 5600, 71, new int[] {43}, 43));
    	TILES.add(new Tile(2644, 4944, 72, new int[] {18, 19, 23, 28, 29, 34}, 18));    	
    	TILES.add(new Tile(11604, 7468, 80, new int[] {}, 80));
    	TILES.add(new Tile(10100, 7888, 81, new int[] {70, 80}, 70));
    	TILES.add(new Tile(9476, 8108, 82, new int[] {81, 84}, 81)); 	
    	TILES.add(new Tile(11984, 4308, 83, new int[] {}, 83));
    	TILES.add(new Tile(8704, 7320, 84, new int[] {82}, 84)); 		
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
