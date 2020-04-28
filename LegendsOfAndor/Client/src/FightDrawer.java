import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;
import org.minueto.image.MinuetoRectangle;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;
import java.util.ArrayList;

public class FightDrawer implements Inputtable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3038256961366914056L;
	private GameStatus gameStatus;
	MinuetoImage background;
	public Fight fight;

	
	TileEntityDrawer teDrawer;
	DiceRoller dice;
	
	private Button rollButton;
	private Button yourTurn;
	private Button notYourTurn;
	private Button confirm;
	private Button damageButton;
	private MinuetoImageFile diceRoll;
	private Button rollAgain;			//button only shown to archer class
	private Button changeRollResult;	//button only shown to mage class
	
	
	private final int WITCHBREW_X = 1000;
	private final int WITCHBREW_Y = 600;
	WitchBrew wb = new WitchBrew(-1);
	private Button witchButton;
	
	private final int SHIELD_X = 1000;
	private final int SHIELD_Y = 600;
	Shield shield = new Shield(-1);
	private Button shieldButton;
	
	private final int HELM_X = 1100;
	private final int HELM_Y = 600;
	Helm helm = new Helm(-1);
	private Button helmButton;
	
	ArrayList<MinuetoImage> diceImages = new ArrayList<MinuetoImage>();
	public FightDrawer(Fight fight) throws IOException {
		gameStatus = GameStatus.getInstance();
		teDrawer = TileEntityDrawer.getInstance();
		dice = new DiceRoller();
		
		background = new MinuetoRectangle(gameStatus.screenWidth, gameStatus.screenHeight, MinuetoColor.RED, true);
		rollButton = new Button(new Coordinate(700,600),50,50,"ROLL DICE",true);
		yourTurn = new Button(new Coordinate(700,500),50,50,"Your Turn",false);
		confirm = new Button(new Coordinate(760,500),50,50,"OK",true);
		notYourTurn = new Button(new Coordinate(700,500),50,50,"Not Your Turn",false);
		rollAgain = new Button(new Coordinate(700,500),50,50,"Roll Again",false);
		changeRollResult = new Button(new Coordinate(690,500),50,50,"CRR",false);
		
		witchButton = new Button(new Coordinate(WITCHBREW_X,WITCHBREW_Y),50,50,"",false);
		shieldButton = new Button(new Coordinate(SHIELD_X,SHIELD_Y),50,50,"",false);
		helmButton = new Button(new Coordinate(HELM_X,HELM_Y),50,50,"",false);
		this.fight = fight;
		
		for (int i = 1; i < 7; i++) {
			try {
				String diceFile = ("images/Heroes/Dice/" + i + ".jpg");
				System.out.println(diceFile);
				diceImages.add( new MinuetoImageFile(diceFile));
			} catch (Exception e) {}
			
		}
		for (int i = 1; i < 7; i++) {
			try {
				String diceFile = ("images/Monsters/Dice/" + i + ".jpg");
				
				diceImages.add( new MinuetoImageFile(diceFile));
			} catch (Exception e) {}
			
		}
		
	}

	
	public void draw() {
		Client.screen.draw(background, 0, 0);
		for (Tuple<Character, Coordinate> member : fight.fightMembers) {
			
		teDrawer.draw(member.first, member.second.getX(), member.second.getY()*200);
			
		}
		
		int j = 1;
		if (fight.heroTurn) {
			if (!(fight.currentHero instanceof Archer )) {
				for (Integer i : fight.heroRolls) {
					
					heroRoll(i,j);
					j++;
					
					
				}
			}
			else if (!fight.heroRolls.isEmpty()){
				heroRoll(fight.heroRolls.get(fight.archerRollNumber),1);
			}
		}
		else {
			for (Integer i : fight.monsterRolls) {
				
				monsterRoll(i,j);
				j++;
				
				
			}
		}
		
		
		if (gameStatus.fight == FightStatus.ROLLPROMPT) {
			confirm.setClickable(true);
			rollButton.setClickable(true);
			if (fight.mainHero == fight.currentHero) {
				yourTurn.draw();
				rollButton.draw();

				//check if the hero is Archer
				if(fight.currentHero instanceof Archer) {

				}

				//check if the hero is Mage
				if(fight.currentHero instanceof Mage) {

				}
			}
			else {
				notYourTurn.draw();
			}
		}

		else if (gameStatus.fight == FightStatus.ROLLRESPONSE) {
			confirm.setClickable(true);
			rollButton.setClickable(false);
			if (fight.inFight(fight.mainHero)) {
				if (fight.mainHero instanceof Mage) {
					changeRollResult.setClickable(true);
					changeRollResult.draw();
				}
				if (fight.currentHero == fight.mainHero && fight.mainHero instanceof Archer && fight.archerRollNumber < 4) {
					rollAgain.setClickable(true);
					rollAgain.draw();
					
				}
				if (fight.currentHero == fight.mainHero && fight.mainHero.getWitchBrew() > 0 && !fight.brewUsed) {
					witchButton.setClickable(true);
					teDrawer.draw(wb, WITCHBREW_X, WITCHBREW_Y);
				}
				if (fight.currentHero == fight.mainHero && fight.mainHero.getHelm() > 0 && !fight.brewUsed &&
						!(fight.currentHero instanceof Archer || fight.currentHero instanceof Mage)) {
					helmButton.setClickable(true);
					teDrawer.draw(helm, HELM_X, HELM_Y);
				}
			}

			
			if (fight.mainHero == fight.currentHero) {
				
				confirm.draw();
			}
			
			
		}
		else if (gameStatus.fight == FightStatus.ROLLMONSTER) {
			confirm.setClickable(true);
			if (fight.mainHero == fight.currentHero) {
				confirm.draw();
			}
			
		}
		else if (gameStatus.fight == FightStatus.DAMAGE) {
			confirm.setClickable(true);
			if (fight.mainHero == fight.currentHero) {
				confirm.draw();
			}
			damageButton.draw();
			if (fight.mainHero.getShield() > 0 && fight.heroTotalRoll < fight.monsterTotalRoll) {
				teDrawer.draw(shield, SHIELD_X, SHIELD_Y);
				shieldButton.setClickable(true);
			}
			else {
				shieldButton.setClickable(false);
			}
		}
		//TODO Draw items once items are implemented
	}

	public void heroRoll(int roll,int offset)  {
		Client.screen.draw(diceImages.get(roll-1), 400 + 100*offset, 400);
	}

	public void monsterRoll(int roll,int offset)  {
		Client.screen.draw(diceImages.get(roll+5), 400 + 100*offset, 400);
		
	}

	public void handleKeyPress(int key) {

	}

	@Override
	public void handleKeyRelease(int key) {

	}

	@Override
	public void handleKeyType(char c) {
		if (c == 'a') {
			fight.tm.endTurn();
			fight.mainHero = fight.tm.getHero();
		}
		else if (c == 'd') {
			gameStatus.setFight(FightStatus.ROLLPROMPT);
		}
		else if (c == 'm') {
			System.out.println(fight.currentHero);
		}

	}

	@Override
	public void handleMousePress(int x, int y, int button) {


		if (fight.mainHero == fight.currentHero && rollButton.isClicked(x, y) && rollButton.isClickable()) {
			
			fight.heroRolls = dice.roll(fight.currentHero);
			System.out.println(fight.heroRolls);
			
			gameStatus.setFight(FightStatus.ROLLRESPONSE);
			
			if (!(fight.currentHero instanceof Archer)) {
				fight.currentRoll = dice.getHighestRoll(fight.heroRolls);
			}
			else {
				fight.currentRoll = fight.heroRolls.get(0);
			}
			//		rollAgain.setClickable(false);
			
		}
		else if(fight.mainHero == fight.currentHero && rollAgain.isClicked(x, y) && rollAgain.isClickable()) {
			System.out.println("ROLLBUTTON");
			fight.archerRollNumber++;
			fight.currentRoll = fight.heroRolls.get(fight.archerRollNumber);
			if (fight.archerRollNumber == 4) {
				rollAgain.setClickable(false);
			}
		}
		else if (gameStatus.fight == FightStatus.ROLLRESPONSE && fight.mainHero == fight.currentHero) { 
			
			if(confirm.isClicked(x, y) && confirm.isClickable()) {
			fight.monsterRolls =dice.roll(fight.currentMonster);
			fight.heroTurn = false;
			gameStatus.fight = FightStatus.ROLLMONSTER;
			fight.brewUsed = false;
			fight.heroTotalRoll += fight.currentRoll;
			fight.heroTotalRoll += fight.currentHero.sp;
			
			}
			else if (witchButton.isClickable() && witchButton.isClicked(x, y) && !fight.brewUsed) {
				fight.currentHero.removeItem(wb);
				fight.currentRoll *= 2;
				fight.brewUsed = true;
			}
			else if (helmButton.isClickable() && helmButton.isClicked(x, y) && !fight.brewUsed) {
				fight.currentHero.removeItem(wb);
				fight.currentRoll = dice.getTotalIdentical(fight.heroRolls);
				fight.brewUsed = true;
			}
		}
			
	
		else if (gameStatus.fight == FightStatus.ROLLMONSTER && confirm.isClickable() && confirm.isClicked(x, y)) {
			fight.monsterTotalRoll = dice.getHighestRoll(fight.monsterRolls) ;// + fight.currentMonster.strength;
			String damage;
			if (fight.monsterTotalRoll > fight.heroTotalRoll) {
				damage = "Heroes took " + (fight.monsterTotalRoll - fight.heroTotalRoll) + " damage";
				for (Hero hero : fight.fightHeroes) {
					hero.wp -= (fight.monsterTotalRoll - fight.heroTotalRoll);
				}
			}
			else if (fight.monsterTotalRoll < fight.heroTotalRoll ) {
				damage = "Monsters took " + (-fight.monsterTotalRoll + fight.heroTotalRoll) + " damage";
				fight.currentMonster.health -= (-fight.monsterTotalRoll + fight.heroTotalRoll);
			}
			else {
				damage = "No one took damage this round";
			}
			try {
				damageButton = new Button(new Coordinate(700,500),1,1,damage,false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			gameStatus.fight = FightStatus.DAMAGE;
		}
		else if (gameStatus.fight == FightStatus.DAMAGE && shieldButton.isClickable() && shieldButton.isClicked(x, y)) {
			try {
				damageButton = new Button(new Coordinate(700,500),1,1,"Shield used. No damage taken",false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Hero hero : fight.fightHeroes) {
				hero.wp += (fight.monsterTotalRoll - fight.heroTotalRoll);
			}
		}
		else if (gameStatus.fight == FightStatus.DAMAGE && confirm.isClickable() && confirm.isClicked(x, y)) {
			fight.currentRoll = 0;
			fight.heroTotalRoll = 0;
			fight.monsterTotalRoll = 0;
			fight.monsterRolls.clear();
			fight.heroRolls.clear();
			fight.archerRollNumber = 0;
			fight.brewUsed = false;
			fight.heroTurn = true;
			
			
			
			ArrayList<Hero> deadHeroes = new ArrayList<Hero>();
			for (Hero hero : fight.fightHeroes) {
				if (hero.wp < 1) {
					deadHeroes.add(hero);
				}
				
			}
			if (fight.fightHeroes.size() == deadHeroes.size()) {
				//ALL HEROES DEAD
			}
			else {
				fight.fightHeroes.removeAll(deadHeroes);
			}
			//HANDLE DEAD HEROES
			System.out.println("MONSTER HEALTH" + fight.currentMonster.health);
			if (fight.currentMonster.health < 1) {
				
				try {
					GameScreen.getInstance().tiles.get(fight.currentMonster.tile).removeTileEntity(fight.currentMonster);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				gameStatus.fight = FightStatus.NONE;
				gameStatus.currentScreen = gameStatus.COLLABORATIVE_SCREEN;
				gameStatus.focus = gameStatus.FOCUS_ON_COLLABORATIVE;
				try {
					GameScreen.getInstance().cd.endBattle(fight.currentMonster);
					GameScreenDrawer.getInstance().collabDrawer.endBattle(fight.currentMonster);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				gameStatus.fight = FightStatus.ROLLPROMPT;
			}
		
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
