package Factory;

import State.*;

public class Statefactory implements ObjectFactory {
    @Override
    public State getObject(String input, String... eventTypes) {
        if(input.toLowerCase().contains("base")) {
            return new BaseState();
        } else if(input.toLowerCase().contains("silver")) {
            return new SilverState();
        } else if(input.toLowerCase().contains("gold")) {
            return new GoldState();
        } else if(input.toLowerCase().contains("platinum")) {
            return new PlatinumState();
        } else {
            return null;
        }
    }
}
