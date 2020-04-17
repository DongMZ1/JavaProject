import java.io.Serializable;
import java.util.ArrayList;

import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class GoldInitializer implements Serializable{
	//freeGoldList is that there are some gold has no regions
	public static ArrayList<Gold> GoldIntializer() throws MinuetoFileException {
        ArrayList<Gold> output = new ArrayList<>();
    Item gold1 = new Gold(1);
       Item gold2 = new Gold(1);
       Item gold3 = new Gold(1);
        Item gold4 = new Gold(5);
        
         output.add((Gold) gold1);
        output.add((Gold) gold2);
       output.add((Gold) gold3);
        output.add((Gold) gold4);       
       
        for (Gold gd : output)
        {
        	if(gd.getTile() >0) {
				Tile.get(gd.getTile()).addTileEntity(gd);
        }
        }
		return output;

	}

}
