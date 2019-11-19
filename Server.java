
package sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;

        try {
            server = new ServerSocket(5000);
            server.setReuseAddress(true);

            // Main is implemented to accept many connections (many clients)
            while (true) {
                Socket client = server.accept();
                System.out.println("User IP: " + client.getInetAddress().getHostAddress());
                Handler clientSock = new Handler(client);

                // The background thread will handle each client separately
                new Thread(clientSock).start();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
