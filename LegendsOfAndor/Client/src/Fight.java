import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;
import org.minueto.image.MinuetoRectangle;
import org.minueto.window.MinuetoWindow;

public class Fight implements Serializable{

	private Tile fightTile;
	boolean isHappening = false;
	TurnManager tm;
	ArrayList<Tuple<Character,Coordinate>> fightMembers;
	ArrayList<Hero> fightHeroes;
	
	Hero mainHero = Client.mainHero;
	public Hero currentHero = Client.mainHero;
	GameStatus gameStatus;

	int herosLeft;
	int monsterRoll;

	int heroRoll;
	int currentRoll;
	Monster currentMonster;
	Dice targetDice;
	private int diceRolled;
	public Fight(TurnManager tm) throws IOException {
		
		this.tm = tm;
		gameStatus = GameStatus.getInstance();
	}
	
	public boolean inFight(Hero h) {
		if (fightHeroes.contains(h)) return true;
		else return false;
	}
	
	public void start(Tile fightTile, Hero initiator) {
		fightMembers = new ArrayList<>();
		fightHeroes = new ArrayList<>();
		heroRoll = 0;
		monsterRoll = 0;
		currentHero = initiator;
		mainHero = Client.mainHero;
		
		this.fightTile = fightTile;
		isHappening = true;
		int monsterOffset = 1;
		for (Character entity : fightTile.getTileCharacters()) {
			//member is a Hero
			if (tm.contains(entity)) {
				fightMembers.add(new Tuple<Character,Coordinate>(entity,new Coordinate(600, tm.indexOf(entity) + 1)));
				fightHeroes.add((Hero) entity);
			}
			
			//member is a Monster
			else {
				fightMembers.add(new Tuple<Character,Coordinate>(entity,new Coordinate(900, monsterOffset)));
				monsterOffset++;
				currentMonster = (Monster) entity;
			}
			
		}
		
		herosLeft = fightHeroes.size();
		gameStatus.setFight(FightStatus.ROLLPROMPT);
		
	}
	
	//fight monster on adjacent tile
	public void startAdjacent(Tile fightTile, Hero hero) {
		fightMembers = new ArrayList<>();
		fightHeroes = new ArrayList<>();
		
		this.fightTile = fightTile;
		isHappening = true;
		int monsterOffset = 1;
		
		fightMembers.add(new Tuple<Character,Coordinate>(hero,new Coordinate(600, tm.indexOf(hero) + 1)));
		fightHeroes.add(hero);		
		
		for (Character entity : fightTile.getTileCharacters()) {
			//member is a Hero
			
			if (tm.contains(entity)) {
				fightMembers.add(new Tuple<Character,Coordinate>(entity,new Coordinate(600, tm.indexOf(entity) + 1)));
				fightHeroes.add((Hero) entity);
				
			}
			
			//member is a Monster
			else {
				fightMembers.add(new Tuple<Character,Coordinate>(entity,new Coordinate(900, monsterOffset)));
				monsterOffset++;
				currentMonster =(Monster) entity;
			}
			
		}	
		
		
		herosLeft = fightHeroes.size();
		gameStatus.setFight(FightStatus.ROLLPROMPT);
		
	}
	
	public int flipRoll(int i) {
		
		int flipNumber = 7 - i;
		return flipNumber;
	}
	
	
}
