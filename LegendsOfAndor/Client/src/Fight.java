import java.util.ArrayList;

public class Fight {

	Tile fightTile;
	boolean isHappening = false;
	ArrayList<TileEntity> fightMembers;
	public Fight() {
		//Fighting is happening on fightTile
	}
	
	public void start() {
		fightMembers = fightTile.getTileEntities();
		System.out.println("FIGHT STARTED WITH ");
		{
			for (TileEntity member : fightMembers) {
				System.out.println(member.toString());
			}
		}
	}
	
	
}
