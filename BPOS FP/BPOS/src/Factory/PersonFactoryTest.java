package Factory;

import static org.junit.jupiter.api.Assertions.*;

class PersonFactoryTest {

    @org.junit.jupiter.api.Test
    void getObject() {
        ObjectFactory factory = new PersonFactory();
        Models.Person person = (Models.Person) factory.getObject("author", "1", "ABC", "XYZ");
        assertTrue(person instanceof Models.Author);

        person = (Models.Person) factory.getObject("publisher", "1", "ABC", "XYZ");
        assertTrue(person instanceof Models.Publisher);

        person = (Models.Person) factory.getObject("customer", "1", "ABC", "XYZ");
        assertTrue(person instanceof Models.Customer);
    }
}