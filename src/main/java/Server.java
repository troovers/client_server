import java.io.*;
import java.net.*;

/**
 * Created by Thomas on 22-4-2016.
 */
public class Server {
    public static void main(String[] args) throws Exception {
        String clientSentence, outSentence;

        if (args.length != 1) {
            System.err.println("Usage: java Server <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        ServerSocket welcomeSocket = new ServerSocket(portNumber);

        System.out.println("Server has started, waiting to connect to a socket..");

        while(true) {
            Socket connectionSocket = welcomeSocket.accept();

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            clientSentence = inFromClient.readLine();

            System.out.println("CLIENT: "+clientSentence);

            if(clientSentence.equals("Hello!")) {
                outSentence =  "Well hello, how are you?\n";
            } else if(clientSentence.equals("I'm fine!")) {
                outSentence =  "That's great!\n";
            } else {
                outSentence = "I'm really stupid, I don't understand your message.. Try 'Hello!'?\n";
            }

            outToClient.writeBytes(outSentence);

            System.out.println("SERVER: "+outSentence);
        }
    }
}
