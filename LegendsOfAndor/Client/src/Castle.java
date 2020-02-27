
public class Castle {

	int health;
	public Castle(int health) {
		this.health = health;
	}
	public void damage(Monster m) {
    	//TODO
    	// Should decrease shield count of castle
		if (m.getClass() == Gor.class) {
			System.out.println("Damaged castle by Gor");
			health--;
		}
		else {
			
		}
		
		if (health == 0) {
			System.out.println("Game Over");
		}
    	
    }
}
