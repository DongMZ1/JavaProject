import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import org.minueto.*;
import org.minueto.handlers.*;
import org.minueto.window.*;
import org.w3c.dom.Text;

public class Client{

    String serverAddress;
    Scanner in;
    PrintWriter out;
    /**
     * Constructs the client by laying out the GUI and registering a listener with the
     * textfield so that pressing Return in the listener sends the textfield contents
     * to the server. Note however that the textfield is initially NOT editable, and
     * only becomes editable AFTER the client receives the NAMEACCEPTED message from
     * the server.
     */
    private Client() {
        /*
        this.serverAddress = serverAddress;

        // Send on enter then clear to prepare for next message
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                out.println(textField.getText());
                textField.setText("");
            }
        });
        */
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


        while(true) {
            if(gameStatus.currentScreen == gameStatus.LOBBY_SCREEN)
                lobbyScreen.draw();
            else if(gameStatus.currentScreen == gameStatus.GAME_SCREEN)
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
