import java.io.IOException;
import java.io.Serializable;

public class Castle implements Serializable{

	public int health;
	public String message;

	public Castle(int health) throws IOException {
		this.health = health;
	}
	
	public void damage(Monster m) {
    	//TODO: Should decrease shield count of castle
		if (m.getClass() == Gor.class) {
			//UPDATE
			System.out.println("Damaged castle by Gor");
			health--;
		}
		else {
			
		}
		
		if (health <= 0) {
			message = "Game Over Rookies\n";
		}
    	
    }
	public void IncreaseHealthIfThereIsFarmer() {
	
}
}
