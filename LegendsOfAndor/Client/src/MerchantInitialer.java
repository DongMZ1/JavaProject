import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.minueto.image.MinuetoImageFile;

public class MerchantInitialer implements Serializable{
	public static ArrayList<Merchant> initializeMerchants() throws IOException {
        ArrayList<Merchant> output = new ArrayList<>();
        Merchant m1 = new Merchant(new MinuetoImageFile("images/Merchant.jpg").scale(0.5, 0.5), 4);
        Merchant m2 = new Merchant(new MinuetoImageFile("images/Merchant.jpg").scale(0.5, 0.5), 36);
        output.add(m1);
        output.add(m2);
        for (Merchant m : output)
        {
        	GameScreen.tiles.get(m.tile).addTileEntity(m);
        }
        

        return output;
    }
}
