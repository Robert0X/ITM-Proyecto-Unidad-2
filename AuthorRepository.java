import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AuthorRepository {

    private static List<Author> authors = new ArrayList<>();

    public static void createAuthor(Author author) {
        authors.add(author);
    }

    public static Author getAuthorById(String id) {
        for (Author author : authors) {
            if (author.getId().equals(id)) {
                return author;
            }
        }
        return null;
    }

    public static List<Author> getAllAuthors() {
        return authors;
    }

    public static List<Author> getAuthorsWithBooks() {
        List<Author> authorsWithBooks = new ArrayList<>();
        for (Author author : authors) {
            if (!author.getBooks().isEmpty()) {
                authorsWithBooks.add(author);
            }
        }
        return authorsWithBooks;
    }

    public static void updateAuthor(String id, Author updatedAuthor) {

        Author author = getAuthorById(id);

        if (author != null) {
            authors.set(authors.indexOf(author), updatedAuthor);
        }

    }

    public static void deleteAuthor(Author author) {
        if (!author.getBooks().isEmpty()) {
            System.out.println("Author can't be deleted because they have assigned books!");
            return;
        }
        authors.remove(author);
    }

}