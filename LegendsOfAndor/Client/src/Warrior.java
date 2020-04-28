import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

import java.io.IOException;

public class Warrior extends Hero {
    //special ability: warrior gets 5 wp instead of 3 while emptying well, this feature is implemented in a function in well class
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1463985262713026798L;

	public Warrior(MinuetoImage heroImage, int tile) throws IOException {
        super(tile);
        diceList.add(new Dice(new int[] {1,2,3,4,5,6}));
        diceList.add(new Dice(new int[] {1,2,3,4,5,6}));
        diceList.add(new Dice(new int[] {1,2,3,4,5,6}));
    }
    
}
