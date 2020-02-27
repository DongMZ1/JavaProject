import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.handlers.*;
import org.minueto.image.*;
import org.minueto.window.MinuetoFullscreen;
import org.minueto.window.MinuetoWindow;

import java.util.ArrayList;

public class GameScreen implements Inputtable{
    private MinuetoWindow screen;
    private MinuetoImageFile defaultBoard = new MinuetoImageFile("images/LegendsOfAndorBoard.jpg");
    private MinuetoImage gameBoard = defaultBoard.scale((double) 1 / 3, (double) 1 / 3);
    private boolean movingCam;
    static ArrayList<Tile> tiles;
    private ArrayList<Monster> monsters;
    private Castle castle = new Castle(1);
    private Hero mainHero;
    private ArrayList<Hero> heroes = new ArrayList<>();
    private MinuetoFont font = new MinuetoFont("Arial",20, true, false);
    private static GameStatus gameStatus;
    private static Camera camera;
    private Coordinate previousMouseCoordinate = new Coordinate(0,0);
    private GameUi gameUi;
    private static final MinuetoImage background = new MinuetoRectangle(12000, 9000, MinuetoColor.BLACK, true);

    public GameScreen(MinuetoWindow screen) throws MinuetoFileException {
        this.screen = screen;
        this.screen.setVisible(true);
        camera = Camera.getInstance();
        this.movingCam = false;
        tiles = new TileInitialiser().initialiseTiles(screen);
        tiles = new TileInitialiser().initialiseCoords(tiles);
        monsters = MonsterInitializer.initializeMonsters();
        mainHero = new Warrior(new MinuetoImageFile("images/Heroes/WarriorMaleIcon.png").scale(Constants.HERO_SCALE, Constants.HERO_SCALE), 0);
        mainHero.time = new Time(new MinuetoImageFile("images/tokenWarrior.png"),this.screen);
        heroes.add(mainHero);
        tiles.get(mainHero.tile).addTileEntity(mainHero);
        gameStatus = GameStatus.getInstance();
        gameUi = GameUi.getInstance();
    }

    public void draw() {
        this.screen.draw(background, 0, 0);
        this.screen.draw(gameBoard, camera.currentPos.getX(), camera.currentPos.getY());
        for(Tile tile : tiles)
            tile.draw();
        gameUi.draw();
        mainHero.time.draw();
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
        for(int i = 0; i < tiles.size(); i++) {
            if(Math.abs(clickedCoord.getX() - tiles.get(i).getMoveX() + clickedCoord.getY() - tiles.get(i).getMoveY()) < closestDist) {
                closestDist = Math.abs(clickedCoord.getX() - tiles.get(i).getMoveX() + clickedCoord.getY() - tiles.get(i).getMoveY());
                closestNum = i;
            }
        }
        return closestNum;
    }
    
    public void newDay() {
    	ArrayList<Monster> toRemove = new ArrayList<>(); //must use because of Enhanced for loop
    	for (Monster monster : monsters) {
    		if(monster.advance()) {
    			tiles.get(monster.tile).removeTileEntity(monster);
    			toRemove.add(monster);
    		}
    	}
    	
    	//These are monsters that reached castle
    	for (Monster rMonster : toRemove)
    	{
    		monsters.remove(rMonster);
    		castle.damage(rMonster);
    		
    	}
    	for (Hero hero : heroes)
    	{
    		hero.time.reset();
    	}
    }
    
    

    public void handleKeyPress(int key) {

    }
    public void handleKeyRelease(int key) {

    }
    public void handleKeyType(char c) {}
    public void handleMousePress(int x, int y, int button) {
    	Coordinate coords = camera.getPosOnBoard(x, y);
    	System.out.println("X: " + coords.getX());
    	System.out.println("Y: " + coords.getY());
    	
        if(y > gameStatus.screenHeight - gameUi.uiHeight)
            gameUi.handleMousePress(x, y, button);
        else if(button == MinuetoMouse.MOUSE_BUTTON_RIGHT) this.movingCam = true;
        else if(button == MinuetoMouse.MOUSE_BUTTON_LEFT && gameStatus.movingCharacter) {
            moveTileEntity(mainHero, mainHero.getTile(), findTileClicked(camera.getPosOnBoard(x, y)));
            mainHero.time.advance();
            gameUi.moveButton.setLabel("End Move");
        }
    }
    public void handleMouseRelease(int x, int y, int button) {
        if(button == MinuetoMouse.MOUSE_BUTTON_RIGHT) this.movingCam = false;
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
