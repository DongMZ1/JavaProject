import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;
import org.minueto.image.MinuetoRectangle;

import java.io.IOException;

public class MinuetoFight {
    static MinuetoImage background = new MinuetoRectangle(1280, 720, MinuetoColor.RED, true);

    static Button rollButton;
    static Button yourTurn;
    static Button confirm;
    static Button notYourTurn;
    static Button rollAgain;
    static Button changeRollResult;
    static MinuetoImageFile diceRoll;
    static {
        try {
            rollButton = new Button(new Coordinate(700,600),50,50,"ROLL DICE",true);
            yourTurn = new Button(new Coordinate(700,500),50,50,"Your Turn",false);
            confirm = new Button(new Coordinate(760,500),50,50,"OK",true);
            notYourTurn = new Button(new Coordinate(700,500),50,50,"Not Your Turn",false);
            rollAgain = new Button(new Coordinate(700,500),50,50,"Roll Again",false);
            changeRollResult = new Button(new Coordinate(690,500),50,50,"CRR",false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Button damageButton;
}
