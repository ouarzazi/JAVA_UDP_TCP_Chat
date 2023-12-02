import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
    
/**
 * this class is a handler for the client
 */
    
public class Handle {
    String clientAddress;
    String clientMessage;
    void Client(Socket clientSocket) {
        clientAddress = clientSocket.getInetAddress().getHostAddress();
        System.out.println("Accepted connection from " + clientAddress);

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            
            while ((clientMessage = bufferedReader.readLine()) != null){
                if (Command() != 0){
                    clientSocket.close();
                    break;
                }
                String[] Parts = clientMessage.split(":");
                String username = Parts[0];
                String message = Parts[1];

                System.out.println("Received from " + clientAddress + " : " + clientMessage);
                clientSocket.getOutputStream().write((username + ": " + message + "\n").getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }

    /**
     * @return 0 if the command is not 'bye', 1 if the command is 'bye'.
     */

    int Command() {

        if (clientMessage.equalsIgnoreCase("bye")) {
            System.out.println(clientAddress + " disconnected");
            return 1;
        }
        return 0;
    }
    
}
    

   