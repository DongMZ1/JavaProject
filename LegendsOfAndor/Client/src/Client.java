import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import org.minueto.*;
import org.minueto.handlers.*;
import org.minueto.window.*;
import org.w3c.dom.Text;

public class Client{

    public static void main(String[] args) throws Exception {
        GameStatus gameStatus = GameStatus.getInstance();
        InputHandler inputHandler = InputHandler.getInputHandler();
        LobbyScreen lobbyScreen = new LobbyScreen(gameStatus.screen);
        GameScreen gameBoard = new GameScreen(gameStatus.screen);
        TextBox textBox = TextBox.getInstance();
        inputHandler.addInput(lobbyScreen);
        inputHandler.addInput(gameBoard);
        inputHandler.addInput(textBox);
        inputHandler.addInput(gameBoard.fight);
        inputHandler.addInput(gameBoard.cd);
        

        while(true) {
            if(gameStatus.currentScreen == gameStatus.LOBBY_SCREEN)
                lobbyScreen.draw();
            else if(gameStatus.currentScreen == gameStatus.GAME_SCREEN || gameStatus.currentScreen == gameStatus.COLLABORATIVE_SCREEN)
                gameBoard.draw();
            else if (gameStatus.currentScreen == gameStatus.FIGHT_SCREEN) {
            	gameBoard.fight();
            }
            
            textBox.draw();

            gameStatus.screen.render();
            inputHandler.handleQueue();
        }
    }


}
