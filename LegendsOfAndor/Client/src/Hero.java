import java.util.ArrayList;

import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class Hero implements Character {
    MinuetoImage heroImage;
    int tile;
    int wp;
    int gold;
    GameStatus gameStatus;
    Time time;
    Farmer farmer = null;
    boolean hasfarmer = false;
    //get a final list of items
    public final ArrayList<Item> items = new ArrayList<Item>();
    
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
		  farmer.isGuidedBy(this);
		  
		  // call farmer.isguide to assign hero to the farmer, then add farmer as a item to itemlist of hero 
	  }
  }
  //remove farmer from itemlist
  public void dropFarmer() {
	  if(hasfarmer) {
		  hasfarmer = false;
		  this.farmer = null;
		  farmer.isDropped();
		  //this for loop check if the item is farmer, then remove it.
		  
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
