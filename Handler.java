package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Handler implements Runnable {
    private final Socket clientSocket;

    public Handler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            String line;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            while ((line = in.readLine()) != null) {
                System.out.printf("Sent from the client: %s\n", line);
                out.println(line);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null)
                    in.close();
                clientSocket.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
