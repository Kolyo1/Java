import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BookClient {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 8000;
        boolean running = true;

        try(Socket socket = new Socket(host, port)){
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            Thread serverReader = new Thread(() -> {
                try {
                    String response;
                    while ((response = in.readLine()) != null) {
                        System.out.println(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            serverReader.setDaemon(true);
            serverReader.start();

            String line;
            while (running) {
                line = userInput.readLine();
                if (line == null) break;
                out.println(line);
            }
            running = false;
        }
        catch(IOException e){
            e.printStackTrace();
    }
}
}