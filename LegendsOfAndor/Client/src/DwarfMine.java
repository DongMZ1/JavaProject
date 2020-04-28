import org.minueto.image.MinuetoImage;

public class DwarfMine implements TileEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5750501813044605449L;
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
