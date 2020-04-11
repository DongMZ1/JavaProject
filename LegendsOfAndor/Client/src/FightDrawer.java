import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;
import org.minueto.image.MinuetoRectangle;
import org.minueto.window.MinuetoWindow;

import java.io.IOException;

public class FightDrawer implements Inputtable{
	private GameStatus gameStatus;
	MinuetoImage background;
	public Fight fight;

	private MinuetoImage mageImage;
	private MinuetoImage warriorImage;
	private MinuetoImage archerImage;
	private MinuetoImage dwarfImage;
	private MinuetoImage gorImage;

	private Button rollButton;
	private Button yourTurn;
	private Button notYourTurn;
	private Button confirm;
	private Button damageButton;
	private MinuetoImageFile diceRoll;
	private Button rollAgain;			//button only shown to archer class
	private Button changeRollResult;	//button only shown to mage class
	public FightDrawer(Fight fight) throws IOException {
		gameStatus = GameStatus.getInstance();
		background = new MinuetoRectangle(gameStatus.screenWidth, gameStatus.screenHeight, MinuetoColor.RED, true);
		rollButton = new Button(new Coordinate(700,600),50,50,"ROLL DICE",true);
		yourTurn = new Button(new Coordinate(700,500),50,50,"Your Turn",false);
		confirm = new Button(new Coordinate(760,500),50,50,"OK",true);
		notYourTurn = new Button(new Coordinate(700,500),50,50,"Not Your Turn",false);
		rollAgain = new Button(new Coordinate(700,500),50,50,"Roll Again",false);
		changeRollResult = new Button(new Coordinate(690,500),50,50,"CRR",false);
		this.fight = fight;
	}

	public void draw() {
		Client.screen.draw(background, 0, 0);
		for (Tuple<Character, Coordinate> member : fight.fightMembers) {
			if(member.first instanceof Mage)
				Client.screen.draw(mageImage, member.second.getX(), member.second.getY()*200);
			else if(member.first instanceof Dwarf)
				Client.screen.draw(dwarfImage, member.second.getX(), member.second.getY()*200);
			else if(member.first instanceof Archer)
				Client.screen.draw(archerImage, member.second.getX(), member.second.getY()*200);
			else if(member.first instanceof Warrior)
				Client.screen.draw(warriorImage, member.second.getX(), member.second.getY()*200);
			else if(member.first instanceof Gor)
				Client.screen.draw(gorImage, member.second.getX(), member.second.getY()*200);
		}
		if (gameStatus.fight == FightStatus.ROLLPROMPT) {
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

			if (fight.inFight(fight.mainHero)) {
				if (fight.mainHero instanceof Mage) {
					changeRollResult.setClickable(true);
					changeRollResult.draw();
				}
				if (fight.mainHero instanceof Archer) {
					confirm.setClickable(true);
					confirm.draw();
				}
			}

			/*
			if (fight.mainHero == fight.currentHero && fight.mainHero.dice.hasRolls()) {
				rollAgain.setClickable(true);
				rollAgain.draw();
			}
			else if (fight.mainHero == fight.currentHero) {
				rollAgain.setClickable(false);;
				confirm.draw();
			}
			*/
			Client.screen.draw(diceRoll, 700, 600);
		}
		else if (gameStatus.fight == FightStatus.ROLLMONSTER) {
			if (fight.mainHero == fight.currentHero) {
				confirm.draw();
			}
			Client.screen.draw(diceRoll, 700, 600);
		}
		else if (gameStatus.fight == FightStatus.DAMAGE) {
			if (fight.mainHero == fight.currentHero) {
				confirm.draw();
			}
			damageButton.draw();
		}
		//TODO Draw items once items are implemented
	}

	public void heroRoll(int roll) throws MinuetoFileException {
		String diceFile = ("images/Heroes/Dice/" + roll + ".png");
		System.out.println(diceFile);
		diceRoll = new MinuetoImageFile(diceFile);
	}

	public void monsterRoll(int roll) throws MinuetoFileException {
		String diceFile = ("images/Monsters/Dice/" + roll + ".png");
		System.out.println(diceFile);
		diceRoll = new MinuetoImageFile(diceFile);
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

		if (fight.mainHero instanceof Mage)
			rollAgain.setClickable(true);

		if (fight.mainHero == fight.currentHero && rollButton.isClicked(x, y) && rollButton.isClickable()) {
			/*
			if (fight.currentHero.dice.hasRolls()) {

				fight.currentRoll = fight.currentHero.dice.roll();
				fight.targetDice = fight.currentHero.dice;

				try {
					heroRoll(fight.currentRoll);
				}
				catch (Exception e) {System.out.println("Error yo");}
				confirm.setClickable(false);

			}
			gameStatus.setFight(FightStatus.ROLLPROMPT);
			if (!fight.currentHero.dice.hasRolls()) {
				rollAgain.setClickable(false);
				confirm.setClickable(true);
			}
			//		rollAgain.setClickable(false);
			*/
		}

		//allow only mage class to change roll result
		else if (fight.mainHero instanceof Mage && changeRollResult.isClicked(x, y) && changeRollResult.isClickable() && gameStatus.fight == FightStatus.ROLLRESPONSE) {
			System.out.println("MAGE BUTTON PRESSED");

			try {
				heroRoll(fight.flipRoll(fight.currentRoll));
			}
			catch (Exception e) {System.out.println("ian is cool");}

		}

		else if (fight.mainHero == fight.currentHero && rollAgain.isClicked(x, y) && rollAgain.isClickable() && gameStatus.fight == FightStatus.ROLLRESPONSE) {
			gameStatus.setFight(FightStatus.ROLLPROMPT);
		}

		else if (fight.mainHero == fight.currentHero && confirm.isClicked(x, y) && confirm.isClickable() && gameStatus.fight == FightStatus.ROLLRESPONSE) {
			/*
			if (fight.currentHero.dice.hasRolls() && !(fight.currentHero instanceof Archer)) {
				System.out.println("BADBABBAD");
			}
			else {
				fight.herosLeft--;
				System.out.println(fight.herosLeft);
				fight.heroRoll += fight.currentHero.dice.getBattleNum();	// add up battle value

				fight.currentHero.dice.endTurn();		// reset dice state
				if (fight.herosLeft != 0) {

					fight.currentHero = fight.tm.endTurn();
					gameStatus.setFight(FightStatus.ROLLPROMPT);
				}
				else {
					while (fight.currentMonster.dice.hasRolls())
						fight.currentMonster.dice.roll();
					fight.monsterRoll = fight.currentMonster.dice.getBattleNum();
					try {
						monsterRoll(fight.monsterRoll);
					}
					catch (Exception e) {}
					System.out.println("monster rolled " + fight.monsterRoll);
					gameStatus.setFight(FightStatus.ROLLMONSTER);
				}
			}
			 */
		}
		else if (fight.mainHero == fight.currentHero && confirm.isClicked(x, y) && confirm.isClickable() && gameStatus.fight == FightStatus.ROLLMONSTER) {
			String damage = "";
			if (fight.heroRoll > fight.monsterRoll) {

				fight.currentMonster.health -= (fight.heroRoll - fight.monsterRoll);
				damage = ("Monsters took " + (fight.heroRoll - fight.monsterRoll) + " damage.");

			}
			else {
				for (Hero h : fight.fightHeroes) {
					h.wp -= (fight.monsterRoll - fight.heroRoll);
					damage = ("Heroes took " + (fight.monsterRoll - fight.heroRoll) + " damage.");

				}
			}
			try {
				damageButton = new Button(new Coordinate(700,300), 200, 50, damage, false);
				gameStatus.setFight(FightStatus.DAMAGE);
			}
			catch (Exception e) {
				System.out.println(e);
			}

		}
		else if (fight.mainHero == fight.currentHero && confirm.isClicked(x, y) && confirm.isClickable() && gameStatus.fight == FightStatus.DAMAGE) {
			gameStatus.setFocus(GameStatus.FOCUS_ON_GAMESCREEN);
			gameStatus.setCurrentScreen(GameStatus.GAME_SCREEN);
			//GameScreen.currentHero.time.advance();
			//GameScreen.currentHero = fight.tm.endTurn();

			fight.isHappening = false;
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
