package State;

import State.State;

public class GoldState implements State {
    @Override
    public int getDiscount() {
        return 10;
    }
}