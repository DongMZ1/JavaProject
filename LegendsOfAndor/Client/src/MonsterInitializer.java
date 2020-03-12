import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImageFile;
import org.minueto.window.MinuetoWindow;

public class MonsterInitializer implements Serializable{

	public static ArrayList<Monster> initializeMonsters() throws IOException {
        ArrayList<Monster> output = new ArrayList<>();
        Monster m1 = new Gor(new MinuetoImageFile("images/Monsters/Gor.png"), 8);
        Monster m2 = new Gor(new MinuetoImageFile("images/Monsters/Gor.png"), 20);
        Monster m3 = new Gor(new MinuetoImageFile("images/Monsters/Gor.png"), 21);
        Monster m4 = new Gor(new MinuetoImageFile("images/Monsters/Gor.png"), 26);
        Monster m5 = new Gor(new MinuetoImageFile("images/Monsters/Gor.png"), 48);
        Monster m6 = new Gor(new MinuetoImageFile("images/Monsters/Skral.PNG"), 19);		
        output.add(m1);
        output.add(m2);
        output.add(m3);
        output.add(m4);
        output.add(m5);
	output.add(m6);
        
        for (Monster monster : output)
        {
        	GameScreen.tiles.get(monster.tile).addTileEntity(monster);
        }
        

        return output;
    }
	
	

}
