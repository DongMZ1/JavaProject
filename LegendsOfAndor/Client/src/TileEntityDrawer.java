import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class TileEntityDrawer {

	private MinuetoImage merchantImage;
	private MinuetoImage gorImage;
	private MinuetoImage skralImage;
	private MinuetoImage mineImage;
	private MinuetoImage fullWellImage;
	private MinuetoImage emptyWellImage;
	private MinuetoImage fogTokenImage;
	private MinuetoImage archerImage;
	private MinuetoImage farmerImage;
	private MinuetoImage goldImage;
	private MinuetoImage wineskinImage;
	private MinuetoImage bowImage;
    private MinuetoImage witchImage;
	private static TileEntityDrawer tileEntityDrawer;

	private TileEntityDrawer() throws MinuetoFileException {
		merchantImage = new MinuetoImageFile("images/Merchant.jpg").scale(0.5, 0.5);
		gorImage = new MinuetoImageFile("images/Monsters/Gor.png").scale(0.4, 0.4);
		skralImage = new MinuetoImageFile("images/Monsters/Skral.jpg");
		fullWellImage = new MinuetoImageFile("images/Well.png").scale(0.5, 0.5);
		emptyWellImage = new MinuetoImageFile("images/emptyWell.png").scale(0.5, 0.5);
		mineImage = new MinuetoImageFile("images/DwarfMine.png");
		fogTokenImage = new MinuetoImageFile("images/fogtoken.jpg").scale(0.2, 0.2);
		witchImage = new MinuetoImageFile("images/witch.jpg").scale(0.07, 0.07);
		archerImage = new MinuetoImageFile("images/Heroes/ArcherMaleIcon.png").scale(Constants.HERO_SCALE, Constants.HERO_SCALE);
		farmerImage = new MinuetoImageFile("images/farmer.png").scale(0.5, 0.5);
		goldImage = new MinuetoImageFile("images/gold.jpg");
		wineskinImage = new MinuetoImageFile("images/wineskin.png").scale(0.5, 0.5);
		//TODO: Set proper Bow image
		bowImage = new MinuetoImageFile("images/Bow.png");
	}

	public static TileEntityDrawer getInstance() throws MinuetoFileException {
		if(tileEntityDrawer == null)
			tileEntityDrawer = new TileEntityDrawer();
		return tileEntityDrawer;
	}

	public void draw(TileEntity tileEntity, int xCoord, int yCoord) {
		if(tileEntity instanceof Merchant)
			Client.screen.draw(merchantImage, xCoord, yCoord);
		else if(tileEntity instanceof Gor)
			Client.screen.draw(gorImage, xCoord, yCoord);
		else if(tileEntity instanceof Skral)
			Client.screen.draw(skralImage, xCoord, yCoord);
		else if(tileEntity instanceof DwarfMine)
			Client.screen.draw(mineImage, xCoord, yCoord);
		else if(tileEntity instanceof FogToken)
			Client.screen.draw(fogTokenImage, xCoord, yCoord);
		else if(tileEntity instanceof Archer)
			Client.screen.draw(archerImage, xCoord, yCoord);
		else if(tileEntity instanceof Farmer)
			Client.screen.draw(farmerImage, xCoord, yCoord);
		else if(tileEntity instanceof Gold)
			Client.screen.draw(goldImage, xCoord, yCoord);
		else if(tileEntity instanceof Wineskin)
			Client.screen.draw(wineskinImage, xCoord, yCoord);
		else if(tileEntity instanceof Witch)
			Client.screen.draw(witchImage, xCoord, yCoord);
		else if(tileEntity instanceof Bow)
			Client.screen.draw(witchImage, xCoord, yCoord);
		else if(tileEntity instanceof Well) {
			if (((Well) tileEntity).isEmpty())
				Client.screen.draw(emptyWellImage, xCoord, yCoord);
			else
				Client.screen.draw(fullWellImage, xCoord, yCoord);
		}
		else System.out.println(tileEntity.getClass());
	}

}
