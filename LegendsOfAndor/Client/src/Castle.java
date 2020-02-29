
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
	public void IncreaseHealthIfThereIsFarmer() {
		// assume tile 0 is the place of castle
		// if the farmer was dropped at tile 0, then farmer will appear at the tileentities list, heath++ then remove the farmer
		for(TileEntity t: Tile.get(0).getTileEntities()) {
			if(t instanceof Farmer) {
				health++;
				Tile.get(0).getTileEntities().remove(t);
			}
			
		}
	}
}
}
