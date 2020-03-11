import java.io.IOException;
import java.util.ArrayList;

import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImageFile;
import org.minueto.window.MinuetoWindow;

public class MonsterInitializer {

	public static ArrayList<Monster> initializeMonsters() throws IOException {
        ArrayList<Monster> output = new ArrayList<>();
        Monster m1 = new Gor(new MinuetoImageFile("images/Monsters/Gor.png"), 4);
        Monster m2 = new Gor(new MinuetoImageFile("images/Monsters/Gor.png"), 5);
        output.add(m1);
        output.add(m2);
        for (Monster monster : output)
        {
        	GameScreen.tiles.get(monster.tile).addTileEntity(monster);
        }
        

        return output;
    }
	
	

}
