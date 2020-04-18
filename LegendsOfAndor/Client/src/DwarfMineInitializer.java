import java.io.Serializable;
import java.util.ArrayList;

import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImageFile;

public class DwarfMineInitializer implements Serializable{
	public static DwarfMine initializemine() throws MinuetoFileException {
        DwarfMine m = new DwarfMine(71);
		Tile.get(m.getTile()).addTileEntity(m);
        return m;
    }
}
