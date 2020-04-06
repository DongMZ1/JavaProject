import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.handlers.MinuetoMouse;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;
import org.minueto.image.MinuetoRectangle;

import java.io.IOException;

public class GameScreenDrawer implements Inputtable{

	final int NUMBERS[] = {0,1,2,3,4,5,6,7,8,9};
	final int ASCIINUMBERS[] = {48,49,50,51,52,53,54,55,56,57};

	private MinuetoImageFile defaultBoard = new MinuetoImageFile("images/LegendsOfAndorBoard.jpg");
	private MinuetoImage gameBoard = defaultBoard.scale((double) 1 / 3, (double) 1 / 3);
	private TextBox textBox = TextBox.getInstance();
	private MinuetoFont font = new MinuetoFont("Arial",20, true, false);
	private GameUi gameUi;
	private static final MinuetoImage background = new MinuetoRectangle(12000, 9000, MinuetoColor.BLACK, true);
	public GameScreen gameScreen;
	private InputHandler inputHandler;
	private static GameScreenDrawer gameScreenDrawer;
	private PlayerBoard playerBoard;
	private static Camera camera;
	private boolean movingCam;
	private Coordinate previousMouseCoordinate = new Coordinate(0,0);
	int toMove;

	public FightDrawer fightDrawer;
	public TileDrawer tileDrawer;

	private GameScreenDrawer() throws IOException {
		gameScreen = new GameScreen();
		gameUi = GameUi.getInstance();
		camera = Camera.getInstance();
		this.movingCam = false;
		playerBoard = PlayerBoard.getInstance(Client.mainHero);
		tileDrawer = TileDrawer.getInstance();
		fightDrawer = new FightDrawer(new Fight(gameScreen.tm));
	}

	public static GameScreenDrawer getInstance() throws IOException {
		if(gameScreenDrawer == null)
			gameScreenDrawer = new GameScreenDrawer();
		return gameScreenDrawer;
	}

	public void draw() {
		Client.screen.draw(background, 0, 0);
		Client.screen.draw(gameBoard, camera.currentPos.getX(), camera.currentPos.getY());
		for(Tile tile : gameScreen.tiles)
			tileDrawer.draw(tile);
		gameUi.draw();
		playerBoard.draw();
		gameScreen.tm.draw();
		if (gameScreen.gameStatus.currentScreen == gameScreen.gameStatus.COLLABORATIVE_SCREEN) {
			gameScreen.cd.draw();
		}
		gameScreen.castle.draw();
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

	public void moveTileEntity(TileEntity tileEntity, int currentTile, int destination){
		assert(gameScreen.tiles.get(currentTile).containsTileEntity(tileEntity));
		gameScreen.tiles.get(currentTile).removeTileEntity(tileEntity);
		gameScreen.tiles.get(destination).addTileEntity(tileEntity);
		tileEntity.setTile(destination);
	}

	public void handleKeyPress(int key) {
	}
	public void handleKeyRelease(int key) {

	}
	//IAN testing shit
	public void handleKeyType(char c) {
		if (c == 'd') {
			gameScreen.newDay();
		}
		else if (c == 'a')
		{
			Client.mainHero = gameScreen.currentHero;
			System.out.println(Client.mainHero);
		}
		else if(c == 'm') {
			playerBoard.update(Client.mainHero);
			playerBoard.toggleFlag();
		}
		else if (c == ' ') {
			if (Client.mainHero.time.left()){
				if (toMove >= 0 && toMove <= 76) {
					if(gameScreen.gameStatus.ui == UIStatus.MOVEBEGIN) {
						if (isValidMove(Client.mainHero.getTile(),toMove)) {
							moveTileEntity(Client.mainHero, Client.mainHero.getTile(),toMove);
							Client.mainHero.time.advance();
							gameScreen.gameStatus.ui = UIStatus.MOVING;
							gameUi.moveButton.setLabel("End Move");
						}

					}
					else if(gameScreen.gameStatus.ui == UIStatus.MOVING) {
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

		if(y > gameScreen.gameStatus.screenHeight - gameUi.uiHeight && x < 650)
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
		if (gameScreen.gameStatus.ui == UIStatus.WAITING) {
			if(Client.mainHero.time.left()) {gameScreen.currentHero.time.advance();}

			gameScreen.endTurn();

			gameScreen.gameStatus.ui = UIStatus.NONE;
		}
		else if (gameScreen.gameStatus.ui == UIStatus.MOVED) {
			gameScreen.gameStatus.ui = UIStatus.NONE;
			gameScreen.endTurn();
			gameUi.moveButton.setLabel("Move");
		}
		else if (gameScreen.gameStatus.ui == UIStatus.FIGHTING) {
			Tile t = gameScreen.tiles.get(Client.mainHero.getTile());
			if (Client.mainHero.time.left()) {
				monsterLoop:
				for (Monster monster : gameScreen.monsters)
				{
					//normal fight
					if(t.containsTileEntity(monster)) {
						fightDrawer.fight.start(t);
						gameScreen.gameStatus.focus = GameStatus.FOCUS_ON_FIGHT;
						gameScreen.gameStatus.currentScreen = GameStatus.FIGHT_SCREEN;
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
								fightDrawer.fight.startAdjacent(adjacentTile, Client.mainHero);
								gameScreen.gameStatus.focus = GameStatus.FOCUS_ON_FIGHT;
								gameScreen.gameStatus.currentScreen = GameStatus.FIGHT_SCREEN;
								break monsterLoop;

							}
						}
					}
				}
			}
			else {
				System.out.println("NO TIME");
			}
			if (!fightDrawer.fight.isHappening) {
				System.out.println("UNABLE TO FIGHT");
			}
			gameScreen.gameStatus.ui = UIStatus.NONE;
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