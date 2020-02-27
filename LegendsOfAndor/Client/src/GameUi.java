import org.minueto.MinuetoFileException;



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
    private GameUi() throws MinuetoFileException {
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

    public static GameUi getInstance() throws MinuetoFileException {
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
            textBox.handleMousePress(x, y, button);
        }
        else {
            if(moveButton.isClicked(x, y) && moveButton.isClickable()) {
                if (!(gameStatus.ui == UIStatus.MOVING)) {
                    gameStatus.ui = UIStatus.MOVING;
                    moveButton.setLabel("Cancel Move");
                }
                else {
                    gameStatus.ui = UIStatus.NONE;
                    moveButton.setLabel("Move");
                }
            }
            else if(fightButton.isClickable() && fightButton.isClicked(x, y) && gameStatus.ui == UIStatus.NONE) {
            	gameStatus.ui = UIStatus.FIGHTING;
            }
            else if(pickupButton.isClickable() && pickupButton.isClicked(x, y))
                ;//TODO pickupButton
            else if(dropButton.isClickable() && dropButton.isClicked(x, y))
                ; //TODO dropButton
            else if(tradeButton.isClickable() && tradeButton.isClicked(x, y))
                ; //TODO tradeButton
            else if(waitButton.isClickable() && waitButton.isClicked(x, y) && gameStatus.ui == UIStatus.NONE) {
            	gameStatus.ui = UIStatus.WAITING;
            }
                
        }
    }
    public void handleMouseRelease(int x, int y, int button) { }
    public void handleMouseMove(int x, int y) { }
    public void handleMouseWheelRotate(int rotation) { }
}
