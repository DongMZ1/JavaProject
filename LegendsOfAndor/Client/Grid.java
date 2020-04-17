import java.util.Optional;

import org.minueto.image.MinuetoImage;

public class Grid {

    private MinuetoImage boardImage;
    private Optional<Tile> currentTile = Optional.empty();
    
    public void setTile(Tile tile) {
		currentTile = Optional.of(tile);
	}

    
	public Grid(MinuetoImage boardImage) {
    	this.boardImage = boardImage;
    }
    
    
    public Optional<Tile> getTile() {
    	return currentTile;
    }
    
    
}
