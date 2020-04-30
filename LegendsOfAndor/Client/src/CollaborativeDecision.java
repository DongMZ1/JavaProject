import java.io.Serializable;
import java.util.ArrayList;



public class CollaborativeDecision implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2759410189088674714L;
	public DecisionType toDecide;
	GameStatus gameStatus;
	
	
	TurnManager tm;
	ArrayList<Tuple<Item,Hero>> items;
	int selectedNumItems = 0;
	
	
	
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
		if (toDecide == DecisionType.TEST) {
			items.add(new Tuple(new Bow(-1),null));
			items.add(new Tuple(new Shield(-1),null));
			items.add(new Tuple(new WitchBrew(-1),null));
			items.add(new Tuple(new Helm(-1),null));
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
		
		
		
		
	}
	
	public void endBattle(Monster m) {
		items.clear();
		selectedNumItems = 0;
		int reward = -1;
		if (m instanceof Wardraks) {
			reward = 6;
		}
		else if (m instanceof Gor) {
			reward = 2;
		}
		else if (m instanceof Skral) {
			reward = 4;
		}
		else {
			reward = 0;
		}
		toDecide = DecisionType.REWARD;
		for (int i = 0; i < reward; i++) {
			items.add(new Tuple(new Gold(-1),null));
			items.add(new Tuple(new WP(),null));
		}
	}
	
	public void draw() {
		
		
	}

}