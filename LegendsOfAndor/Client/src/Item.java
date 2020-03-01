import org.minueto.image.MinuetoImage;

public interface Item extends TileEntity {
	 public MinuetoImage getImage();

	    public int getTile();
	    public void setTile(int tile);
}