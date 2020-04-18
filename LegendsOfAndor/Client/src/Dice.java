import java.io.Serializable;
import java.util.Random;

public class Dice implements Serializable {
	private int[] numbers;
	private Random random;
	public int roll() {
		return numbers[random.nextInt(numbers.length)];
	}

	public Dice(int[] numbers) {
		this.numbers = numbers;
		random = new Random();
	}

	public int getOppositeNumber(int number) {
		int index = 0;
		while(index < 6 && numbers[index] != number) index++;
		if(index < 3)
			return numbers[index + 3];
		else
			return numbers[index - 3];
	}

}
