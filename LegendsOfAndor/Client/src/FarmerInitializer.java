import java.util.ArrayList;

import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class FarmerInitializer {
	public static ArrayList<Farmer> initializeFarmers() throws MinuetoFileException {
        ArrayList<Farmer> output = new ArrayList<>();
        MinuetoImage farmerimage = new MinuetoImageFile("images/farmer.png");
        Farmer f1 = new Farmer(farmerimage, 5);
        Farmer f2 = new Farmer(farmerimage, 6);
        output.add(f1);
        output.add(f2);
        
        for (Farmer f : output)
        {
        	GameScreen.tiles.get(f.tile).addTileEntity(f);
        }

        return output;
}


}
