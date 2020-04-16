import java.io.IOException;
import java.util.ArrayList;

import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;
import org.minueto.image.MinuetoRectangle;

public class CollaborativeDecisionDrawer implements Inputtable {

	static CollaborativeDecisionDrawer cdDrawer;
	
	TurnManager tm;
	TileEntityDrawer tileDrawer;
	private MinuetoImage archerImage;
	private MinuetoImage warriorImage;
	private MinuetoImage dwarfImage;
	private MinuetoImage mageImage;
	MinuetoImage background;
	ArrayList<Button> itemButtons;
	Button okButton;
	TextBox textBox;
//	private InputHandler inputHandler;
	GameStatus gameStatus;
	
	
	public static CollaborativeDecisionDrawer getInstance() throws IOException {
		if(cdDrawer == null)
			cdDrawer = new CollaborativeDecisionDrawer(DecisionType.START, null);
		return cdDrawer;
	}
	
	public CollaborativeDecisionDrawer(DecisionType d, TurnManager tm){
		
		
		try {
			archerImage = new MinuetoImageFile("images/Heroes/ArcherMaleIcon.png").scale(Constants.HERO_SCALE, Constants.HERO_SCALE);
			
			textBox = TextBox.getInstance();
			gameStatus = GameStatus.getInstance();
//			 inputHandler = InputHandler.getInputHandler();
			 tileDrawer = TileEntityDrawer.getInstance();
		} catch (Exception e) {}
		this.tm = tm;
		background = new MinuetoRectangle(gameStatus.screenWidth, 400, MinuetoColor.GREEN, true);
		itemButtons = new ArrayList<>();
		
		if (CollaborativeDecision.toDecide == DecisionType.START) {
			createDecision(7);
		}
		
}
	private void createDecision(int numberOfItems) {
		itemButtons.clear();
		int offset = 3;
		try {
		for (int i = 0; i < numberOfItems; i++) {
			
			itemButtons.add(new Button(new Coordinate(100*offset,300), 50, 89, "Claim", true));
			offset++;
			
		}
		okButton = new Button(new Coordinate(100*offset + 150, 150),50,89,"READY",true);
		}
		catch (Exception e) {}
	}
	public void decisionLoop() {
		Client.screen.draw(background, 0, 0);
		if (CollaborativeDecision.toDecide == DecisionType.NONE) {
			gameStatus.focus = GameStatus.FOCUS_ON_GAMESCREEN;
//			inputHandler.removeInput(this);
			gameStatus.currentScreen = gameStatus.GAME_SCREEN;
			
		}
		int remainingSlots = CollaborativeDecision.items.size();
		for (int i = 0; i < CollaborativeDecision.items.size(); i++) {
			
			Item item = CollaborativeDecision.items.get(i).first;
			Hero hero = CollaborativeDecision.items.get(i).second;
			Button button = itemButtons.get(i);
//			System.out.println(item);
			if (hero != null) {
				if(hero instanceof Warrior)
					Client.screen.draw(warriorImage, button.getCoordinate().getX(), button.getCoordinate().getY()-300);
				else if(hero instanceof Dwarf)
					Client.screen.draw(dwarfImage, button.getCoordinate().getX(), button.getCoordinate().getY()-300);
				else if(hero instanceof Mage)
					Client.screen.draw(mageImage, button.getCoordinate().getX(), button.getCoordinate().getY()-300);
				else if(hero instanceof Archer)
					Client.screen.draw(archerImage, button.getCoordinate().getX(), button.getCoordinate().getY()-300);
				remainingSlots--;

			}
			if (remainingSlots < 1) {
				okButton.draw();
				
			}
			button.draw();
			tileDrawer.draw(item, button.getCoordinate().getX(), button.getCoordinate().getY()-150);
			
		}
		
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
		if (c == 'a')
    	{
    		tm.endTurn();
    		Client.mainHero = tm.getHero();
    	}
	}
	@Override
	public void handleMousePress(int x, int y, int button) {
		if(textBox.inputClicked(x, y) || textBox.outputClicked(x, y)) {
			gameStatus.lastFocused = gameStatus.FOCUS_ON_COLLABORATIVE;
            gameStatus.focus = gameStatus.FOCUS_ON_TEXTBOX;
            
        }
		int i = 0;
		for (Button b : itemButtons) {
			if (b.isClicked(x, y)) {
				CollaborativeDecision.items.get(i).second = Client.mainHero;
			}
			i++;
		}
		
		if (okButton.isClickable() && okButton.isClicked(x, y)) {
			CollaborativeDecision.toDecide = DecisionType.NONE;
		}
		InputThread.updateVariable();
		
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
