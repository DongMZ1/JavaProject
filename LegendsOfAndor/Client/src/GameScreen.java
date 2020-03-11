import org.minueto.MinuetoColor;
import org.minueto.MinuetoEventQueue;
import org.minueto.MinuetoFileException;
import org.minueto.handlers.*;
import org.minueto.image.*;
import org.minueto.window.MinuetoFrame;
import org.minueto.window.MinuetoFullscreen;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameScreen implements Inputtable{
    private MinuetoWindow screen;
    private MinuetoImageFile defaultBoard = new MinuetoImageFile("images/LegendsOfAndorBoard.jpg");
    private MinuetoImage gameBoard = defaultBoard.scale((double) 1 / 3, (double) 1 / 3);
    private boolean movingCam;
    static ArrayList<Tile> tiles;
    private ArrayList<Monster> monsters;
    private ArrayList<Merchant> merchants;
    private ArrayList<Well> wells;
    private ArrayList<Farmer> farmers;
    private DwarfMine mine;
    private Castle castle = new Castle(1);
    static Hero mainHero;
    static Hero currentHero;
    private Hero hero2;
    private TurnManager tm;
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
	
    public GameScreen(MinuetoWindow screen) throws IOException {
        this.screen = screen;
        this.screen.setVisible(true);
        camera = Camera.getInstance();
        this.movingCam = false;
        inputHandler = InputHandler.getInputHandler();
        
//        tiles = new TileInitialiser().initialiseTiles(screen);
//        tiles = new TileInitialiser().initialiseCoords(tiles);
        tiles = Tile.getAll();
        for (Tile tile : tiles)
        	tile.setScreen(screen);
        
        monsters = MonsterInitializer.initializeMonsters();
        wells = WellInitializer.initializeWells();
        mine = DwarfMineInitializer.initializemine();
        merchants = MerchantInitialer.initializeMerchants();
        FarmerInitializer.initializeFarmers();
        GoldInitializer.GoldIntializer();
        
        mainHero = new Archer(new MinuetoImageFile("images/Heroes/ArcherMaleIcon.png").scale(Constants.HERO_SCALE, Constants.HERO_SCALE), 0, true);
        mainHero.time = new Time(new MinuetoImageFile("images/tokenWarrior.png"),this.screen);
        hero2 = new Warrior(new MinuetoImageFile("images/Heroes/WarriorFemaleIcon.png").scale(Constants.HERO_SCALE, Constants.HERO_SCALE), 1, false);
        hero2.time = new Time(new MinuetoImageFile("images/tokenWarrior.png"),this.screen);       
        
        tm = new TurnManager(new ArrayList<Hero>());
        tm.addHero(mainHero);
        tm.addHero(hero2);
        currentHero = mainHero;
               
        
        gameStatus = GameStatus.getInstance();
        gameUi = GameUi.getInstance();
        fight = new Fight(this.screen,gameStatus.screenWidth, gameStatus.screenHeight, this.tm);
//        cd = new CollaborativeDecision(DecisionType.START,screen, tm);
        playerBoard = PlayerBoard.getInstance(mainHero);
    }
    
    public void fight() {
    	fight.draw();
    }

    public void draw() {
        this.screen.draw(background, 0, 0);
        this.screen.draw(gameBoard, camera.currentPos.getX(), camera.currentPos.getY());
        for(Tile tile : tiles)
            tile.draw();
        gameUi.draw();
	    playerBoard.draw();
        tm.draw();
        if (gameStatus.currentScreen == gameStatus.COLLABORATIVE_SCREEN) {
        	cd.draw();
        }
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
    		mainHero = currentHero;
    	}
    	else if(c == 'm') {
    		playerBoard.toggleFlag();
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
        	if (mainHero.time.left()){
	        	if(gameStatus.ui == UIStatus.MOVEBEGIN) {
	        		
	        			moveTileEntity(mainHero, mainHero.getTile(), findTileClicked(camera.getPosOnBoard(x, y)));
			            mainHero.time.advance();
			            gameStatus.ui = UIStatus.MOVING;
			            gameUi.moveButton.setLabel("End Move");
		            
		            
		        }
	        	else if(gameStatus.ui == UIStatus.MOVING) {
		            	moveTileEntity(mainHero, mainHero.getTile(), findTileClicked(camera.getPosOnBoard(x, y)));	
		            	mainHero.time.advance();	            
		            }
		            
		            
		        }
        	else {
            	gameUi.moveButton.setLabel("No Time");

            	}
	       }
        
    }
    
    public void handleMouseRelease(int x, int y, int button) {
        if(button == MinuetoMouse.MOUSE_BUTTON_RIGHT) this.movingCam = false;
        if (gameStatus.ui == UIStatus.WAITING) {
        	if(mainHero.time.left()) {currentHero.time.advance();}
        	
        	endTurn();
        	
        	gameStatus.ui = UIStatus.NONE;
        }
        else if (gameStatus.ui == UIStatus.MOVED) {
        	gameStatus.ui = UIStatus.NONE;
        	endTurn();
        	gameUi.moveButton.setLabel("Move");
        }
        else if (gameStatus.ui == UIStatus.FIGHTING) {
        	Tile t = tiles.get(mainHero.getTile());
        	if (mainHero.time.left()) {
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
	        		else if(mainHero instanceof Archer && Arrays.asList(t.getAdjacentTiles()).contains(monster.getTile())) {
	        			
	        			Tile monsterTile = tiles.get(monster.getTile());
	        			fight.startAdjacent(monsterTile, mainHero);
	        			
	        			gameStatus.focus = GameStatus.FOCUS_ON_FIGHT;
	        			gameStatus.currentScreen = GameStatus.FIGHT_SCREEN;
	        			break;
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
	    
        else if(gameStatus.ui == UIStatus.PICKING) {
        	//MinuetoImage background = new MinuetoRectangle(12000, 9000, MinuetoColor.BLACK, true);
        	PickupOption p1 = new PickupOption("Pick up choice:");
        	p1.start();
        	//mainHero.replenishWP();
        	//mainHero.pickupFarmer();
        	//mainHero.pickupGold();
        	gameStatus.ui = UIStatus.MOVEBEGIN;
        }
        
        else if(gameStatus.ui == UIStatus.DROPING) {
        	DropOffOption x1 = new DropOffOption("Drop off");
        	x1.start();
        	gameStatus.ui = UIStatus.MOVEBEGIN;
        }
        else if(gameStatus.ui == UIStatus.Trade ) {
        	mainHero.Buy2WPfor2Gold();
        	gameStatus.ui = UIStatus.MOVEBEGIN;
        }
	    
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






