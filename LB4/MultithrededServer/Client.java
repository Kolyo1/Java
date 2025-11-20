import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 6000;

        try(Socket socket = new Socket(host,port)){
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);

            while(true){
                System.out.println("Choose an operation:\n1- Add Book\n2- Rent Book\n3- Return Book\n4- Exit");
                String choiceLine = scanner.nextLine().trim();
                int choice;
                try {
                    choice = Integer.parseInt(choiceLine);
                } catch (NumberFormatException nfe) {
                    System.out.println("Please enter a number between 1 and 4.");
                    continue;
                }
                if (choice < 1 || choice > 4) {
                    System.out.println("Please choose a valid option (1-4).");
                    continue;
                }
                out.writeInt(choice);
                switch(choice){
                    case 1:
                        System.out.println("Title");
                        out.writeUTF(scanner.nextLine());
                        System.out.println("Author");
                        out.writeUTF(scanner.nextLine());
                        System.out.println("Is Rented (true/false)");
                        String rentedLine = scanner.nextLine().trim().toLowerCase();
                        boolean isRented = rentedLine.equals("true") || rentedLine.equals("t") || rentedLine.equals("yes") || rentedLine.equals("y");
                        out.writeBoolean(isRented);
                        if(isRented){
                            System.out.println("Rented By");
                            out.writeUTF(scanner.nextLine());
                        }
                        out.flush();
                        // read server response
                        try {
                            String resp = in.readUTF();
                            System.out.println("Server: " + resp);
                        } catch (Exception e) {
                            System.out.println("No response from server.");
                        }
                    break;
                    case 2:
                        int rentId;
                        while (true) {
                            System.out.println("Book ID to rent:");
                            String idLine = scanner.nextLine().trim();
                            try {
                                rentId = Integer.parseInt(idLine);
                                break;
                            } catch (NumberFormatException nfe) {
                                System.out.println("Please enter a valid numeric book ID.");
                            }
                        }
                        out.writeInt(rentId);
                        System.out.println("Renter Name:");
                        out.writeUTF(scanner.nextLine());
                        out.flush();
                        try {
                            String resp2 = in.readUTF();
                            System.out.println("Server: " + resp2);
                        } catch (Exception e) {
                            System.out.println("No response from server.");
                        }
                    break;
                    case 3:
                        int returnId;
                        while (true) {
                            System.out.println("Book ID to return:");
                            String idLine2 = scanner.nextLine().trim();
                            try {
                                returnId = Integer.parseInt(idLine2);
                                break;
                            } catch (NumberFormatException nfe) {
                                System.out.println("Please enter a valid numeric book ID.");
                            }
                        }
                        out.writeInt(returnId);
                        out.flush();
                        try {
                            String resp3 = in.readUTF();
                            System.out.println("Server: " + resp3);
                        } catch (Exception e) {
                            System.out.println("No response from server.");
                        }
                    break;
                    case 4:
                        System.out.println("Exiting...");
                        socket.close();
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                        continue;
            }
        }
    }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}