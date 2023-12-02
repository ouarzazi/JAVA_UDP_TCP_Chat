import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * This class is a simple UDP server
 */


public class UDPServer {
    private int port;
    private DatagramSocket socket;

    /**
     * @param port The server will listen in this port
     * @throws SocketException If there is an issue when we are creating the DatagramSocket.
     */

    private UDPServer(int port) throws SocketException {
        this.port = port;
        socket = new DatagramSocket(this.port);
    }

    /**
     * We launch the UDP server
     */

    public void launch(){
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        try {

            while (true) {
                socket.receive(receivePacket);
                displayReceivedMessage(receivePacket);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param receivePacket The DatagramPacket that contains the received data.
     * @throws UnsupportedEncodingException If there is an issue when we decode the received message.
     */

    private void displayReceivedMessage(DatagramPacket receivePacket) throws UnsupportedEncodingException {
        InetAddress inetAddress = receivePacket.getAddress();
        String clientAddress = inetAddress.getHostAddress(); //retrieve IP address as a string from InetAddress object
        String receivedMessage = new String(receivePacket.getData(),0, receivePacket.getLength(), "UTF-8");
        System.out.println("received from " + clientAddress + ": " + receivedMessage);
    }

    public String toString(){
        return "UDP Server on port " + port;
    }

    private static int getPort(String[] args) {
        if (args.length == 0) {
            System.out.println("Please use correct format : java UDPServer <port>");
            System.exit(-1);
        }

        int port=0;
        try {
            port = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException e){
            System.err.println("Invalid port number (int).");
            System.exit(-2);
        }
        return port;
    }

    /**
     * The main method that creates an instance of UDPServer and launches the server to start 
     * listening.
     */

    public static void main(String[] args){
        int port = getPort(args);

        UDPServer udpServer = null;

        try {
            udpServer = new UDPServer(port);
        } catch (SocketException e) {
            System.err.println("port already in use");
            System.exit(-3);
        }
        System.out.println(udpServer);
        udpServer.launch();

    }

}
