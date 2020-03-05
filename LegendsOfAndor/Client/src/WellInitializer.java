import java.util.ArrayList;

import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImageFile;
import org.minueto.window.MinuetoWindow;

public class WellInitializer {

	public static ArrayList<Well> initializeWells() throws MinuetoFileException {
        ArrayList<Well> output = new ArrayList<>();
        Well w1 = new Well(new MinuetoImageFile("images/Well.png"), 5);
        output.add(w1);
        for (Well well : output)
        {
        	GameScreen.tiles.get(well.getTile()).addTileEntity(well);
        }
        
        return output;
    }
	
	

}
