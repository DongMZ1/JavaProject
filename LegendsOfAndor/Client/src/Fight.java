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

	/**
	 * 
	 */
	private static final long serialVersionUID = -4625916128508934867L;
	private Tile fightTile;
	boolean isHappening = false;
	TurnManager tm;
	ArrayList<Tuple<Character,Coordinate>> fightMembers;
	ArrayList<Hero> fightHeroes;
	
	
	public Hero currentHero = Client.mainHero;
//	GameStatus gameStatus;

	int herosLeft;
	int monsterTotalRoll;

	int heroTotalRoll;
	int currentRoll;
	Monster currentMonster;
	Dice targetDice;
	private int diceRolled;
	ArrayList<Integer> heroRolls = new ArrayList<Integer>();
	ArrayList<Integer> monsterRolls = new ArrayList<Integer>();
	boolean heroTurn = true;
	int archerRollNumber = 0;
	boolean brewUsed=false;
	public Fight(TurnManager tm) throws IOException {
		this.tm = tm;
		
//		gameStatus = GameStatus.getInstance();
	}
	
	public boolean inFight(Hero h) {
		if (fightHeroes.contains(h)) return true;
		else return false;
	}
	
	public void start(int tileNumber, Hero initiator) {
		fightMembers = new ArrayList<>();
		fightHeroes = new ArrayList<>();
		heroTotalRoll = 0;
		monsterTotalRoll = 0;
		currentHero = initiator;
		
		
		this.fightTile = Tile.get(tileNumber);
		isHappening = true;
		int monsterOffset = 1;
		for (Character entity : fightTile.getTileCharacters()) {
			//member is a Hero
			System.out.println(entity);
			if (entity instanceof Hero) {
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
//		Client.gameStatus.setFight(FightStatus.ROLLPROMPT);
		System.out.println(fightMembers);
	}
	
	//fight monster on adjacent tile
	public void startAdjacent(int tileNumber, Hero hero) {
		fightMembers = new ArrayList<>();
		fightHeroes = new ArrayList<>();
		
		this.fightTile = Tile.get(tileNumber);
		isHappening = true;
		int monsterOffset = 1;
		
		fightMembers.add(new Tuple<Character,Coordinate>(hero,new Coordinate(600, tm.indexOf(hero) + 1)));
		fightHeroes.add(hero);		
		
		for (Character entity : fightTile.getTileCharacters()) {
			//member is a Hero
			System.out.println(entity);
			if (entity instanceof Hero) {
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
//		Client.gameStatus.setFight(FightStatus.ROLLPROMPT);
		
	}
	
	public int flipRoll(int i) {
		
		int flipNumber = 7 - i;
		return flipNumber;
	}
	
	public boolean currentIsMain() {
		return (currentHero.getClass() == Client.mainHero.getClass());
	}
}
