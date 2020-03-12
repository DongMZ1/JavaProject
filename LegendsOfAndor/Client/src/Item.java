import java.io.Serializable;

import org.minueto.image.MinuetoImage;

public interface Item extends TileEntity, Serializable {
	 public MinuetoImage getImage();

	    public int getTile();
	    //UPDATE
	    public void setTile(int tile);
}