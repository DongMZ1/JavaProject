import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

import java.io.IOException;

public class Warrior extends Hero {
    //special ability: warrior gets 5 wp instead of 3 while emptying well, this feature is implemented in a function in well class
	
    public Warrior(MinuetoImage heroImage, int tile, boolean mainHero) throws IOException {
        super(tile, mainHero);
        dice = new WarriorDice();
    }
    
}
