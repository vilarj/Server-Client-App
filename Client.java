package socket_mini_chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable {
    private int port;
    private String message;

    public Client (int port, String message){
        this.port = port;
        this.message = message;
    }

    @Override
    public void run() {
        final String HOST = "192.168.0.6"; // this is my IP4v Address
        final int PORT = 5000;
        DataOutputStream output;

        try{
            Socket socket = new Socket(HOST, PORT);

            output = new DataOutputStream(socket.getOutputStream()); // output

            output.writeUTF(message);

            socket.close(); // closing the client
        }
        catch(IOException e){
            Logger.getLogger(socket.Client.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
