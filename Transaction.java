import java.util.Date;
import java.util.Random;

public class Transaction {

    private String id;
    private TransactionType type;
    private Client client;
    private Book book;
    private Date date;

    public Transaction(TransactionType type, Client client, Book book) {
        this.id = generateId();
        this.type = type;
        this.client = client;
        this.book = book;
        this.date = new Date();
    }

    public Transaction() {
        // Constructor sin argumentos, para registerTransaction() en
        // LibraryController.java
    }

    public String getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public Client getClient() {
        return client;
    }

    public Book getBook() {
        return book;
    }

    public Date getDate() {
        return date;
    }

    private String generateId() {
        Random random = new Random();
        return String.valueOf(random.nextLong());
    }
}

enum TransactionType {
    BORROW,
    RETURN
}