package Decorataor;

public abstract class OrderDecorator implements Order {
    protected Order order;

    OrderDecorator(Order order) {
        this.order = order;
    }
}
