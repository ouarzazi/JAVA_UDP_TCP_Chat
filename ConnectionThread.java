import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This class represents a thread  that handle the communication with one client 
 */

public class ConnectionThread extends Thread {
    private Socket clientSocket;

    public ConnectionThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // We output stream to write to client
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println(message);

                // We send a response to the client
                writer.println("Server received your message >> " + message);
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
