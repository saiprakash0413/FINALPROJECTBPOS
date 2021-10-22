package Models;

import Decorataor.Order;
import Factory.ObjectFactory;
import Factory.Statefactory;
import State.State;

import java.util.ArrayList;
import java.util.List;

import State.*;

public class Customer extends Person {

    private ObjectFactory factory;
    private State accountState;
    private List<Book> boughtBooks;
    private ShoppingCart cart;
    private List<Order> orders;

    public Customer(String id, String name, String address) {
        super(id, name, address);
        factory = new Statefactory();
        accountState = new BaseState();
        boughtBooks = new ArrayList<>();
        cart = new ShoppingCart();
        orders = new ArrayList<>();
    }

    public void addBook(Book book) {
        if(book != null) {
            cart.addBook(book);
            System.out.println("[Info] Models.Book with title " + book.getTitle() + " has been added to cart");
        }
    }

    public void removeBook(Book book) {
        if(book != null) {
            cart.removeBook(book);
            System.out.println("[Info] Models.Book with title " + book.getTitle() + " has been removed from cart");
        }
    }

    public void checkOut(double amount) {
        Order order = cart.checkOut(amount, accountState.getDiscount());
        if(order != null) {
            System.out.println("Payment Successful");
            List<Book> newBooks = cart.getItems();
            boughtBooks.addAll(newBooks);
            evaluateState();
            cart = new ShoppingCart();
            orders.add(order);
        } else {
            System.out.println("Payment Failed");
        }
    }

    public double getCartAmount() {
        return cart.getOrderAmount();
    }

    private void evaluateState() {
        if (boughtBooks.size() <= 10) accountState = (State) factory.getObject("base");
        else if (boughtBooks.size() <= 20) accountState = (State) factory.getObject("silver");
        else if (boughtBooks.size() <= 30) accountState = (State) factory.getObject("gold");
        else  accountState = (State) factory.getObject("platinum");
    }

    public void displayCartItems() {
        System.out.println(cart.toString());
    }

    public Book getCartBook(String title) {
        return cart.getBook(title);
    }

    public List<Book> getCartItems() {
        return cart.getItems();
    }

    public void addDelivery() {
        cart.addDelivery();
        System.out.println("[Info] Delivery Added");
    }

    public void addGift() {
        cart.addGift();
        System.out.println("[Info] Gift Packing Added");
    }

    public void placeOrder() {
        cart.createOrder(accountState.getDiscount());
    }

    public List<Order> getOrders() {
        return orders;
    }
}