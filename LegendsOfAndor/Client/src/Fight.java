import java.util.ArrayList;

import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoRectangle;
import org.minueto.window.MinuetoWindow;

public class Fight {

	private MinuetoWindow screen;
	MinuetoImage background;
	private Tile fightTile;
	boolean isHappening = false;
	TurnManager tm;
	ArrayList<Tuple<TileEntity,Coordinate>> fightMembers = new ArrayList<>();
	
	public Fight(MinuetoWindow screen, int x, int y, TurnManager tm) {
		this.screen = screen;
		this.tm = tm;
		background = new MinuetoRectangle(x, y, MinuetoColor.BLACK, true);
	}
	
	public void start(Tile fightTile) {
		this.fightTile = fightTile;
		isHappening = true;
		int monsterOffset = 1;
		for (TileEntity entity : fightTile.getTileEntities()) {
			//member is a Hero
			System.out.println(entity);
			if (tm.contains(entity)) {
				fightMembers.add(new Tuple<TileEntity,Coordinate>(entity,new Coordinate(200, tm.indexOf(entity) + 1)));
				System.out.println(tm.indexOf(entity) + 1);
			}
			
			//member is a Monster
			else {
				fightMembers.add(new Tuple<TileEntity,Coordinate>(entity,new Coordinate(500, monsterOffset)));
				monsterOffset++;
			}
			
		}
		
		
		
	}
	
	public void draw() {
		this.screen.draw(background, 0, 0);
		for (Tuple<TileEntity, Coordinate> member : fightMembers) {
			this.screen.draw(member.first.getImage(), member.second.getX(), member.second.getY()*200);
		}
	}
	
	
}
