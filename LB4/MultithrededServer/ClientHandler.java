import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread{
    final Socket clientSocket;
    final Library library;

    public ClientHandler(Socket socket, Library library) {
        this.clientSocket = socket;
        this.library = library;
    }

    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

            while (true) {
                int op;
                try {
                    op = in.readInt();
                } catch (Exception e) {
                    break;
                }
                switch (op) {
                    case 1: // Add Book
                        String title = in.readUTF();
                        String author = in.readUTF();
                        boolean isRented = in.readBoolean();
                        String rentedBy;
                        if (isRented) {
                            rentedBy = in.readUTF();
                        } else {
                            rentedBy = "Not Rented";
                        }
                        Book book = library.addBook(title, author, isRented, rentedBy);
                        out.writeUTF("Added successfully: ID " + book.getId());
                        out.flush();
                        break;
                    case 2: // Rent book
                        int rentId = in.readInt();
                        String renterName = in.readUTF();
                        String rentResponse = library.rentBook(rentId, renterName);
                        out.writeUTF(rentResponse);
                        out.flush();
                        break;

                    case 3: // Return book
                        int returnId = in.readInt();
                        String returnResponse = library.returnBook(returnId);
                        out.writeUTF(returnResponse);
                        out.flush();
                        break;
                    case 4: // Exit
                        clientSocket.close();
                        return;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Client disconnected: " + e.getMessage());
        }
    }
}
