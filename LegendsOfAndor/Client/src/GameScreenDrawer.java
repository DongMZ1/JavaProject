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
	private int PrinceCost = 0;

	private MinuetoImageFile defaultBoard = new MinuetoImageFile("images/LegendsOfAndorBoard.jpg");
	private MinuetoImageFile timeImage = new MinuetoImageFile("images/tokenWP.png");
	private MinuetoImageFile narratorImage = new MinuetoImageFile("images/tokenWP.png");
	private MinuetoImage gameBoard = defaultBoard.scale((double) 1 / 3, (double) 1 / 3);
	private TextBox textBox = TextBox.getInstance();
	private MinuetoFont font = new MinuetoFont("Arial",20, true, false);
	public GameUi gameUi;
	private static final MinuetoImage background = new MinuetoRectangle(9000, 9000, MinuetoColor.BLACK, true);
	public GameScreen gameScreen;
	private static GameScreenDrawer gameScreenDrawer;
	public PlayerBoard playerBoard;
	private static Camera camera;
	private boolean movingCam;
	private Coordinate previousMouseCoordinate = new Coordinate(0,0);
	int toMove;
	private CastleDrawer castleDrawer;
	public FightDrawer fightDrawer;
	public TileDrawer tileDrawer;
	public CollaborativeDecisionDrawer collabDrawer;
	private GameScreenDrawer() throws IOException {
		gameScreen = GameScreen.getInstance();
		gameUi = GameUi.getInstance();
		camera = Camera.getInstance();
		this.movingCam = false;
		playerBoard = PlayerBoard.getInstance(Client.mainHero);
		tileDrawer = TileDrawer.getInstance();
		fightDrawer = new FightDrawer(new Fight(gameScreen.tm));
		castleDrawer = CastleDrawer.getInstance();
		collabDrawer = CollaborativeDecisionDrawer.getInstance();
	}

	public void updateGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		this.gameScreen.gameScreen = gameScreen;
		Tile.TILES = gameScreen.tiles;
		this.gameUi.gameScreen = gameScreen;
	}
	public void updateGameStatus(GameStatus gameStatus) {
		this.gameUi.gameStatus = gameStatus;
		this.gameScreen.gameStatus = gameStatus;
		this.gameScreen.cd.gameStatus = gameStatus;
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
		//gameScreen.tm.draw();
		if (gameScreen.gameStatus.currentScreen == gameScreen.gameStatus.COLLABORATIVE_SCREEN) {
			collabDrawer.decisionLoop();
			gameScreen.cd.decisionLoop();
		}
		castleDrawer.draw(gameScreen.castle);
		Coordinate timeCoordinate = this.camera.getPosOnScreen(Client.getMainHero().time.x, Client.getMainHero().time.y);
		Client.screen.draw(timeImage, timeCoordinate.getX(), timeCoordinate.getY());
		//draw narrator
		Coordinate narratorCoordinate = this.camera.getPosOnScreen(gameScreen.narrator.x, gameScreen.narrator.y);
		Client.screen.draw(narratorImage, narratorCoordinate.getX(), narratorCoordinate.getY());
		
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

	public void moveTileEntity(TileEntity tileEntity, int currentTile, int destination) {
		assert(gameScreen.tiles.get(currentTile).containsTileEntity(tileEntity));
		gameScreen.tiles.get(currentTile).removeTileEntity(tileEntity);
		gameScreen.tiles.get(destination).addTileEntity(tileEntity);
		tileEntity.setTile(destination);
		//InputThread.updateVariable();
	}

	public void moveHero(int currentTile, int destination) {
		for(int i = 0; i < gameScreen.tiles.get(currentTile).tileEntities.size(); i++) {
			if(gameScreen.tiles.get(currentTile).tileEntities.get(i).getClass().toString().equals(Client.getMainHero().getClass().toString()))
				gameScreen.tiles.get(currentTile).tileEntities.remove(i);
		}
		Client.getMainHero().setTile(destination);
		gameScreen.tiles.get(destination).tileEntities.add(Client.getMainHero());
		//InputThread.updateVariable();
	}

	public void handleKeyPress(int key) {
	}
	public void handleKeyRelease(int key) {

	}
	//IAN testing shit
	public void handleKeyType(char c) {
		if (c == 'd') {
			try {
				gameScreen.newDay();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			InputThread.updateVariable();
		}
		else if (c == 'a')
		{
			System.out.println(gameScreen.currentHero);
		}
		else if(c == 'm') {
			playerBoard.update(Client.getMainHero());
			playerBoard.toggleFlag();
		}
		else if(c == 'g') {
			//move 4 tile will cost one time
			if(this.PrinceCost == 4) {
				Client.getMainHero().time.advance();
				this.PrinceCost = 0;
				InputThread.updateVariable();
			}
			if(gameScreen.gameStatus.ui == UIStatus.MovePrince && Client.getMainHero().canMakeMove() && this.gameScreen.hasPrince) {
				//when starting move prince, it cost 1 time;
				int[] adjacentTile = Tile.get(GameScreen.gameScreen.princeThorald.tile).getAdjacentTiles();
				for(int i = 0; i < adjacentTile.length; i++) {
					if(adjacentTile[i] == toMove) {
						Tile.get(GameScreen.gameScreen.princeThorald.tile).removeTileEntity(GameScreen.gameScreen.princeThorald);
						Tile.get(toMove).addTileEntity(GameScreen.gameScreen.princeThorald);
						GameScreen.gameScreen.princeThorald.tile = toMove;
						this.PrinceCost ++;
						InputThread.updateVariable();
						System.out.println(this.PrinceCost);
					}
				}
			}
			toMove = 0;
		}
		else if (c == ' ') {
			System.out.println(toMove);
			System.out.println(gameScreen.tm.getHero().getClass());
			if (Client.getMainHero().canMakeMove() && gameScreen.gameStatus.ui == UIStatus.MOVING && gameScreen.mainIsCurrent()){
				if (toMove >= 0 && toMove <= 76) {
						if (isValidMove(Client.getMainHero().getTile(),toMove)) {
							moveHero(Client.getMainHero().getTile(),toMove);
							Client.getMainHero().time.advance();
							InputThread.updateVariable();
						}
					}
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
		if(y > gameScreen.gameStatus.screenHeight - gameUi.uiHeight && x < 650)
			gameUi.handleMousePress(x, y, button);
		else if(button == MinuetoMouse.MOUSE_BUTTON_RIGHT)
			this.movingCam = true;
		else if(button == MinuetoMouse.MOUSE_BUTTON_LEFT && gameScreen.mainIsCurrent()) {
			if (Client.getMainHero().canMakeMove()) {
				if (Client.gameStatus.ui == UIStatus.MOVEBEGIN) {
					moveTileEntity(Client.getMainHero(), Client.getMainHero().getTile(), gameScreen.findTileClicked(camera.getPosOnBoard(x, y)));
					Client.getMainHero().time.advance();
					Client.gameStatus.ui = UIStatus.MOVING;
					gameUi.moveButton.setLabel("End Move");
				} else if (Client.gameStatus.ui == UIStatus.MOVING) {
					moveTileEntity(Client.getMainHero(), Client.getMainHero().getTile(), gameScreen.findTileClicked(camera.getPosOnBoard(x, y)));
					Client.getMainHero().time.advance();
				}
			} else
				gameUi.moveButton.setLabel("No Time");
		}
	}

	public void handleMouseRelease(int x, int y, int button) {
		if(button == MinuetoMouse.MOUSE_BUTTON_RIGHT) this.movingCam = false;
		if (gameScreen.gameStatus.ui == UIStatus.WAITING) {
			if(Client.getMainHero().time.getTime() <= 10) {Client.getMainHero().time.advance();}

			gameScreen.tm.endTurn();
			gameScreen.currentHero = gameScreen.tm.getHero();
			gameScreen.gameStatus.ui = UIStatus.NONE;
			InputThread.updateVariable();
		}
		else if (gameScreen.gameStatus.ui == UIStatus.MOVED) {
			gameScreen.gameStatus.ui = UIStatus.NONE;
			gameScreen.tm.endTurn();
			gameScreen.currentHero = gameScreen.tm.getHero();
			gameUi.moveButton.setLabel("Move");
			InputThread.updateVariable();
		}
		else if (gameScreen.gameStatus.ui == UIStatus.FIGHTING) {
			Tile t = gameScreen.tiles.get(Client.getMainHero().getTile());
			if (Client.getMainHero().time.getTime() <= 10) {
				monsterLoop:
				for (Monster monster : gameScreen.monsters)
				{
					//normal fight
					if(t.containsTileEntity(monster)) {
						fightDrawer.fight.start(t, gameScreen.tm.getHero());
						gameScreen.gameStatus.focus = GameStatus.FOCUS_ON_FIGHT;
						gameScreen.gameStatus.currentScreen = GameStatus.FIGHT_SCREEN;
						break;
					}

					//fight monter on adjacent tile
					else if(Client.getMainHero() instanceof Archer) {
						int[] adjacentTiles = t.getAdjacentTiles();
//	        			System.out.println(t);
						for (int i =0; i < adjacentTiles.length; i++) {
//	        				System.out.println(adjacentTiles[i]);
							Tile adjacentTile = Tile.get(adjacentTiles[i]);
							if (adjacentTile.containsTileEntity(monster)) {
								fightDrawer.fight.startAdjacent(adjacentTile, Client.getMainHero());
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
