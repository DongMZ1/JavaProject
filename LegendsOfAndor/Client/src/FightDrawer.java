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
	GameStatus gameStatus;
	GameScreen gameScreen;
	MinuetoImage background;
	private static FightDrawer fightdrawer;

	
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
	public FightDrawer() throws IOException {
		gameStatus = Client.gameStatus.getInstance();
		gameScreen = GameScreen.getInstance();
		teDrawer = TileEntityDrawer.getInstance();
		dice = new DiceRoller();
		
		background = new MinuetoRectangle(Client.gameStatus.screenWidth, Client.gameStatus.screenHeight, MinuetoColor.RED, true);
		rollButton = new Button(new Coordinate(700,600),50,50,"ROLL DICE",true);
		yourTurn = new Button(new Coordinate(700,500),50,50,"Your Turn",false);
		confirm = new Button(new Coordinate(760,500),50,50,"OK",true);
		notYourTurn = new Button(new Coordinate(700,500),50,50,"Not Your Turn",false);
		rollAgain = new Button(new Coordinate(700,500),50,50,"Roll Again",false);
		changeRollResult = new Button(new Coordinate(690,500),50,50,"CRR",false);
		
		witchButton = new Button(new Coordinate(WITCHBREW_X,WITCHBREW_Y),50,50,"",false);
		shieldButton = new Button(new Coordinate(SHIELD_X,SHIELD_Y),50,50,"",false);
		helmButton = new Button(new Coordinate(HELM_X,HELM_Y),50,50,"",false);
		
		
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

	public static FightDrawer getInstance() throws IOException {
		if(fightdrawer == null)
			fightdrawer = new FightDrawer();
		return fightdrawer;
	}
	public void draw() {
		Client.screen.draw(background, 0, 0);
		for (Tuple<Character, Coordinate> member : gameScreen.fight.fightMembers) {
			
		teDrawer.draw(member.first, member.second.getX(), member.second.getY()*200);
			
		}
		
		int j = 1;
		if (gameScreen.fight.heroTurn) {
			if (!(gameScreen.fight.currentHero instanceof Archer )) {
				for (Integer i : gameScreen.fight.heroRolls) {
					
					heroRoll(i,j);
					j++;
					
					
				}
			}
			else if (!gameScreen.fight.heroRolls.isEmpty()){
				heroRoll(gameScreen.fight.heroRolls.get(gameScreen.fight.archerRollNumber),1);
			}
		}
		else {
			for (Integer i : gameScreen.fight.monsterRolls) {
				
				monsterRoll(i,j);
				j++;
				
				
			}
		}
		
		
		if (gameScreen.gameStatus.fight == FightStatus.ROLLPROMPT) {
			confirm.setClickable(true);
			rollButton.setClickable(true);
			if (gameScreen.fight.currentIsMain()) {
				yourTurn.draw();
				rollButton.draw();

				//check if the hero is Archer
				if(gameScreen.fight.currentHero instanceof Archer) {

				}

				//check if the hero is Mage
				if(gameScreen.fight.currentHero instanceof Mage) {

				}
			}
			else {
				notYourTurn.draw();
			}
		}

		else if (Client.gameStatus.fight == FightStatus.ROLLRESPONSE) {
			confirm.setClickable(true);
			rollButton.setClickable(false);
			
				if (Client.mainHero instanceof Mage) {
					changeRollResult.setClickable(true);
					changeRollResult.draw();
				}
				if (gameScreen.fight.currentIsMain() && Client.mainHero instanceof Archer && gameScreen.fight.archerRollNumber < 4) {
					rollAgain.setClickable(true);
					rollAgain.draw();
					
				}
				if (gameScreen.fight.currentIsMain() && Client.getMainHero().getWitchBrew() > 0 && !gameScreen.fight.brewUsed) {
					witchButton.setClickable(true);
					teDrawer.draw(wb, WITCHBREW_X, WITCHBREW_Y);
				}
				if (gameScreen.fight.currentIsMain() && Client.getMainHero().getHelm() > 0 && !gameScreen.fight.brewUsed &&
						!(gameScreen.fight.currentHero instanceof Archer || gameScreen.fight.currentHero instanceof Mage)) {
					helmButton.setClickable(true);
					teDrawer.draw(helm, HELM_X, HELM_Y);
				}
			

			
			if (gameScreen.fight.currentIsMain()) {
				
				confirm.draw();
			}
			
			
		}
		else if (Client.gameStatus.fight == FightStatus.ROLLMONSTER) {
			confirm.setClickable(true);
			if (gameScreen.fight.currentIsMain()) {
				confirm.draw();
			}
			
		}
		else if (Client.gameStatus.fight == FightStatus.DAMAGE) {
			confirm.setClickable(true);
			if (gameScreen.fight.currentIsMain()) {
				confirm.draw();
			}
			if (damageButton == null) {
				String damage = createDamageString();
				try {
					damageButton = new Button(new Coordinate(700,500),1,1,damage,false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			damageButton.draw();
			if (Client.getMainHero().getShield() > 0 && gameScreen.fight.heroTotalRoll < gameScreen.fight.monsterTotalRoll) {
				teDrawer.draw(shield, SHIELD_X, SHIELD_Y);
				shieldButton.setClickable(true);
			}
			else {
				shieldButton.setClickable(false);
			}
		}
		//TODO Draw items once items are implemented
	}

	public String createDamageString() {
		String damage;
		if (gameScreen.fight.monsterTotalRoll > gameScreen.fight.heroTotalRoll) {
			damage = "Heroes took " + (gameScreen.fight.monsterTotalRoll - gameScreen.fight.heroTotalRoll) + " damage";
			for (Hero hero : gameScreen.fight.fightHeroes) {
				hero.wp -= (gameScreen.fight.monsterTotalRoll - gameScreen.fight.heroTotalRoll);
			}
		}
		else if (gameScreen.fight.monsterTotalRoll < gameScreen.fight.heroTotalRoll ) {
			damage = "Monsters took " + (-gameScreen.fight.monsterTotalRoll + gameScreen.fight.heroTotalRoll) + " damage";
			gameScreen.fight.currentMonster.health -= (-gameScreen.fight.monsterTotalRoll + gameScreen.fight.heroTotalRoll);
		}
		else {
			damage = "No one took damage this round";
		}
		return damage;
	}
	
	public void heroRoll(int roll,int offset)  {
		Client.screen.draw(diceImages.get(Math.abs(roll)-1), 400 + 100*offset, 400);
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
			gameScreen.fight.tm.endTurn();
//			gameScreen.fight.mainHero = gameScreen.fight.tm.getHero();
		}
		else if (c == 'd') {
			Client.gameStatus.setFight(FightStatus.ROLLPROMPT);
		}
		else if (c == 'm') {
			System.out.println(gameScreen.fight.currentHero);
		}

	}

	@Override
	public void handleMousePress(int x, int y, int button) {


		if (gameScreen.fight.currentIsMain() && rollButton.isClicked(x, y) && rollButton.isClickable()) {
			
			gameScreen.fight.heroRolls = dice.roll(gameScreen.fight.currentHero);
			System.out.println(gameScreen.fight.heroRolls);
			gameScreen.fight.herosLeft--;
			if (gameScreen.fight.herosLeft > 0) {
				gameScreen.fight.tm.endTurn();
				gameScreen.fight.currentHero = gameScreen.tm.getHero();
				
			}else {
				gameScreen.fight.herosLeft = gameScreen.fight.fightHeroes.size();
				Client.gameStatus.setFight(FightStatus.ROLLRESPONSE);
				
				
			}
			if (!(gameScreen.fight.currentHero instanceof Archer)) {
				gameScreen.fight.heroTotalRoll += dice.getHighestRoll(gameScreen.fight.heroRolls);
			}
			else {
				gameScreen.fight.currentRoll = gameScreen.fight.heroRolls.get(0);
			}
			
			//		rollAgain.setClickable(false);
			
		}
		else if(gameScreen.fight.currentIsMain() && rollAgain.isClicked(x, y) && rollAgain.isClickable()) {
			System.out.println("ROLLBUTTON");
			gameScreen.fight.archerRollNumber++;
			gameScreen.fight.currentRoll = gameScreen.fight.heroRolls.get(gameScreen.fight.archerRollNumber);
			if (gameScreen.fight.archerRollNumber == 4) {
				rollAgain.setClickable(false);
			}
		}
		else if (Client.gameStatus.fight == FightStatus.ROLLRESPONSE && gameScreen.fight.currentIsMain()) { 
			
			if(confirm.isClicked(x, y) && confirm.isClickable()) {
			gameScreen.fight.monsterRolls =dice.roll(gameScreen.fight.currentMonster);
			gameScreen.fight.heroTurn = false;
			Client.gameStatus.fight = FightStatus.ROLLMONSTER;
			gameScreen.fight.brewUsed = false;
			gameScreen.fight.heroTotalRoll += gameScreen.fight.currentRoll;
			for (Hero hero : gameScreen.fight.fightHeroes) {
				gameScreen.fight.heroTotalRoll += hero.sp;
			}
			
			
			}
			else if (witchButton.isClickable() && witchButton.isClicked(x, y) && !gameScreen.fight.brewUsed) {
				gameScreen.fight.currentHero.removeItem(wb);
				gameScreen.fight.currentRoll *= 2;
				gameScreen.fight.brewUsed = true;
			}
			else if (helmButton.isClickable() && helmButton.isClicked(x, y) && !gameScreen.fight.brewUsed) {
				gameScreen.fight.currentHero.removeItem(wb);
				gameScreen.fight.currentRoll = dice.getTotalIdentical(gameScreen.fight.heroRolls);
				gameScreen.fight.brewUsed = true;
			}
		}
			
	
		else if (Client.gameStatus.fight == FightStatus.ROLLMONSTER && confirm.isClickable() && confirm.isClicked(x, y)) {
			gameScreen.fight.monsterTotalRoll = dice.getHighestRoll(gameScreen.fight.monsterRolls) ;// + gameScreen.fight.currentMonster.strength;
			String damage = createDamageString();
			
			try {
				damageButton = new Button(new Coordinate(700,500),1,1,damage,false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Client.gameStatus.fight = FightStatus.DAMAGE;
		}
		else if (Client.gameStatus.fight == FightStatus.DAMAGE && shieldButton.isClickable() && shieldButton.isClicked(x, y)) {
			try {
				damageButton = new Button(new Coordinate(700,500),1,1,"Shield used. No damage taken",false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Hero hero : gameScreen.fight.fightHeroes) {
				hero.wp += (gameScreen.fight.monsterTotalRoll - gameScreen.fight.heroTotalRoll);
			}
		}
		else if (Client.gameStatus.fight == FightStatus.DAMAGE && confirm.isClickable() && confirm.isClicked(x, y)) {
			gameScreen.fight.currentRoll = 0;
			gameScreen.fight.heroTotalRoll = 0;
			gameScreen.fight.monsterTotalRoll = 0;
			gameScreen.fight.monsterRolls.clear();
			gameScreen.fight.heroRolls.clear();
			gameScreen.fight.archerRollNumber = 0;
			gameScreen.fight.brewUsed = false;
			gameScreen.fight.heroTurn = true;
			
			
			
			ArrayList<Hero> deadHeroes = new ArrayList<Hero>();
			for (Hero hero : gameScreen.fight.fightHeroes) {
				if (hero.wp < 1) {
					deadHeroes.add(hero);
				}
				
			}
			if (gameScreen.fight.fightHeroes.size() == deadHeroes.size()) {
				//ALL HEROES DEAD
			}
			else {
				gameScreen.fight.fightHeroes.removeAll(deadHeroes);
			}
			//HANDLE DEAD HEROES
			System.out.println("MONSTER HEALTH" + gameScreen.fight.currentMonster.health);
			if (gameScreen.fight.currentMonster.health < 1) {
				
				try {
					GameScreen.getInstance().tiles.get(gameScreen.fight.currentMonster.tile).removeTileEntity(gameScreen.fight.currentMonster);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Client.gameStatus.fight = FightStatus.NONE;
				Client.gameStatus.currentScreen = Client.gameStatus.COLLABORATIVE_SCREEN;
				Client.gameStatus.focus = Client.gameStatus.FOCUS_ON_COLLABORATIVE;
				try {					
					gameScreen.cd.endBattle(gameScreen.fight.currentMonster);
					gameScreen.cd.tm = gameScreen.tm;
					Client.gameScreenDrawer.collabDrawer.endBattle(gameScreen.fight.currentMonster);
//					GameScreen.getInstance().cd.endBattle(gameScreen.fight.currentMonster);
//					GameScreenDrawer.getInstance().collabDrawer.endBattle(gameScreen.fight.currentMonster);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				Client.gameStatus.fight = FightStatus.ROLLPROMPT;
			}
		
		}
		
		
		System.out.println(gameScreen.fight.heroTotalRoll);
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
