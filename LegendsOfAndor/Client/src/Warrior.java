import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class Warrior extends Hero {
    //TODO Implement Class Separation
    public Warrior(MinuetoImage heroImage, int tile, boolean mainHero) throws MinuetoFileException {
        super(heroImage, tile, mainHero);
        

    }

    public MinuetoImage getImage() {
        return this.heroImage;
    }
}
