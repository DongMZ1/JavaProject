import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.minueto.*;
import org.minueto.handlers.*;
import org.minueto.image.MinuetoImageFile;
import org.minueto.window.*;

public class Client {

    static LobbyScreen lobbyScreen;
    static GameScreenDrawer gameScreenDrawer;
    static TextBox textBox;
    public static Hero mainHero;
    static GameStatus gameStatus;
    static MinuetoWindow screen = new MinuetoFrame(1280, 720, true);
    public static void main(String[] args) throws Exception {
        screen.setVisible(true);
    	mainHero = new Dwarf(7);
    	gameStatus = GameStatus.getInstance();
        InputHandler inputHandler = InputHandler.getInputHandler();
        lobbyScreen = new LobbyScreen();
        gameScreenDrawer = GameScreenDrawer.getInstance();
        textBox = TextBox.getInstance();
        inputHandler.addInput(lobbyScreen);
        inputHandler.addInput(gameScreenDrawer);
        inputHandler.addInput(textBox);
        inputHandler.addInput(gameScreenDrawer.fightDrawer);
        inputHandler.addInput(gameScreenDrawer.collabDrawer);
        new InputThread().start();
        Thread.sleep(2000);
        gameScreenDrawer.gameScreen.addHero(mainHero);
        gameScreenDrawer.gameScreen.castle = new Castle(5 - gameScreenDrawer.gameScreen.tm.heroes.size());
        InputThread.updateVariable();
        while (true) {
            if (gameStatus.currentScreen == gameStatus.LOBBY_SCREEN)
                lobbyScreen.draw();
            else if (gameStatus.currentScreen == gameStatus.GAME_SCREEN || gameStatus.currentScreen == gameStatus.COLLABORATIVE_SCREEN) {
                gameScreenDrawer.draw();
            }
            else if (gameStatus.currentScreen == gameStatus.FIGHT_SCREEN) {
                gameScreenDrawer.fightDrawer.draw();
            }

            textBox.draw();

            screen.render();
            inputHandler.handleQueue();
        }
    }

    public static Hero getMainHero() {
        for(Hero hero : gameScreenDrawer.gameScreen.tm.heroes) {
            if(hero.getClass().equals(mainHero.getClass()))
                return hero;
        }
        throw new NullPointerException();
    }
}

class InputThread extends Thread{
    //Basic network code init
    static String serverAddress = "192.168.2.138";

    static Socket socket;
    static ObjectInputStream in;
    static ObjectOutputStream out;

    static {
        try {
            socket = new Socket(serverAddress, 59001);
            OutputStream pee = socket.getOutputStream();
            out = new ObjectOutputStream(pee);
            InputStream poop = socket.getInputStream();
            in = new ObjectInputStream(poop);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public InputThread() {

    }

    public void run() {
        try {
        while (true) {
            Object input = in.readObject();
            System.out.println(input.getClass());
            if(input instanceof GameStatus) {
                Client.gameStatus = (GameStatus) input;
                Client.gameScreenDrawer.updateGameStatus((GameStatus) input);
            }
            else if(input instanceof GameScreen) {
                Client.gameScreenDrawer.updateGameScreen((GameScreen) input);
            }
            else
                System.out.print("Whoops");
        }
        } catch(Exception e) {}
    }

    public static void updateVariable() {
        try {
            out.writeObject(Client.gameStatus);
			out.writeObject(Client.gameScreenDrawer.gameScreen);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
