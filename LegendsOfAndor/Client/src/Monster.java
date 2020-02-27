import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

public class Monster implements TileEntity
{
	MinuetoImage monsterImage;
    int tile;
    GameStatus gameStatus;
    public Monster(MinuetoImage monsterImage, int tile) throws MinuetoFileException {
        this.monsterImage = monsterImage;
        this.tile = tile;
        this.gameStatus = GameStatus.getInstance();
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
