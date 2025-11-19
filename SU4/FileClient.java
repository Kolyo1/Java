import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class FileClient {

    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 7000;
        String outputFile = "received.bin";

        try(Socket socket = new Socket(host, port)){
            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            FileOutputStream fos = new FileOutputStream(new File(outputFile));

            long expected = in.readLong();
            byte[] buffer = new byte[4096];
            long bytesReadTotal = 0;

            while(expected > bytesReadTotal){
                int bytesRead = (int) Math.min(buffer.length, expected - bytesReadTotal);
                int read = in.read(buffer, 0, bytesRead);
                if(read == -1) break;
                fos.write(buffer, 0, read);
                bytesReadTotal += read;
            }
            fos.close();   
            System.out.println("Получени байтове: " + bytesReadTotal);
            System.out.println("Очаквана дължина: " + expected);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
