import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class represents is a TCP Server
 */

public class TCPServer {
    private int port;
    private ServerSocket serverSocket;
    private Handle handle;

    /**
     * @param port   In this port, the server will listen to the incoming connections of clients.
     * @param handle  is the Handle instancewe are using to process client connections and client commands.
     * @throws IOException If there is an issue creating the ServerSocket.
     */

    private TCPServer(int port, Handle handle) throws IOException{
        this.port = port;
        this.handle = handle;
        serverSocket = new ServerSocket(port);
        System.out.println("TCP Server on port " + port);
    }

    /**
     * We launch the TCP server
     */
    public void launch() {
  
        try{

            while (true) {
                Socket clientSocket = serverSocket.accept();
                handle.Client(clientSocket);
                handle.Command();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

// Original place of handles is in Handle.java

    private static int getPort(String[] args) {
        if (args.length == 0) {
            System.err.println("Please use correct format: java TCPServer <port>");
            System.exit(-1);
        }

        int port = 0;
        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Invalid port number (int).");
            System.exit(-2);
        }
        return port;
    }

    public static void main(String[] args) {
        int port = getPort(args);

        TCPServer tcpServer = null;
        try{
            tcpServer = new TCPServer(port,new Handle());
        } catch (IOException e) {
            System.err.println("Port Already in use");
            System.exit(-3);
        }
        tcpServer.launch();
    }
}
