import java.io.Serializable;
import java.util.ArrayList;

public class TurnManager implements Serializable{
	/**
	 * 
	 */
	private static TurnManager tm;
	public ArrayList<Hero> heroes;
	private int index = 0;
	
	public TurnManager(ArrayList<Hero> heroes)
	{
		this.heroes = heroes;
		for (Hero hero : heroes) {
			Tile.get(hero.tile).addTileEntity(hero);
		}
	}
	
	public void addHero(Hero hero) {
		heroes.add(hero);
		Tile.get(hero.tile).addTileEntity(hero);
	}
	
	public void newDay() {
		for (Hero hero : heroes)
    	{
    		hero.time.reset();
    	}
	}
	
	public Hero endTurn() {
		return getHero();
	}
	
	public boolean timeLeft() {
		for (Hero hero : heroes) {
			if (hero.time.getTime() < 10) {
				return true;
			}
		}
		return false;
	}
	
	
	
	public boolean contains(TileEntity hero) {
		return heroes.contains(hero);
	}
	
	public int indexOf(TileEntity hero) {
		return heroes.indexOf(hero);
	}
	
	//Circular array getHero
	public Hero getHero() {
		index++;
		if (index == heroes.size())
		{
			index = 0;
		}
		return heroes.get(index);
	}

	public int getSize() {
		return heroes.size();
	}	
}
