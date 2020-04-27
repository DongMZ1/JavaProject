import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImageFile;
import org.minueto.window.MinuetoWindow;

public class MonsterInitializer implements Serializable{

	public static ArrayList<Monster> initializeMonsters() throws IOException {
        ArrayList<Monster> output = new ArrayList<>();
     //   Monster m1 = new Gor(8);
     //  	Monster m2 = new Gor(20);
      // 	Monster m3 = new Gor(21);
    //   	Monster m4 = new Gor(26);
     //  	Monster m5 = new Gor(48);
      // 	Monster m6 = new Gor(19);
     //  	output.add(m1);
     //   output.add(m2);
     //   output.add(m3);
    //    output.add(m4);
     //   output.add(m5);
	//	output.add(m6);
        
        for (Monster monster : output)
        {
			Tile.get(monster.tile).addTileEntity(monster);
        }
        

        return output;
    }
	
	

}
