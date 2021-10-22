package Models;

import Observer.EventManager;
import Observer.EventType;
import Observer.IEventListener;

import java.util.ArrayList;
import java.util.List;

public class Publisher extends Person implements IEventListener {

    private List<Book> publishedBooks;
    private List<Book> availableBooks;
    private EventManager eventManager;

    public Publisher(String id, String name, String address) {
        super(id, name, address);
        eventManager = new EventManager();
        availableBooks = new ArrayList<>();
        publishedBooks = new ArrayList<>();
    }

    @Override
    public void update(String eventType, Book book) {
        System.out.println("[Notification] This book " + book.getTitle() + " is available for publish for " + name + " and " + id);
        availableBooks.add(book);
    }

    public void publishAvailableBooks() throws CloneNotSupportedException {
        for(Book book: availableBooks) {
            System.out.println("[Info] " + id + " and " + name + " Models.Publisher publishes a book with title: " + book.getTitle());
            book.setPublisher(this);
            eventManager.notify(EventType.PUBLISH, book);
            publishedBooks.add((book));
        }
        availableBooks = new ArrayList<>();
    }

    public void systemFollow(IEventListener listener) {
        eventManager.subscribe(EventType.PUBLISH, listener);
        System.out.println("[Info] System " + ((BPOSytem) listener).getID() + " added as observer");
    }

    public void systemUnFollow(IEventListener listener) {
        eventManager.unsubscribe(EventType.PUBLISH, listener);
        System.out.println("[Info] System " + ((BPOSytem) listener).getID() + " removed as observer");
    }

}
