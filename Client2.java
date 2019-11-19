package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    private static final String HOST = "10.220.88.53";
    private static final int PORT = 5000;

    public static void main (String[] args) {
        try (Socket socket = new Socket(HOST, PORT)) {
            Scanner scanner = new Scanner(System.in);
            String line = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // this stores what the user inputs
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // this takes what the server says and output it

            while (!"exit".equalsIgnoreCase(line)) {
                line = scanner.nextLine();
                out.println(line);
                out.flush();
                System.out.println("Second Client says: " + in.readLine());
            }
            scanner.close(); // Closing the Scanner object
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
