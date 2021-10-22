package Factory;

public interface ObjectFactory {

    Object getObject(String input, String... eventTypes);
}
