import java.io.Serializable;
import java.util.ArrayList;

import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoRectangle;
import org.minueto.window.MinuetoWindow;

public class CollaborativeDecision implements Inputtable, Serializable {
	private MinuetoWindow screen;
	DecisionType toDecide;
	GameStatus gameStatus;
	InputHandler inputHandler;
	MinuetoImage background;
	TurnManager tm;
	ArrayList<Tuple<Item,Hero>> items;
	ArrayList<Button> itemButtons;
	Button okButton;
	TextBox textBox;
	private MinuetoImage archerImage;
	private MinuetoImage warriorImage;
	private MinuetoImage dwarfImage;
	private MinuetoImage mageImage;
	
	
	public CollaborativeDecision(DecisionType d, MinuetoWindow screen, TurnManager tm){
		toDecide = d;		
		try {
		 gameStatus = GameStatus.getInstance();
		 inputHandler = InputHandler.getInputHandler();
		 textBox = TextBox.getInstance();
		}
		catch (Exception e) {}
		
		
		
		gameStatus.focus = GameStatus.FOCUS_ON_COLLABORATIVE;
		
		
		this.screen = screen;
		this.tm = tm;
		background = new MinuetoRectangle(gameStatus.screenWidth, 400, MinuetoColor.GREEN, true);
		
		items = new ArrayList<>();
		itemButtons = new ArrayList<>();
		if (toDecide == DecisionType.START) {
			int offset = 1;
			try {
			for (int i = 0; i < 5; i++) {
				items.add(new Tuple(new Gold(-1),null));
				itemButtons.add(new Button(new Coordinate(100*offset,150), 50, 89, "Claim", true));
				offset++;
			}
			for (int i = 0; i < 2; i++) {
				items.add(new Tuple(new Wineskin(-1),null));
				itemButtons.add(new Button(new Coordinate(100*offset,150), 50, 89, "Claim", true));
				offset++;
			}
			okButton = new Button(new Coordinate(100*offset + 150, 150),50,89,"READY",true);
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
		for (Tuple<Item,Hero> combo : items) {
			combo.second.items.add(combo.first);
		}
		}
		int remainingSlots = items.size();
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i).first;
			Hero hero = items.get(i).second;
			Button button = itemButtons.get(i);
			
			if (hero != null) {
				if(hero instanceof Warrior)
					screen.draw(warriorImage, button.getCoordinate().getX(), button.getCoordinate().getY()+100);
				else if(hero instanceof Dwarf)
					screen.draw(dwarfImage, button.getCoordinate().getX(), button.getCoordinate().getY()+100);
				else if(hero instanceof Mage)
					screen.draw(mageImage, button.getCoordinate().getX(), button.getCoordinate().getY()+100);
				else if(hero instanceof Archer)
					screen.draw(archerImage, button.getCoordinate().getX(), button.getCoordinate().getY()+100);
				remainingSlots--;

			}
			if (remainingSlots < 1) {
				okButton.draw();
				
			}
			button.draw();
			//screen.draw(item.getImage(), button.getCoordinate().getX(), button.getCoordinate().getY());
			
		}
		
	}
	
	public void draw() {
		screen.draw(background, 0, 0);
		decisionLoop();
	}

	@Override
	public void handleKeyPress(int key) {
		
		
	}

	@Override
	public void handleKeyRelease(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKeyType(char c) {
		// TODO Auto-generated method stub
		if (c == 'a')
    	{
    		Client.mainHero = tm.endTurn();
    	}
	}

	@Override
	public void handleMousePress(int x, int y, int button) {
		// TODO Auto-generated method stub
		if(textBox.inputClicked(x, y) || textBox.outputClicked(x, y)) {
			gameStatus.lastFocused = gameStatus.FOCUS_ON_COLLABORATIVE;
            gameStatus.focus = gameStatus.FOCUS_ON_TEXTBOX;
            
        }
		int i = 0;
		for (Button b : itemButtons) {
			if (b.isClicked(x, y)) {
				items.get(i).second = Client.mainHero;
			}
			i++;
		}
		
		if (okButton.isClickable() && okButton.isClicked(x, y)) {
			toDecide = DecisionType.NONE;
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
