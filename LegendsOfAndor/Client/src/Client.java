import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import org.minueto.*;
import org.minueto.handlers.*;
import org.minueto.window.*;
import org.w3c.dom.Text;

public class Client{

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
            //handle();
        }

    }

    public static void handle() {
        String input;
        String variable;
        String value;
        while(in.hasNextLine()) {
            input = in.nextLine();
            variable = input.split(",")[0];
            value = input.split(",")[1];
            switch(variable){
                case "message": System.out.println(value);
            }
        }
    }

    public static void updateVariable(String variable, String value) {
        out.println(variable + "," + value);
    }
}
