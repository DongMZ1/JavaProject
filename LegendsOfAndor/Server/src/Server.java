import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {

    // The set of all the print writers for all the clients, used for broadcast.
    private static HashSet<ObjectOutputStream> writers = new HashSet<>();
    private static GameScreen gameScreen;
    private static GameStatus gameStatus;

    public Server() throws IOException {
    }

    public static void main(String[] args) throws Exception {
        System.out.println("The game server is running...");
        gameScreen = GameScreen.getInstance();
        gameStatus = GameStatus.getInstance();
        Executor pool = Executors.newFixedThreadPool(4);
        InetAddress addr = InetAddress.getByName("192.168.1.84");
        //InetAddress addr = InetAddress.getByName("0.0.0.0");
        try (ServerSocket listener = new ServerSocket(59001,50, addr)) {
            while (true) {
                pool.execute(new Handler(listener.accept()));
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
                // Accept messages from this client and broadcast them.
                while (true) {
                    Object input = in.readObject();
                    if(input instanceof GameScreen)
                        gameScreen = (GameScreen) input;
                    else if(input instanceof GameStatus)
                        gameStatus = (GameStatus) input;
                    for (ObjectOutputStream writer : writers) {
                        writer.writeObject(input);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
