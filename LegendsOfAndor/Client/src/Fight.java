import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;
import org.minueto.image.MinuetoRectangle;
import org.minueto.window.MinuetoWindow;

public class Fight implements Inputtable, Serializable{

	private Tile fightTile;
	boolean isHappening = false;
	TurnManager tm;
	ArrayList<Tuple<Character,Coordinate>> fightMembers;
	ArrayList<Hero> fightHeroes;
	
	Hero mainHero = Client.mainHero;
	private Hero currentHero = Client.mainHero;
	public Hero getCurrentHero() {
		return currentHero;
	}
	//UPDATE
	public void setCurrentHero(Hero currentHero) {
		this.currentHero = currentHero;
	}

	GameStatus gameStatus;
	public GameStatus getGameStatus() {
		return gameStatus;
	}
	//UPDATE
	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	int herosLeft;
	int monsterRoll;
	
	public int getMonsterRoll() {
		return monsterRoll;
	}
	//UPDATE
	public void setMonsterRoll(int monsterRoll) {
		this.monsterRoll = monsterRoll;
	}

	int heroRoll;
	int currentRoll;
	Monster currentMonster;
	Dice targetDice;

	//button only shown to archer class
	private Button rollAgain;



	//button only shown to mage class
	private Button changeRollResult;


	public Fight(TurnManager tm) throws IOException {
		
		
		this.tm = tm;
		setGameStatus(GameStatus.getInstance());
	}
	
	public boolean inFight(Hero h) {
		if (fightHeroes.contains(h)) return true;
		else return false;
	}
	
	public void start(Tile fightTile) {
		fightMembers = new ArrayList<>();
		fightHeroes = new ArrayList<>();
		heroRoll = 0;
		monsterRoll = 0;
		setCurrentHero(GameScreen.currentHero);
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
	
	
	public void draw() {
		Client.screen.draw(MinuetoFight.background, 0, 0);
		for (Tuple<Character, Coordinate> member : fightMembers) {
			Client.screen.draw(member.first.getImage(), member.second.getX(), member.second.getY()*200);
		}
		if (gameStatus.fight == FightStatus.ROLLPROMPT) {
			if (mainHero == currentHero) {
				MinuetoFight.yourTurn.draw();
				MinuetoFight.rollButton.draw();
				
				//check if the hero is Archer
				if(currentHero instanceof Archer) {
					
				}
				
				//check if the hero is Mage
				if(currentHero instanceof Mage) {
					
					
				}
				
			}
			else {
				MinuetoFight.notYourTurn.draw();
			}
		}
		
		else if (gameStatus.fight == FightStatus.ROLLRESPONSE) {
			
			if (inFight(mainHero)) {
				if (mainHero instanceof Mage) {
					changeRollResult.setClickable(true);
					changeRollResult.draw();
				}
				if (mainHero instanceof Archer) {
					MinuetoFight.confirm.setClickable(true);
					MinuetoFight.confirm.draw();
				}
			}
			
			
			if (mainHero == currentHero && mainHero.dice.hasRolls()) {
				rollAgain.setClickable(true);
				rollAgain.draw();
			}
			else if (mainHero == currentHero) {
				rollAgain.setClickable(false);;
				MinuetoFight.confirm.draw();
			}
			
			Client.screen.draw(MinuetoFight.diceRoll, 700, 600);
		}
		else if (gameStatus.fight == FightStatus.ROLLMONSTER) {
			if (mainHero == currentHero) {
				MinuetoFight.confirm.draw();
			}
			Client.screen.draw(MinuetoFight.diceRoll, 700, 600);
		}
		else if (gameStatus.fight == FightStatus.DAMAGE) {
			if (mainHero == currentHero) {
				MinuetoFight.confirm.draw();
			}
			MinuetoFight.damageButton.draw();
		}
		//TODO Draw items once items are implemented
	}
	
	public int flipRoll(int i) {
		
		int flipNumber = 7 - i;
		targetDice.setCurrentNumber(flipNumber);
		System.out.println(targetDice);
		System.out.println(targetDice.rolledNums);
		
		return flipNumber;
	}
	
	public void heroRoll(int roll) throws MinuetoFileException {		
		String diceFile = ("images/Heroes/Dice/" + roll + ".png");
		System.out.println(diceFile);
		MinuetoFight.diceRoll = new MinuetoImageFile(diceFile);
	}
	
	public void monsterRoll(int roll) throws MinuetoFileException {		
		String diceFile = ("images/Monsters/Dice/" + roll + ".png");
		System.out.println(diceFile);
		MinuetoFight.diceRoll = new MinuetoImageFile(diceFile);
	}
	
	public void handleKeyPress(int key) {
		
    }

	@Override
	public void handleKeyRelease(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKeyType(char c) {
		// TODO Auto-generated method stub
		if (c == 'a') {
			mainHero = tm.endTurn();
			}
		else if (c == 'd') {
			gameStatus.setFight(FightStatus.ROLLPROMPT);
		}
		else if (c == 'm') {
			System.out.println(currentHero);
		}
		
	}

	@Override
	public void handleMousePress(int x, int y, int button) {
		
		if (mainHero instanceof Mage)
			rollAgain.setClickable(true);
		
		if (mainHero == currentHero && MinuetoFight.rollButton.isClicked(x, y) && MinuetoFight.rollButton.isClickable()) {
			
			if (currentHero.dice.hasRolls()) {
				
				currentRoll = currentHero.dice.roll();
				targetDice = currentHero.dice;
				
				try {
					heroRoll(currentRoll);
				}
				catch (Exception e) {System.out.println("Error yo");}
				MinuetoFight.confirm.setClickable(false);
				
			}
			gameStatus.setFight(FightStatus.ROLLPROMPT);
			if (!currentHero.dice.hasRolls()) {
				rollAgain.setClickable(false);
				MinuetoFight.confirm.setClickable(true);
			}
	//		rollAgain.setClickable(false);
		
		}
		
		//allow only mage class to change roll result
		else if (mainHero instanceof Mage && changeRollResult.isClicked(x, y) && changeRollResult.isClickable() && gameStatus.fight == FightStatus.ROLLRESPONSE) {
			System.out.println("MAGE BUTTON PRESSED");
			
			try {
			heroRoll(flipRoll(currentRoll));	
			}
			catch (Exception e) {System.out.println("ian is cool");}
			
		}	
		
		else if (mainHero == currentHero && rollAgain.isClicked(x, y) && rollAgain.isClickable() && gameStatus.fight == FightStatus.ROLLRESPONSE) {
			gameStatus.setFight(FightStatus.ROLLPROMPT);
		}
		
		else if (mainHero == currentHero && MinuetoFight.confirm.isClicked(x, y) && MinuetoFight.confirm.isClickable() && gameStatus.fight == FightStatus.ROLLRESPONSE) {
			if (currentHero.dice.hasRolls() && !(currentHero instanceof Archer)) {
				System.out.println("BADBABBAD");
			}
			else {
				herosLeft--;
				System.out.println(herosLeft);
				heroRoll += currentHero.dice.getBattleNum();	// add up battle value
				
				currentHero.dice.endTurn();		// reset dice state
				if (herosLeft != 0) {
					
					currentHero = tm.endTurn();
					gameStatus.setFight(FightStatus.ROLLPROMPT);
				}
				else {
					while (currentMonster.dice.hasRolls())
						currentMonster.dice.roll();
					monsterRoll = currentMonster.dice.getBattleNum();
					try {
						monsterRoll(monsterRoll);
					}
					catch (Exception e) {}
					System.out.println("monster rolled " + monsterRoll);
					gameStatus.setFight(FightStatus.ROLLMONSTER);
				}
			}
		}
		else if (mainHero == currentHero && MinuetoFight.confirm.isClicked(x, y) && MinuetoFight.confirm.isClickable() && gameStatus.fight == FightStatus.ROLLMONSTER) {
			String damage = "";
			if (heroRoll > monsterRoll) {
				
				currentMonster.health -= (heroRoll - monsterRoll);
				damage = ("Monsters took " + (heroRoll - monsterRoll) + " damage.");
				
			}
			else {
				for (Hero h : fightHeroes) {
					h.wp -= (monsterRoll - heroRoll);
					damage = ("Heroes took " + (monsterRoll - heroRoll) + " damage.");
					
				}
			}
			try {
				MinuetoFight.damageButton = new Button(new Coordinate(700,300), 200, 50, damage, false);
				gameStatus.setFight(FightStatus.DAMAGE);
			}
			catch (Exception e) {
				System.out.println(e);
			}
					
		}
		else if (mainHero == currentHero && MinuetoFight.confirm.isClicked(x, y) && MinuetoFight.confirm.isClickable() && gameStatus.fight == FightStatus.DAMAGE) {
			gameStatus.setFocus(GameStatus.FOCUS_ON_GAMESCREEN);
			gameStatus.setCurrentScreen(GameStatus.GAME_SCREEN);
			GameScreen.currentHero.time.advance();
			GameScreen.currentHero = tm.endTurn();
			
			isHappening = false;
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
