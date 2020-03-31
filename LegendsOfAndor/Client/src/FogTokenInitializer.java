

import java.io.IOException;
import java.util.ArrayList;

import org.minueto.image.MinuetoImageFile;

public class FogTokenInitializer{
	public static ArrayList<FogToken> InitializeFogtoken() throws IOException {
        ArrayList<FogToken> output = new ArrayList<>();
        FogToken f1 = new FogToken(new MinuetoImageFile("images/fogtoken.jpg").scale(0.2, 0.2), 8, 7);
        FogToken f2 = new FogToken(new MinuetoImageFile("images/fogtoken.jpg").scale(0.2, 0.2), 11, 2);
        FogToken f3 = new FogToken(new MinuetoImageFile("images/fogtoken.jpg").scale(0.2, 0.2), 12, 1);
        FogToken f4 = new FogToken(new MinuetoImageFile("images/fogtoken.jpg").scale(0.2, 0.2), 13, 3);
        FogToken f5 = new FogToken(new MinuetoImageFile("images/fogtoken.jpg").scale(0.2, 0.2), 49, 4);
        FogToken f6 = new FogToken(new MinuetoImageFile("images/fogtoken.jpg").scale(0.2, 0.2), 16, 6);
        FogToken f7 = new FogToken(new MinuetoImageFile("images/fogtoken.jpg").scale(0.2, 0.2), 32, 1);
        FogToken f8 = new FogToken(new MinuetoImageFile("images/fogtoken.jpg").scale(0.2, 0.2), 46, 5);
        FogToken f9 = new FogToken(new MinuetoImageFile("images/fogtoken.jpg").scale(0.2, 0.2), 44, 6);
        FogToken f10 = new FogToken(new MinuetoImageFile("images/fogtoken.jpg").scale(0.2, 0.2), 42, 1);
        FogToken f11 = new FogToken(new MinuetoImageFile("images/fogtoken.jpg").scale(0.2, 0.2), 48, 1);
        FogToken f12 = new FogToken(new MinuetoImageFile("images/fogtoken.jpg").scale(0.2, 0.2), 47, 5);
        FogToken f13 = new FogToken(new MinuetoImageFile("images/fogtoken.jpg").scale(0.2, 0.2), 56, 1);
        FogToken f14 = new FogToken(new MinuetoImageFile("images/fogtoken.jpg").scale(0.2, 0.2), 63, 5);
        FogToken f15 = new FogToken(new MinuetoImageFile("images/fogtoken.jpg").scale(0.2, 0.2), 64, 8);
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
        	GameScreen.tiles.get(f.tile).addTileEntity(f);
        }
        

        return output;
    }
}
