import java.util.ArrayList;

import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class GoldInitializer {
	public static ArrayList<Gold> initializeGold() throws MinuetoFileException {
        ArrayList<Gold> output = new ArrayList<>();
        MinuetoImage goldImage = new MinuetoImageFile("images/gold.png");
        Gold gold1 = new Gold(goldImage, 4);
        Gold gold2 = new Gold(goldImage, 4);
        Gold gold3 = new Gold(goldImage, 3);
        Gold gold4 = new Gold(goldImage, 2);
        for (Gold f : output)
        {
        	GameScreen.tiles.get(f.tile).addTileEntity(f);
        }
        output.add(gold1);
        output.add(gold2);
        output.add(gold3);
        output.add(gold4);
        return output;
}
}
