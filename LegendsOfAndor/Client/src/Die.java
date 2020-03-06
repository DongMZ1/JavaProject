import java.util.Arrays;
import java.util.Random;

enum DiceColor {
	BLUE, GREEN, YELLOW, PURPLE, RED, BLACK
}

public interface Die {
	
	public void roll();
	public void rollOnce();
	public int getSum();
	public void flipRoll();
	
}

class playingDie implements Die {
	// TODO integrate functionality in characters and monsters
	
	private DiceColor diceColor; 
	private int currNumOfDice;
	private final int maxNumOfDice;
	private int[] numbersRolled;
	private int lastRolled;
	private int[] dieNumbers;
	private Random random = new Random();

	
	public playingDie (DiceColor diceColor, int currNumOfDice, int maxNumOfDice, int[] dieNumbers) {
		assert currNumOfDice > 0 && currNumOfDice <= maxNumOfDice && dieNumbers.length == 6;
		
		this.diceColor = diceColor;
		this.currNumOfDice = currNumOfDice;
		this.maxNumOfDice = maxNumOfDice;
		this.numbersRolled = new int[maxNumOfDice];
		this.dieNumbers = dieNumbers;
	
	}
	
	void reset() {
		for (int i = 0; i < maxNumOfDice; i++)
			numbersRolled[i] = 0;
	}
	
	@Override
	public void roll() {
		reset();
		for (int i = 0; i < currNumOfDice; i++)
			numbersRolled[i] = dieNumbers[random.nextInt(6)];
	}
	
	@Override
	public void rollOnce() {
		lastRolled = dieNumbers[random.nextInt(6)];
	}

	
	public int getSum() {
		return Arrays.stream(numbersRolled).sum();
	}

	@Override
	public void flipRoll() {
		
	}
	
}