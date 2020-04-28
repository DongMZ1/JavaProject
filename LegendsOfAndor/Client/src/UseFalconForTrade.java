
import java.io.Serializable;

import org.minueto.MinuetoColor;
import org.minueto.MinuetoEventQueue;
import org.minueto.handlers.MinuetoKeyboard;
import org.minueto.handlers.MinuetoKeyboardHandler;
import org.minueto.handlers.MinuetoMouseHandler;
import org.minueto.handlers.MinuetoWindowHandler;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoText;
import org.minueto.window.MinuetoFrame;
import org.minueto.window.MinuetoWindow;

public class UseFalconForTrade implements MinuetoKeyboardHandler,
MinuetoMouseHandler,
MinuetoWindowHandler, Serializable{
	MinuetoWindow window;			// The Minueto window
	MinuetoEventQueue eventQueue;
	boolean closing;

   
  public static void main(String[] args) {
	  UseFalconForTrade f1 = new UseFalconForTrade();
  }
	public UseFalconForTrade() {

		
		MinuetoFont fontArial19;				// Font used to draw on the screen.
		MinuetoImage imageText = null, imageText1 = null, imageText2 = null, imageText3 = null, imageText4 = null, exitText;
		
		
		// Create a 640 by 480 window
		window = new MinuetoFrame(1200, 400, true);
		// Build the event queue.
		eventQueue = new MinuetoEventQueue();
		
		// Register the keyboard handler with the event queue.
		window.registerKeyboardHandler(this, eventQueue);

		fontArial19 = new MinuetoFont("Arial",19,false, false);
		
		
		
		
			imageText = new MinuetoText("Press 1 to send a gold; Press 2 get a gold; Press 3 to send a runestone; Press 4 get a runestone" ,fontArial19,MinuetoColor.BLUE);
			imageText1 = new MinuetoText("Press 5 to send a bow; Press 6 get a bow; Press 7 to send a shield; Press 8 to get a shield" ,fontArial19,MinuetoColor.BLUE);
			imageText2 = new MinuetoText("Press 9 to send a witchBrew; Press 0 to get a witchBrew; Press A to send a Telescope, Press B to get a telescope;" ,fontArial19,MinuetoColor.BLUE);
			imageText3 = new MinuetoText("Press C to send a wineskin, Press D to get a wineskin" ,fontArial19,MinuetoColor.BLUE);
			imageText4 = new MinuetoText("Press E to send a Helm, Press F to get a Helm;" ,fontArial19,MinuetoColor.BLUE);
		
		
		exitText = new MinuetoText("Press 'q' to quit." ,fontArial19,MinuetoColor.BLUE);

		// Show the game window.
		window.setVisible(true);
	
		// Game/rendering loop
		while(true) {
		
			// Clear the window.
			window.clear();
			
			// Draw the instructions.
			window.draw(imageText, 0, 0);
			window.draw(imageText1, 0, 60);
			window.draw(imageText2, 0, 120);
			window.draw(imageText3, 0, 180);
			window.draw(imageText4, 0, 240);
			window.draw(exitText, 0, 360);
			
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
		if (value == MinuetoKeyboard.KEY_1) {
			for(Item i: Client.getMainHero().items) {
				if(i instanceof Gold) {
					Client.getMainHero().items.remove(i);
					GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items.add(i);
				}
			}
		}
		if (value == MinuetoKeyboard.KEY_2) {
			for(Item i: GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items) {
				if(i instanceof Gold) {
					GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items.remove(i);
					Client.getMainHero().items.add(i);
				}
			}
		}
		if (value == MinuetoKeyboard.KEY_3) {
			for(Item i: Client.getMainHero().items) {
				if(i instanceof RuneStone) {
					Client.getMainHero().items.remove(i);
					GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items.add(i);
				}
			}
		}
		if (value == MinuetoKeyboard.KEY_4) {
			for(Item i: GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items) {
				if(i instanceof RuneStone) {
					GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items.remove(i);
					Client.getMainHero().items.add(i);
				}
			}
		}
		if (value == MinuetoKeyboard.KEY_5) {
			for(Item i: Client.getMainHero().items) {
				if(i instanceof Bow) {
					Client.getMainHero().items.remove(i);
					GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items.add(i);
				}
			}
		}
		if (value == MinuetoKeyboard.KEY_6) {
			for(Item i: GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items) {
				if(i instanceof Bow) {
					GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items.remove(i);
					Client.getMainHero().items.add(i);
				}
			}
		}
		if (value == MinuetoKeyboard.KEY_7) {
			for(Item i: Client.getMainHero().items) {
				if(i instanceof Shield) {
					Client.getMainHero().items.remove(i);
					GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items.add(i);
				}
			}
		}
		if (value == MinuetoKeyboard.KEY_8) {
			for(Item i: GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items) {
				if(i instanceof Shield) {
					GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items.remove(i);
					Client.getMainHero().items.add(i);
				}
			}
		}
		if (value == MinuetoKeyboard.KEY_9) {
			for(Item i: Client.getMainHero().items) {
				if(i instanceof WitchBrew) {
					Client.getMainHero().items.remove(i);
					GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items.add(i);
				}
			}
		}
		if (value == MinuetoKeyboard.KEY_0) {
			for(Item i: GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items) {
				if(i instanceof WitchBrew) {
					GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items.remove(i);
					Client.getMainHero().items.add(i);
				}
			}
		}
		if (value == MinuetoKeyboard.KEY_A) {
			for(Item i: Client.getMainHero().items) {
				if(i instanceof Telescope) {
					Client.getMainHero().items.remove(i);
					GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items.add(i);
				}
			}
		}
		if (value == MinuetoKeyboard.KEY_B) {
			for(Item i: GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items) {
				if(i instanceof Telescope) {
					GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items.remove(i);
					Client.getMainHero().items.add(i);
				}
			}
		}
		if (value == MinuetoKeyboard.KEY_C) {
			for(Item i: Client.getMainHero().items) {
				if(i instanceof Wineskin) {
					Client.getMainHero().items.remove(i);
					GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items.add(i);
				}
			}
		}
		if (value == MinuetoKeyboard.KEY_D) {
			for(Item i: GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items) {
				if(i instanceof Wineskin) {
					GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items.remove(i);
					Client.getMainHero().items.add(i);
				}
			}
		}
		if (value == MinuetoKeyboard.KEY_E) {
			for(Item i: Client.getMainHero().items) {
				if(i instanceof Helm) {
					Client.getMainHero().items.remove(i);
					GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items.add(i);
				}
			}
		}
		if (value == MinuetoKeyboard.KEY_F) {
			for(Item i: GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items) {
				if(i instanceof Helm) {
					GameScreen.gameScreen.tm.heroes.get(GameStatus.gameStatus.TheHeroNumberInTurnManagerForFalconTrade).items.remove(i);
					Client.getMainHero().items.add(i);
				}
			}
		}
	

		if(value == MinuetoKeyboard.KEY_Q) {
			this.closing = true;
			window.close();
		}
		
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


