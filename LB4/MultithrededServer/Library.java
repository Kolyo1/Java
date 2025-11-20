import java.util.*;

public class Library {
    Map<Integer, Book> books = new HashMap<>();

    public synchronized Book addBook(String title, String author, boolean isRented, String rentedBy ) {
        Book book = new Book(title, author, isRented, rentedBy);
        books.put(book.getId(), book);
        return book;
    }
    
    public synchronized Book getBook(int id) {
        return books.get(id);
    }

    public synchronized String rentBook(int id, String renter){
        Book book = books.get(id);
        if(book == null){
            return "Book not found.";
        }
        if (book.isRented()) {
            return "Book is already rented by " + book.getRentedBy() + ".";
        }
        book.setRented(true);
        book.setRentedBy(renter);
        return "Rented successfully to " + renter + ".";
    }

    public synchronized String returnBook(int id){
        Book book = books.get(id);
        if(book == null){
            return "Book not found.";
        }
        if (!book.isRented()) {
            return "Book is not currently rented.";
        }
        book.setRented(false);
        book.setRentedBy("Not Rented");
        return "Returned successfully";
    }
}
