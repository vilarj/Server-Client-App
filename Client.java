package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    public static void main(String[] args) throws IOException {
       final String HOST = "192.168.0.6"; // this is my IP4v Address
       final int PORT = 5000;
       DataInputStream input;
       DataOutputStream output;

       try{
           Socket socket = new Socket(HOST, PORT);

           input = new DataInputStream(socket.getInputStream()); // input
           output = new DataOutputStream(socket.getOutputStream()); // output

           output.writeUTF("Hey world! I'm a human being");

           String clientMessage = input.readUTF();

           System.out.println(clientMessage);

           socket.close(); // closing the client

       }
       catch(IOException e){
           Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
       }

    }
}
