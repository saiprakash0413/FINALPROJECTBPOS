package user;
import Factory.ObjectFactory;
import Factory.PersonFactory;
import Models.*;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class User {

    public static void main(String[] args)  {
        System.out.println("System started with this ID: 101");
        Scanner scanner = new Scanner(System.in);
        BPOSytem system = new BPOSytem(101);

        HashMap<String, Integer> counterMap = new HashMap<>();
        counterMap.put("customer", 0);
        counterMap.put("author", 0);
        counterMap.put("publisher", 0);

        ObjectFactory factory = new PersonFactory();

        HashMap<String, HashMap<Integer, Person>> map = new HashMap<>();
        HashMap<Integer, Person> customers = new HashMap<>();
        HashMap<Integer, Person> publishers = new HashMap<>();
        HashMap<Integer, Person> authors = new HashMap<>();
        map.put("customer", customers);
        map.put("publisher", publishers);
        map.put("author", authors);

        boolean EXIT_FLAG = true;
        while (EXIT_FLAG) {
            System.out.println("=========== Console =============");
            System.out.println();
            System.out.println("Possible Queries: ");
            System.out.println("1. Add Customer");
            System.out.println("2. Add Author");
            System.out.println("3. Add Publisher");
            System.out.println("4. Publisher follow author");
            System.out.println("5. Publisher unfollow author");
            System.out.println("6. Author Write");
            System.out.println("7. Publisher Publish");
            System.out.println("8. Buys available books for system");
            System.out.println("9. Display all books of the system");
            System.out.println("10. Search or Add books in customer's cart");
            System.out.println("11. Display customer's cart");
            System.out.println("12. Remove customer's cart");
            System.out.println("13. Checkout customer's cart");
            System.out.println("14. Exit");

            System.out.println();
            System.out.print("Enter your query choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    User.createUser("customer", scanner, counterMap, factory, map, system);
                    System.out.println("[Info] Models.Customer Added !!");
                    break;
                case 2:
                    User.createUser("author", scanner, counterMap, factory, map, system);
                    System.out.println("[Info] Models.Author Added !!");
                    break;
                case 3:
                    User.createUser("publisher", scanner, counterMap, factory, map, system);
                    System.out.println("[Info] Models.Publisher Added !!");
                    break;
                case 4:
                    User.follow(true, scanner, map);
                    System.out.println("[Info] Done !!");
                    break;
                case 5:
                    User.follow(false, scanner, map);
                    System.out.println("[Info] Done !!");
                    break;
                case 6:
                    User.write(scanner, map);
                    break;
                case 7:
                    User.publish(scanner, map);
                    break;
                case 8:
                    system.buyAvailableBooks();
                    break;
                case 9:
                    system.displayAllBooks();
                    break;
                case 10:
                    User.searchBook(scanner, map, system);
                    break;
                case 11:
                    User.displayCart(scanner, map);
                    break;
                case 12:
                    User.removeCart(scanner, map);
                    break;
                case 13:
                    User.checkoutCart(scanner, map, system);
                    break;
                case 14:
                    EXIT_FLAG = false;
                    System.out.println("[Exit] Thanks and Bye !!");
                    break;
                default:
                    System.out.println("[Error] Invalid Choice !!");
            }
        }
    }

    private static void checkoutCart(Scanner scanner, HashMap<String, HashMap<Integer, Person>> map, BPOSytem system) {
        System.out.print("Enter your customer Id: ");
        int customerId = scanner.nextInt();
        try {
            Customer customer = (Customer) map.get("customer").get(customerId);

            customer.placeOrder();
            List<Book> books = customer.getCartItems();
            System.out.print("Do you want to add delivery option (yes|no)");
            String choice = scanner.next();
            if (choice.toLowerCase().equals("yes")) customer.addDelivery();

            System.out.print("Do you want to pack these books as gift (yes|no)");
            choice = scanner.next();
            if (choice.toLowerCase().equals("yes")) customer.addGift();

            System.out.println();
            double amount = customer.getCartAmount();
            System.out.println("[Info] Total Amount: " + amount);
            System.out.println("[Info] Payment in process ...........");
            System.out.println();
            Thread.sleep(5000);

            customer.checkOut(amount);
            for (Book book : books) system.removeBook(book);
        } catch (InterruptedException e) {
            System.out.println("[Error] Operation failed !!");
        } catch (NullPointerException nullPointer) {
            System.out.println("[Error] Invalid ID !!");
            System.out.println("[Error] Operation failed !!");
        } catch (Exception e) {
            System.out.println("[Error] Operation failed !!");
        }
    }

    private static void removeCart(Scanner scanner, HashMap<String, HashMap<Integer, Person>> map) {
        System.out.print("Enter your customer Id: ");
        int customerId = scanner.nextInt();
        try {
            Customer customer = (Customer) map.get("customer").get(customerId);

            System.out.print("Enter the title which you want to remove from the cart: ");
            String title = scanner.next();
            Book book = customer.getCartBook(title);
            customer.removeBook(book);
        } catch (NullPointerException nullPointer) {
            System.out.println("[Error] Invalid ID !!");
            System.out.println("[Error] Operation failed !!");
        } catch (Exception e) {
            System.out.println("[Error] Operation failed !!");
        }
    }

    private static void displayCart(Scanner scanner, HashMap<String, HashMap<Integer, Person>> map) {
        System.out.print("Enter your customer Id: ");
        int customerId = scanner.nextInt();
        try {
            Customer customer = (Customer) map.get("customer").get(customerId);
            customer.displayCartItems();
        } catch (NullPointerException nullPointer) {
            System.out.println("[Error] Invalid ID !!");
            System.out.println("[Error] Operation failed !!");
        } catch (Exception e) {
            System.out.println("[Error] Operation failed !!");
        }
    }

    private static void searchBook(Scanner scanner, HashMap<String, HashMap<Integer, Person>> map, BPOSytem system) {
        System.out.print("Enter your customer Id: ");
        int customerId = scanner.nextInt();
        try {
            Customer customer = (Customer) map.get("customer").get(customerId);

            System.out.print("Enter the title: ");
            String title = scanner.next();
            Book book = system.getBook(title);
            System.out.print("Do you want to add this book into your cart (yes|no): ");
            String choice = scanner.next();
            if (choice.toLowerCase().equals("yes")) customer.addBook(book);
        } catch (NullPointerException nullPointer) {
            System.out.println("[Error] Invalid ID !!");
            System.out.println("[Error] Operation failed !!");
        } catch (Exception e) {
            System.out.println("[Error] Operation failed !!");
        }
    }

    private static void publish(Scanner scanner, HashMap<String, HashMap<Integer,Person>> map) {
        System.out.print("Enter Models.Publisher ID which want to publish available books: ");
        int id = scanner.nextInt();
        try {
            ((Publisher) map.get("publisher").get(id)).publishAvailableBooks();
        } catch (NullPointerException nullPointer) {
            System.out.println("[Error] Invalid ID !!");
            System.out.println("[Error] Operation failed !!");
        } catch (CloneNotSupportedException e) {
            System.out.println("[Error] Not able to publish");
            System.out.println("[Error] Operation failed !!");
        } catch (Exception e) {
            System.out.println("[Error] Operation failed !!");
        }
    }

    private static void write(Scanner scanner, HashMap<String, HashMap<Integer,Person>> map)  {
        try {
            System.out.print("Enter Models.Author ID which want to write: ");
            int id = scanner.nextInt();
            Author author = (Author) map.get("author").get(id);
            System.out.print("Enter the Title: ");
            String title = scanner.next();
            Book book = new Book();
            book.setTitle(title);
            author.writes(book);
        } catch (NullPointerException nullPointer) {
            System.out.println("[Error] Invalid ID !!");
            System.out.println("[Error] Operation failed !!");
        } catch (CloneNotSupportedException e) {
            System.out.println("[Error] Not able to write the book");
            System.out.println("[Error] Operation failed !!");
        } catch (Exception e) {
            System.out.println("[Error] Operation failed !!");
        }
    }

    private static void follow(boolean followFlag, Scanner scanner, HashMap<String, HashMap<Integer, Person>> map) {
        try {
            System.out.print("Enter publisher Id which you want to " + ((followFlag) ? "follow" : "unfollow") + ": ");
            int observerId = scanner.nextInt();
            System.out.print("Enter author Id whom you want to " + ((followFlag) ? "follow" : "unfollow") + ": ");
            int producerId = scanner.nextInt();

            Publisher publisher = (Publisher) map.get("publisher").get(observerId);
            Author author = (Author) map.get("author").get(producerId);
            if (followFlag) author.publisherFollow(publisher);
            else author.publisherUnFollow(publisher);
        } catch (NullPointerException nullPointer) {
            System.out.println("[Error] Invalid ID !!");
            System.out.println("[Error] Operation failed !!");
        } catch (Exception e) {
            System.out.println("[Error] Operation failed !!");
        }
    }

    private static void createUser(String type, Scanner scanner, HashMap<String, Integer> counterMap, ObjectFactory factory, HashMap<String, HashMap<Integer, Person>> map, BPOSytem system) {
        try {
            System.out.println("Your " + type + " ID is " + counterMap.get(type));
            System.out.print("Enter Your Name : ");
            String name = scanner.next();
            System.out.print("Enter Your Address: ");
            String address = scanner.next();
            Person person = (Person) factory.getObject(type, "" + counterMap.get(type), name, address);
            HashMap<Integer, Person> users = map.get(type);
            users.put(counterMap.get(type), person);
            map.put(type, users);

            if (type.equals("publisher")) ((Publisher) person).systemFollow(system);

            int count = counterMap.get(type);
            counterMap.put(type, count + 1);
        } catch (Exception e) {
            System.out.println("[Error] Not able to create User !!");
        }
    }

}
