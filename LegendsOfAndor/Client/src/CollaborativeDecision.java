import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoRectangle;
import org.minueto.window.MinuetoWindow;

public class CollaborativeDecision implements Inputtable {
	private MinuetoWindow screen;
	DecisionType toDecide = DecisionType.NONE;
	GameStatus gameStatus;
	InputHandler inputHandler;
	MinuetoImage background;
	public CollaborativeDecision(DecisionType d, MinuetoWindow screen){
		toDecide = d;
		
		try {
		 gameStatus = GameStatus.getInstance();
		 inputHandler = InputHandler.getInputHandler();
		}
		catch (Exception e) {}
		gameStatus.focus = GameStatus.FOCUS_ON_COLLABORATIVE;
		System.out.println("JFJF");
		
		inputHandler.addInput(this);
		this.screen = screen;
		background = new MinuetoRectangle(gameStatus.screenWidth, 400, MinuetoColor.GREEN, true);
		
		decisionLoop();
		gameStatus.currentScreen = GameStatus.COLLABORATIVE_SCREEN;
	}
	
	public void decisionLoop() {
		if (toDecide == DecisionType.NONE) {
		gameStatus.focus = GameStatus.FOCUS_ON_GAMESCREEN;
		inputHandler.removeInput(this);
		gameStatus.currentScreen = gameStatus.GAME_SCREEN;
		}
	}
	
	public void draw() {
		screen.draw(background, 0, 0);
		
	}

	@Override
	public void handleKeyPress(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKeyRelease(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKeyType(char c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleMousePress(int x, int y, int button) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleMouseRelease(int x, int y, int button) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleMouseMove(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleMouseWheelRotate(int rotation) {
		// TODO Auto-generated method stub
		
	}
}
