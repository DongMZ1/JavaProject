import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.Stack;

import org.minueto.image.MinuetoImage;


enum DieColor {
	BLUE, GREEN, YELLOW, PURPLE, RED, BLACK
}

public interface Die {
	int roll();
	int getLastRollIndex();
}

class PlayingDie implements Die {

	private final DieColor dieColor;
	private int[] dieNumbers;
	private int lastRolledIndex;
	
	public static final int[] regularDieNumbers = {1, 2, 3, 4, 5, 6};
	public static final int[] blackDieNumbers = {6, 6, 8, 10, 10, 12};
	
	private PlayingDie(DieColor dieColor, int[] dieNumbers) {
		this.dieColor = dieColor;
		this.dieNumbers = dieNumbers;
	}
	
	public int[] getDieNumbers() {
		return dieNumbers;
	}
	
	public DieColor getDieColor() {
		return dieColor;
	}
	
	static Random random = new Random();
	
	@Override
	public int roll() {	
		lastRolledIndex = random.nextInt(6);
		return dieNumbers[lastRolledIndex];
	}
	
	public int getCurrentNumber() {
		return dieNumbers[lastRolledIndex];
	}
	
	@Override
	public int getLastRollIndex() {
		return lastRolledIndex;
	}
	
	private static final PlayingDie BLUEDIE = new PlayingDie(DieColor.BLUE,  regularDieNumbers);
	private static final PlayingDie GREENDIE = new PlayingDie(DieColor.GREEN, regularDieNumbers);
	private static final PlayingDie YELLOWDIE = new PlayingDie(DieColor.YELLOW, regularDieNumbers);
	private static final PlayingDie PURPLEDIE = new PlayingDie(DieColor.PURPLE, regularDieNumbers);
	private static final PlayingDie REDDIE = new PlayingDie(DieColor.RED, regularDieNumbers);
	private static final PlayingDie BLACKDIE = new PlayingDie(DieColor.BLACK, blackDieNumbers);
	
	public static PlayingDie blueDieInstance() { return BLUEDIE; }
	public static PlayingDie greenDieInstance() { return GREENDIE; }
	public static PlayingDie yellowDieInstance() { return YELLOWDIE; }
	public static PlayingDie purpleDieInstance() { return PURPLEDIE; }
	public static PlayingDie redDieInstance() { return REDDIE; }
	public static PlayingDie blackDieInstance() { return BLACKDIE; }

}


abstract class Dice {
	
	protected PlayingDie regularDie;
	private Optional<PlayingDie> blackDie = Optional.empty();
	protected PlayingDie currentlyUsedDie;
	
	protected Stack<Integer> rolledNums = new Stack<>();
	
	protected int currentNumOfDice;
	protected int rollsLeft;
	
	public void addBlackDie() {
		blackDie = Optional.of(PlayingDie.blackDieInstance());
		currentNumOfDice --;
	}
	
	public void removeBlackDie() {
		blackDie = Optional.empty();
		currentNumOfDice ++;
	}
	
	protected void useRegularDie() {
		currentlyUsedDie = regularDie;
	}
	
	protected void useBlackDie() {
		if (blackDie.isPresent())
			currentlyUsedDie = blackDie.get();
	}
		
	public int roll() {	
		int number = 5;
		if (rollsLeft > 0) {
			number = currentlyUsedDie.roll();
			rollsLeft --;
		}
		rolledNums.push(number);
		
		return number;
	}
	
	public boolean hasRolls() {
		return rollsLeft > 0;
	}

	protected void setCurrentNumber(int number) {
		rolledNums.pop();
		rolledNums.push(number);
	}
	
	public int getLastRollIndex() {
		return currentlyUsedDie.getLastRollIndex();
	}
	
	public int[] getCurrentDieNumbers() {
		return currentlyUsedDie.getDieNumbers();
	}
	
	public int getBattleNum() {
		int max = 0;
		while (rolledNums.size() > 0) {
			int value = rolledNums.pop();
			if (value > max)
				max = value;
		}
		return max;
	}
	
	public void endTurn() {
		rollsLeft = currentNumOfDice;
	}
	
	public int getRollsLeft() {
		return rollsLeft;
	}
	
	void upgrade() { currentNumOfDice ++; rollsLeft ++;}
	void downgrade() {currentNumOfDice --; rollsLeft --;}
}

class ArcherDice extends Dice {	
	
	public ArcherDice() {
		currentNumOfDice = 3;
		rollsLeft = currentNumOfDice;
		regularDie = PlayingDie.greenDieInstance();
		currentlyUsedDie = regularDie;
	}

	@Override
	public int roll() {
		
		
		if (rolledNums.isEmpty())
			rolledNums.push(super.roll());
		else {
			rolledNums.pop();
			rolledNums.push(super.roll());
		}
			
		return rolledNums.peek();
	}
	
	@Override
	public int getBattleNum() {
		return rolledNums.peek();
	}
	
}
	


class DwarfDice extends Dice {
	
	public DwarfDice() {
		currentNumOfDice = 1;
		rollsLeft = currentNumOfDice;
		regularDie = PlayingDie.yellowDieInstance();
		currentlyUsedDie = regularDie;
	}
}


class MageDice extends Dice {
	
	public MageDice() {
		regularDie = PlayingDie.purpleDieInstance();
		currentlyUsedDie = regularDie;
		currentNumOfDice = 1;
		rollsLeft = currentNumOfDice;
	}
	
	public void flipRoll(Dice target) {
		int flipIndex = 5 - target.getLastRollIndex();
		int flipNumber = target.getCurrentDieNumbers()[flipIndex];
		target.setCurrentNumber(flipNumber);
	};	
	
	@Override
	public int roll() {
		if (rolledNums.isEmpty())
			rolledNums.push(super.roll());
		else rolledNums.pop();
		rolledNums.push(super.roll());
		return rolledNums.peek();
	}
	
	@Override
	void upgrade() {}
	@Override
	void downgrade() {}
}
	

class WarriorDice extends Dice {

	public WarriorDice() {
		currentNumOfDice = 2;
		rollsLeft = currentNumOfDice;
		regularDie = PlayingDie.blueDieInstance();
		currentlyUsedDie=regularDie;
	}
}


class MonsterDice extends Dice {

	public MonsterDice() {
		currentNumOfDice = 2;
		rollsLeft = currentNumOfDice;
		regularDie = PlayingDie.redDieInstance();
		currentlyUsedDie = regularDie;
	}

	public int getBattleNum() {
		int value = 0;
		int max = 0;
		int previous = 0;
		while (rolledNums.size() > 0) {
			value = rolledNums.pop();
			
			if (value == previous) {
				value = value * 2;
			}
			if (value > max)
				max = value;
			
			previous = value;
		}
		return max;
	}

}


