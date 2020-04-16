import java.io.Serializable;
import java.util.ArrayList;



public class CollaborativeDecision implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2759410189088674714L;
	public static DecisionType toDecide;
	GameStatus gameStatus;
	
	
	TurnManager tm;
	public static ArrayList<Tuple<Item,Hero>> items;
	
	
	
	
	public CollaborativeDecision(DecisionType d, TurnManager tm){
		toDecide = d;		
		try {
		 gameStatus = GameStatus.getInstance();
		 
		 
		}
		catch (Exception e) {}
		
		
		
		gameStatus.focus = GameStatus.FOCUS_ON_COLLABORATIVE;
		
		
		
		this.tm = tm;
		
		
		items = new ArrayList<>();
		
		if (toDecide == DecisionType.START) {
			createStart();
		}
		
	
		
		
		gameStatus.currentScreen = GameStatus.COLLABORATIVE_SCREEN;
	}
	private void createStart() {
		items.clear();
		
		try {
		for (int i = 0; i < 5; i++) {
			items.add(new Tuple(new Gold(-1),null));
			
			
		}
		for (int i = 0; i < 2; i++) {
			items.add(new Tuple(new Wineskin(-1),null));
			
			
		}
		
		}
		catch (Exception e) {}
	}
	
	
	public void decisionLoop() {
		//Right now only used when finish
		if (toDecide == DecisionType.NONE) {
		gameStatus.focus = GameStatus.FOCUS_ON_GAMESCREEN;
		
		gameStatus.currentScreen = gameStatus.GAME_SCREEN;
		for (Tuple<Item,Hero> combo : items) {
			combo.second.items.add(combo.first);
		}
		}
		
		
	}
	
	public void draw() {
		
		
	}

}