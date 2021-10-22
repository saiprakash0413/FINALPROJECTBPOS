package Decorataor;

public class OrderBase implements Order {

    private double amount;

    public OrderBase(double amount) {
        this.amount = amount;
    }

    @Override
    public double getTotalAmount() {
        return amount;
    }
}
