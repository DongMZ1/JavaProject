import org.minueto.MinuetoFileException;
import org.minueto.window.MinuetoFullscreen;
import org.minueto.window.MinuetoWindow;

import java.util.ArrayList;

public class TileInitialiser {
    //TODO Remove TileIntiliser Class
    public TileInitialiser() {}
    public ArrayList<Tile> initialiseTiles(MinuetoWindow screen) throws MinuetoFileException {
        ArrayList<Tile> output = new ArrayList<>();
        output.add(new Tile(1353, 1439, screen, 0,new int[] {1, 2, 4, 5, 6, 7, 11}));
        output.add(new Tile(1450, 2225, screen, 1, new int[] {0, 2, 3, 4}));
        output.add(new Tile(2043,2205, screen, 2, new int[] {0, 1, 3, 6, 14}));
        output.add(new Tile(1800, 2685, screen, 3, new int[] {1, 2, 4, 10, 14, 19, 20}));
        output.add(new Tile(994, 2408, screen, 4, new int[] {0, 1, 3, 5, 20, 21}));
        output.add(new Tile(378, 2256, screen, 5, new int[] {0, 4, 21}));
        output.add(new Tile(2725, 2079, screen, 6, new int[] {0, 2, 11, 13, 14, 17}));
        output.add(new Tile(1289, 784, screen, 7, new int[] {0, 8, 9, 11, 15}));
        output.add(new Tile(2095, 866, screen, 8, new int[] {7, 9, 11}));
        output.add(new Tile(1551, 283, screen, 9, new int[] {7, 8, 15}));
        output.add(new Tile(2028, 3074, screen, 10, new int[] {3, 14, 18, 19}));

        return output;
    }

    public ArrayList<Tile> initialiseCoords(ArrayList<Tile> tiles) {
    	Tile tile0 = tiles.get(0);
    	Tile tile1 = tiles.get(1);
    	Tile tile2 = tiles.get(2);
    	Tile tile3 = tiles.get(3);
    	Tile tile4 = tiles.get(4);
    	Tile tile5 = tiles.get(5);
    	Tile tile6 = tiles.get(6);
    	Tile tile7 = tiles.get(7);
    	Tile tile8 = tiles.get(8);
    	Tile tile9 = tiles.get(9);
    	Tile tile10 = tiles.get(10);
    	tile0.setup(1353, 1439,0);
    	tile1.setup(1450, 2225,0);
    	tile2.setup(2043,2205,1);
    	tile3.setup(1800, 2685,2);
    	tile4.setup(994, 2408,3);
    	tile5.setup(378, 2256,4);
    	tile6.setup(2725, 2079,5);
    	tile7.setup(1289, 784, 6);
    	tile8.setup(2095, 866, 7);
    	tile9.setup(1551, 283, 8);
    	tile10.setup(2028, 3074, 9);
        return tiles;
    }

}
