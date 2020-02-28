import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class Hero implements Character {
    MinuetoImage heroImage;
    int tile;
    GameStatus gameStatus;
    Time time;
    int health;
    public final boolean mainHero;
    public Hero(MinuetoImage heroImage, int tile, boolean mainHero) throws MinuetoFileException {
        this.heroImage = heroImage;
        this.tile = tile;
        this.gameStatus = GameStatus.getInstance();
        this.mainHero = mainHero;
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
