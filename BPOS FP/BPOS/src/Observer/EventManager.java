package Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Models.*;

public class EventManager {

    private Map<String, List<IEventListener>> listeners;

    public EventManager() {
        this.listeners = new HashMap<>();
        this.listeners.put(EventType.PUBLISH, new ArrayList<>());
        this.listeners.put(EventType.WRITE, new ArrayList<>());
    }

    public void subscribe(String eventType, IEventListener listener) {
        List<IEventListener> users =  this.listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(String eventType, IEventListener listener) {
        List<IEventListener> users =  this.listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(String eventType, Book book) throws CloneNotSupportedException {
        List<IEventListener> users =  this.listeners.get(eventType);
        for (IEventListener listener : users) {
            Book newBook = (Book) book.clone();
            listener.update(eventType, newBook);
        }
    }
}
