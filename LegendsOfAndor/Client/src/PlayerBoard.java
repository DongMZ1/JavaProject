import java.io.IOException;

import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;
import org.minueto.image.MinuetoRectangle;
import org.minueto.image.MinuetoText;

public class PlayerBoard {
    private static PlayerBoard playerBoard;
    private static GameStatus gameStatus;
    private boolean flag = false;
    
    private int[] spX = {620};
    int sp;
    
    int wp;
    private int[] wpX = {730};
    private int[] wpY = {260};
    
    private String image;
    //private MinuetoImage playerBoardImage = new MinuetoRectangle(width, height, MinuetoColor.BLACK, true);
    private MinuetoImage playerBoardImage;
    private MinuetoImage spImage = new MinuetoImageFile("images/tokenWarrior.png");
    private MinuetoImage wpImage = new MinuetoImageFile("images/tokenWarrior.png");
    
   
    
    private PlayerBoard(Hero hero) throws IOException {
    	gameStatus = GameStatus.getInstance();
    	
    	sp = hero.sp;
    	wp = hero.wp;
    	
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
    		gameStatus.screen.draw(playerBoardImage, 300, 100);
    		gameStatus.screen.draw(spImage, 665, 205);
    		gameStatus.screen.draw(wpImage, 795, 260);
//            gameStatus.screen.draw(new MinuetoText("Strength Point: " + sp , font, MinuetoColor.WHITE), , 510);
//            gameStatus.screen.draw(new MinuetoText("Will Power: " + wp , font, MinuetoColor.WHITE), 910, 530);
    	}
    	
    	else {
    		
    	}
    }
    
    public void update(Hero hero) {
    	draw();
    }
    
    public void toggleFlag() {
    	if(flag == true) {
    		flag = false;
    	}
    	
    	else {
    		flag = true;
    	}
    }
}
