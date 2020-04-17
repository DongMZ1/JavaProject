import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Monster implements Character, Serializable
{
    int tile;
    int health;
    ArrayList<Dice> diceList;
    public Monster(int tile) throws IOException {
        this.tile = tile;
    }
    //UPDATE
    public void setTile(int tile) {
        this.tile = tile;
    }
    public int getTile() {
        return this.tile;
    }

    public int advance() {
		return 0;
    	/*
    	Tile current = gameScreen.tiles.get(tile);
    	if (tile > 0) {
    	
    	gameScreen.moveTileEntity(this, tile, current.getNextTile());
    	}
    	
    	return current.getNextTile();
    */
	}
    
    

}
