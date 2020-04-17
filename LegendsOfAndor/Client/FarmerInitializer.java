import java.io.Serializable;
import java.util.ArrayList;

import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class FarmerInitializer implements Serializable{
	public static ArrayList<Farmer> initializeFarmers() throws MinuetoFileException {
        ArrayList<Farmer> output = new ArrayList<>();
        Farmer f1 = new Farmer(5);
        Farmer f2 = new Farmer(6);
        output.add(f1);
        output.add(f2);
        
        for (Farmer f : output)
        {
                Tile.get(f.getTile()).addTileEntity(f);
        }

        return output;
}


}
