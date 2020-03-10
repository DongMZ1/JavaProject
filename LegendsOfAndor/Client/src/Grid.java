import java.util.Optional;

import org.minueto.image.MinuetoImage;

public class Grid {

    private MinuetoImage boardImage;
    private boolean isMappedToSingleTile;
    private Optional<Tile> currentTile = Optional.empty();
    
    public Grid(MinuetoImage boardImage) {
    	this.boardImage = boardImage;
    }
    
    
    public Optional<Tile> getTile() {
    	return currentTile;
    }
    
}
