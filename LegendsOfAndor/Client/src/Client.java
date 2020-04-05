import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
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
    	Client.mainHero = new Archer(0, true);
    	gameStatus = GameStatus.getInstance();
        InputHandler inputHandler = InputHandler.getInputHandler();
        lobbyScreen = new LobbyScreen();
        gameScreenDrawer = GameScreenDrawer.getInstance();
        textBox = TextBox.getInstance();
        new InputThread(gameStatus, lobbyScreen, gameScreenDrawer, textBox).start();
        inputHandler.addInput(lobbyScreen);
        inputHandler.addInput(gameScreenDrawer);
        inputHandler.addInput(textBox);
        inputHandler.addInput(gameScreenDrawer.fightDrawer);
        inputHandler.addInput(gameScreenDrawer.gameScreen.cd);
        

        while (true) {
            if (gameStatus.currentScreen == gameStatus.LOBBY_SCREEN)
                lobbyScreen.draw();
            else if (gameStatus.currentScreen == gameStatus.GAME_SCREEN || gameStatus.currentScreen == gameStatus.COLLABORATIVE_SCREEN)
                gameScreenDrawer.draw();
            else if (gameStatus.currentScreen == gameStatus.FIGHT_SCREEN) {
                gameScreenDrawer.fightDrawer.draw();
            }

            textBox.draw();

            screen.render();
            inputHandler.handleQueue();
        }

    }

	
}

class InputThread extends Thread{
    private GameStatus gameStatus;
    private LobbyScreen lobbyScreen;
    private GameScreenDrawer gameScreenDrawer;
    private TextBox textBox;
    private static int playerNumber;
    //Basic network code init
    //static String serverAddress = "10.121.175.40";
    static String serverAddress = "0.0.0.0";

    static Socket socket;
    static ObjectInputStream in;
    static ObjectOutputStream out;
    Object o;

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


    public InputThread(GameStatus gameStatus, LobbyScreen lobbyScreen, GameScreenDrawer gameScreenDrawer, TextBox textBox) {
        this.gameStatus = gameStatus;
        this.lobbyScreen = lobbyScreen;
        this.gameScreenDrawer = gameScreenDrawer;
        this.textBox = textBox;
//        this.playerNumber = Integer.parseInt(in.nextLine().split(",")[2]);
    }

    public void run() {
 //       String input;
        String variable;
        String value;
        int sender;/*
        while(in.hasNextLine()) {
            input = in.nextLine();
            sender = Integer.parseInt(input.split(",")[0]);
            variable = input.split(",")[1];
            value = input.split(",")[2];
            if(sender != playerNumber) {
                switch (variable) {
                    case "message":
                        textBox.addMessage("Player " + sender, value);
                        break;
                    case "":
                        break;

                    default:
                        System.out.println("Invalid Message Sent Over Network");
                }
            }
        }*/
        try {
        while (true) {
            Object input = in.readObject();
            System.out.println(input);
        }
        } catch(Exception e) {}
    }

    public static void updateVariable() {
        try {
			out.writeObject(Client.gameScreenDrawer);
			out.writeObject(Client.lobbyScreen);
			out.writeObject(Client.gameStatus);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
