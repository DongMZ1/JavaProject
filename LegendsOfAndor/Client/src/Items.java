import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

public class Items implements TileEntity{

	MinuetoImage itemImage;
    int tile;
    GameStatus gameStatus;
    public Items(MinuetoImage itemImage, int tile) throws MinuetoFileException {
        this.itemImage = itemImage;
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
        return this.itemImage;
    }
}
