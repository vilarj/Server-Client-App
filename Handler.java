
package sockets;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Handler implements Runnable {
    private final Socket clientSocket;
    Handler(Socket socket) {this.clientSocket = socket;}
    private String line = "";

    @Override
    public void run() {
        BufferedReader input = null;
        PrintWriter output = null;

        try {
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(clientSocket.getOutputStream(), true);

            while ((line = input.readLine()) != null) {
                System.out.printf("Request from the client: %s\n", line);
                output.println(SendFile(line));
            }
        }

        catch (IOException ex) {ex.printStackTrace();}
        finally {
            try {
                if (output != null) {
                    output.close();
                }
                if (input != null)
                    input.close();
                clientSocket.close();
            }
            catch (IOException ex) {ex.printStackTrace();}
        }
    }

    /**
     * Function that gets a request from the user and send
     * back the file requested from the user
     *
     * @param file
     * @return "destinations.txt"
     */
    private String SendFile (String file) {
        try (Scanner read = new Scanner(new File("destinations.txt"))) {
            do {
                if (file.isEmpty())
                    System.out.println("=============The file is empty==============");
            }
            while(read.hasNext()); {
                file += read.nextLine();
            }
        }
        catch (FileNotFoundException ex) {
            ex.fillInStackTrace();
        }
        return file;
    }
}
