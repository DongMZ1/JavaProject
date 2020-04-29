import org.minueto.MinuetoColor;
import org.minueto.MinuetoEventQueue;
import org.minueto.MinuetoFileException;
import org.minueto.handlers.MinuetoMouse;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;
import org.minueto.image.MinuetoRectangle;
import org.minueto.window.MinuetoFrame;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;
import java.io.Serializable;


public class GameUi implements Inputtable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5637901486561880437L;

	private static GameUi gameUi;

	MinuetoImage timeTokenImage;

    public Button moveButton;
    public Button fightButton;
    public Button pickupButton;
    public Button dropButton;
    public Button tradeButton;
    public Button waitButton;
    public Button actionButton;
    public Button informationButton;
    public Button currentLegendCard;
    public Button MovePrince;
    public GameStatus gameStatus;
    public GameScreen gameScreen;
    private TextBox textBox;

    private int turnButtonWidth = 200;
    private int turnButtonHeight = 36;
    public int uiHeight = 360;
    public boolean moveInterrupted = false;
   


    private GameUi() throws IOException {
        textBox = TextBox.getInstance();
        gameStatus = GameStatus.getInstance();
        gameScreen = GameScreen.getInstance();
        informationButton = new Button(new Coordinate(textBox.getWidth(), gameStatus.screenHeight - turnButtonHeight),
                turnButtonHeight, turnButtonWidth, "Information", true);
        actionButton = new Button(new Coordinate(textBox.getWidth(), gameStatus.screenHeight - 2*turnButtonHeight),
                turnButtonHeight, turnButtonWidth, "Action", true);
        waitButton = new Button(new Coordinate(textBox.getWidth(), gameStatus.screenHeight- 3*turnButtonHeight),
                turnButtonHeight, turnButtonWidth, "Wait", true);
        tradeButton = new Button(new Coordinate(textBox.getWidth(), gameStatus.screenHeight - 4*turnButtonHeight),
                turnButtonHeight, turnButtonWidth, "Trade", true);
        dropButton = new Button(new Coordinate(textBox.getWidth(), gameStatus.screenHeight - 5*turnButtonHeight),
                turnButtonHeight, turnButtonWidth, "Drop", true);
        pickupButton = new Button(new Coordinate(textBox.getWidth(), gameStatus.screenHeight - 6*turnButtonHeight),
                turnButtonHeight, turnButtonWidth, "Pickup", true);
        fightButton = new Button(new Coordinate(textBox.getWidth(), gameStatus.screenHeight - 7*turnButtonHeight),
                turnButtonHeight, turnButtonWidth, "Fight", true);
        moveButton = new Button(new Coordinate(textBox.getWidth(), gameStatus.screenHeight - 8*turnButtonHeight),
                turnButtonHeight, turnButtonWidth, "Move", true);
        currentLegendCard = new Button(new Coordinate(textBox.getWidth(), gameStatus.screenHeight - 9*turnButtonHeight),
                turnButtonHeight, turnButtonWidth, "LegendCard", true);
        MovePrince = new Button(new Coordinate(textBox.getWidth(), gameStatus.screenHeight - 10*turnButtonHeight),
                turnButtonHeight, turnButtonWidth, "Move Prince", true);
 //       cancel = new Button(new Coordinate(Pickupscreen.getWidth(), Pickupscreen.getHeight() - turnButtonHeight),
  //              turnButtonHeight, turnButtonWidth, "cancel", true);
//        pickupFarmer =  new Button(new Coordinate(Pickupscreen.getWidth(), Pickupscreen.getHeight() - 2*turnButtonHeight),
//                turnButtonHeight, turnButtonWidth, "pickupFarmer", true);
//        pickupWP = new Button(new Coordinate(Pickupscreen.getWidth(), Pickupscreen.getHeight() - 3*turnButtonHeight),
//                turnButtonHeight, turnButtonWidth, "pickup Will Power", true);
//        pickupGold = new Button(new Coordinate(Pickupscreen.getWidth(), Pickupscreen.getHeight() - 4*turnButtonHeight),
//                turnButtonHeight, turnButtonWidth, "pickup Gold", true);

		timeTokenImage = new MinuetoImageFile("images/tokenWarrior.png");
    }

    public static GameUi getInstance() throws IOException {
        if(gameUi == null)
            gameUi = new GameUi();
        return gameUi;
    }
    
   

    public void draw() {
        moveButton.draw();
        fightButton.draw();
        pickupButton.draw();
        dropButton.draw();
        tradeButton.draw();
        waitButton.draw();
        actionButton.draw();
        textBox.draw();
        informationButton.draw();
        currentLegendCard.draw();
        MovePrince.draw();
        //Client.screen.draw(timeTokenImage, Client.getMainHero().time.x, Client.getMainHero().time.y);
    }

   // public void drawPickup() {
   // 	MinuetoImage background = new MinuetoRectangle(12000, 9000, MinuetoColor.BLACK, true);
//        Pickupscreen.registerMouseHandler(cancel, );
//    	Pickupscreen.registerMouseHandler(pickupFarmer, queue);
//    	Pickupscreen.registerMouseHandler(pickupWP, queue);
//    	Pickupscreen.registerMouseHandler(pickupGold, );
//    	cancel.draw();
//    	pickupFarmer.draw();
//    	pickupWP.draw();
//    	pickupGold.draw();
   // }
    public void handleKeyPress(int key) { }
    public void handleKeyRelease(int key) { }
    public void handleKeyType(char c) { }
    public void handleMousePress(int x, int y, int button) {
    	if (button == MinuetoMouse.MOUSE_BUTTON_LEFT) {
	        if(textBox.inputClicked(x, y) || textBox.outputClicked(x, y)) {
	        	gameStatus.lastFocused=gameStatus.FOCUS_ON_GAMESCREEN;
	            gameStatus.focus = gameStatus.FOCUS_ON_TEXTBOX;
	        }
	        else if(gameScreen.mainIsCurrent()) {
	            if(moveButton.isClicked(x, y) && moveButton.isClickable()) {
	            	if(gameStatus.ui != UIStatus.MOVING && !moveInterrupted) {
		            	gameStatus.ui = UIStatus.MOVING;
		            	moveButton.setLabel("Stop Hero");
		            	}else {
		            		moveButton.setLabel("Move Hero");
			            	gameStatus.ui = UIStatus.MOVED;
			            	moveInterrupted = false;
		            	}
	                
	            }
	            else{
	            	moveInterrupted = true;
	            }
	            
	            if(fightButton.isClickable() && fightButton.isClicked(x, y) && verify()) {
	            	gameStatus.ui = UIStatus.FIGHTING;
	            }
	            else if(MovePrince.isClickable() && MovePrince.isClicked(x, y)) {
	            	if(gameStatus.ui != UIStatus.MovePrince) {
	            	gameStatus.ui = UIStatus.MovePrince;
	            	MovePrince.setLabel("Stop Move");
	            	}else {
		            	MovePrince.setLabel("Move Prince");
		            	gameStatus.ui = UIStatus.NONE;
	            	}
	      
	            }
	            else if(pickupButton.isClickable() && pickupButton.isClicked(x, y)) {
	            	PickupOption p1 = new PickupOption("Pick up choice:");
	            	p1.start();
	            	//gameStatus.ui = UIStatus.PICKING;
	            	//this.drawPickup();
	            }
	            else if(dropButton.isClickable() && dropButton.isClicked(x, y)) {
	            	DropOffOption x1 = new DropOffOption("Drop off");
	            	x1.start();
	            }
	            	
	            else if(tradeButton.isClickable() && tradeButton.isClicked(x, y)) {
	            	
                    TradeWithMerChantHandler h1 = new TradeWithMerChantHandler();
	            }
	            else if(actionButton.isClickable() && actionButton.isClicked(x, y)) {
	            	ActionHander u1 = new ActionHander();
	            }
	            else if(informationButton.isClickable() && informationButton.isClicked(x, y)) {
	            	Cards.showHeroInformationBoard();
	            }
	                //TODO tradeButton
	            else if(waitButton.isClickable() && waitButton.isClicked(x, y) && verify()) {
	            	gameStatus.ui = UIStatus.WAITING;
	            }
	            else if(currentLegendCard.isClickable() && currentLegendCard.isClicked(x, y)) {
	            	System.out.println("The Legend2Cardindex: " + Client.gameStatus.legend2CardIndex);
	            		Cards.showLegend2Card(Client.gameStatus.legend2CardIndex);
	            }
	            
	        }
        }
    }
    private boolean verify() {
    	if (gameStatus.ui == UIStatus.NONE && gameScreen.tm.getHero() == Client.mainHero) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    public void handleMouseRelease(int x, int y, int button) { }
    public void handleMouseMove(int x, int y) { }
    public void handleMouseWheelRotate(int rotation) { }
}
