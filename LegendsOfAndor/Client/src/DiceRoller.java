import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

import org.minueto.MinuetoEventQueue;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.window.MinuetoWindow;

public class DiceRoller implements Serializable {
	
	private int[] numbers;
	private final int[] normalDice;
	private final int[] blackDice;
	private Random random;
	
	public DiceRoller() {
		normalDice = new int[]{1,2,3,4,5,6};
		blackDice = new int[] {6,7,8,9,10,11};
		numbers = normalDice;
		random = new Random();
	}

	
	//function for Mage to turn a roll to opposite value
	public int getOppositeNumber(int rollValue) {
		int value = rollValue;
		return 6 + 1 - value ;
	}
	
	//return sum of identical rolls or greater roll, depending on which is larger
	public int getTotalIdentical(ArrayList<Integer> rolls) {
		
		int value = 0;
		int temp = getHighestRoll(rolls);
		int sum = 0;
		
		ArrayList<Integer> identicals = (ArrayList<Integer>) rolls.stream().filter(i -> Collections.frequency(rolls, i) > 1).collect(Collectors.toList());
		
		if(identicals.isEmpty()) {
			value = temp;
		}
		
		else if(Collections.frequency(identicals, identicals.get(0)) == identicals.size()) {
			sum = identicals.stream().mapToInt(Integer::intValue).sum();
			value = sum > temp ? sum : temp;
		}
		
		else {
			int nb1 = identicals.get(0);
			int nb2 = 0;
			
			for(int i = 0; i < identicals.size(); i++) {
				if(nb1 == identicals.get(i)) {
					nb1 += nb1;
				}
				
				else {
					nb2 += identicals.get(i);
				}
			}
			
			sum = nb1 > nb2 ? nb1 : nb2;
			
		}
		
		value = sum > temp ? sum : temp;
		
		return value;
	}
	
	//take a list as input and find the highest value 
	public int getHighestRoll(ArrayList<Integer> rolls) {
		
		int value = 0;
		
		for(int i = 0; i < rolls.size(); i++) {
			if(rolls.get(i) > value) {
				value = rolls.get(i);
			}
		}
		
		return value;
	}
	
	
	public ArrayList<Integer> roll(int times){
		
		ArrayList<Integer> diceRoll = new ArrayList<Integer>();
		for(int i = 0; i < times; i++) {
			diceRoll.add(numbers[random.nextInt(6)]);
		}
		return diceRoll;
		
	}
	
	//roll a dice
	public ArrayList<Integer> roll(Character c) {
		//Hero needs to add his current strength points to his highest roll
		ArrayList<Integer> diceRoll = new ArrayList<Integer>();
		if(c instanceof Hero) {
			numbers = ((Hero) c).hasBlackDice ? blackDice : normalDice;
		}
		
		
		if(c instanceof Warrior) {
			diceRoll = warriorRoll(((Warrior) c).wp);
		}
		
		else if(c instanceof Archer) {
			//archerRoll() is incomplete: archer can only re-roll the die for a limited number of time (= number of dice he has)
			diceRoll = archerRoll(((Archer) c).wp);
		}
		
		else if(c instanceof Dwarf) {
			diceRoll = dwarfRoll(((Dwarf) c).wp);
		}
		
		else if(c instanceof Mage) {
			diceRoll = mageRoll();
		}
		
		else if(c instanceof Gor) {
			diceRoll = gorRoll();
		}
		
		else if(c instanceof Skral) {
			diceRoll = skralRoll();
		}
		
		numbers = normalDice;
		return diceRoll;
	}
	
	private ArrayList<Integer> skralRoll() {
		ArrayList<Integer> rolls = new ArrayList<Integer>();
		int nbDice = 2;
		
		for(int i = 0; i <= nbDice; i++) {
			rolls.add(numbers[random.nextInt(6)]);
		}
		
		return rolls;
	}

	
	private ArrayList<Integer> gorRoll() {
		ArrayList<Integer> rolls = new ArrayList<Integer>();
		int nbDice = 2;
		
		for(int i = 0; i <= nbDice; i++) {
			rolls.add(numbers[random.nextInt(6)]);
		}
		
		return rolls;
	}
	
	
	private ArrayList<Integer> mageRoll() {
		ArrayList<Integer> rolls = new ArrayList<Integer>();
		rolls.add(numbers[random.nextInt(6)]);
		return rolls;
	}
	
	private ArrayList<Integer> dwarfRoll(int wp) {
		ArrayList<Integer> rolls = new ArrayList<Integer>();
		int nbDice = 0;
		
		if(wp < 7) {
			nbDice = 1;
		}
		
		else if(wp >= 7 && wp < 14) {
			nbDice = 2;
		}
		
		else {
			nbDice = 3;
		}
		
		for(int i = 0; i <= nbDice; i++) {
			int temp = random.nextInt(6);
			rolls.add(numbers[temp]);
		}
		
		return rolls;
	}
	
	private ArrayList<Integer> archerRoll(int wp) {
		ArrayList<Integer> rolls = new ArrayList<Integer>();
		rolls.add(numbers[random.nextInt(6)]);
		return rolls;
	}
	
	
	private ArrayList<Integer> warriorRoll(int wp) {
		ArrayList<Integer> rolls = new ArrayList<Integer>();

		int nbDice = 0; //number of dice a hero has
		
		if(wp < 7) {
			nbDice = 2;
		}
		
		else if(wp >= 7 && wp < 14) {
			nbDice = 3;
		}
		
		else {
			nbDice = 4;
		}
		
		for(int i = 0; i <= nbDice; i++) {
			int temp = random.nextInt(6);
			rolls.add(numbers[temp]);

		}
		
		return rolls; 
	}

}



