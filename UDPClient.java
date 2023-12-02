import java.io.Console;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * This class is a UDP client
 */

public class UDPClient {
    private int port;
    private String hostname;
    private DatagramSocket socket;

    /**
     * @param port  The port that the client will communicate in it with the server.
     * @param hostname The hostname  of the server.
     * @throws SocketException If there is an issue when we are creating the DatagramSocket.
     */
    private UDPClient(int port, String hostname) throws SocketException {
        this.port = port;
        this.hostname = hostname;
        socket = new DatagramSocket();
    }

    /**
     * we launch the UDP client
     */

    public void launch() {
        try {
            Console console = System.console();
            InetAddress serverAddress = InetAddress.getByName(hostname);

            while (true) {

                String input = console.readLine("Your message is : ");
                byte[] sendData = input.getBytes("UTF-8");
                handleContentCommand(input);
                getSendPacket(serverAddress, sendData);

            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void handleContentCommand(String input) {
        if (input.equalsIgnoreCase("bye")){
            System.out.println("Goodbye !");
            socket.close();
            System.exit(1);
        }
    }

    /**
     * @param serverAddress The server Address
     * @throws IOException If there is an issue when we are sending the DatagramPacket.
     */
    
    private void getSendPacket(InetAddress serverAddress, byte[] sendData) throws IOException {
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, port);
        socket.send(sendPacket);
    }

    public String toString(){
        return "UDP Client on port " + port + " and hostname: " + hostname;
    }

    public static void main(String[] args) {

        String hostname = null;

        try {
            hostname = args[0];
        } catch (NumberFormatException e) {
            System.err.println("Invalid hostname.");
            System.exit(-4);
        }

        int port = 0;
        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Invalid port number (int).");
            System.exit(-5);
        }
        UDPClient udpClient = null;
        try {
            udpClient = new UDPClient(port,hostname);
        } catch (SocketException e) {
            System.err.println("port already in use");
            System.exit(-6);
        }
        System.out.println(udpClient);
        udpClient.launch();

    }

}