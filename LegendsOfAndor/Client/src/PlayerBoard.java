import java.io.IOException;
import java.io.Serializable;

import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;
import org.minueto.image.MinuetoRectangle;
import org.minueto.image.MinuetoText;

public class PlayerBoard implements Serializable{
    private static PlayerBoard playerBoard;
    private static GameStatus gameStatus;
    private boolean flag = false;
    
    int sp;
    private int[] spX = {620, 665, 695, 725, 760, 785, 825, 855, 885, 915, 945, 975, 1005, 1035};
    
    int wp;
    private int[] wpX = {730, 795, 825, 895, 935, 965, 1075, 730, 795, 825, 895, 935, 965, 1075, 730, 795, 825, 895, 935, 965, 1075};
    private int[] wpY = {260, 260, 260, 260, 260, 260, 260, 310, 310, 310, 310, 310, 310, 310, 360, 360, 360, 360, 360, 360, 360};
    
    int gold;
    private int[] goldX = {9000, 700};
    
    private String image;
    private MinuetoImage playerBoardImage;
    private MinuetoImage spImage = new MinuetoImageFile("images/tokenWarrior.png");
    private MinuetoImage wpImage = new MinuetoImageFile("images/tokenWarrior.png");
    private MinuetoImage goldImage = new MinuetoImageFile("images/gold.jpg").scale(0.1, 0.1);
   
    
    private PlayerBoard(Hero hero) throws IOException {
    	gameStatus = GameStatus.getInstance();
    	
    	sp = hero.sp;
    	wp = hero.wp;
    	if(hero.hasGold) {
    		gold = 1;
    	}
    	
    	else {
    		gold = 0;
    	}
    	
    	if(hero instanceof Warrior) {
    		image = "images/Heroes/WarriorMaleBoard.png";
    	}
    	
    	else if(hero instanceof Archer) {
    		image = "images/Heroes/ArcherMaleBoard.png";
    	}
    	
    	else if(hero instanceof Warrior) {
    		image = "images/Heroes/DwarfMaleBoard.png";
    	}
    	
    	else {
    		image = "images/Heroes/MageMaleBoard.png";
    	}
    	
    	
    	playerBoardImage = new MinuetoImageFile(image).scale(300/ 671.0, 300/ 671.0);
    }

    public static PlayerBoard getInstance(Hero hero) throws IOException {
        if(playerBoard == null) {
        	playerBoard = new PlayerBoard(hero);
        }
            
        
        return playerBoard;
    }

    public void draw() {
    	if(flag) {
    		Client.screen.draw(playerBoardImage, 300, 100);
    		Client.screen.draw(spImage, spX[sp - 1], 205);
    		Client.screen.draw(wpImage, wpX[wp], wpY[wp]);
    		Client.screen.draw(goldImage, goldX[gold], 130);
    	}
    	
    	else {
    		
    	}
    }
    
    public void update(Hero hero) {
    	sp = hero.sp;
    	wp = hero.wp;
    	
    	if(hero.hasGold) {
    		gold = 1;
    	}
    	
    	else {
    		gold = 0;
    	}
    	
    }
    
    public void toggleFlag() {
    	flag = !flag;
    }
}