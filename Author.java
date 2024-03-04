import java.util.ArrayList;

public class Author {

    private Profile profile;
    private ArrayList<Book> books;
    private static int nextId = 1;

    private String id;

    public Author(Profile profile) {
        this.id = String.valueOf(nextId++);
        this.profile = profile;
        this.books = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
    }
}