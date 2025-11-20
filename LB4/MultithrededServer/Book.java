import java.util.concurrent.atomic.AtomicInteger;

public class Book{
    static final AtomicInteger nextId = new AtomicInteger(1);
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
        this.isRented = rented;
        if (!rented) {
            this.rentedBy = "Not Rented";
        }
    }

    public void setRentedBy(String renter) {
        if (renter == null || renter.isEmpty()) {
            this.rentedBy = "Not Rented";
        } else {
            this.rentedBy = renter;
        }
    }
    
}