import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Monster implements Character, Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6562256286229349527L;
	int tile;
    int health;
    int strength;
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
    	Tile current = GameScreen.gameScreen.tiles.get(this.tile);
    	if (this.tile > 0) {
    	
    	GameScreen.gameScreen.moveTileEntity(this, tile, current.getNextTile());
    	}
    	
    	return current.getNextTile();
	}
    
    

}
