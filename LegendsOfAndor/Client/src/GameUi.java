import org.minueto.MinuetoColor;
import org.minueto.MinuetoEventQueue;
import org.minueto.MinuetoFileException;
import org.minueto.handlers.MinuetoMouse;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoRectangle;
import org.minueto.window.MinuetoFrame;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;


public class GameUi implements Inputtable {
    private static GameUi gameUi;

    public Button moveButton;
    public Button fightButton;
    public Button pickupButton;
    public Button dropButton;
    public Button tradeButton;
    public Button waitButton;

    private GameStatus gameStatus;
    private TextBox textBox;
    private PlayerBoard playerBoard;
    private int turnButtonWidth = 200;
    private int turnButtonHeight = 50;
    public int uiHeight = 300;
    
    //private MinuetoWindow Pickupscreen = new MinuetoFrame(1000, 1000, true);
    public Button cancel;
    public Button pickupFarmer;
    public Button pickupWP;
    public Button pickupGold;
    private GameUi() throws IOException {
        textBox = TextBox.getInstance();
        gameStatus = GameStatus.getInstance();
        playerBoard = PlayerBoard.getInstance();
        waitButton = new Button(new Coordinate(textBox.getWidth(), gameStatus.screenHeight- turnButtonHeight),
                turnButtonHeight, turnButtonWidth, "Wait", true);
        tradeButton = new Button(new Coordinate(textBox.getWidth(), gameStatus.screenHeight - 2*turnButtonHeight),
                turnButtonHeight, turnButtonWidth, "Trade", true);
        dropButton = new Button(new Coordinate(textBox.getWidth(), gameStatus.screenHeight - 3*turnButtonHeight),
                turnButtonHeight, turnButtonWidth, "Drop", true);
        pickupButton = new Button(new Coordinate(textBox.getWidth(), gameStatus.screenHeight - 4*turnButtonHeight),
                turnButtonHeight, turnButtonWidth, "Pickup", true);
        fightButton = new Button(new Coordinate(textBox.getWidth(), gameStatus.screenHeight - 5*turnButtonHeight),
                turnButtonHeight, turnButtonWidth, "Fight", true);
        moveButton = new Button(new Coordinate(textBox.getWidth(), gameStatus.screenHeight - 6*turnButtonHeight),
                turnButtonHeight, turnButtonWidth, "Move", true);
        
 //       cancel = new Button(new Coordinate(Pickupscreen.getWidth(), Pickupscreen.getHeight() - turnButtonHeight),
  //              turnButtonHeight, turnButtonWidth, "cancel", true);
//        pickupFarmer =  new Button(new Coordinate(Pickupscreen.getWidth(), Pickupscreen.getHeight() - 2*turnButtonHeight),
//                turnButtonHeight, turnButtonWidth, "pickupFarmer", true);
//        pickupWP = new Button(new Coordinate(Pickupscreen.getWidth(), Pickupscreen.getHeight() - 3*turnButtonHeight),
//                turnButtonHeight, turnButtonWidth, "pickup Will Power", true);
//        pickupGold = new Button(new Coordinate(Pickupscreen.getWidth(), Pickupscreen.getHeight() - 4*turnButtonHeight),
//                turnButtonHeight, turnButtonWidth, "pickup Gold", true);
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
        playerBoard.draw();
        textBox.draw();
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
	        else {
	            if(moveButton.isClicked(x, y) && moveButton.isClickable() && GameScreen.currentHero == GameScreen.mainHero) {
	                if (gameStatus.ui == UIStatus.NONE) {
	                    gameStatus.ui = UIStatus.MOVEBEGIN;
	                    moveButton.setLabel("Cancel Move");
	                }
	                else if ((gameStatus.ui == UIStatus.MOVEBEGIN)) {
	                    gameStatus.ui = UIStatus.NONE;
	                    gameUi.moveButton.setLabel("Move");
	                    
	                }
	                else if ((gameStatus.ui == UIStatus.MOVING)) {
	                    gameStatus.ui = UIStatus.MOVED;
	                    
	                }
	                
	            }
	            else if(fightButton.isClickable() && fightButton.isClicked(x, y) && verify()) {
	            	gameStatus.ui = UIStatus.FIGHTING;
	            }
	            else if(pickupButton.isClickable() && pickupButton.isClicked(x, y)) {
	            	gameStatus.ui = UIStatus.PICKING;
	            	//this.drawPickup();
	            }
	            else if(dropButton.isClickable() && dropButton.isClicked(x, y)) {
	            	gameStatus.ui = UIStatus.DROPING;
	            }
	            	
	            else if(tradeButton.isClickable() && tradeButton.isClicked(x, y))
	                ; //TODO tradeButton
	            else if(waitButton.isClickable() && waitButton.isClicked(x, y) && verify()) {
	            	gameStatus.ui = UIStatus.WAITING;
	            }
	        }
        }
    }
    private boolean verify() {
    	if (gameStatus.ui == UIStatus.NONE && GameScreen.currentHero == GameScreen.mainHero) {
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
