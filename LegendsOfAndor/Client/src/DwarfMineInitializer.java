import java.util.ArrayList;

import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImageFile;

public class DwarfMineInitializer {
	public static DwarfMine initializemine() throws MinuetoFileException {
        DwarfMine m = new DwarfMine(new MinuetoImageFile("images/Well.png"), 71); 
        GameScreen.tiles.get(m.getTile()).addTileEntity(m);
        return m;
    }
}
