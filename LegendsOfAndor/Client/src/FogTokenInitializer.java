

import java.io.IOException;
import java.util.ArrayList;

import org.minueto.image.MinuetoImageFile;

public class FogTokenInitializer{
	public static ArrayList<FogToken> InitializeFogtoken() throws IOException {
        ArrayList<FogToken> output = new ArrayList<>();
        FogToken f1 = new FogToken(8, 8);
        FogToken f2 = new FogToken(11, 2);
        FogToken f3 = new FogToken(12, 1);
        FogToken f4 = new FogToken(13, 3);
        FogToken f5 = new FogToken(49, 4);
        FogToken f6 = new FogToken(16, 6);
        FogToken f7 = new FogToken(32, 1);
        FogToken f8 = new FogToken(46, 5);
        FogToken f9 = new FogToken(44, 6);
        FogToken f10 = new FogToken(42, 1);
        FogToken f11 = new FogToken(48, 1);
        FogToken f12 = new FogToken(47, 5);
        FogToken f13 = new FogToken(56, 1);
        FogToken f14 = new FogToken(63, 5);
        FogToken f15 = new FogToken(64, 7);
        output.add(f1);
        output.add(f2);
        output.add(f3);
        output.add(f4);
        output.add(f5);
        output.add(f6);
        output.add(f7);
        output.add(f8);
        output.add(f9);
        output.add(f10);
        output.add(f11);
        output.add(f12);
        output.add(f13);
        output.add(f14);
        output.add(f15);
        
        for (FogToken f : output)
        {
                Tile.get(f.tile).addTileEntity(f);
        }
        

        return output;
    }
}
