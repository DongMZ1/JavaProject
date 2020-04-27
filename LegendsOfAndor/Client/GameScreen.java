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

    public ArrayList<Tile> tiles;
	public ArrayList<Monster> monsters;
	public ArrayList<Merchant> merchants;
	public ArrayList<Well> wells;
	public ArrayList<Farmer> farmers;
	public DwarfMine mine;
	public Castle castle;
	public Witch witch;
    public Castle getCastle() {
    	return this.castle;
    }
    public TurnManager tm;
	CollaborativeDecision cd;
    public GameStatus gameStatus;
    public ArrayList<FogToken> fogtokens;
    public Narrator narrator;
    public static GameScreen gameScreen;
	public static GameScreen getInstance() throws IOException {
		if(gameScreen == null)
			gameScreen = new GameScreen();
		return gameScreen;
	}

    private GameScreen() throws IOException {
        
//        tiles = new TileInitialiser().initialiseTiles(screen);
//        tiles = new TileInitialiser().initialiseCoords(tiles);
        tiles = Tile.getAll();
        
        monsters = MonsterInitializer.initializeMonsters();
        wells = new ArrayList<>();
        wells.add(new Well(5));
		wells.add(new Well(35));
		wells.add(new Well(45));
		wells.add(new Well(55));
		for (Well well : wells)
			tiles.get(well.getTile()).addTileEntity(well);
        mine = DwarfMineInitializer.initializemine();
        merchants = MerchantInitialer.initializeMerchants();
        farmers = FarmerInitializer.initializeFarmers();
        //GoldInitializer.GoldIntializer();
        fogtokens = FogTokenInitializer.InitializeFogtoken();
        tm = new TurnManager(new ArrayList<>());
        narrator = new Narrator();
        gameStatus = GameStatus.getInstance();
//        cd = new CollaborativeDecision(DecisionType.START,screen, tm);
        castle = new Castle(5 - tm.getSize());
    }

    public void addHero(Hero hero) {
		tm.addHero(hero);
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

	public void moveTileEntity(TileEntity tileEntity, int currentTile, int destination){
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
    		w.replenishWell();
    	}
    	tm.newDay();
    	this.narrator.advance();
    }

}





