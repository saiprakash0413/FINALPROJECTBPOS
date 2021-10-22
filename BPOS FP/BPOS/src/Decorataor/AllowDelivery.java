package Decorataor;

public class AllowDelivery extends OrderDecorator {

    public AllowDelivery(Order order) {
        super(order);
    }

    @Override
    public double getTotalAmount() {
        return order.getTotalAmount() + 50;
    }
}
