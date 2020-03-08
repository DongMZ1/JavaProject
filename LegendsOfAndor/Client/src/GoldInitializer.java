import java.util.ArrayList;

import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class GoldInitializer {
	//freeGoldList is that there are some gold has no regions
        ArrayList<Gold> freeGoldList = new ArrayList<>();
        MinuetoImage goldImage = new MinuetoImageFile("images/gold.png");
        Item gold1 = new Gold( -1);
        Item gold2 = new Gold( -1);
        Item gold3 = new Gold( -1);
        Item gold4 = new Gold(10);
       
        for (Gold gd : output)
        {
        	if(gd.tile >0) {
        	GameScreen.tiles.get(gd.tile).addTileEntity(gd);
        }
        }
        output.add(gold1);
        output.add(gold2);
        output.add(gold3);
        output.add(gold4);

}
