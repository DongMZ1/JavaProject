import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import org.minueto.*;
import org.minueto.handlers.*;
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

    public static void main(String[] args) throws Exception {

        GameStatus gameStatus = GameStatus.getInstance();
        InputHandler inputHandler = InputHandler.getInputHandler();
        lobbyScreen = new LobbyScreen(gameStatus.screen);
        gameBoard = new GameScreen(gameStatus.screen);
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

            gameStatus.screen.render();
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
    static String serverAddress = "127.0.0.1";

    static Socket socket;
    static Scanner in;
    static PrintWriter out;

    static {
        try {
            socket = new Socket(serverAddress, 59001);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public InputThread(GameStatus gameStatus, LobbyScreen lobbyScreen, GameScreen gameBoard, TextBox textBox) {
        this.gameStatus = gameStatus;
        this.lobbyScreen = lobbyScreen;
        this.gameBoard = gameBoard;
        this.textBox = textBox;
        this.playerNumber = Integer.parseInt(in.nextLine().split(",")[2]);
    }

    public void run() {
        String input;
        String variable;
        String value;
        int sender;
        while(in.hasNextLine()) {
            input = in.nextLine();
            sender = Integer.parseInt(input.split(",")[0]);
            variable = input.split(",")[1];
            value = input.split(",")[2];
            if(sender != playerNumber) {
                switch (variable) {
                    case "message":
                        System.out.println(value);
                        textBox.addMessage("Player " + playerNumber, value);
                        break;
                    case "":
                        break;

                    default:
                        System.out.println("Invalid Message Sent Over Network");
                }
            }
        }
    }

    public static void updateVariable(String variable, String value) {
        out.println(playerNumber + "," + variable + "," + value);
    }
}
