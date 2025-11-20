import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

class Book{
    private static final AtomicInteger nextId = new AtomicInteger(1);
    int Id;
    String title;
    String author;
    boolean isRented;
    String rentedBy;

    public Book(String title, String author, boolean isRented, String rentedBy) {
        this.Id = nextId.getAndIncrement();
        this.title = title;
        this.author = author;
        this.isRented = isRented;
        if(!isRented){
            this.rentedBy = "Not Rented";
        }
        else{
            this.rentedBy = rentedBy;
        }
    }

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isRented() {
        return isRented;
    }

    public String getRentedBy() {
        return rentedBy;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }
    
}

public class BookServer {
    public static void main(String[] args) {
        final int PORT = 8000;

        try (ServerSocket server = new ServerSocket(PORT)) {
            Socket socket = server.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            List<Book> bookList = new ArrayList<>();
            out.println("Connected");

           while(true){
            out.println("Menu :");
            out.println("1. Add Book");
            out.println("2. Rent Book");
            out.println("3. Return Book");
            out.println("4. Quit");

            String choice = in.readLine();
            switch(choice){
                case "1":
                    out.println("Enter Book Title:");
                    String title = in.readLine();
                    out.println("Enter Book Author:");
                    String author = in.readLine();
                    out.println("Is the book currently rented? (y/n):");
                    String rentedResp = in.readLine();
                    boolean isRented = rentedResp != null && (rentedResp.equalsIgnoreCase("y") || rentedResp.equalsIgnoreCase("yes"));
                    String rentedBy = "";
                    if (isRented) {
                        out.println("Enter renter name:");
                        rentedBy = in.readLine();
                    }
                    Book newBook = new Book(title, author, isRented, rentedBy);
                    bookList.add(newBook);
                    out.println("Book added successfully with ID: " + newBook.getId());
                    break;
                case "2":
                    out.println("Enter Book ID to Rent:");
                    int rentId = Integer.parseInt(in.readLine());
                    boolean foundRent = false;
                    for(Book book : bookList){
                        if(book.getId() == rentId){
                            foundRent = true;
                            if(!book.isRented()){
                                book.setRented(true);
                                out.println("Book rented successfully.");
                            } else {
                                out.println("Book is already rented by " + book.getRentedBy());
                            }
                            break;
                        }
                    }
                    if(!foundRent){
                        out.println("Book ID not found.");
                    }
                    break;
                case "3":
                    out.println("Enter Book ID to Return:");
                    int returnId = Integer.parseInt(in.readLine());
                    boolean foundReturn = false;
                    for(Book book : bookList){
                        if(book.getId() == returnId){
                            foundReturn = true;
                            if(book.isRented()){
                                book.setRented(false);
                                out.println("Book returned successfully.");
                            } else {
                                out.println("Book was not rented.");
                            }
                            break;
                        }
                    }
                    if(!foundReturn){
                        out.println("Book ID not found.");
                    }
                    break;
                case "4":
                    out.println("Goodbye!");
                    socket.close();
                    return;
                default:
                    out.println("Invalid choice. Please try again.");
            }
           }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}