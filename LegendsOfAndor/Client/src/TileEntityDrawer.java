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
	private MinuetoImage dwarfImage;
	private MinuetoImage warriorImage;
	private MinuetoImage mageImage;
	private MinuetoImage farmerImage;
	private MinuetoImage goldImage;
	private MinuetoImage wineskinImage;
	private MinuetoImage bowImage;
    private MinuetoImage witchImage;
    private MinuetoImage falconImage;
    private MinuetoImage helmImage;
    private MinuetoImage shieldImage;
    private MinuetoImage witchBrewImage;
    private MinuetoImage telescopeImage;
    private MinuetoImage princeThoraldImage;
    private MinuetoImage wardrakImage;
    private MinuetoImage runestoneImage;
    private MinuetoImage medicalherbImage;
    private MinuetoImage skraltowerImage;
    private MinuetoImage wpImage;
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
		archerImage = new MinuetoImageFile("images/Heroes/ArcherMaleIcon.jpg").scale(Constants.HERO_SCALE, Constants.HERO_SCALE);
		dwarfImage = new MinuetoImageFile("images/Heroes/DwarfMaleIcon.jpg").scale(Constants.HERO_SCALE, Constants.HERO_SCALE);
		warriorImage = new MinuetoImageFile("images/Heroes/WarriorMaleIcon.jpg").scale(Constants.HERO_SCALE, Constants.HERO_SCALE);
		mageImage = new MinuetoImageFile("images/Heroes/MageMaleIcon.jpg").scale(Constants.HERO_SCALE, Constants.HERO_SCALE);
		farmerImage = new MinuetoImageFile("images/farmer.png").scale(0.5, 0.5);
		goldImage = new MinuetoImageFile("images/gold.jpg").scale(0.25,0.25);
		wineskinImage = new MinuetoImageFile("images/wineskin.png").scale(0.5, 0.5);
		//TODO: Set proper Bow image
		bowImage = new MinuetoImageFile("images/Bow.png");
		falconImage = new MinuetoImageFile("images/falcon.jpg").scale(0.07, 0.07);
		helmImage = new MinuetoImageFile("images/helm.jpg").scale(0.07, 0.07);
		shieldImage = new MinuetoImageFile("images/shield.png").scale(0.05, 0.05);
		witchBrewImage = new MinuetoImageFile("images/witchBrew.jpg").scale(0.1, 0.1);
		princeThoraldImage = new MinuetoImageFile("images/princeThorald.jpg").scale(0.04, 0.04);
		wardrakImage = new MinuetoImageFile("images/wardrak.jpg").scale(0.04, 0.04);
		telescopeImage = new MinuetoImageFile("images/telescope.jpg").scale(0.05, 0.05);
		runestoneImage = new MinuetoImageFile("images/runestone.jpg").scale(0.2, 0.2);
		medicalherbImage = new MinuetoImageFile("images/MedicalHerb.jpg").scale(0.1, 0.1);
		skraltowerImage = new MinuetoImageFile("images/skraltower.jpg").scale(0.1, 0.1);
		wpImage = new MinuetoImageFile("images/skraltower.jpg").scale(0.1, 0.1);

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
		else if(tileEntity instanceof Dwarf)
			Client.screen.draw(dwarfImage, xCoord, yCoord);
		else if(tileEntity instanceof Warrior)
			Client.screen.draw(warriorImage, xCoord, yCoord);
		else if(tileEntity instanceof Mage)
			Client.screen.draw(mageImage, xCoord, yCoord);
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
		else if(tileEntity instanceof Falcon)
			Client.screen.draw(falconImage, xCoord, yCoord);
		else if(tileEntity instanceof Shield)
			Client.screen.draw(shieldImage, xCoord, yCoord);
		else if(tileEntity instanceof WitchBrew)
			Client.screen.draw(witchBrewImage, xCoord, yCoord);
		else if(tileEntity instanceof Helm)
			Client.screen.draw(helmImage, xCoord, yCoord);
		else if(tileEntity instanceof PrinceThorald)
			Client.screen.draw(princeThoraldImage, xCoord, yCoord);
		else if(tileEntity instanceof Telescope)
			Client.screen.draw(telescopeImage, xCoord, yCoord);
		else if(tileEntity instanceof Wardraks)
			Client.screen.draw(wardrakImage, xCoord, yCoord);
		else if(tileEntity instanceof RuneStone)
			Client.screen.draw(runestoneImage, xCoord, yCoord);
		else if(tileEntity instanceof MedicalHerb)
			Client.screen.draw(medicalherbImage, xCoord, yCoord);
		else if(tileEntity instanceof SkralTower)
			Client.screen.draw(skraltowerImage, xCoord, yCoord);
		else if(tileEntity instanceof Well) {
			if (((Well) tileEntity).isEmpty())
				Client.screen.draw(emptyWellImage, xCoord, yCoord);
			else
				Client.screen.draw(fullWellImage, xCoord, yCoord);
		}
		else if (tileEntity instanceof WP) {
			Client.screen.draw(wpImage, xCoord, yCoord);
		}
		else System.out.println(tileEntity.getClass());
	}

}
