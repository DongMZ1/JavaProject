import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class Warrior extends Hero {
    //special ability: warrior gets 5 wp instead of 3 while emptying well, this feature is implemented in a function in well class
	
    public Warrior(MinuetoImage heroImage, int tile, boolean mainHero) throws MinuetoFileException {
        super(heroImage, tile, mainHero);
        
    }

    public MinuetoImage getImage() {
        return this.heroImage;
    }
}
