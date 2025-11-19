import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {

    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 6000;
        boolean running = true;

        try(Socket socket = new Socket(host, port)){
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String line;

            while (running) {
                line = userInput.readLine();
                if (line == null) break;
                out.println(line);
                String response = in.readLine();
                if (response == null) {
                    System.out.println("Server closed connection.");
                    break;
                }
                System.out.println("Server response: " + response);
                if ("Bye".equals(response)) {
                    break;
                }
            }
            running = false;
        }
        catch(IOException e){
            e.printStackTrace();
    }
}
}
