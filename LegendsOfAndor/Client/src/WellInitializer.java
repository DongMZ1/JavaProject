import java.io.IOException;
import java.util.ArrayList;

import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImageFile;
import org.minueto.window.MinuetoWindow;

public class WellInitializer {

	public static ArrayList<Well> initializeWells() throws IOException {
        ArrayList<Well> output = new ArrayList<>();
        Well w1 = new Well(new MinuetoImageFile("images/Well.png"), 5);
        Well w2 = new Well(new MinuetoImageFile("images/Well.png"), 35);
        Well w3 = new Well(new MinuetoImageFile("images/Well.png"), 45);
        Well w4 = new Well(new MinuetoImageFile("images/Well.png"), 55);
        output.add(w1);
        output.add(w2);
        output.add(w3);
        output.add(w4);
        
	
        for (Well well : output)
        {
        	GameScreen.tiles.get(well.getTile()).addTileEntity(well);
        }
        
        return output;
    }
	
	

}
