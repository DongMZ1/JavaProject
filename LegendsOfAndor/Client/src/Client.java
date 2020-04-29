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

    static PreGameScreen preGameScreen;
    static GameScreenDrawer gameScreenDrawer;
    static GameStatus gameStatus;
    public static Hero mainHero;
    public static int playerNum;
    static {
        try {
            mainHero = new Archer(0);
            gameScreenDrawer = GameScreenDrawer.getInstance();
            gameStatus = GameStatus.getInstance();
        } catch (IOException e) { e.printStackTrace();}
    }
    static InputHandler inputHandler;
    static TextBox textBox;
    static MinuetoWindow screen = new MinuetoFrame(1280, 720, true);
    public static void main(String[] args) throws Exception {
        screen.setVisible(true);
    	mainHero = new Dwarf(7);
    	gameStatus = GameStatus.getInstance();
        InputHandler inputHandler = InputHandler.getInputHandler();
        gameScreenDrawer = GameScreenDrawer.getInstance();
        preGameScreen = PreGameScreen.getInstance();
        textBox = TextBox.getInstance();
        inputHandler.addInput(gameScreenDrawer);
        inputHandler.addInput(textBox);
        inputHandler.addInput(gameScreenDrawer.fightDrawer);
        inputHandler.addInput(gameScreenDrawer.collabDrawer);
        inputHandler.addInput(preGameScreen);
        while(!preGameScreen.isConnected) {
            preGameScreen.draw();
            screen.render();
            inputHandler.handleQueue();
        }
        new InputThread(preGameScreen.getAddress()).start();
        while(!preGameScreen.lobbyScreen.readyToStart) {
            preGameScreen.draw();
            screen.render();
            inputHandler.handleQueue();
        }
        gameScreenDrawer.gameScreen.addHero(mainHero);
        gameScreenDrawer.gameScreen.castle = new Castle(5 - gameScreenDrawer.gameScreen.tm.heroes.size());
        InputThread.updateVariable();
        while (true) {
            if (gameStatus.currentScreen == gameStatus.LOBBY_SCREEN)
                preGameScreen.draw();
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
    static String serverAddress;

    static Socket socket;
    static ObjectInputStream in;
    static ObjectOutputStream out;

    public InputThread(String serverAddress) {
        try {
            this.serverAddress = serverAddress;
            socket = new Socket(serverAddress, 59001);
            OutputStream pee = socket.getOutputStream();
            out = new ObjectOutputStream(pee);
            InputStream poop = socket.getInputStream();
            in = new ObjectInputStream(poop);
        } catch (Exception e) {}
    }

    public void run() {
        try {
        while (true) {
            Object input = in.readObject();
            System.out.println(input.getClass());
            if(input instanceof GameStatus) {
                Client.gameStatus = (GameStatus) input;
                Client.gameScreenDrawer.updateGameStatus((GameStatus) input);
                Client.preGameScreen.lobbyScreen.gameStatus = (GameStatus) input;
                Client.inputHandler.gameStatus = (GameStatus) input;
            }
            else if(input instanceof GameScreen) {
                Client.gameScreenDrawer.updateGameScreen((GameScreen) input);
            }
            else if(input instanceof String) {
                String[] inputs = ((String) input).split(" ");
                System.out.println(inputs[0]);
                if(inputs[0].equals("s")) {
                    int playerNum = Integer.parseInt(inputs[1]);
                    Client.playerNum = playerNum;
                }
                else if(inputs[0].equals("p")) {
                    int playerNum = Integer.parseInt(inputs[1]);
                    int selection = Integer.parseInt(inputs[2]);
                    while(Client.preGameScreen.lobbyScreen.players.size()<playerNum) {
                        int currentPlayerNum = Client.preGameScreen.lobbyScreen.players.size() + 1;
                        Client.preGameScreen.lobbyScreen.players.add(new LobbyPlayer(currentPlayerNum, 100, 50 + ((currentPlayerNum-1) * 125)));
                    }
                    Client.preGameScreen.lobbyScreen.players.get(playerNum-1).setHero(selection);
                    System.out.println(Client.preGameScreen.lobbyScreen.players.size());
                }
                else if(inputs[0].equals('m')) {

                }
            }
        }
        } catch(Exception e) { System.out.println("Goodbye!"); }
    }

    public static void updateVariable() {
        try {
            out.writeObject(Client.gameStatus);
			out.writeObject(Client.gameScreenDrawer.gameScreen);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void sendString(String toSend) {
        try {
            out.writeObject(toSend);
        } catch (IOException e) { e.printStackTrace(); }
    }

}
