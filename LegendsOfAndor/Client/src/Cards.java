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

public class Cards implements MinuetoKeyboardHandler,
MinuetoMouseHandler,
MinuetoWindowHandler{
	MinuetoWindow window;			// The Minueto window
	MinuetoEventQueue eventQueue;
	boolean closing;
	public static void showHeroInformationBoard() {
		Cards show = new Cards(-1);
	}
   public static void drawLegend1EventCard(int cardNB) throws MinuetoFileException, IOException{
	   switch(cardNB) {
	   case 1:
		   @SuppressWarnings("unused") Cards card1 = new Cards(1);
			   Monster m1 = new Gor(new MinuetoImageFile("images/Monsters/Gor.png").scale(0.4, 0.4), 16);
		        Monster m2 = new Gor(new MinuetoImageFile("images/Monsters/Gor.png").scale(0.4, 0.4), 22);
		        Monster m3 = new Gor(new MinuetoImageFile("images/Monsters/Gor.png").scale(0.4, 0.4), 23);
		        Monster m4 = new Gor(new MinuetoImageFile("images/Monsters/Gor.png").scale(0.4, 0.4), 24);
		        GameScreen.tiles.get(m1.tile).addTileEntity(m1);
		        GameScreen.tiles.get(m2.tile).addTileEntity(m2);
		        GameScreen.tiles.get(m3.tile).addTileEntity(m3);
		        GameScreen.tiles.get(m4.tile).addTileEntity(m4);
		        Client.gameScreenDrawer.gameScreen.monsters.add(m1);
		        Client.gameScreenDrawer.gameScreen.monsters.add(m2);
		        Client.gameScreenDrawer.gameScreen.monsters.add(m3);
		        Client.gameScreenDrawer.gameScreen.monsters.add(m4);
		   break;
	   case 2:
		   @SuppressWarnings("unused") Cards card2 = new Cards(2);
		   for(TileEntity t: GameScreen.tiles.get(55).getTileEntities()) {
			   if(t instanceof Well) {
				   GameScreen.tiles.get(55).removeTileEntity(t);
				   break;
			   }
		   }
		   break;
	   case 3:
		   @SuppressWarnings("unused") Cards card3 = new Cards(3);
		   Item g1 = new Gold(26);
	       Item g2 = new Gold(26);
	       Item g3 = new Gold(26);
	       GameScreen.tiles.get(g1.getTile()).addTileEntity(g1);
	        GameScreen.tiles.get(g2.getTile()).addTileEntity(g2);
	        GameScreen.tiles.get(g3.getTile()).addTileEntity(g3);
		   break;
	   case 4:
		   @SuppressWarnings("unused") Cards card4 = new Cards(4);
		   for(Hero h: Client.gameScreenDrawer.gameScreen.tm.heroes) {
			   if(h.tile >= 0 && h.tile <= 9) {
				   h.wp = h.wp +2 ;
			   }
		   }
		   break;
	   case 5:
		   @SuppressWarnings("unused") Cards card5 = new Cards(5);
		   for(Hero h: Client.gameScreenDrawer.gameScreen.tm.heroes) {
			   if(h instanceof Warrior && h.wp > 12) {
				  h.wp = 12;
			   }
			   if(h instanceof Archer && h.wp > 12) {
				   h.wp = 12;
			   }
		   }
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
		   for(Hero h: Client.gameScreenDrawer.gameScreen.tm.heroes) {
			   if(h instanceof Wizard && h.wp > 12) {
				  h.wp = 12;
			   }
			   if(h instanceof Dwarf && h.wp > 12) {
				   h.wp = 12;
			   }
		   }
		   break;
	   case 10: 
		   @SuppressWarnings("unused") Cards card10 = new Cards(10);
		   for(Hero h: Client.gameScreenDrawer.gameScreen.tm.heroes) {
			  if(h.wp <= 10) {
				  h.wp = h.wp +2;
			  }
		   }
		   break;
	   case 11: 
		   @SuppressWarnings("unused") Cards card11 = new Cards(11);
		   for(Hero h: Client.gameScreenDrawer.gameScreen.tm.heroes) {
				  if(h.sp > 1) {
					  h.sp = h.sp -1;
				  }
			   }
		   break;
	   }
	   //after each extraction, event card index ++;
	   Client.gameScreenDrawer.gameScreen.Lengend1EventCardIndex ++;
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
		MinuetoImage imageText = null, imageText1 = null, imageText2 = null, eventcardindexText = null, imageText3 = null, exitText;		// Instructions drawn on the screen.
		
		
		// Create a 640 by 480 window
		window = new MinuetoFrame(640, 480, true);
		// Build the event queue.
		eventQueue = new MinuetoEventQueue();
		
		// Register the keyboard handler with the event queue.
		window.registerKeyboardHandler(this, eventQueue);

		fontArial19 = new MinuetoFont("Arial",19,false, false);
		
		
		if(CardNumber == 1) {
			eventcardindexText = new MinuetoText("Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
		imageText = new MinuetoText("Sudden screams echo through the countryside. They're coming! " ,fontArial19,MinuetoColor.BLUE);
		imageText1 = new MinuetoText("The gors are moving in the gray of dawn, and RietBurg Castke is in danger!" ,fontArial19,MinuetoColor.BLUE);
		imageText2 = new MinuetoText("Place gors on sapces 16, 22, 23, and 24." ,fontArial19,MinuetoColor.BLUE);
		imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
		}
		if(CardNumber == 2) {
			eventcardindexText = new MinuetoText("Event Card 2:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Rampaging creatures despoil the well in the Watchful Woods." ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("The well token on space 55 is removed from the game!" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 3) {
			eventcardindexText = new MinuetoText("Event Card 3:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Place 3 gold from the trove and place it on space 26." ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("A hero who enters this space or is already standing on it can collect the gold." ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 4) {
			eventcardindexText = new MinuetoText("Event Card 4:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Each Hero who is on a space with a number between 0 and 9" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("will now get 2 willpower points." ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 5) {
			eventcardindexText = new MinuetoText("Event Card 5:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("This particularly affects the warrior and the arther." ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("If they have more than 12 willpower points, thes heros must" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("reduce their willpower points to 12." ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 6) {
			eventcardindexText = new MinuetoText("Event Card 6:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("The group loses a total of 3 willpower points. You can decide as a " ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("group which hero loses how many points." ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 7) {
			eventcardindexText = new MinuetoText("Event Card 7:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("The hero with the lowest rank is allowed to uncover any two fog tokens." ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("But a token is only triggered when a hero ends his move on that space." ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 8) {
			eventcardindexText = new MinuetoText("Event Card 8:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Each hero can now purchase another 5 willpower points in exchange for" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("2 gold." ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 9) {
			eventcardindexText = new MinuetoText("Event Card 9:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("This particular affects the wizard and dwarf." ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("If they have more than 12 willpwer, these heros must" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("reduce their willpower points to 12" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 10) {
			eventcardindexText = new MinuetoText("Event Card 10:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Each hero who has fewer than 10 willpower points" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("will now get 2 extra." ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 11) {
			eventcardindexText = new MinuetoText("Event Card 11:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Any hero on a space loses 1 strength point." ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("If a hero only has 1 strength point, nothing happens to him." ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		
		//gold event card
		if(CardNumber == 100) {
			eventcardindexText = new MinuetoText("Golden Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 100) {
			eventcardindexText = new MinuetoText("Golden Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 100) {
			eventcardindexText = new MinuetoText("Golden Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 100) {
			eventcardindexText = new MinuetoText("Golden Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 100) {
			eventcardindexText = new MinuetoText("Golden Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 100) {
			eventcardindexText = new MinuetoText("Golden Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 100) {
			eventcardindexText = new MinuetoText("Golden Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 100) {
			eventcardindexText = new MinuetoText("Golden Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 100) {
			eventcardindexText = new MinuetoText("Golden Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 100) {
			eventcardindexText = new MinuetoText("Golden Event Card 1:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		
		
		if(CardNumber == 1000) {
			eventcardindexText = new MinuetoText("Fogtoken:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Strength Point +1!" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 1001) {
			eventcardindexText = new MinuetoText("Fogtoken:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Willpower +2!" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 1002) {
			eventcardindexText = new MinuetoText("Fogtoken:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("Willpower +3!" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 1003) {
			eventcardindexText = new MinuetoText("Fogtoken:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("You got a Gold!" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 1004) {
			eventcardindexText = new MinuetoText("Fogtoken:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("A gor is placed on current Tile!" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		
		if(CardNumber == 1005) {
			eventcardindexText = new MinuetoText("Fogtoken:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("You got a WineSkin!" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		if(CardNumber == 1006) {
			eventcardindexText = new MinuetoText("Fogtoken:" ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("You got a Witch's brew" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			}
		//card number -1 is to show what does the main hero have
		if(CardNumber == -1) {
			eventcardindexText = new MinuetoText("Gold Number:  " + Client.mainHero.getGoldNm() + "  Wineskin:  " +  Client.mainHero.getWineskin() ,fontArial19,MinuetoColor.BLUE);
			imageText = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("" ,fontArial19,MinuetoColor.BLUE);
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

