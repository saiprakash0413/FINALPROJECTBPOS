package Decorataor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void getTotalAmount() {
        Order order = new OrderBase(1000);
        order = new AllowDelivery(order);
        assertEquals(1050, order.getTotalAmount());

        order = new AllowGiftedOrder(order);
        assertEquals(1150, order.getTotalAmount());
    }
}