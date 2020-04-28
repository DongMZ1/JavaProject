import java.io.Serializable;

public interface TileEntity extends Serializable{
    public int getTile();
    //UPDATE
    public void setTile(int tile);
}
