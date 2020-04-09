import java.io.Serializable;

import org.minueto.image.MinuetoImage;

public interface Item extends TileEntity, Serializable {
	    public int getTile();
	    //UPDATE
	    public void setTile(int tile);
}