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
        } catch (IOException ex) {ex.printStackTrace();}

        finally {
            try {

                if (output != null)
                    output.close();

                if (input != null)
                    input.close();

                clientSocket.close();
            } catch (IOException ex) {ex.printStackTrace();}
        }
    }

    /**
     * Gets the file name from the users request and if the file exists,
     * returns the contents to the user in a new file
     * @param fileName
     * @return a message that informs the user of
     * the location of the file after it is sent
     */
    private String SendFile (String fileName) throws IOException {
        File file = new File(fileName);
        String msg = "";

        try (Scanner read = new Scanner(file)) {
            if (file.length() == 0) {
                System.out.println("File was empty");
                return "=============The file is empty==============";
            }

            while (read.hasNext()) {
                msg += read.nextLine() + "\r\n";
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            return "=============The file wan't found==============";
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\goulartk1\\Desktop\\contentsOfRequestedFile.txt"));
        writer.write(msg);
        writer.close();
        System.out.println("File successfully transferred");
        return "Your file can be found on your desktop after this process has been terminated: contentsOfRequestedFile.txt";
    }
}
