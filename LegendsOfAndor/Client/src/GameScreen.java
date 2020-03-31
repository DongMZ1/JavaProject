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

public class GameScreen implements Inputtable, Serializable{
    private MinuetoImageFile defaultBoard = new MinuetoImageFile("images/LegendsOfAndorBoard.jpg");
    private MinuetoImage gameBoard = defaultBoard.scale((double) 1 / 3, (double) 1 / 3);
    private boolean movingCam;
    static ArrayList<Tile> tiles;
    public ArrayList<Monster> monsters;
    private ArrayList<Merchant> merchants;
    private ArrayList<Well> wells;
    private ArrayList<Farmer> farmers;
    private DwarfMine mine;
    private Castle castle;
    public Castle getCastle() {
    	return this.castle;
    }
    public int Lengend1EventCardIndex = 1;
    static Hero currentHero;
    private Hero hero2;
    public TurnManager tm;
    private InputHandler inputHandler;
    Fight fight;
    CollaborativeDecision cd;
    private TextBox textBox = TextBox.getInstance();
    private MinuetoFont font = new MinuetoFont("Arial",20, true, false);
    private static GameStatus gameStatus;
    private static Camera camera;
    private Coordinate previousMouseCoordinate = new Coordinate(0,0);
    private GameUi gameUi;
    private static final MinuetoImage background = new MinuetoRectangle(12000, 9000, MinuetoColor.BLACK, true);
    private PlayerBoard playerBoard;
    private ArrayList<FogToken> fogtokens;
    
    int toMove; 
    final int NUMBERS[] = {0,1,2,3,4,5,6,7,8,9}; 
    final int ASCIINUMBERS[] = {48,49,50,51,52,53,54,55,56,57}; 
	
    public GameScreen() throws IOException {
        camera = Camera.getInstance();
        this.movingCam = false;
        inputHandler = InputHandler.getInputHandler();
        
//        tiles = new TileInitialiser().initialiseTiles(screen);
//        tiles = new TileInitialiser().initialiseCoords(tiles);
        tiles = Tile.getAll();
        for (Tile tile : tiles)
        	tile.setScreen(Client.screen);
        
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
        gameUi = GameUi.getInstance();
        fight = new Fight(Client.screen,gameStatus.screenWidth, gameStatus.screenHeight, this.tm);
//        cd = new CollaborativeDecision(DecisionType.START,screen, tm);
        playerBoard = PlayerBoard.getInstance(Client.mainHero);
        castle = new Castle(5 - tm.getSize(), Client.screen);
    }
    
    public void fight() {
    	fight.draw();
    }

    public void draw() {
        Client.screen.draw(background, 0, 0);
        Client.screen.draw(gameBoard, camera.currentPos.getX(), camera.currentPos.getY());
        for(Tile tile : tiles)
            tile.draw();
        gameUi.draw();
	    playerBoard.draw();
        tm.draw();
        if (gameStatus.currentScreen == gameStatus.COLLABORATIVE_SCREEN) {
        	cd.draw();
        }
	    castle.draw();
    }

    public static boolean isValidMove(int currentInt, int destInt) {
    	Tile currentTile = Tile.get(currentInt);
    	int[] adjacentTiles = currentTile.getAdjacentTiles();
//		System.out.println(t);
		for (int i =0; i < adjacentTiles.length; i++) {
//			System.out.println(adjacentTiles[i]);
			if (destInt == adjacentTiles[i]) return true;
    		}
		return false;
    }
    
    public static void moveTileEntity(TileEntity tileEntity, int currentTile, int destination){
        assert(tiles.get(currentTile).containsTileEntity(tileEntity));
        tiles.get(currentTile).removeTileEntity(tileEntity);
        tiles.get(destination).addTileEntity(tileEntity);
        tileEntity.setTile(destination);
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
    
    

    public void handleKeyPress(int key) {
    }
    public void handleKeyRelease(int key) {

    }
    //IAN testing shit
    public void handleKeyType(char c) {
    	if (c == 'd') {
    		newDay();
    	}
    	else if (c == 'a')
    	{
    		Client.mainHero = currentHero;
    		System.out.println(Client.mainHero);
    	}
    	else if(c == 'm') {
		playerBoard.update(Client.mainHero);
    		playerBoard.toggleFlag();
    	}	 
    	else if (c == ' ') { 
    		if (Client.mainHero.time.left()){ 
    			if (toMove >= 0 && toMove <= 76) { 
	            	if(gameStatus.ui == UIStatus.MOVEBEGIN) { 
	            		 if (isValidMove(Client.mainHero.getTile(),toMove)) {
	            			moveTileEntity(Client.mainHero, Client.mainHero.getTile(),toMove); 
	    		            Client.mainHero.time.advance(); 
	    		            gameStatus.ui = UIStatus.MOVING; 
	    		            gameUi.moveButton.setLabel("End Move"); 
	            		 }
	    	             
	    	        } 
	            	else if(gameStatus.ui == UIStatus.MOVING) { 
	            		if (isValidMove(Client.mainHero.getTile(),toMove)) {
	    	            	moveTileEntity(Client.mainHero, Client.mainHero.getTile(),toMove);	 
	    	            	Client.mainHero.time.advance();
	            		}
	    	            } 
	    	             
	    	             
	    	        } 
    			 
    		} 
    		else { 
            	gameUi.moveButton.setLabel("No Time"); 
 
            	} 
    		toMove = 0; 
    	} 
    	else { 
    	for (int i = 0; i <10; i++) { 
    		if (c == ASCIINUMBERS[i]) { 
    			toMove *= 10; 
    			toMove += i; 
    		} 
    	} 
    	} 
    }
    public void handleMousePress(int x, int y, int button) {
 /*   	Coordinate coords = camera.getPosOnScreen(x, y);
    	System.out.println("X: " + x);
    	System.out.println("Y: " + y); */
    	
        if(y > gameStatus.screenHeight - gameUi.uiHeight && x < 650)
            gameUi.handleMousePress(x, y, button);
        
        else if(button == MinuetoMouse.MOUSE_BUTTON_RIGHT) this.movingCam = true;
        else if(button == MinuetoMouse.MOUSE_BUTTON_LEFT) {
/*        	if (mainHero.time.left()){
	        	if(gameStatus.ui == UIStatus.MOVEBEGIN) {
	        		
	        			moveTileEntity(mainHero, mainHero.getTile(), findTileClicked(camera.getPosOnBoard(x, y)));
			            mainHero.time.advance();
			            gameStatus.ui = UIStatus.MOVING;
			            gameUi.moveButton.setLabel("End Move");
		            
		            
		        }
	        	else if(gameStatus.ui == UIStatus.MOVING) {
				//UPDATE
		            	moveTileEntity(mainHero, mainHero.getTile(), findTileClicked(camera.getPosOnBoard(x, y)));	
		            	mainHero.time.advance();	            
		            }
		            
		            
		        }
        	else {
            	gameUi.moveButton.setLabel("No Time");

            	}*/
	       } 
        
    }
    
    public void handleMouseRelease(int x, int y, int button) {
        if(button == MinuetoMouse.MOUSE_BUTTON_RIGHT) this.movingCam = false;
        if (gameStatus.ui == UIStatus.WAITING) {
        	if(Client.mainHero.time.left()) {currentHero.time.advance();}
        	
        	endTurn();
        	
        	gameStatus.ui = UIStatus.NONE;
        }
        else if (gameStatus.ui == UIStatus.MOVED) {
        	gameStatus.ui = UIStatus.NONE;
        	endTurn();
        	gameUi.moveButton.setLabel("Move");
        }
        else if (gameStatus.ui == UIStatus.FIGHTING) {
        	Tile t = tiles.get(Client.mainHero.getTile());
        	if (Client.mainHero.time.left()) {
        		monsterLoop:
	        	for (Monster monster : monsters)
	        	{	
	        		//normal fight
	        		if(t.containsTileEntity(monster)) {
	        			fight.start(t);
	        			gameStatus.focus = GameStatus.FOCUS_ON_FIGHT;
	        			gameStatus.currentScreen = GameStatus.FIGHT_SCREEN;
	        			break;
	        		}
	        		
	        		//fight monter on adjacent tile
	        		else if(Client.mainHero instanceof Archer) {
	        			int[] adjacentTiles = t.getAdjacentTiles();
//	        			System.out.println(t);
	        			for (int i =0; i < adjacentTiles.length; i++) {
//	        				System.out.println(adjacentTiles[i]);
	        				Tile adjacentTile = Tile.get(adjacentTiles[i]);
	        				if (adjacentTile.containsTileEntity(monster)) {
	        					fight.startAdjacent(adjacentTile, Client.mainHero);	    	        			
	    	        			gameStatus.focus = GameStatus.FOCUS_ON_FIGHT;
	    	        			gameStatus.currentScreen = GameStatus.FIGHT_SCREEN;
	    	        			break monsterLoop;
	    	        			
	    	        		}
	        				}
	        			}	        				
	        	}
        	}
        	else {
        		System.out.println("NO TIME");
        	}
        	if (!fight.isHappening) {
        		System.out.println("UNABLE TO FIGHT");
        	}
        	gameStatus.ui = UIStatus.NONE;
        }
	    
      //  else if(gameStatus.ui == UIStatus.PICKING) {
        	//MinuetoImage background = new MinuetoRectangle(12000, 9000, MinuetoColor.BLACK, true);
        //	PickupOption p1 = new PickupOption("Pick up choice:");
        //	p1.start();
       // 	gameStatus.ui = UIStatus.NONE;
     //   }
        
      //  else if(gameStatus.ui == UIStatus.DROPING) {
       // 	DropOffOption x1 = new DropOffOption("Drop off");
        //	x1.start();
        //	gameStatus.ui = UIStatus.NONE;
    //    }
   //     else if(gameStatus.ui == UIStatus.Trade ) {
        	//Client.mainHero.Buy2SPfor2Gold();
        	//currentHero.Buy2SPfor2Gold();
				
			//	try {
			//		Cards.drawLegend1EventCard((Lengend1EventCardIndex));
	//	} catch (IOException e) {
					// TODO Auto-generated catch block
			//		e.printStackTrace();
			//	}	
        //	gameStatus.ui = UIStatus.NONE;
       // }
      //  else if(gameStatus.ui == UIStatus.UseItem) {
       // 	useItemHander u1 = new useItemHander();
        //	gameStatus.ui = UIStatus.NONE;
      //  }
	    
    }
    public void handleMouseMove(int x, int y) {
        if(movingCam) {
            camera.moveCamera(x - previousMouseCoordinate.getX(), y - previousMouseCoordinate.getY());
        }
        previousMouseCoordinate.setPos(x, y);
    }
    public void handleMouseWheelRotate(int rotation) {
        if(rotation == 1) {
            camera.zoomIn();
        }
        else if(rotation == -1) {
            camera.zoomOut();
        }
        gameBoard = defaultBoard.scale((double) 1 / camera.boardZoom, (double) 1 / camera.boardZoom);
    }
}






