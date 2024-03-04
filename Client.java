import java.util.ArrayList;

public class Client {
    private Profile profile;
    private ArrayList<Book> borrowedBooks;
    private String Id;
    private static int nextId = 1;

    public Client(Profile profile) {
        this.Id = "C" + nextId++; // Aquí usamos "C" como prefijo para indicar que es un ID de cliente
        this.profile = profile;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getId() {
        return Id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public boolean addBorrowedBook(Book book) {
        if (borrowedBooks.size() < 3 && book.isAvailable()) {
            borrowedBooks.add(book);
            book.setAvailable(false);
            return true;
        } else {
            return false;
        }
    }

    public void removeBorrowedBook(Book book) {
        borrowedBooks.remove(book);
        book.setAvailable(true);
    }

    public boolean returnBorrowedBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.setAvailable(true);
            return true;
        } else {
            return false;
        }
    }
}