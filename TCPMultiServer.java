import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class is  multi threaded TCP server 
 */

public class TCPMultiServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9988);

            while (true) {
                //We accept the new client connection
                Socket clientSocket = serverSocket.accept();

                // We Create a ConnectionThread for the client
                ConnectionThread connectionThread = new ConnectionThread(clientSocket);
                
                // We start it 
                connectionThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
