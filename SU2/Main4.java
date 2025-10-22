package SU2;

import java.lang.reflect.Array;
import java.util.*;

abstract class LibraryItem {
    String title;

    public LibraryItem(String title) {
        this.title = title;
    }

    abstract void displayInfo();
}

class Book extends LibraryItem {
    String author;
    
    public Book(String title, String author) {
        super(title);
        this.author = author;
    }

    @Override
    void displayInfo() {
        System.out.println("Book Title: " + title + ", Author: " + author);
    }
}

class Magazine extends LibraryItem {
    int issueNumber;
    
    public Magazine(String title, int issueNumber) {
        super(title);
        this.issueNumber = issueNumber;
    }

    @Override
    void displayInfo() {
        System.out.println("Magazine Title: " + title + ", Issue Number: " + issueNumber);
    }
}

public class Main4 {
    public static void main(String[] args) {
       ArrayList<LibraryItem> library = new ArrayList<>();
       library.add(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
       library.add(new Magazine("National Geographic", 202));

         for (LibraryItem item : library) {
              item.displayInfo();
         }
    }
}
