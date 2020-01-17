import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class PlayerBoard {
    private static PlayerBoard playerBoard;

    private MinuetoImage playerBoardImage = new MinuetoImageFile("images/Heroes/WarriorMaleBoard.png").scale(300/ 671.0, 300/ 671.0);

    public PlayerBoard() throws MinuetoFileException {

    }

    public static PlayerBoard getInstance() throws MinuetoFileException {
        if(playerBoard == null)
            playerBoard = new PlayerBoard();
        return playerBoard;
    }

    public void draw() {

    }
}
