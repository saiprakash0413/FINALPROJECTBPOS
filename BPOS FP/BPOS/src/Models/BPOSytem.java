package Models;

import Observer.IEventListener;

import java.util.ArrayList;
import java.util.List;

public class BPOSytem implements IEventListener {

    private int ID;
    private List<Book> books;
    private List<Book> availableBooks;

    public BPOSytem(int id) {
        ID = id;
        books = new ArrayList<>();
        availableBooks = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    @Override
    public void update(String eventType, Book book) {
        System.out.println("[Notification] This book " + book.getTitle() + " is available for display for system: " + ID);
        availableBooks.add(book);
    }

    public void buyAvailableBooks() {
        for(Book book: availableBooks) {
            System.out.println("System buys a book with title: " + book.getTitle() + ", publisher: " + book.getPublisher().getName() + ", author: " + book.getAuthor().getName());
            book.setSellingPrice(1000);
            books.add(book);
        }
        availableBooks = new ArrayList<>();
    }

    public void displayAllBooks() {
        for(Book book: books) System.out.println("[ID]: " + book.getIsbn()  + ", [Title]: " + book.getTitle() + ", [Models.Publisher]: " + book.getPublisher().getName() + ", [Models.Author]: " + book.getAuthor().getName() + ", [MRP Price]: " + book.getTotalAmount());
    }

    public Book getBook(String title) {
        for(Book book: books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public void removeBook(Book book) {
        books.remove(book);
        System.out.println("[Info] Models.Book with title " + book.getTitle() + " has been removed from system " + ID);
    }
}
