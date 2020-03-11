import org.minueto.MinuetoFileException;

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

    public void handleKeyPress(int key) { }
    public void handleKeyRelease(int key) { }
    public void handleKeyType(char c) { }
    public void handleMousePress(int x, int y, int button) {
        if(textBox.inputClicked(x, y) || textBox.outputClicked(x, y)) {
            gameStatus.focus = gameStatus.FOCUS_ON_TEXTBOX;
            System.out.print("JFJFJF");
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
