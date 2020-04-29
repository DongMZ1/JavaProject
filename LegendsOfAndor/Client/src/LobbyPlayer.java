import org.minueto.MinuetoColor;
import org.minueto.image.*;

public class LobbyPlayer {
	static MinuetoImage warriorThumbnail;
	static MinuetoImage archerThumbnail;
	static MinuetoImage mageThumbnail;
	static MinuetoImage dwarfThumbnail;
	static MinuetoImage background = new MinuetoRectangle(1000, 100, new MinuetoColor(230, 230, 230), true);
	static MinuetoFont font = new MinuetoFont("Times New Roman",20, false, false);
	static MinuetoImage chooseCharHighlight;
	int index;
	int currentHero;
	MinuetoText playerName;
	public LobbyPlayer(int playerNum) {
		try {
			dwarfThumbnail = new MinuetoImageFile("images/Heroes/DwarfMaleThumbnail.png").scale(0.75,0.75);
			mageThumbnail = new MinuetoImageFile("images/Heroes/MageMaleThumbnail.png").scale(0.75,0.75);
			archerThumbnail = new MinuetoImageFile("images/Heroes/ArcherMaleThumbnail.png").scale(0.75,0.75);
			warriorThumbnail = new MinuetoImageFile("images/Heroes/WarriorMaleThumbnail.png").scale(0.75,0.75);
		} catch(Exception e) {}
		this.playerName = new MinuetoText("Player " + playerNum, font, MinuetoColor.BLACK);
		this.currentHero = 0;
		this.chooseCharHighlight = new MinuetoRectangle(100, 100, MinuetoColor.RED, false);
		index = 0;
	}

	public void draw(int x, int y) {
		Client.screen.draw(background, x, y);
		Client.screen.draw(playerName, x+10, y+10);

		Client.screen.draw(warriorThumbnail, x+300, y+10);
		Client.screen.draw(archerThumbnail, x+450, y+10);
		Client.screen.draw(dwarfThumbnail, x+600, y+10);
		Client.screen.draw(mageThumbnail, x+750, y+10);
		Client.screen.draw(chooseCharHighlight, x+300+(150*index), y+10);
	}

	public void setHero(int index) {
		this.index = index;
	}

	public void handleMousePress(int x, int y, int button) {

	}
}
