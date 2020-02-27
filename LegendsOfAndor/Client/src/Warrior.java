import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class Warrior extends Hero {
    //TODO Implement Class Separation
    public Warrior(MinuetoImage heroImage, int tile) throws MinuetoFileException {
        super(heroImage, tile);
        

    }

    public MinuetoImage getImage() {
        return this.heroImage;
    }
}
