import org.minueto.window.MinuetoFrame;
import org.minueto.window.MinuetoWindow;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {

    public static File savesFolder = new File("./saves");

    private static HashSet<ObjectOutputStream> writers = new HashSet<>();
    public static Executor pool;

    private static GameScreen gameScreen;
    private static GameStatus gameStatus;

    public static InetAddress addr;
    public Server() {
    }

    public static void main(String[] args) throws IOException {
        savesFolder.mkdir();
        /*
        URL url_name = new URL("http://bot.whatismyipaddress.com");
        BufferedReader sc =
                new BufferedReader(new InputStreamReader(url_name.openStream()));
        // reads system IPAddress
        addr = InetAddress.getByName(sc.readLine().trim());
        */
        addr = InetAddress.getLocalHost();
        pool = Executors.newFixedThreadPool(4);
        ServerInterface serverInterface = new ServerInterface(addr.getHostAddress());
        serverInterface.start();

        while(true);
    }


    public static void createNewGame() throws IOException {
        gameScreen = GameScreen.getInstance();
        gameStatus = GameStatus.getInstance();
        gameStatus.focus = gameStatus.FOCUS_ON_LOBBY;
    }

    public static void loadGame(String nameOfSaveFile) throws IOException, ClassNotFoundException {
        File saveDirectory = new File("./saves/" + nameOfSaveFile);
        saveDirectory.mkdir();

        //Save GameScreen
        ObjectInputStream saveGame = new ObjectInputStream(new FileInputStream(new File("./saves/"+nameOfSaveFile+"/gameScreen")));
        gameScreen = (GameScreen) saveGame.readObject();
        saveGame.close();

        //Save GameStatus
        saveGame = new ObjectInputStream(new FileInputStream(new File("./saves/"+nameOfSaveFile+"/gameStatus")));
        gameStatus = (GameStatus) saveGame.readObject();
        saveGame.close();
        gameStatus.focus=GameStatus.FOCUS_ON_LOBBY;
        gameStatus.currentScreen=GameStatus.LOBBY_SCREEN;
        gameStatus.loaded = true;

    }

    public static String[] getSaveFiles() {
        return savesFolder.list();
    }

    public static void saveGame(String nameOfSaveFile) throws IOException {
        File saveDirectory = new File("./saves/" + nameOfSaveFile);
        saveDirectory.mkdir();

        //Save GameScreen
        ObjectOutputStream saveGame = new ObjectOutputStream(new FileOutputStream(new File("./saves/"+nameOfSaveFile+"/gameScreen")));
        saveGame.writeObject(gameScreen);
        saveGame.close();

        //Save GameStatus
        saveGame = new ObjectOutputStream(new FileOutputStream(new File("./saves/"+nameOfSaveFile+"/gameStatus")));
        saveGame.writeObject(gameStatus);
        saveGame.close();
    }

    public static class ServerRunner extends Thread {

        public void run() {
            try (ServerSocket listener = new ServerSocket(59001,50, InetAddress.getLocalHost())) {
                while (true) {
                    pool.execute(new Handler(listener.accept()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * The client handler task.
     */
    private static class Handler implements Runnable {
        private Socket socket;
        private ObjectInputStream in;
        private ObjectOutputStream out;

        /**
         * Constructs a handler thread, squirreling away the socket. All the interesting
         * work is done in the run method. Remember the constructor is called from the
         * server's main method, so this has to be as short as possible.
         */
        public Handler(Socket socket) {
            this.socket = socket;
        }

        /**
         * Services this thread's client by repeatedly requesting a screen name until a
         * unique one has been submitted, then acknowledges the name and registers the
         * output stream for the client in a global set, then repeatedly gets inputs and
         * broadcasts them.
         */
        public void run() {
            try {
                in = new ObjectInputStream(socket.getInputStream());
                out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(Server.gameScreen);
                out.writeObject(Server.gameStatus);
                writers.add(out);
                out.writeObject("s " + writers.size());
                for(ObjectOutputStream writer : writers)
                    writer.writeObject("p " + writers.size() + " 0");
                // Accept messages from this client and broadcast them.
                while (true) {
                    Object input = in.readObject();
                    System.out.println(input.getClass());
                    if(input instanceof GameScreen)
                        gameScreen = (GameScreen) input;
                    else if(input instanceof GameStatus)
                        gameStatus = (GameStatus) input;
                    for (ObjectOutputStream writer : writers) {
                        System.out.println(input.getClass());
                        writer.writeObject(input);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    System.out.println("Player has left the server");
                    writers.remove(out);
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
