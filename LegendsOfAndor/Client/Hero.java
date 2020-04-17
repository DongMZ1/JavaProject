import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class Hero implements Character, Serializable {
	int tile;
	int wp;
	int sp;
	Time time;
	Farmer farmer = null;
	boolean hasfarmer = false;
	boolean hasGold = false;
    boolean holdWineSkinForUse = false; //set wineskin to ready to use before using it, example: 
    //set it true before move, so that timer will not increase if this attribute is true
    //get a final list of items
    public final ArrayList<Item> items = new ArrayList<Item>();
    ArrayList<Dice> diceList;
    boolean isFinishedForDay;
    public void setTime(Time time) {
    	this.time = time;
    }

    public Hero(int tile) throws IOException {
        this.tile = tile;
        this.sp = 1;
        this.wp = 7;
        this.diceList = new ArrayList<>();
        this.time = new Time();
        isFinishedForDay = false;
    }

	//UPDATE
	public void setTile(int tile) {
		this.tile = tile;
	}
	public int getTile() {
		return this.tile;
	}
	///////////////general///////////////////////////////////////////////////////////
	 public boolean hasMerchant() {
	    	for(TileEntity t: Tile.get(this.tile).getTileEntities()) {
	    		if(t instanceof Merchant) {
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	 
	    public boolean hasMonster() {
	    	for(TileEntity t: Tile.get(this.tile).getTileEntities()) {
	    		if(t instanceof Monster) {
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	    
	  	public boolean CheckHasCreature() {
		  	for(TileEntity t: Tile.get(tile).getTileEntities()) {
			  	if(t instanceof Monster) {
				  	return true;
			  	}
		  	}
		  	return false;
	  	}
	 
	 
	 
	//Wineskin//////////////////////////////////////////////////////////////////////
   	public void UseWineSkinForMove() {
	   	int wineSkinCount = 0;
	   	for(Item i: items) {
			if(i instanceof Wineskin) {
				wineSkinCount ++;
			}
		}
		if(wineSkinCount > 0) {
			for(Item i: items) {
				if(i instanceof Wineskin) {
					this.items.remove(i);
					break;
				}
			}
			this.time.time --;
			this.time.x = this.time.x - 550;
		}
   }
	public void addWineSkin(){
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
	
	public void BuyWineSkinFor2Gold(){
		if(this.getGoldNm() >= 2 && this.hasMerchant()) { 
			//remove gold first
			int count = 0;
				for(Item i: this.items) {
					if(i instanceof Gold) {
					count = items.indexOf(i);
			}
				}
				items.remove(count);
				
				count = 0;
				for(Item i: this.items) {
					if(i instanceof Gold) {
					count = items.indexOf(i);
			}
				}
				items.remove(count);
		
		//add wine skin
		int wineSkinCount = 0;
		for(Item i: items) {
			if(i instanceof Wineskin) {
				wineSkinCount ++;
			}
		}
		while(wineSkinCount < 2) {
			this.items.add(new Wineskin(this.tile));
		}
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

	public int getWineskin() {
		int WineskinCount = 0;
		for(Item i: items) {
			if(i instanceof Wineskin) {
				WineskinCount ++;
			}
		}
		return WineskinCount;
	}
	
	
////////////Bow///////////////////////////////////////////////////////////////////
	public void BuyBowFor2Gold(){
		//if have money/merchant
		if(this.getGoldNm() >= 2 && this.hasMerchant()) { 
			//remove gold first
			int count = 0;
				for(Item i: this.items) {
					if(i instanceof Gold) {
					count = items.indexOf(i);
			}
				}
				items.remove(count);
				
				count = 0;
				for(Item i: this.items) {
					if(i instanceof Gold) {
					count = items.indexOf(i);
			}
				}
				items.remove(count);
		
		//add wine skin
		int BowCount = 0;
		for(Item i: items) {
			if(i instanceof Bow) {
				BowCount ++;
			}
		}
		while(BowCount < 2) {
			this.items.add(new Bow(this.tile));
		}
		}
	}
	

	
	
	
	//Well///////////////////////////////////////////////////////////////////////////////
	//UPDATE
	public void replenishWP() {
		ArrayList<TileEntity> entities = Tile.get(tile).getTileEntities(); 	
		if(entities != null) {
			for (TileEntity t : entities) {
				if (t instanceof Well) {
					wp += ((Well) t).emptiedByHero(this);
				}
			}
    	}
    }  
	//UPDATE
	
	
	
	//Farmer/////////////////////////////////////////////////////////////////////////////////
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
	  	if(!CheckHasCreature()) {
		 	if(hasfarmer && this.tile == 0) {
			  	hasfarmer = false;
			  	farmer.isDropped();
			  	this.farmer = null;
			  	//we need castle health increase.
			  	//Client.gameScreenDrawer.gameScreen.getCastle().health++;
			  	//System.out.println("Health:  " + Client.gameScreenDrawer.gameScreen.getCastle().health);
		  	}
			if(hasfarmer) {
				hasfarmer = false;
				Tile.get(tile).getTileEntities().add(this.farmer);
				farmer.isDropped();
				this.farmer = null;
			}
  		}
  	}


			// call farmer.isguide to assign hero to the farmer, then add farmer as a item to itemlist of hero 
		
	

	



    


    
    
//Gold///////////////////////////////////////////////////////////////////   
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
	
	public void Buy2SPfor2Gold() {
		//firstly, find out if the hero has more than two gold for trade willpower
		System.out.println("gold: "+ this.getGoldNm());
		if(this.getGoldNm() >= 2 && this.hasMerchant()) { 
			int count = 0;
				for(Item i: this.items) {
					if(i instanceof Gold) {
					count = items.indexOf(i);
			}
				}
				items.remove(count);
				
				count = 0;
				for(Item i: this.items) {
					if(i instanceof Gold) {
					count = items.indexOf(i);
			}
				}
				items.remove(count);
				sp = sp+2;
		}
		System.out.println("gold: "+ this.getGoldNm());
		if(this.getGoldNm() == 0) {
			hasGold = false;
		}
	}
	
	
	//fog token/////////////////////////////////////////////////////////////////////////
	
	
	
	public void FilpFogTokenForPreviewOnly(int tileNB) {
		for (TileEntity f: Tile.get(tileNB).getTileEntities()) {
			if(f instanceof FogToken) {
				if(((FogToken)f).tokenNumber == 1) {
					//Cards card1 = new Cards(Client.gameScreenDrawer.gameScreen.Lengend1EventCardIndex);
			}
				if(((FogToken)f).tokenNumber == 2) {Cards c1 = new Cards(1000);}
				if(((FogToken)f).tokenNumber == 3) {Cards c1 = new Cards(1001);}
				if(((FogToken)f).tokenNumber == 4) {Cards c1 = new Cards(1002);}
				if(((FogToken)f).tokenNumber == 5) {Cards c1 = new Cards(1003);}
				if(((FogToken)f).tokenNumber == 6) {Cards c1 = new Cards(1004);}
				if(((FogToken)f).tokenNumber == 7) {Cards c1 = new Cards(1005);}
				if(((FogToken)f).tokenNumber == 8) {Cards c1 = new Cards(1006);}
			}
		}
	}
	
	

	public void RevealFogToken() {
    	for (TileEntity f: Tile.get(tile).getTileEntities()) {
			if(f instanceof FogToken) {
				//1 is draw a event card
				if(((FogToken)f).tokenNumber == 1) {
								Cards.drawEventCard(GameStatus.gameStatus.EventCardIndex);
				}
				if(((FogToken)f).tokenNumber == 2) {
					Cards c1 = new Cards(1000);
					 this.sp ++;
				}

				if(((FogToken)f).tokenNumber == 3) {
					Cards c1 = new Cards(1001);
					 this.wp = this.wp + 2;
				}

				if(((FogToken)f).tokenNumber == 4) {
					Cards c1 = new Cards(1002);
					this.wp = this.wp + 3;
				}

				if(((FogToken)f).tokenNumber == 5) {
					Cards c1 = new Cards(1003);
						Item g = new Gold(this.tile);
						Tile.get(tile).getTileEntities().add(g);
						this.pickupGold();
				}

				if(((FogToken)f).tokenNumber == 6) {
					Cards c1 = new Cards(1004);
					 try {
						if(hasMonster()) {
							int nexttile = Tile.get(this.tile).getNextTile();
							Monster MM1 = new Gor(nexttile);
							Client.gameScreenDrawer.gameScreen.tiles.get(nexttile).addTileEntity(MM1);
							Client.gameScreenDrawer.gameScreen.monsters.add(MM1);
						}
						else {
							Monster MM1 = new Gor(this.tile);
							Client.gameScreenDrawer.gameScreen.tiles.get(this.tile).addTileEntity(MM1);
							Client.gameScreenDrawer.gameScreen.monsters.add(MM1);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if(((FogToken)f).tokenNumber == 7) {
					Cards c1 = new Cards(1005);
						this.addWineSkin();
						this.addWineSkin();
					}
				

				if(((FogToken)f).tokenNumber == 8) {
					Cards c1 = new Cards(1006);
					GameScreen.gameScreen.witch = new Witch(this.tile);
					GameScreen.gameScreen.tiles.get(this.tile).addTileEntity(GameScreen.gameScreen.witch);
				}
				 
					Tile.get(tile).getTileEntities().remove(f);
					break;
			}
		}
    }

	
	
	//Other things//////////////////////////////////////////////////////////////////
	public int[] rollDice() {
    	int[] values = new int[diceList.size()];
    	for(int i = 0; i < diceList.size(); i++)
    		values[i] = diceList.get(i).roll();
    	return values;
	}

	public void endDay() {
    	isFinishedForDay = true;
    	time.reset();
	}

	public boolean canMakeMove() {
		if(time.getTime() < 7)
			return true;
		else if(time.getTime() < 10 && this.wp > 2)
			return true;
		else
			return false;
	}
}
