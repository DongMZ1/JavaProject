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

    /*
    private void run() throws IOException  {
        try {
            Socket socket = new Socket(serverAddress, 59001);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);

            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.startsWith("SUBMITNAME")) {
                    out.println(getName());
                } else if (line.startsWith("NAMEACCEPTED")) {
                    this.frame.setTitle("Chatter - " + line.substring(13));
                    textField.setEditable(true);
                } else if (line.startsWith("MESSAGE")) {
                    messageArea.append(line.substring(8) + "\n");
                }
            }
        } finally {
            frame.setVisible(false);
            frame.dispose();
        }
    }
    */

    static LobbyScreen lobbyScreen;
    static GameScreen gameBoard;
    static TextBox textBox;
    static Hero mainHero;
    static GameStatus gameStatus;
    static MinuetoWindow screen = new MinuetoFrame(1280, 720, true);
    public static void main(String[] args) throws Exception {
        screen.setVisible(true);
    	Client.mainHero = new Archer(new MinuetoImageFile("images/Heroes/ArcherMaleIcon.png").scale(Constants.HERO_SCALE, Constants.HERO_SCALE), 0, true);
        
         gameStatus = GameStatus.getInstance();
        InputHandler inputHandler = InputHandler.getInputHandler();
        lobbyScreen = new LobbyScreen();
        gameBoard = new GameScreen();
        textBox = TextBox.getInstance();
        new InputThread(gameStatus, lobbyScreen, gameBoard, textBox).start();
        inputHandler.addInput(lobbyScreen);
        inputHandler.addInput(gameBoard);
        inputHandler.addInput(textBox);
        inputHandler.addInput(gameBoard.fight);
        inputHandler.addInput(gameBoard.cd);
        

        while (true) {
            if (gameStatus.currentScreen == gameStatus.LOBBY_SCREEN)
                lobbyScreen.draw();
            else if (gameStatus.currentScreen == gameStatus.GAME_SCREEN || gameStatus.currentScreen == gameStatus.COLLABORATIVE_SCREEN)
                gameBoard.draw();
            else if (gameStatus.currentScreen == gameStatus.FIGHT_SCREEN) {
                gameBoard.fight();
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
    private GameScreen gameBoard;
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


    public InputThread(GameStatus gameStatus, LobbyScreen lobbyScreen, GameScreen gameBoard, TextBox textBox) {
        this.gameStatus = gameStatus;
        this.lobbyScreen = lobbyScreen;
        this.gameBoard = gameBoard;
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
			out.writeObject(Client.gameBoard);
			out.writeObject(Client.lobbyScreen);
            out.writeObject(Client.gameStatus);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
