import java.util.ArrayList;

public class TurnManager {
	/**
	 * 
	 */
	
	private ArrayList<Hero> heroes;
	private int index = 0;
	
	public TurnManager(ArrayList<Hero> heroes)
	{
		this.heroes = heroes;
		for (Hero hero : heroes) {
			GameScreen.tiles.get(hero.tile).addTileEntity(hero);
		}
	}
	
	public void addHero(Hero hero) {
		heroes.add(hero);
		GameScreen.tiles.get(hero.tile).addTileEntity(hero);
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
	public void draw() {
		for (Hero hero : heroes) {
			hero.time.draw();
		}
	}
}
