package Decorataor;

public class AllowGiftedOrder extends OrderDecorator {
    public AllowGiftedOrder(Order order) {
        super(order);
    }

    @Override
    public double getTotalAmount() {
        return order.getTotalAmount() + 100;
    }
}
