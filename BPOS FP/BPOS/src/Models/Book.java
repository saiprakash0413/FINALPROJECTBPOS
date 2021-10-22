package Models;

public class Book implements Cloneable {

    private String isbn;
    private String title;
    private int sellingPrice;
    private Person publisher;
    private Person author;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Person getPublisher() {
        return publisher;
    }

    public void setPublisher(Person publisher) {
        this.publisher = publisher;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public int getTotalAmount() {
        return sellingPrice;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
