import org.minueto.image.MinuetoImage;

public interface TileEntity {
    public MinuetoImage getImage();

    public int getTile();
    //UPDATE
    public void setTile(int tile);
}
