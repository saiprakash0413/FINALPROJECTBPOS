package Models;

import Observer.EventManager;
import Observer.EventType;
import Observer.IEventListener;

import java.util.ArrayList;
import java.util.List;

public class Author extends Person {

    private List<Book> books;
    private EventManager eventManager;

    public Author(String id, String name, String address) {
        super(id, name, address);
        books = new ArrayList<>();
        eventManager = new EventManager();
    }

    public void writes(Book book) throws CloneNotSupportedException {
        System.out.println("Models.Author: " + name +  " writes a book with title: " + book.getTitle());
        book.setAuthor(this);
        books.add(book);
        eventManager.notify(EventType.WRITE, book);
    }

    public void publisherFollow(IEventListener listener) {
        eventManager.subscribe(EventType.WRITE, listener);
        System.out.println("[Info] " + ((Publisher) listener).getName() + " added as follower");
    }

    public void publisherUnFollow(IEventListener listener) {
        eventManager.unsubscribe(EventType.WRITE, listener);
        System.out.println("[Info] " + ((Publisher) listener).getName() + " removed as observer");
    }

    public List<Book> getBooks() {
        return books;
    }
}