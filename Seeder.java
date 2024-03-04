import java.util.Date;

public class Seeder {

    public static void initialize() {

        // Crear autores
        Author author1 = new Author(new Profile("Gabriel", "García Márquez", new Date()));
        Author author2 = new Author(new Profile("Isabel", "Allende", new Date()));

        AuthorRepository.createAuthor(author1);
        AuthorRepository.createAuthor(author2);

        // Crear libros
        Book book1 = new Book("9780007491426", "Cien años de soledad", author1, new Date());
        Book book2 = new Book("9780571267277", "La casa de los espíritus", author2, new Date());

        BookRepository.createBook(book1);
        BookRepository.createBook(book2);

        // Asignar libros a autores
        author1.getBooks().add(book1);
        author2.getBooks().add(book2);

        // Crear algunos clientes
        Client client1 = new Client(new Profile("Juan", "Soto", new Date()));
        Client client2 = new Client(new Profile("Maria", "Perez", new Date()));

        ClientRepository.createClient(client1);
        ClientRepository.createClient(client2);

        // Generar prestamos iniciales
        Transaction transaction1 = new Transaction(TransactionType.BORROW, client1, book1);
        TransactionRepository.logTransaction(transaction1);

        Transaction transaction2 = new Transaction(TransactionType.BORROW, client2, book2);
        TransactionRepository.logTransaction(transaction2);

    }

}