import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//Carlos Jimenez & Kyle Goulart

public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;
        OutputStream output = null;

        try {
            server = new ServerSocket(5000);
            server.setReuseAddress(true);
            System.out.print("~~Server initialized~~\n");

            // Main is implemented to accept many connections (many clients)
            while (true) {
                Socket client = server.accept();
                System.out.println("User IP: {" + client.getInetAddress().getHostAddress() + "}");
                Handler clientSock = new Handler(client);
                new Thread(clientSock).start();
            }
        }
        catch (IOException ex) {ex.printStackTrace();}

        finally {

            if (server != null) {

                try {
                    server.close();
                } catch (IOException ex) {ex.printStackTrace();}
            }
        }
    }
}
