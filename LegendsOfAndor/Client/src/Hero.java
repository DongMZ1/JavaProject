import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class Hero implements TileEntity {
    MinuetoImage heroImage;
    int tile;
    GameStatus gameStatus;
    Time time;
    public Hero(MinuetoImage heroImage, int tile) throws MinuetoFileException {
        this.heroImage = heroImage;
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
        return this.heroImage;
    }

    // TODO Move HeroDraw to Hero
    // TODO Scale drawing of Hero with boardZoom
/*
    public void draw() {
        gameStatus.screen, );
    }
 */
}
