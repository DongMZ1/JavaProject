import java.io.IOException;
import java.io.Serializable;

public class Castle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2443137192783921016L;
	public int health;
	public String message;

	public Castle(int health) throws IOException {
		this.health = health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public void damage(Monster m) {
    	//TODO: Should decrease shield count of castle
		if (m instanceof Monster) {
			//UPDATE
			System.out.println("Damaged castle by Gor");
			health--;
		}
		else {
			
		}
		
		if (health <= 0) {
			message = "Game Over Rookies\n";
			Cards N = new Cards(310);
		}
    	
    }
	public void IncreaseHealthIfThereIsFarmer() {
	
}
}
