import java.io.IOException;
import java.util.ArrayList;

import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;
import org.minueto.image.MinuetoRectangle;
import org.minueto.window.MinuetoWindow;

public class Fight implements Inputtable{

	private MinuetoWindow screen;
	MinuetoImage background;
	private Tile fightTile;
	boolean isHappening = false;
	TurnManager tm;
	ArrayList<Tuple<Character,Coordinate>> fightMembers;
	ArrayList<Hero> fightHeroes;
	ArrayList<Monster> fightMonsters;
	Hero mainHero = GameScreen.mainHero;
	Hero currentHero = GameScreen.currentHero;
	GameStatus gameStatus;
	int herosLeft;
	int monsterRoll;
	int heroRoll;
	int currentRoll;
	Monster currentMonster;
	Dice targetDice;
	
	private Button rollButton;
	private Button yourTurn;
	private Button notYourTurn;
	private Button confirm;
	private Button damageButton;
	private MinuetoImageFile diceRoll;

	//button only shown to archer class
	private Button rollAgain;
	
	
	
	//button only shown to mage class
	private Button changeRollResult;
		

	public Fight(MinuetoWindow screen, int x, int y, TurnManager tm) throws IOException {
		
		
		this.screen = screen;
		this.tm = tm;
		background = new MinuetoRectangle(x, y, MinuetoColor.RED, true);
		gameStatus = GameStatus.getInstance();
		rollButton = new Button(new Coordinate(700,600),50,50,"ROLL DICE",true);
		yourTurn = new Button(new Coordinate(700,500),50,50,"Your Turn",false);
		confirm = new Button(new Coordinate(700,500),50,50,"OK",true);
		notYourTurn = new Button(new Coordinate(700,500),50,50,"Not Your Turn",false);
		rollAgain = new Button(new Coordinate(700,500),50,50,"Roll Again",false);
		changeRollResult = new Button(new Coordinate(700,500),50,50,"Change Roll Result",false);
	}
	
	public void start(Tile fightTile) {
		fightMembers = new ArrayList<>();
		fightHeroes = new ArrayList<>();
		fightMonsters = new ArrayList<>();
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
				fightMonsters.add((Monster) entity);
			}
			
		}
		
		herosLeft = fightHeroes.size();
		gameStatus.fight = FightStatus.ROLLPROMPT;
		
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
		gameStatus.fight = FightStatus.ROLLPROMPT;
		
	}	
	
	
	public void draw() {
		this.screen.draw(background, 0, 0);
		for (Tuple<Character, Coordinate> member : fightMembers) {
			this.screen.draw(member.first.getImage(), member.second.getX(), member.second.getY()*200);
		}
		if (gameStatus.fight == FightStatus.ROLLPROMPT) {
			if (mainHero == currentHero) {
				yourTurn.draw();
				rollButton.draw();
				
				//check if the hero is Archer
				if(currentHero instanceof Archer) {
					rollAgain.setClickable(true);
					rollAgain.draw();
				}
				
				//check if the hero is Mage
				if(currentHero instanceof Mage) {
					changeRollResult.setClickable(true);
					changeRollResult.draw();
					
				}
				
			}
			else {
				notYourTurn.draw();
			}
		}
		
		else if (gameStatus.fight == FightStatus.ROLLRESPONSE) {
			if (mainHero == currentHero) {
				confirm.draw();
			}
			this.screen.draw(diceRoll, 700, 600);
		}
		else if (gameStatus.fight == FightStatus.ROLLMONSTER) {
			if (mainHero == currentHero) {
				confirm.draw();
			}
			this.screen.draw(diceRoll, 700, 600);
		}
		else if (gameStatus.fight == FightStatus.DAMAGE) {
			if (mainHero == currentHero) {
				confirm.draw();
			}
			damageButton.draw();
		}
		//TODO Draw items once items are implemented
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
			currentHero = tm.endTurn();
		}
		else if (c == 'd') {
			gameStatus.fight = FightStatus.ROLLPROMPT;
		}
		
	}

	@Override
	public void handleMousePress(int x, int y, int button) {
		
		if (mainHero instanceof Mage)
			rollAgain.setClickable(true);
		
		if (mainHero == currentHero && rollButton.isClicked(x, y) && rollButton.isClickable() || 
				currentHero instanceof Archer && rollAgain.isClicked(x, y) && rollAgain.isClickable() && gameStatus.fight == FightStatus.ROLLRESPONSE) {
			
			while (currentHero.dice.hasRolls()) {
				currentRoll = currentHero.dice.roll();
				targetDice = currentHero.dice;
				
				//check if hero's Archer and make rollAgain button clickable
				if(currentHero instanceof Archer && currentHero.dice.hasRolls()) {
					rollAgain.setClickable(true);
				}
				else 
					rollAgain.setClickable(false);
				
				// wait 3seconds for archer to re roll his dice or 
				// for mage to flip any roll
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(currentHero instanceof Archer) {
					rollAgain.setClickable(false);
					break;	//timed out -> proceed with current roll
				}				
			}
			
			rollAgain.setClickable(false);
		
		}
		
		//allow only mage class to change roll result
		else if (currentHero instanceof Mage && changeRollResult.isClicked(x, y) && changeRollResult.isClickable() && gameStatus.fight == FightStatus.ROLLRESPONSE) {
	
			MageDice mageDice = (MageDice) currentHero.dice;
			mageDice.flipRoll(targetDice);	
		}		
		
		else if (mainHero == currentHero && confirm.isClicked(x, y) && confirm.isClickable() && gameStatus.fight == FightStatus.ROLLRESPONSE) {
			if (herosLeft != 0) {
				heroRoll += currentHero.dice.getBattleNum();	// add up battle value
				currentHero = tm.endTurn();
				currentHero.dice.endTurn();		// reset dice state
				gameStatus.fight = FightStatus.ROLLPROMPT;
			}
			else {
				while (currentMonster.dice.hasRolls())
					currentMonster.dice.roll();
				monsterRoll = currentMonster.dice.getBattleNum();
			}
		}
		else if (mainHero == currentHero && confirm.isClicked(x, y) && confirm.isClickable() && gameStatus.fight == FightStatus.ROLLMONSTER) {
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
				damageButton = new Button(new Coordinate(700,300), 200, 50, damage, false);
				gameStatus.fight = FightStatus.DAMAGE;
			}
			catch (Exception e) {
				System.out.println(e);
			}
					
		}
		else if (mainHero == currentHero && confirm.isClicked(x, y) && confirm.isClickable() && gameStatus.fight == FightStatus.DAMAGE) {
			gameStatus.focus = GameStatus.FOCUS_ON_GAMESCREEN;
			gameStatus.currentScreen = GameStatus.GAME_SCREEN;
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
