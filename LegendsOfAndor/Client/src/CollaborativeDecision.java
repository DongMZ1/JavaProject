import java.util.ArrayList;

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
	TurnManager tm;
	ArrayList<Item> items;
	ArrayList<Button> itemButtons;
	
	public CollaborativeDecision(DecisionType d, MinuetoWindow screen, TurnManager tm){
		toDecide = d;		
		try {
		 gameStatus = GameStatus.getInstance();
		 inputHandler = InputHandler.getInputHandler();
		}
		catch (Exception e) {}
		
		inputHandler.addInput(this);
		
		gameStatus.focus = GameStatus.FOCUS_ON_COLLABORATIVE;
		
		
		this.screen = screen;
		background = new MinuetoRectangle(gameStatus.screenWidth, 400, MinuetoColor.GREEN, true);
		
		items = new ArrayList<>();
		itemButtons = new ArrayList<>();
		if (toDecide == DecisionType.START) {
			int offset = 1;
			try {
			for (int i = 0; i < 5; i++) {
				items.add(new Gold(-1));
				itemButtons.add(new Button(new Coordinate(100*offset,150), 50, 89, "Claim", true));
				offset++;
			}
			}
			catch (Exception e) {}
		}
		
		
		gameStatus.currentScreen = GameStatus.COLLABORATIVE_SCREEN;
	}
	
	public void decisionLoop() {
		if (toDecide == DecisionType.NONE) {
		gameStatus.focus = GameStatus.FOCUS_ON_GAMESCREEN;
		inputHandler.removeInput(this);
		gameStatus.currentScreen = gameStatus.GAME_SCREEN;
		}
		
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			Button button = itemButtons.get(i);
			
			button.draw();
			screen.draw(item.getImage(), button.getCoordinate().getX(), button.getCoordinate().getY());
			
		}
	}
	
	public void draw() {
		screen.draw(background, 0, 0);
		decisionLoop();
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
		System.out.println("HERE");
	}

	@Override
	public void handleMousePress(int x, int y, int button) {
		// TODO Auto-generated method stub
		System.out.println("HERE");
		for (Button b : itemButtons) {
			if (b.isClicked(x, y)) {
				System.out.println(b);
			}
		}
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
