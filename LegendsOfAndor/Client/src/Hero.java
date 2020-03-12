import java.io.IOException;
import java.util.ArrayList;

import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class Hero implements Character {
	MinuetoImage heroImage;
	int tile;
	int wp;
	int sp;
	GameStatus gameStatus;
	Time time;
	Farmer farmer = null;
	boolean hasfarmer = false;
	boolean hasGold = false;
    boolean holdWineSkinForUse = false; //set wineskin to ready to use before using it, example: 
    //set it true before move, so that timer will not increase if this attribute is true
    //get a final list of items
    //get a final list of items
    public final ArrayList<Item> items = new ArrayList<Item>();
    public final boolean mainHero;
    Dice dice;
    //UPDATE
 
    public void setholdWineSkinForUse(boolean holdWineSkinForUse) {
    	this.holdWineSkinForUse = holdWineSkinForUse;
    }
    public void sethasGold(boolean hasGold) {
    	this.hasGold = hasGold;
    }
    public void sethasfarmer(boolean hasfarmer) {
    	this.hasfarmer = hasfarmer;
    }
    public void setFarmer(Farmer farmer) {
    	this.farmer = farmer;
    }
    public void setTime(Time time) {
    	this.time = time;
    }
    public void setgameStatus(GameStatus gameStatus) {
    	this.gameStatus = gameStatus;
    }
    public void setsp(int sp) {
    	this.sp = sp;
    }
    public void setwp(int wp) {
    	this.wp = wp;
    }
    public Hero(MinuetoImage heroImage, int tile, boolean mainHero) throws IOException {
        this.heroImage = heroImage;
        this.tile = tile;
        this.gameStatus = GameStatus.getInstance();
        this.mainHero = mainHero;
        this.sp = 1;
        this.wp = 7;
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
  
    
     public void addWineSkin() throws MinuetoFileException {
    	//
    	int wineSkinCount = 0;
		  for(Item i: items) {
			  if(i instanceof Wineskin) {
				  wineSkinCount ++;
			  }
		  }
		  if(wineSkinCount < 2) {
			  this.items.add(new Wineskin(this.tile));
		  }
    }
    
    public void ReadytoUseWineskin() {
    	if(holdWineSkinForUse == false) {
    	for(Item i: items) {
			  if(i instanceof Wineskin) {
				  holdWineSkinForUse = true;
				  items.remove(i);
				  break; //use a wineskin, remove the wine skin item from items list, set holdWineSkinForUse = false
			  }
		  }
    }
    }

	//UPDATE
	public void setTile(int tile) {
		this.tile = tile;
	}
	public int getTile() {
		return this.tile;
	}
	public MinuetoImage getImage() {
		return this.heroImage;
	}

	//UPDATE
	public void addWineSkin() throws MinuetoFileException {
		int wineSkinCount = 0;
		for(Item i: items) {
			if(i instanceof Wineskin) {
				wineSkinCount ++;
			}
		}
		if(wineSkinCount < 2) {
			this.items.add(new Wineskin(this.tile));
		}
	}

	public void ReadytoUseWineskin() {
		if(holdWineSkinForUse == false) {
			for(Item i: items) {
				if(i instanceof Wineskin) {
					holdWineSkinForUse = true;
					items.remove(i);
					break; //use a wineskin, remove the wine skin item from items list, set holdWineSkinForUse = false
				}
			}
		}
	}


	//UPDATE
	public void replenishWP() {
		ArrayList<TileEntity> entities = Tile.get(tile).getTileEntities(); 	
		if(entities != null) {
			for (TileEntity t : entities) {
				if (t instanceof Well) {
					try {
						wp += ((Well) t).emptiedByHero(this);
					} catch (MinuetoFileException e) {
						
						e.printStackTrace();
					}
				}
			}
    	}
    }  
//UPDATE	
  public void pickupFarmer() {
	  if(!hasfarmer) {
		  for(TileEntity t: Tile.get(tile).getTileEntities()) {
			  if(t instanceof Farmer) {
				  hasfarmer = true;
				  this.farmer = (Farmer)t;
				  Tile.get(tile).getTileEntities().remove(t);
				  farmer.isGuidedBy(this);
				  break;
				  //remove the farmer from tileEntities list also if there are two farmer in the tileEntities list, break can prevent bug
			  }
		  }
		 
		  
		  // call farmer.isguide to assign hero to the farmer, then add farmer as a item to itemlist of hero 
	  }
  }
  //UPDATE
  public void dropFarmer() {
	  if(hasfarmer) {
		  hasfarmer = false;
		  Tile.get(tile).getTileEntities().add(this.farmer);	  
		  farmer.isDropped();
		  this.farmer = null;
	  }
  }


			// call farmer.isguide to assign hero to the farmer, then add farmer as a item to itemlist of hero 
		}
	}
	//UPDATE
	public void dropFarmer() {
		if(hasfarmer) {
			hasfarmer = false;
			Tile.get(tile).getTileEntities().add(this.farmer);	  
			farmer.isDropped();
			this.farmer = null;
		}
	}

	//UPDATE
	public void pickupGold() {
		for (TileEntity t: Tile.get(tile).getTileEntities()) {
			if(t instanceof Gold) {
				this.items.add((Gold)t);
				Tile.get(tile).getTileEntities().remove(t);
				//only pick up 1 gold(one gold object represents one gold) each time
				hasGold = true;
				break;
			}
		}
	}	
	//UPDATE
	public void dropGold() {
		for (Item i: items) {
			if(i instanceof Gold) {
				this.items.remove(i);
				Tile.get(tile).getTileEntities().add(i);
				//only pick up 1 gold(one gold object represents one gold) each time
				if(getGoldNm() == 0){
					hasGold = false;
				}
				break;
			}
		}
	}

	public int getGoldNm() {
		int goldCount = 0;
		for(Item i: items) {
			if(i instanceof Gold) {
				goldCount ++;
			}
		}
		return goldCount;
	}

	//UPDATE
	public void Buy2WPfor2Gold() {
		//firstly, find out if the hero has more than two gold for trade willpower
		if(getGoldNm() > 2) { 
			//Second check if there is a merchant
			for(TileEntity t: Tile.get(tile).getTileEntities()) {
				if(t instanceof Merchant) {
					//if there is a merchant then trade, set a counter to remove two gold from items list of hero
					int counter = 0;
					for(Item i: items) {

						//if we already remove two gold, then add two wp, otherwise continue remove gold object of items list
						if(counter == 2) {this.wp = this.wp +2; break;}
						if(i instanceof Gold) {
							this.items.remove(i);
							counter++;
						}
					}

				}
			}
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
