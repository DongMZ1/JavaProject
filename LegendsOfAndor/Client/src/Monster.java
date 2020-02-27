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
    
    public boolean advance()
    {
    	//Returns True if monster should be destroyed (Reached castle)
    	//Else false (Monster stays on board)
    	if (tile > 0) {
    	Tile current = GameScreen.tiles.get(tile);
    	GameScreen.moveTileEntity(this, tile, current.getNextTile());
    	}
    	else {
    		return true;
    	}
    	
    	
    	
    	if (tile == 0) {
    		damageCastle();
    	}
    	return false;
    }
    
    public void damageCastle() {
    	//TODO
    	// Should decrease shield count of castle
    	System.out.println("Damaged castle");
    }

}
