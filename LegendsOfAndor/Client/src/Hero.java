import java.util.ArrayList;

import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class Hero implements TileEntity {
    MinuetoImage heroImage;
    int tile;
    GameStatus gameStatus;
    Time time;
    Farmer farmer = null;
    boolean hasfarmer = false;
    //get a final list of items
    public final ArrayList<Items> items = new ArrayList<Items>();
    
    public final boolean mainHero;
    public Hero(MinuetoImage heroImage, int tile, boolean mainHero) throws MinuetoFileException {
        this.heroImage = heroImage;
        this.tile = tile;
        this.gameStatus = GameStatus.getInstance();
        this.mainHero = mainHero;
           }
    public void setTile(int tile) {
        this.tile = tile;
    }
    public int getTile() {
        return this.tile;
    }
    public MinuetoImage getImage() {
        return this.heroImage;
    }
    
  public void pickupFarmer(Farmer farmer) {
	  if(!hasfarmer) {
		  hasfarmer = true;
		  this.farmer = farmer;
		  farmer.isguideby(this);
		  items.add(farmer);
		  // call farmer.isguide to assign hero to the farmer, then add farmer as a item to itemlist of hero 
	  }
  }
  //remove farmer from itemlist
  public void dropFarmer() {
	  if(hasfarmer) {
		  hasfarmer = false;
		  this.farmer = null;
		  farmer.isdropped();
		  //this for loop check if the item is farmer, then remove it.
		  for(Items i: items) {
			  if(i instanceof Farmer) {
				  items.remove(i);
			  }
		  }
	  }
  }
  //this method allow that if the hero move to different regions, the items which hero
  // processed will also change its tile(move with heros), this should be called after hero's movement
  public void keepupdateHeroTile() {
	  for(Items i : items) {
		  i.tile = this.tile;
	  }
  }

    // TODO Move HeroDraw to Hero
    // TODO Scale drawing of Hero with boardZoom
/*
    public void draw() {
        gameStatus.screen, );
    }
 */
}
