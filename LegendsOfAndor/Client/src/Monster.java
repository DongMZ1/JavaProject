import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

import java.io.IOException;
import java.io.Serializable;

public class Monster implements Character, Serializable
{
    int tile;
    GameStatus gameStatus;
    int health; 
    Dice dice;
    public Monster(int tile) throws IOException {
        this.tile = tile;
        this.gameStatus = GameStatus.getInstance();
        dice = new MonsterDice();
    }
    //UPDATE
    public void setTile(int tile) {
        this.tile = tile;
    }
    public int getTile() {
        return this.tile;
    }

//    @Return the tile that the monster advances to
    public int advance()
    {
    	Tile current = GameScreen.tiles.get(tile);
    	if (tile > 0) {
    	
    	GameScreen.moveTileEntity(this, tile, current.getNextTile());
    	}
    	
    	return current.getNextTile();
    }
    
    

}
