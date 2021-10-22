package Factory;

import Models.*;

public class PersonFactory implements ObjectFactory {
    @Override
    public Person getObject(String input, String... personInfo) {
        if(input.toLowerCase().contains("author")) {
            return new Author(personInfo[0], personInfo[1], personInfo[2]);
        } else if(input.toLowerCase().contains("publisher")) {
            return new Publisher(personInfo[0], personInfo[1], personInfo[2]);
        } else if(input.toLowerCase().contains("customer")) {
            return new Customer(personInfo[0], personInfo[1], personInfo[2]);
        } else {
            return null;
        }
    }
}
