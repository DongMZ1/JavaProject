import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.minueto.image.MinuetoImageFile;

public class MerchantInitialer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6418518260654051400L;

	public static ArrayList<Merchant> initializeMerchants() throws IOException {
        ArrayList<Merchant> output = new ArrayList<>();
        Merchant m1 = new Merchant(18);
        Merchant m2 = new Merchant(57);
        Merchant m3 = new Merchant(71);
        output.add(m1);
        output.add(m2);
        output.add(m3);
        for (Merchant m : output)
        {
                Tile.get(m.tile).addTileEntity(m);
        }
        

        return output;
    }
}
