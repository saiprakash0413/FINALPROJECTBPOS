package Observer;

import Models.*;

public interface IEventListener {

    void update(String eventType, Book book);
}
