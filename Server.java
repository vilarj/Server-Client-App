package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is intended to represent the client's side
 * from the server. Initializes the server and
 * places the client online or offline.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        final int PORT = 5000; // port number
        ServerSocket server; // server object
        Socket socket = null; // socket object
        DataInputStream input; // input
        DataOutputStream output; // output

        try{
            server = new ServerSocket(PORT);
            System.out.println("Server initialized");

            while(true){
                socket = server.accept();

                // Creating the bridge of communication
                System.out.println("Client is online");
                input = new DataInputStream(socket.getInputStream());
                output = new DataOutputStream(socket.getOutputStream());


                String serverMessage = input.readUTF(); // This waits for the client to send something
                System.out.println(serverMessage);

                output.writeUTF("Welcome to the server!"); // message from the server

                socket.close(); // closing the client NOT the server
                System.out.println("Client is offline");
            }
        }
        catch (IOException e){
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
