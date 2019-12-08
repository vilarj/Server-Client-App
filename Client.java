import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int PORT = 5000;
    private static final String HOST = "10.220.73.108";

    public static void main(String[] args) {

        try (Socket socket = new Socket(HOST, PORT)) {
            Scanner scanner = new Scanner(System.in);
            String line = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // this stores what the user inputs
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // this takes what the server says and output it
            System.out.print("Enter the name of the file you are looking for: ");

            while (!"exit".equalsIgnoreCase(line)) {
                line = scanner.nextLine();

                if (line.toLowerCase().equals("quit") || line.toLowerCase().equals("exit")) {
                    System.out.print("~Thank you for using our server~");
                    System.exit(0);
                }
                out.println(line);
                out.flush();
                System.out.println("Server says: " + in.readLine());
            }
            scanner.close(); // Closing the Scanner object
        } catch (IOException ex) {ex.printStackTrace();}
    }
}
