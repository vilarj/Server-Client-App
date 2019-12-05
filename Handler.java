
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
     * @param fileName
     * @return "destinations.txt"
     */
    private String SendFile (String fileName) throws IOException {
        File f = new File(fileName);
        String newString = "";
        try (Scanner read = new Scanner(f)) {
            if (f.length() == 0)
                System.out.println("=============The file is empty==============");

            while (read.hasNext()) {
                newString += read.nextLine() + " ";
            }
        } catch (FileNotFoundException ex) {
            System.exit(0);
        }
        //return newString;
        BufferedWriter writer = new BufferedWriter(new FileWriter("newFile.txt"));
        writer.write(newString);
        writer.close();
        return "Your file can be found src folder after this process has been terminated.";
    }
}
