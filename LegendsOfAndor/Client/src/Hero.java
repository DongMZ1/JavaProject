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
	 
	 public boolean hasWitch() {
	    	for(TileEntity t: Tile.get(this.tile).getTileEntities()) {
	    		if(t instanceof Witch) {
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
	
	public void BuyWineskinFor2Gold(){
		//if have money/merchant
		if(this.getGoldNm() >= 2 && this.hasMerchant() && this.getWineskin() <2) { 
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
		int WineskinCount = 0;
		for(Item i: items) {
			if(i instanceof Wineskin) {
				WineskinCount ++;
			}
		}
		if(WineskinCount < 2) {
			this.items.add(new Wineskin(this.tile));
			WineskinCount++;
		}
		if(WineskinCount < 2) {
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
	
	public void dropWineskin() {
		for (Item t: this.items) {
			if(t instanceof Wineskin) {
				this.items.remove(t);
				Tile.get(tile).addTileEntity(t);
				break;
			}
		}
	}	
	
	public void pickupWineskin() {
		for (TileEntity t: Tile.get(tile).getTileEntities()) {
			if(t instanceof Wineskin && this.getWineskin() < 2) {
				this.items.add((Wineskin)t);
				Tile.get(tile).removeTileEntity(t);
				break;
			}
		}
	}	
////////////Bow///////////////////////////////////////////////////////////////////
	public void BuyBowFor2Gold(){
		//if have money/merchant
		if(this.getGoldNm() >= 2 && this.hasMerchant() &&this.getBow()<2 ) { 
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
		if(BowCount < 2) {
			this.items.add(new Bow(this.tile));
			BowCount++;
		}
		if(BowCount < 2) {
			this.items.add(new Bow(this.tile));
		}
		}
	}
	
	public int getBow() {
		int BowCount = 0;
		for(Item i: items) {
			if(i instanceof Bow) {
				BowCount ++;
			}
		}
		return BowCount;
	}
	
	public void pickupBow() {
		for (TileEntity t: Tile.get(tile).getTileEntities()) {
			if(t instanceof Bow && this.getBow() < 2) {
				this.items.add((Bow)t);
				Tile.get(tile).removeTileEntity(t);
				break;
			}
		}
	}	
	
	public void dropBow() {
		for (Item t: this.items) {
			if(t instanceof Bow) {
				this.items.remove(t);
				Tile.get(tile).addTileEntity(t);
				break;
			}
		}
	}	
	
	
	
	
	
	//Falcon//////////////////////////////////////////////////////////////////////////////
	public void BuyFalconFor2Gold(){
		//if have money/merchant
		if(this.getGoldNm() >= 2 && this.hasMerchant() && this.getFalcon() < 2) { 
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
		int falconCount = 0;
		for(Item i: items) {
			if(i instanceof Falcon) {
				falconCount ++;
			}
		}
		if(falconCount < 2) {
			this.items.add(new Falcon(this.tile));
			falconCount++;
		}
		if(falconCount < 2) {
			this.items.add(new Falcon(this.tile));
		}
		}
	}
	
	public int getFalcon() {
		int FalconCount = 0;
		for(Item i: items) {
			if(i instanceof Falcon) {
				FalconCount ++;
			}
		}
		return FalconCount;
	}
	
	public void pickupFalcon() {
		for (TileEntity t: Tile.get(tile).getTileEntities()) {
			if(t instanceof Falcon && this.getFalcon() < 2) {
				this.items.add((Falcon)t);
				Tile.get(tile).removeTileEntity(t);
				break;
			}
		}
	}	
	
	public void dropFalcon() {
		for (Item t: this.items) {
			if(t instanceof Falcon) {
				this.items.remove(t);
				Tile.get(tile).addTileEntity(t);
				break;
			}
		}
	}	
	
	
	
	
	//Helm///////////////////////////////////////////////////////////////////////////////////
	public void BuyHelmFor2Gold(){
		//if have money/merchant
		if(this.getGoldNm() >= 2 && this.hasMerchant() && this.getHelm() <2) { 
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
		int HelmCount = 0;
		for(Item i: items) {
			if(i instanceof Helm) {
				HelmCount ++;
			}
		}
		if(HelmCount < 2) {
			this.items.add(new Helm(this.tile));
			HelmCount++;
		}
		if(HelmCount < 2) {
			this.items.add(new Helm(this.tile));
		}
		}
	}
	
	public int getHelm() {
		int HelmCount = 0;
		for(Item i: items) {
			if(i instanceof Helm) {
				HelmCount ++;
			}
		}
		return HelmCount;
	}
	
	public void pickupHelm() {
		for (TileEntity t: Tile.get(tile).getTileEntities()) {
			if(t instanceof Helm && this.getBow() < 2) {
				this.items.add((Helm)t);
				Tile.get(tile).removeTileEntity(t);
				break;
			}
		}
	}	
	
	public void dropHelm() {
		for (Item t: this.items) {
			if(t instanceof Helm) {
				this.items.remove(t);
				Tile.get(tile).addTileEntity(t);
				break;
			}
		}
	}	
	
	//Shield/////////////////////////////////////////////////////////////////////////////
	public void BuyShieldFor2Gold(){
		//if have money/merchant
		if(this.getGoldNm() >= 2 && this.hasMerchant() && this.getShield() < 2) { 
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
		int ShieldCount = 0;
		for(Item i: items) {
			if(i instanceof Shield) {
				ShieldCount ++;
			}
		}
		if(ShieldCount < 2) {
			this.items.add(new Shield(this.tile));
			ShieldCount++;
		}
		if(ShieldCount < 2) {
			this.items.add(new Shield(this.tile));
		}
		}
	}
	
	public int getShield() {
		int ShieldCount = 0;
		for(Item i: items) {
			if(i instanceof Shield) {
				ShieldCount ++;
			}
		}
		return ShieldCount;
	}
	
	public void pickupShield() {
		for (TileEntity t: Tile.get(tile).getTileEntities()) {
			if(t instanceof Shield && this.getShield() < 2) {
				this.items.add((Shield)t);
				Tile.get(tile).removeTileEntity(t);
				break;
			}
		}
	}	
	
	public void dropShield() {
		for (Item t: this.items) {
			if(t instanceof Shield) {
				this.items.remove(t);
				Tile.get(tile).addTileEntity(t);
				break;
			}
		}
	}	
	//Telescope//////////////////////////////////////////////////////////////////////////
	public void BuyTelescopeFor2Gold(){
		//if have money/merchant
		if(this.getGoldNm() >= 2 && this.hasMerchant() && this.getTelescope() < 2) { 
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
		int TelescopeCount = 0;
		for(Item i: items) {
			if(i instanceof Telescope) {
				TelescopeCount ++;
			}
		}
		if(TelescopeCount < 2) {
			this.items.add(new Telescope(this.tile));
			TelescopeCount++;
		}
		if(TelescopeCount < 2) {
			this.items.add(new Telescope(this.tile));
		}
		}
	}
	
	public int getTelescope() {
		int TelescopeCount = 0;
		for(Item i: items) {
			if(i instanceof Telescope) {
				TelescopeCount ++;
			}
		}
		return TelescopeCount;
	}
	
	public void pickupTelescope() {
		for (TileEntity t: Tile.get(tile).getTileEntities()) {
			if(t instanceof Telescope && this.getTelescope() < 2) {
				this.items.add((Telescope)t);
				Tile.get(tile).removeTileEntity(t);
				break;
			}
		}
	}	
	
	public void dropTelescope() {
		for (Item t: this.items) {
			if(t instanceof Telescope) {
				this.items.remove(t);
				Tile.get(tile).addTileEntity(t);
				break;
			}
		}
	}	
	
	
	
	
	//Witchbrew//////////////////////////////////////////////////////////////////////////
	
	public void BuyWitchBrewForGold(){
		//if have money/witch
		if(this.getGoldNm() >= (GameScreen.gameScreen.tm.getSize()+1) && this.hasWitch() && this.getWitchBrew() < 2) { 
			//remove gold first
			for(int i = 0; i < (GameScreen.gameScreen.tm.getSize()+1); i++ ) {
			int count = 0;
				for(Item item: this.items) {
					if(item instanceof Gold) {
					count = items.indexOf(item);
			}
				}
				items.remove(count);
			}
		//add wine skin
		int WitchbrewCount = 0;
		for(Item i: items) {
			if(i instanceof WitchBrew) {
				WitchbrewCount ++;
			}
		}
		if(WitchbrewCount < 2) {
			this.items.add(new WitchBrew(this.tile));
			WitchbrewCount++;
		}
		if(WitchbrewCount < 2) {
			this.items.add(new WitchBrew(this.tile));
		}
		}
	}
	
	public int getWitchBrew() {
		int WitchBrewCount = 0;
		for(Item i: items) {
			if(i instanceof WitchBrew) {
				WitchBrewCount ++;
			}
		}
		return WitchBrewCount;
	}
	
	public void pickupWitchBrew() {
		for (TileEntity t: Tile.get(tile).getTileEntities()) {
			if(t instanceof WitchBrew && this.getWitchBrew() < 2) {
				this.items.add((WitchBrew)t);
				Tile.get(tile).removeTileEntity(t);
				break;
			}
		}
	}	
	
	public void dropWitchBrew() {
		for (Item t: this.items) {
			if(t instanceof WitchBrew) {
				this.items.remove(t);
				Tile.get(tile).addTileEntity(t);
				break;
			}
		}
	}	
	
	//Well///////////////////////////////////////////////////////////////////////////////

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
					if(this.getWitchBrew() < 2) {
						this.items.add(new WitchBrew(this.tile));
					}
					if(this.getWitchBrew() < 2) {
						this.items.add(new WitchBrew(this.tile));
					}
					
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
