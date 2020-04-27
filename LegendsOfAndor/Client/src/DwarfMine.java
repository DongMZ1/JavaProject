import org.minueto.image.MinuetoImage;

public class DwarfMine implements TileEntity{

	int tile;

	public DwarfMine(int newTile ) {
		tile = newTile ;
	}

	@Override
	public int getTile() {
		// TODO Auto-generated method stub
		return tile;
	}

	@Override
	public void setTile(int Tile) {
		tile = Tile;
		
	}

}
