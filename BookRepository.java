import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private static List<Book> books = new ArrayList<>();

    public static void createBook(Book book) {
        books.add(book);
    }

    public static void updateBook(String isbn, Book updatedBook) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                books.set(books.indexOf(book), updatedBook);
                return;
            }
        }
    }

    public static Book getBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public static List<Book> getAllBooks() {
        return books;
    }

    public static List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public static List<Book> getBorrowedBooks() {
        List<Book> borrowedBooks = new ArrayList<>();
        for (Book book : books) {
            if (!book.isAvailable()) {
                borrowedBooks.add(book);
            }
        }
        return borrowedBooks;
    }

    public static void deleteBook(Book book) {
        if (!book.isAvailable()) {
            System.out.println("Book can't be deleted because it is borrowed!");
            return;
        }
        books.remove(book);
    }
}