
package sockets;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * In order for this to work, the client should be in the same PORT and HOST
 */
public class Client {
    private static final int PORT = 5000;
    private static final String HOST = "192.168.0.6";

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT)) {
            Scanner scanner = new Scanner(System.in);
            String line = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // this stores what the user inputs
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // this takes what the server says and output it

            while (!"exit".equalsIgnoreCase(line)) {
                line = scanner.nextLine();
                out.println(line);
                out.flush();
                System.out.println("First Client says: " + in.readLine());
            }
            scanner.close(); // Closing the Scanner object
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
