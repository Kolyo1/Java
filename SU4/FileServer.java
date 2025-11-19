import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Usage: java FileServer <data.bin>");
            return;
        }

        String fileName = args[0];
        int port = 7000;

        File file = new File(fileName);
        if (!file.exists() || !file.isFile()) {
            System.out.println("This file does not exist: " + fileName);
            return;
        }

        try(ServerSocket server = new ServerSocket(port)){
            while(true){
            Socket socket = server.accept();
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            long fileLength = file.length();
            out.writeLong(fileLength);
            out.flush();

            
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while( (bytesRead = fis.read(buffer)) != -1 ) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();
            fis.close();
            socket.close();

        }
    }
    catch (IOException e) {
        e.printStackTrace();
    }
}
}
