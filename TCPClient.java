import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class is a TCP client that connect to the TCP server
 */
public class TCPClient {
    private int port;
    private String hostname;
    private Socket socket;
    private String clientAddress;
    private boolean isFirstMessage;
    private String username;

        /**
     * @param port The port where the client will connect to the server.
     * @param hostname The hostname of the server.
     * @throws UnknownHostException if we have a problem with the hostname (unknown hostname)
     * @throws IOException   If there is an issue when we are  connecting to the server.
     */

    private TCPClient(int port, String hostname) throws UnknownHostException, IOException {
        this.port = port;
        this.isFirstMessage = true;
        this.hostname = hostname;

    }

    /**
     * We launch the TCP client
     */
    public void launch() {
            try {
                this.socket = new Socket(hostname, port);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.clientAddress = socket.getInetAddress().getHostAddress();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            while (true) {

                System.out.print("Enter message: ");
                String input = reader.readLine();  
                writer.println(username + ":" + input);

                if (input.equalsIgnoreCase("bye")){
                    System.out.println("Goodbye " + username +" see you soon!");
                    socket.close();
                    break;
                }

                String response = serverReader.readLine();
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getusername(){
        System.out.print("choose a username: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            username = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String toString() {
        return   username + " connected on port " + port +"\nNotice: Type bye to disconnect ";
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java TCPClient <hostname> <port>");
            System.exit(-4);
        }

        String hostname = args[0];
        int port = 0;
        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Invalid port number (int).");
            System.exit(-5);
        }

        TCPClient tcpClient = null;
        try {
            tcpClient = new TCPClient(port, hostname);
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + hostname);
            System.exit(-6);
        } catch (IOException e) {
            System.err.println("Error connecting to server.");
            System.exit(-7);
        }
        tcpClient.getusername();
        System.out.println(tcpClient);
        tcpClient.launch();
    }
}