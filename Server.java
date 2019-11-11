package socket_mini_chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Observable implements Runnable {
    private int port;

    public Server(int port){
        this.port = port;
    }

    @Override
    public void run() {
        ServerSocket server; // server object
        Socket socket = null; // socket object
        DataInputStream input; // input

        try{
            server = new ServerSocket(port);
            System.out.println("Server initialized");

            while (true){
                socket = server.accept();

                // Creating the bridge of communication
                System.out.println("Client is online");
                input = new DataInputStream(socket.getInputStream());

                String serverMessage = input.readUTF(); // This waits for the client to send something
                System.out.println(serverMessage);

                // Notify the changes: this idea of Observable
                this.setChanged(); // it notifies that something is going to change
                this.notifyObservers(serverMessage); // this is what is being sent
                this.clearChanged(); // clears the message


                socket.close(); // closing the client NOT the server
                System.out.println("Client is offline");
            }
        }
        catch (IOException e){
            Logger.getLogger(socket.Server.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
