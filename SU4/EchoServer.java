import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) {
        int port = 6000;
        int counter = 0;
        boolean running = true;
        try(ServerSocket server = new ServerSocket(port)){
            Socket socket = server.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);


            String line;
            while (running) {
                line = in.readLine();
                if (line == null) {
                    break;
                }
                if (line.equalsIgnoreCase("quit")) {
                    out.println("Bye"); 
                    break;
                }
                counter++;
                out.println("#" + counter + ": " + line);
            }
            socket.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        running = false;
    }
}
