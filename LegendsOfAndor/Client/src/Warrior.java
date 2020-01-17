import org.minueto.image.MinuetoImage;

public class Warrior extends Hero {
    //TODO Implement Class Separation
    public Warrior(MinuetoImage heroImage, int tile) {
        super(heroImage, tile);
    }

    public MinuetoImage getImage() {
        return this.heroImage;
    }
}
