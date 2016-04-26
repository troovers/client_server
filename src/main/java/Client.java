import java.io.*;
import java.net.*;

/**
 * Created by Thomas on 22-4-2016.
 */
public class Client {
    public static void main(String[] args) throws Exception {
        String sentence, modifiedSentence;

        if (args.length != 2) {
            System.err.println("Usage: java Client <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            Socket clientSocket = new Socket(hostName, portNumber);

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            sentence = inFromUser.readLine();

            outToServer.writeBytes(sentence + "\n");

            modifiedSentence = inFromServer.readLine();

            System.out.println("SERVER: " + modifiedSentence);

            clientSocket.close();
        }
    }
}
