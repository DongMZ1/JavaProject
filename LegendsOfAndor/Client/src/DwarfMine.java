import org.minueto.image.MinuetoImage;

public class DwarfMine implements TileEntity{

	static MinuetoImage mineImage;
	int tile;

	public DwarfMine(MinuetoImage Image,int newTile ) {
		mineImage = Image;
		tile = newTile ;
	}
	
	@Override
	public MinuetoImage getImage() {
		// TODO Auto-generated method stub
		return mineImage;
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
