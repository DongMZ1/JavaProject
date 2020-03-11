import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

import java.io.IOException;

public class Monster implements Character
{
	MinuetoImage monsterImage;
    int tile;
    GameStatus gameStatus;
    int health; 
    Dice dice;
    public Monster(MinuetoImage monsterImage, int tile) throws IOException {
        this.monsterImage = monsterImage;
        this.tile = tile;
        this.gameStatus = GameStatus.getInstance();
        dice = new MonsterDice();
    }
    public void setTile(int tile) {
        this.tile = tile;
    }
    public int getTile() {
        return this.tile;
    }
    public MinuetoImage getImage() {
        return this.monsterImage;
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
