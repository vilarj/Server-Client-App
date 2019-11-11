package socket_mini_chat;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class Form1 extends javax.swing.JFrame implements Observer {

    private JPanel panel1;
    private JButton sendButton;

    public Form1 () {
        Server server = new Server(5000); // creating a server
        server.addObserver(this); // add people to be able to notify to "this" (which is the class)

        Thread thread = new Thread(server);
        thread.start();
    }

    private void Send (java.awt.event.ActionEvent evt) {
        String message = "1: " + this.sendButton.getText() + "\n";
        //this.sendButton.append(message);

        Client client = new Client(5000, message);
        Thread thread = new Thread(client);
        thread.start();
    }

    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Form1().setVisible(true);

            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        //this.sendButton.append( (String) arg);
    }
}
