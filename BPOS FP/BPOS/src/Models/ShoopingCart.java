package Models;

import Decorataor.AllowDelivery;
import Decorataor.AllowGiftedOrder;
import Decorataor.Order;
import Decorataor.OrderBase;

import java.util.ArrayList;
import java.util.List;

class ShoppingCart {

    private List<Book> books;
    private Order order;

    public ShoppingCart() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public int getTotalAmount() {
        int total = 0;
        for(Book book: books) {
            total += book.getTotalAmount();
        }
        return total;
    }

    public Order checkOut(double amount, double discount) {
        double price = getTotalAmount();
        price = price - (price*discount)/100;

        if(amount >= price) return order;
        return null;
    }

    public List<Book> getItems() {
        return books;
    }

    public Book getBook(String title) {
        for(Book book: books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String result = "";
        for(Book book: books) result += "[ID]: " + book.getIsbn()  + ", [Title]: " + book.getTitle() + ", [Models.Publisher]: " + book.getPublisher().getName() + ", [Models.Author]: " + book.getAuthor().getName() + ", [MRP Price]: " + book.getTotalAmount() + "\n";
        return result;
    }

    public void addDelivery() {
        if(order != null) order = new AllowDelivery(order);
    }

    public void addGift() {
        if(order != null) order = new AllowGiftedOrder(order);
    }

    public void createOrder(int discount) {
        double price = getTotalAmount();
        price = price - (price*discount)/100;

        order = new OrderBase(price);
    }

    public double getOrderAmount() {
        if(order != null) return order.getTotalAmount();
        return 0;
    }
}
