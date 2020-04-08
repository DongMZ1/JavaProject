import org.minueto.MinuetoColor;
import org.minueto.MinuetoEventQueue;
import org.minueto.MinuetoFileException;
import org.minueto.handlers.*;
import org.minueto.image.*;
import org.minueto.window.MinuetoFrame;
import org.minueto.window.MinuetoFullscreen;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameScreen implements Serializable{

    static ArrayList<Tile> tiles;
    public ArrayList<Monster> monsters;
    private ArrayList<Merchant> merchants;
    private ArrayList<Well> wells;
    private ArrayList<Farmer> farmers;
    private DwarfMine mine;
    public Castle castle;
    public Castle getCastle() {
    	return this.castle;
    }
    public int Lengend2EventCardIndex = 1;
    static Hero currentHero;
    private Hero hero2;
    public TurnManager tm;
	CollaborativeDecision cd;

    public static GameStatus gameStatus;
    private ArrayList<FogToken> fogtokens;
    public boolean Legend2ModeIsEasy = false;
    public Narrator narrator;
    public GameScreen() throws IOException {
        
//        tiles = new TileInitialiser().initialiseTiles(screen);
//        tiles = new TileInitialiser().initialiseCoords(tiles);
        tiles = Tile.getAll();
        
        monsters = MonsterInitializer.initializeMonsters();
        wells = WellInitializer.initializeWells();
        mine = DwarfMineInitializer.initializemine();
        merchants = MerchantInitialer.initializeMerchants();
        FarmerInitializer.initializeFarmers();
        GoldInitializer.GoldIntializer();
        fogtokens = FogTokenInitializer.InitializeFogtoken();
        Client.mainHero.time = new Time(new MinuetoImageFile("images/tokenWarrior.png"),Client.screen);
  //      hero2 = new Mage(new MinuetoImageFile("images/Heroes/MageFemaleIcon.png").scale(Constants.HERO_SCALE, Constants.HERO_SCALE), 1, false);
  //      hero2.time = new Time(new MinuetoImageFile("images/tokenWarrior.png"),Client.screen);
        
        tm = new TurnManager(new ArrayList<Hero>());
        tm.addHero(Client.mainHero);
  //      tm.addHero(hero2);
        currentHero = Client.mainHero;
               
        
        gameStatus = GameStatus.getInstance();
//        cd = new CollaborativeDecision(DecisionType.START,screen, tm);
        castle = new Castle(5 - tm.getSize(), Client.screen);
    }

    public int findTileClicked(Coordinate clickedCoord) {
        int closestDist = Integer.MAX_VALUE;
        int closestNum = 0;
        for (Tile tile: tiles) {
        	if(Math.abs(clickedCoord.getX() - tile.getMoveX() + clickedCoord.getY() - tile.getMoveY()) < closestDist) {
                closestDist = Math.abs(clickedCoord.getX() - tile.getMoveX() + clickedCoord.getY() - tile.getMoveY());
                closestNum ++;
        	}
        }
//        for(int i = 0; i < tiles.size(); i++) {
//            if(Math.abs(clickedCoord.getX() - tiles.get(i).getMoveX() + clickedCoord.getY() - tiles.get(i).getMoveY()) < closestDist) {
//                closestDist = Math.abs(clickedCoord.getX() - tiles.get(i).getMoveX() + clickedCoord.getY() - tiles.get(i).getMoveY());
//                closestNum = i;
//            }
//        }
        return closestNum;
    }

	public static void moveTileEntity(TileEntity tileEntity, int currentTile, int destination){
		assert(tiles.get(currentTile).containsTileEntity(tileEntity));
		tiles.get(currentTile).removeTileEntity(tileEntity);
		tiles.get(destination).addTileEntity(tileEntity);
		tileEntity.setTile(destination);
	}
    
    
    public void newDay() {
    	ArrayList<Monster> toRemove = new ArrayList<>(); //must use because of Enhanced for loop
    	ArrayList<Integer> occupiedSpaces = new ArrayList<>();
    	
    	for (Monster monster : monsters) {
    		Integer mTile = monster.advance();
    		//If at castle then toRemove
    		if(mTile == 0) {
    			tiles.get(monster.tile).removeTileEntity(monster);
    			toRemove.add(monster);
    		}
    		//If space is occupied, skip over it and add new space to occupied space
    		else if (occupiedSpaces.contains(mTile)) {
    			occupiedSpaces.add(monster.advance());
    		}
    		//Else was just a normal move. Save tile that ended on
    		else {
    			occupiedSpaces.add(mTile);
    		}
    	}
    	
    	//These are monsters that reached castle
    	for (Monster rMonster : toRemove)
    	{
    		monsters.remove(rMonster);
    		castle.damage(rMonster);
    		
    	}
    	
    	//replenish all wells on map
    	for(Well w: wells) {
    		try {
				w.replenishWell();
			} catch (MinuetoFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	tm.newDay();
    	
    }
    
    public void endTurn() {
    	currentHero = tm.endTurn();
    	if (!tm.timeLeft()) {
    		newDay();
    	}
    }
}






