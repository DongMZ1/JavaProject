import java.io.IOException;
import java.util.ArrayList;

import org.minueto.MinuetoColor;
import org.minueto.MinuetoEventQueue;
import org.minueto.MinuetoFileException;
import org.minueto.handlers.MinuetoKeyboard;
import org.minueto.handlers.MinuetoKeyboardHandler;
import org.minueto.handlers.MinuetoMouseHandler;
import org.minueto.handlers.MinuetoWindowHandler;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;
import org.minueto.image.MinuetoText;
import org.minueto.window.MinuetoFrame;
import org.minueto.window.MinuetoWindow;

public class Cards implements MinuetoKeyboardHandler, MinuetoMouseHandler, MinuetoWindowHandler{
	MinuetoWindow window;			// The Minueto window
	MinuetoEventQueue eventQueue;
	boolean closing;
	private static GameStatus gameStatus;

	static {
		try {
			gameStatus = GameStatus.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void showHeroInformationBoard() {
		Cards show = new Cards(-1);
	}
	public static void drawLegend1EventCard(int cardNB) throws IOException{
	   	switch(cardNB) {
	   	case 1:
		   	@SuppressWarnings("unused") Cards card1 = new Cards(1);
		   	Monster m1 = new Gor(16);
			Monster m2 = new Gor(22);
			Monster m3 = new Gor(23);
			Monster m4 = new Gor(24);
			Tile.get(m1.tile).addTileEntity(m1);
			Tile.get(m2.tile).addTileEntity(m2);
			Tile.get(m3.tile).addTileEntity(m3);
			Tile.get(m4.tile).addTileEntity(m4);
			//Client.gameBoard.monsters.add(m1);
			//Client.gameBoard.monsters.add(m2);
			//Client.gameBoard.monsters.add(m3);
			//Client.gameBoard.monsters.add(m4);
		   break;
	   case 2:
		   @SuppressWarnings("unused") Cards card2 = new Cards(2);
		   for(TileEntity t: Tile.get(55).getTileEntities()) {
			   if(t instanceof Well) {
				   Tile.get(55).removeTileEntity(t);
				   break;
			   }
		   }
		   break;
	   case 3:
		   @SuppressWarnings("unused") Cards card3 = new Cards(3);
		   Item g1 = new Gold(26);
	       Item g2 = new Gold(26);
	       Item g3 = new Gold(26);
		   Tile.get(g1.getTile()).addTileEntity(g1);
		   Tile.get(g2.getTile()).addTileEntity(g2);
		   Tile.get(g3.getTile()).addTileEntity(g3);
		   break;
	   case 4:
		   @SuppressWarnings("unused") Cards card4 = new Cards(4);
		   /*
		   for(Hero h: Client.gameBoard.tm.heroes) {
			   if(h.tile >= 0 && h.tile <= 9) {
				   h.wp = h.wp +2 ;
			   }
		   }
		   */
		   break;
	   case 5:
		   @SuppressWarnings("unused") Cards card5 = new Cards(5);
		   /*
		   for(Hero h: Client.gameBoard.tm.heroes) {
			   if(h instanceof Warrior && h.wp > 12) {
				  h.wp = 12;
			   }
			   if(h instanceof Archer && h.wp > 12) {
				   h.wp = 12;
			   }
		   }
		    */
		   break;
	   case 6: 
		   @SuppressWarnings("unused") Cards card6 = new Cards(6);
		   LoseWPDistributerHander l1 = new LoseWPDistributerHander();
		   LoseWPDistributerHander l2 = new LoseWPDistributerHander();
		   LoseWPDistributerHander l3 = new LoseWPDistributerHander();
		   break;
	   case 7: 
		   @SuppressWarnings("unused") Cards card7 = new Cards(7);
		   EventCardForRevealFogTokenHandler c1 = new EventCardForRevealFogTokenHandler();
		   EventCardForRevealFogTokenHandler c2 = new EventCardForRevealFogTokenHandler();
		   break;
	   case 8: 
		   @SuppressWarnings("unused") Cards card8 = new Cards(8);
		   TwoGoldForFiveWPEventCardHandler cc1 = new TwoGoldForFiveWPEventCardHandler();
		   break;
	   case 9: 
		   @SuppressWarnings("unused") Cards card9 = new Cards(9);
		   /*
		   for(Hero h: Client.gameBoard.tm.heroes) {
			   if(h instanceof Mage && h.wp > 12) {
				  h.wp = 12;
			   }
			   if(h instanceof Dwarf && h.wp > 12) {
				   h.wp = 12;
			   }
		   }
		    */
		   break;
	   case 10: 
		   @SuppressWarnings("unused") Cards card10 = new Cards(10);
		   /*
		   for(Hero h: Client.gameBoard.tm.heroes) {
			  if(h.wp <= 10) {
				  h.wp = h.wp +2;
			  }
		   }
		    */
		   break;
	   case 11: 
		   @SuppressWarnings("unused") Cards card11 = new Cards(11);
		   /*
		   for(Hero h: Client.gameBoard.tm.heroes) {
				  if(h.sp > 1) {
					  h.sp = h.sp -1;
				  }
			   }
		    */
		   break;
	   }
	   //after each extraction, event card index ++;
	   //Client.gameBoard.Lengend1EventCardIndex ++;
   }
   
   
   public static void showLegend2Card(int legend2cardindex) {
	   switch(legend2cardindex) {
	   case 1:
		   @SuppressWarnings("unused") Cards A1 = new Cards(301);
		   if(gameStatus.Legend2ModeIsEasy) {
			   @SuppressWarnings("unused") Cards A3Easy = new Cards(302);
			   }else {
				 @SuppressWarnings("unused") Cards A3Hard = new Cards(303);
			   }
		   @SuppressWarnings("unused") Cards A4 = new Cards(304);
		   @SuppressWarnings("unused") Cards A5 = new Cards(305);
		   break;
	   case 2:
		   if(gameStatus.Legend2ModeIsEasy) {
			   @SuppressWarnings("unused") Cards C1Easy = new Cards(306);
			   }else {
				 @SuppressWarnings("unused") Cards C1Hard = new Cards(307);
			   }
		   Cards C2 = new Cards(308);
		   break;
	   case 3: 
		   Cards G = new Cards(309);
	   case 4:
		   Cards N = new Cards(310);
	   }
		   
   }
   

   
   public static void DrawLegend2Card(int legend2cardindex) {
	   switch(legend2cardindex) {
	   case 1:
		   @SuppressWarnings("unused") Cards A1 = new Cards(301);
		   if(gameStatus.Legend2ModeIsEasy) {
			   @SuppressWarnings("unused") Cards A3Easy = new Cards(302);
			   }else {
				 @SuppressWarnings("unused") Cards A3Hard = new Cards(303);
			   }
		   @SuppressWarnings("unused") Cards A4 = new Cards(304);
		   @SuppressWarnings("unused") Cards A5 = new Cards(305);
		   break;
	   case 2:
		   if(gameStatus.Legend2ModeIsEasy) {
			   @SuppressWarnings("unused") Cards C1Easy = new Cards(306);
			   }else {
				 @SuppressWarnings("unused") Cards C1Hard = new Cards(307);
			   }
		   Cards C2 = new Cards(308);
		   break;
	   case 3: 
		   Cards G = new Cards(309);
	   case 4:
		   Cards N = new Cards(310);
	   }
   }
   
   public static void drawGoldenEventCards(int cardNB) {
	   switch(cardNB) {
   case 1:
	   @SuppressWarnings("unused") Cards card100 = new Cards(100);
	   break;
   case 2:
	   @SuppressWarnings("unused") Cards card2 = new Cards(2);
	   break;
   case 3:
	   @SuppressWarnings("unused") Cards card3 = new Cards(3);
	   break;
   case 4:
	   @SuppressWarnings("unused") Cards card4 = new Cards(4);
	   break;
   case 5:
	   @SuppressWarnings("unused") Cards card5 = new Cards(5);
	   break;
   case 6: 
	   @SuppressWarnings("unused") Cards card6 = new Cards(6);
	   break;
   case 7: 
	   @SuppressWarnings("unused") Cards card7 = new Cards(7);
	   break;
   case 8: 
	   @SuppressWarnings("unused") Cards card8 = new Cards(8);
	   break;
   case 9: 
	   @SuppressWarnings("unused") Cards card9 = new Cards(9);
	   break;
   case 10: 
	   @SuppressWarnings("unused") Cards card10 = new Cards(10);
	   break;
   case 11: 
	   @SuppressWarnings("unused") Cards card11 = new Cards(11);
	   break;
   }
   }
   
	public Cards(int CardNumber) {

		
		MinuetoFont fontArial19;				// Font used to draw on the screen.
		MinuetoImage imageText = null, imageText1 = null, imageText2 = null, eventcardindexText = null, imageText3 = null,
				imageText4 = null, imageText5 = null, imageText6 = null, imageText7 = null,exitText;		// Instructions drawn on the screen.
		
		
		// Create a 1200 by 480 window
		window = new MinuetoFrame(1200, 480, true);
		// Build the event queue.
		eventQueue = new MinuetoEventQueue();
		
		// Register the keyboard handler with the event queue.
		window.registerKeyboardHandler(this, eventQueue);

		fontArial19 = new MinuetoFont("Arial",19,false, false);
		
		{
		if(CardNumber == 1) {
			eventcardindexText = new MinuetoText("Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
		imageText = new MinuetoText("Sudden screams echo through the countryside. They're coming! " ,fontArial19,MinuetoColor.BLUE);
		imageText1 = new MinuetoText("The gors are moving in the gray of dawn, and RietBurg Castke is in danger!" ,fontArial19,MinuetoColor.BLUE);
		imageText2 = new MinuetoText("Place gors on sapces 16, 22, 23, and 24." ,fontArial19,MinuetoColor.BLUE);
		imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
		imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
		imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
		imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
		imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
		}
		if(CardNumber == 2) {
			eventcardindexText = new MinuetoText("Event Card 2:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Rampaging creatures despoil the well in the Watchful Woods." ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("The well token on space 55 is removed from the game!" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 3) {
			eventcardindexText = new MinuetoText("Event Card 3:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Place 3 gold from the trove and place it on space 26." ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("A hero who enters this space or is already standing on it can collect the gold." ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 4) {
			eventcardindexText = new MinuetoText("Event Card 4:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Each Hero who is on a space with a number between 0 and 9" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("will now get 2 willpower points." ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 5) {
			eventcardindexText = new MinuetoText("Event Card 5:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("This particularly affects the warrior and the arther." ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("If they have more than 12 willpower points, thes heros must" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("reduce their willpower points to 12." ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 6) {
			eventcardindexText = new MinuetoText("Event Card 6:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("The group loses a total of 3 willpower points. You can decide as a " ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("group which hero loses how many points." ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 7) {
			eventcardindexText = new MinuetoText("Event Card 7:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("The hero with the lowest rank is allowed to uncover any two fog tokens." ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("But a token is only triggered when a hero ends his move on that space." ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 8) {
			eventcardindexText = new MinuetoText("Event Card 8:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Each hero can now purchase another 5 willpower points in exchange for" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("2 gold." ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 9) {
			eventcardindexText = new MinuetoText("Event Card 9:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("This particular affects the wizard and dwarf." ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("If they have more than 12 willpwer, these heros must" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("reduce their willpower points to 12" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 10) {
			eventcardindexText = new MinuetoText("Event Card 10:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Each hero who has fewer than 10 willpower points" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("will now get 2 extra." ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 11) {
			eventcardindexText = new MinuetoText("Event Card 11:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Any hero on a space loses 1 strength point." ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("If a hero only has 1 strength point, nothing happens to him." ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		
		//gold event card
		if(CardNumber == 100) {
			eventcardindexText = new MinuetoText("Golden Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 100) {
			eventcardindexText = new MinuetoText("Golden Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 100) {
			eventcardindexText = new MinuetoText("Golden Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 100) {
			eventcardindexText = new MinuetoText("Golden Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 100) {
			eventcardindexText = new MinuetoText("Golden Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 100) {
			eventcardindexText = new MinuetoText("Golden Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 100) {
			eventcardindexText = new MinuetoText("Golden Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 100) {
			eventcardindexText = new MinuetoText("Golden Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 100) {
			eventcardindexText = new MinuetoText("Golden Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 100) {
			eventcardindexText = new MinuetoText("Golden Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		
		//fogtoken
		if(CardNumber == 1000) {
			eventcardindexText = new MinuetoText("Fogtoken:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Strength Point +1!" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 1001) {
			eventcardindexText = new MinuetoText("Fogtoken:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Willpower +2!" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 1002) {
			eventcardindexText = new MinuetoText("Fogtoken:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Willpower +3!" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 1003) {
			eventcardindexText = new MinuetoText("Fogtoken:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("You got a Gold!" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 1004) {
			eventcardindexText = new MinuetoText("Fogtoken:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("A gor is placed on current Tile!" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		
		if(CardNumber == 1005) {
			eventcardindexText = new MinuetoText("Fogtoken:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("You got a WineSkin!" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 1006) {
			eventcardindexText = new MinuetoText("Fogtoken:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("You got a Witch's brew" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		//card number -1 is to show what does the main hero have
		if(CardNumber == -1) {
			eventcardindexText = new MinuetoText("Gold Number:  " + Client.mainHero.getGoldNm() + "  Wineskin:  " +  Client.mainHero.getWineskin() ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		
		
		
		//legend2 card
		
		if(CardNumber == 301) {
			eventcardindexText = new MinuetoText("A1: Here is a reminder before continuing to legend2: A hero always chooese between two options, both cost time on" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Fighting costs 1 hour per battle round. Moving costs 1 hour per game board space." ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("The free actions: activate a fog token, Empty a well, pick up or deposit gold/gemstones or articles from or onto a space." ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("Trade or give gold/gemstones or artiles to another hero on the same space" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("Use articles, buy artiles or strength points from a merchant." ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("None of these actions cost any hours on the time track. They can also be carried out when it is not the hero turn." ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 302) {
			eventcardindexText = new MinuetoText("A3 Easy: Place your heros on the spaces corresponding to their rank numbers:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText(" dwarf on 7, warrior on 14, archer on 25, wizard on 34. " ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("Place gors on space 8, 20, 21, 26, 48 and one skral on 19" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("Many farmers had asked for help and are seeking shelter behind the high walls of Rietburg Castle" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("Place one farmer token on each spaces 24 and 36." ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 303) {
			eventcardindexText = new MinuetoText("A3 Hard: Place your heros on the spaces corresponding to their rank numbers:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText(" dwarf on 7, warrior on 14, archer on 25, wizard on 34. " ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("Place gors on space 8, 20, 21, 26, 48 and one skral on 19" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("Many farmers had asked for help and are seeking shelter behind the high walls of Rietburg Castle" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("Place one farmer token on each spaces 24." ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 304) {
			eventcardindexText = new MinuetoText("A4: This adventure starts with farmers who can be brought into the castle. The players can " ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("move their heros to a farmer token and carry it along with their own figure. A hero can carry several farmer token." ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("Tasks: The heros must heal the king with the medicinal herb. To do that they must find the witch" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("Only she knows the location where this herb grows. The witch is hiding behind one of the fog tokens." ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 305) {
			eventcardindexText = new MinuetoText("A5: When a hero enters a space with fog token showing the witch's brew. The witch card is uncovered." ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Now its time to decide when The Rune Stones Legend card comes into play. One player rolls one if heros dice" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("Place the Rune stone legend card with its arrow pointing to the corresponding letter on the legend track" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("This Card will be triggered when the narrator reaches this letter. From now on, any articles may be purchased" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("from the merchants for 2 gold each. See the equipment board for the function of the articles. Witch's brew cannot be purchased" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("Witch's brew cannot be purchased. Each hero starts with two strength points. The group gets 5 gold and 2 wineskins, " ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("you all decide together who gets what " ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 306) {
			eventcardindexText = new MinuetoText("C1 Easy: A hero rolls one hero dice and adds 50 to the number rolled. The total number indicates the number of " ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("The space on which the skral stronghold is located. If there is another creature on the same space, it is immediately" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("removed from the game. The skral on the tower does not move." ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("The skral on the tower has 6 willpower points and the following number of strength points for 2 heros: 10," ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("for 3 heros = 20, for 4 heros = 30." ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("Tasks: The skral on the tower must be defeated. As soon as he is defeated. The narrator is advanced to the letter N" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("on the Legend track." ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("Place a farmer token on space 28." ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 307) {
			eventcardindexText = new MinuetoText("C1 Hard: A hero rolls one hero dice and adds 50 to the number rolled. The total number indicates the number of " ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("The space on which the skral stronghold is located. If there is another creature on the same space, it is immediately" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("removed from the game. The skral on the tower does not move." ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("The skral on the tower has 6 willpower points and the following number of strength points for 2 heros: 20," ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("for 3 heros = 30, for 4 heros = 40." ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("Tasks: The skral on the tower must be defeated. As soon as he is defeated. The narrator is advanced to the letter N" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("on the Legend track." ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("Place a farmer token on space 28." ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 308) {
			eventcardindexText = new MinuetoText("C2: Place gors on 27 and 31, and one skral on 29. But there's good news from the south too: Prince " ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Thorald on the space with travern (72, to right of space 23 in the southern forest). If the prince is stand on" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("the same space as creature, he counts as 4 extra strength points for the heros in a battle with the creature." ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("Instead of fighting or moving, a hero can now also chooese the move prince action during his move. That will cost" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("him 1 hour on the time track. He can also do that several times during his turn." ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("Prince Thorald accompanies the heros up to letter G on the legend track." ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("Legend goal: The Legend is won when the Narrator reaches the letter N on the Legend track, and ... the" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("castle has been defended, and the medicinal herb is on the castle space" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText(", and the skral on the tower has been defeated" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 309) {
			eventcardindexText = new MinuetoText("G: Prince Thorald joins up with a scouting patrol with the intention of leaving for just a few days." ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("But he is not be see again for quite a long time. Prince Thorald is removed from the game." ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("Black shadows are moving in the moon light. The rumors were right - the wardraks are coming" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("Place wardraks on space 26 and 27. If one of the space is already occupied by a creature, the new creature" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("is moved along the arrow to adjacent space. A wardrak has 10 strength and 7 willpower points, and use 2 black dice" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("in the battle. These creature espeaclly dangerous, because they move twice each sunshine" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("For every wardrak defeated, the hero get a reward of 6 gold or 6 willpower points, or any combination" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("adding up to 6." ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 310) {
			eventcardindexText = new MinuetoText("N:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Win: The legend ended well if the medicinal herb is on the castle space, and skral on the tower was defeated" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("and the castle was successfully defended" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("Loss: The legend ended badly if the medicainal herbs is not in the castle space. or the skral on the tower" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("was not defeated or the castle was not defended." ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		//witch brew card
		if(CardNumber == -2){
			eventcardindexText = new MinuetoText("Finally! There in the fog, one of the heros discover the witch named Reka" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("The hero standing on the witch's space activates the fog token and gets her magic potion fo free." ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("From now on, a hero stand on the same space as witch can buy her brew. The price depends on the number" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("of heros. Important: the archer always pays 1 hold less than others. In a battle, the witch brew doubles" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("the value of one die, and it can be used twice. Reca knows where to find the medicinal herb to heal the king" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("roll of 1 or 2 = medicinal herbs on space 37; roll of 3 or 4 = medicinal herbs on space 67" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("roll 5 or 6 = medicinal herb on space 61." ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
		}
		if(CardNumber == -3) {
		eventcardindexText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
		imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
		imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
		imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
		imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
		imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
		imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
		imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
		imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
		}
		
		if(CardNumber == -4) {
			eventcardindexText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText5 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText6 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText7 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
		}
			exitText = new MinuetoText("Press 'q' to quit." ,fontArial19,MinuetoColor.BLUE);
		// Show the game window.
		window.setVisible(true);
	
		// Game/rendering loop
		while(true) {
		
			// Clear the window.
			window.clear();
			
			// Draw the instructions.
			window.draw(eventcardindexText, 0, 0);
			window.draw(imageText, 0, 30);
			window.draw(imageText1, 0, 60);
			window.draw(imageText2, 0, 90);
			window.draw(imageText3, 0, 120);
			window.draw(imageText4, 0, 150);
			window.draw(imageText5, 0, 180);
			window.draw(imageText6, 0, 210);
			window.draw(imageText7, 0, 240);
			window.draw(exitText, 0, 450);
			
			// Handle all the events in the event queue.
			while (eventQueue.hasNext()) {
				eventQueue.handle();
				if (closing) return;
				}
			
			// Render all graphics in the back buffer.
			window.render();
			Thread.yield();		
		}		
	}
	}

	@Override
	public void handleKeyPress(int value) {
		if (value == MinuetoKeyboard.KEY_Q) {
			this.closing = true;
			window.close(); }
		
	}

	@Override
	public void handleKeyRelease(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKeyType(char keyChar) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	* Print out the key values when the user releases a key. Constants to
	* identify key values are available in the MinuetoKeyboard class.
	**/


	/**
	* Not used. Mostly used to implement typing stuff, such as text box.
	*/


	@Override
	public void handleGetFocus() {
	// TODO Auto-generated method stub

	}

	@Override
	public void handleLostFocus() {
	// TODO Auto-generated method stub

	}

	@Override
	public void handleQuitRequest() {
	// TODO Auto-generated method stub

	}

	@Override
	public void handleMinimizeWindow() {
	// TODO Auto-generated method stub

	}

	@Override
	public void handleRestoreWindow() {
	// TODO Auto-generated method stub

	}

	@Override
	public void handleMousePress(int x, int y, int button) {
	// TODO Auto-generated method stub

	}

	@Override
	public void handleMouseRelease(int x, int y, int button) {
	// TODO Auto-generated method stub

	}

	@Override
	public void handleMouseMove(int x, int y) {
	// TODO Auto-generated method stub

	}

}

