import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LibraryController {

    private static Scanner scanner = new Scanner(System.in);

    public static void showMainMenu() {

        while (true) {

            System.out.println("1. Manage books");
            System.out.println("2. Manage clients");
            System.out.println("3. Manage authors");
            System.out.println("4. Register transactions");
            System.out.println("5. Generate reports");
            System.out.println("0. Exit");

            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    showBookMenu();
                    break;
                case 2:
                    showClientMenu();
                    break;
                case 3:
                    showAuthorMenu();
                    break;
                case 4:
                    showTransactionMenu();
                    break;
                case 5:
                    showReportMenu();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }

        }
    }

    public static void showBookMenu() {

        System.out.println("1. Register new book");
        System.out.println("2. Update book");
        System.out.println("3. Delete book");
        System.out.println("4. List available books");
        System.out.println("5. List borrowed books");
        System.out.println("0. Back to main menu");

        int option = Integer.parseInt(scanner.nextLine());

        switch (option) {
            case 1:
                createBook();
                break;
            case 2:
                updateBook();
                break;
            case 3:
                deleteBook();
                break;
            case 4:
                listAvailableBooks();
                break;
            case 5:
                listBorrowedBooks();
                break;
            case 0:
                showMainMenu();
                break;
            default:
                System.out.println("Invalid option");
        }

    }

    private static void createBook() {
        System.out.println("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        System.out.println("Enter author ID: ");
        String authorId = scanner.nextLine();

        Author author = AuthorRepository.getAuthorById(authorId);

        if (author == null) {
            System.out.println("Author not found");
            return;
        }
        Book newBook = new Book(isbn, title, author, new Date());
        BookRepository.createBook(newBook);
        System.out.println("Book created successfully!");
    }

    private static void updateBook() {
        System.out.print("Enter book ISBN to update: ");
        String isbn = scanner.nextLine();
        Book existingBook = BookRepository.getBookByIsbn(isbn);
        if (existingBook == null) {
            System.out.println("Book not found");
            return;
        }
        System.out.print("Enter updated title: ");
        String updatedTitle = scanner.nextLine();
        existingBook.setTitle(updatedTitle);
        BookRepository.updateBook(isbn, existingBook);
        System.out.println("Book updated successfully!");
    }

    private static void deleteBook() {
        System.out.print("Enter book ISBN to delete: ");
        String isbn = scanner.nextLine();
        Book book = BookRepository.getBookByIsbn(isbn);
        if (book == null) {
            System.out.println("Book not found");
            return;
        }
        if (!book.isAvailable()) {
            System.out.println("Cannot delete book because it is borrowed");
            return;
        }
        BookRepository.deleteBook(book);
        System.out.println("Book deleted successfully!");
    }

    private static void listAvailableBooks() {
        List<Book> availableBooks = BookRepository.getAvailableBooks();
        for (Book book : availableBooks) {
            System.out.println("ISBN: " + book.getIsbn() + ", Title: " + book.getTitle() + ", Author: "
                    + book.getAuthor().getProfile().getName());
        }
    }

    private static void listBorrowedBooks() {
        List<Book> borrowedBooks = BookRepository.getBorrowedBooks();
        for (Book book : borrowedBooks) {
            System.out.println("ISBN: " + book.getIsbn() + ", Title: " + book.getTitle() + ", Author: "
                    + book.getAuthor().getProfile().getName());
        }
    }

    public static void showClientMenu() {

        System.out.println("1. Register new client");
        System.out.println("2. Update client data");
        System.out.println("3. Delete client");
        System.out.println("4. List all clients");
        System.out.println("5. List clients with borrowed books");
        System.out.println("0. Back to main menu");

        int option = Integer.parseInt(scanner.nextLine());

        switch (option) {
            case 1:
                createClient();
                break;
            case 2:
                updateClient();
                break;
            case 3:
                deleteClient();
                break;
            case 4:
                listClients();
                break;
            case 5:
                listClientsWithBooks();
                break;
            case 0:
                showMainMenu();
                break;
            default:
                System.out.println("Invalid option");
        }

    }

    public static void showAuthorMenu() {
        System.out.println("1. Register new author");
        System.out.println("2. Update author data");
        System.out.println("3. Delete author");
        System.out.println("4. List all authors");
        System.out.println("5. List authors with books");
        System.out.println("0. Back to main menu");
        int option = Integer.parseInt(scanner.nextLine());
        switch (option) {
            case 1:
                createAuthor();
                break;
            case 2:
                updateAuthor();
                break;
            case 3:
                deleteAuthor();
                break;
            case 4:
                listAuthors();
                break;
            case 5:
                listAuthorsWithBooks();
                break;
            case 0:
                showMainMenu();
                break;
            default:
                System.out.println("Invalid option");
        }

    }

    private static void createAuthor() {
        System.out.println("Enter author first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter author last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter birth date (dd/mm/yyyy): ");
        String birthStr = scanner.nextLine();
        Date birthDate = parseStringToDate(birthStr);
        Profile profile = new Profile(firstName, lastName, birthDate);
        Author author = new Author(profile);
        AuthorRepository.createAuthor(author);
        System.out.println("Author created successfully!");
    }

    private static void updateAuthor() {
        System.out.print("Enter author ID to update: ");
        String id = scanner.nextLine();
        Author existingAuthor = AuthorRepository.getAuthorById(id);
        if (existingAuthor == null) {
            System.out.println("Author not found");
            return;
        }
        System.out.print("Enter updated first name: ");
        String updatedFirstName = scanner.nextLine();
        System.out.print("Enter updated last name: ");
        String updatedLastName = scanner.nextLine();
        System.out.print("Enter updated birth date (dd/mm/yyyy): ");
        String updatedBirthStr = scanner.nextLine();
        Date updatedBirthDate = parseStringToDate(updatedBirthStr);
        Profile updatedProfile = new Profile(updatedFirstName, updatedLastName, updatedBirthDate);
        existingAuthor.setProfile(updatedProfile);
        AuthorRepository.updateAuthor(id, existingAuthor);
        System.out.println("Author updated successfully!");
    }

    private static void deleteAuthor() {
        System.out.print("Enter author ID to delete: ");
        String id = scanner.nextLine();
        Author author = AuthorRepository.getAuthorById(id);
        if (author == null) {
            System.out.println("Author not found");
            return;
        }
        if (!author.getBooks().isEmpty()) {
            System.out.println("Cannot delete author because they have assigned books!");
            return;
        }
        AuthorRepository.deleteAuthor(author);
    }

    private static void listAuthors() {
        List<Author> authors = AuthorRepository.getAllAuthors();
        for (Author author : authors) {
            System.out.println(author.getProfile().getName());
        }
    }

    private static void listAuthorsWithBooks() {
        List<Author> authorsWithBooks = AuthorRepository.getAuthorsWithBooks();
        for (Author author : authorsWithBooks) {
            System.out.println("Author: " + author.getProfile().getName());
            System.out.println("Books:");
            for (Book book : author.getBooks()) {
                System.out.println("- " + book.getTitle());
            }
        }
    }

    public static void showTransactionMenu() {
        System.out.println("1. Register new transaction");
        System.out.println("2. Update transaction");
        System.out.println("3. Delete transaction");
        System.out.println("4. View transactions");
        System.out.println("0. Back to main menu");
        int option = Integer.parseInt(scanner.nextLine());
        switch (option) {
            case 1:
                registerTransaction();
                break;
            case 2:
                updateTransaction();
                break;
            case 3:
                deleteTransaction();
                break;
            case 4:
                showTransactions(null);
            case 0:
                showMainMenu();
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    public static void registerTransaction() {
        // Aquí debes crear un objeto Transaction y pasarlo como argumento a
        // logTransaction
        Transaction newTransaction = new Transaction(); // Asegúrate de inicializar correctamente la transacción
        TransactionRepository.logTransaction(newTransaction);
    }

    private static void updateTransaction() {
        System.out.print("Enter transaction ID to update: ");
        String id = scanner.nextLine();
        Transaction existingTransaction = TransactionRepository.getTransactionById(id);
        if (existingTransaction == null) {
            System.out.println("Transaction not found");
            return;
        }
        // Aquí iría el código para actualizar la transacción
    }

    private static void deleteTransaction() {
        System.out.print("Enter transaction ID to delete: ");
        String id = scanner.nextLine();
        Transaction transaction = TransactionRepository.getTransactionById(id);
        if (transaction == null) {
            System.out.println("Transaction not found");
            return;
        }
        // Código para eliminar la transacción
    }

    private static void showTransactions(List<Transaction> transactions) {
        System.out.println("Select an option:");
        System.out.println("1. Lend book");
        System.out.println("2. Return book");
        System.out.println("0. Back to main menu");
        int option = Integer.parseInt(scanner.nextLine());
        switch (option) {
            case 1:
                lendBook();
                break;
            case 2:
                returnBook();
                break;
            case 0:
                showMainMenu();
                break;
        }
    }

    private static void lendBook() {
        System.out.print("Enter client ID: ");
        String clientId = scanner.nextLine();
        Client client = ClientRepository.getClient(clientId);
        if (client == null) {
            System.out.println("Client not found");
            return;
        }
        System.out.print("Enter book ISBN: ");
        String bookIsbn = scanner.nextLine();
        Book book = BookRepository.getBookByIsbn(bookIsbn);
        if (book == null) {
            System.out.println("Book not found");
            return;
        }
        if (client.getBorrowedBooks().size() < 3 && book.isAvailable()) {
            client.addBorrowedBook(book);
            book.setAvailable(false);
            TransactionRepository.logTransaction(new Transaction(TransactionType.BORROW, client, book));
            System.out.println("Book lent successfully!");
        } else {
            System.out.println("Client has already borrowed 3 books or book is not available.");
        }
    }

    private static void returnBook() {
        System.out.print("Enter client ID: ");
        String clientId = scanner.nextLine();
        Client client = ClientRepository.getClient(clientId);
        if (client == null) {
            System.out.println("Client not found");
            return;
        }
        System.out.print("Enter book ISBN: ");
        String bookIsbn = scanner.nextLine();
        Book book = BookRepository.getBookByIsbn(bookIsbn);
        if (book == null) {
            System.out.println("Book not found");
            return;
        }
        if (client.getBorrowedBooks().contains(book)) {
            client.removeBorrowedBook(book);
            book.setAvailable(true);
            TransactionRepository.logTransaction(new Transaction(TransactionType.RETURN, client, book));
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Client does not have this book.");
        }
    }

    private static void createClient() {

        System.out.println("Enter client first name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter client last name: ");
        String lastName = scanner.nextLine();

        System.out.println("Enter birth date (dd/mm/yyyy): ");
        String birthStr = scanner.nextLine();
        Date birthDate = parseStringToDate(birthStr);

        Profile profile = new Profile(firstName, lastName, birthDate);

        Client client = new Client(profile);

        ClientRepository.createClient(client);

        System.out.println("Client created successfully!");
    }

    private static Date parseStringToDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Parsear la cadena de texto a un objeto Date
            return formatter.parse(dateString);
        } catch (ParseException e) {
            // Manejar cualquier error de formato de fecha
            e.printStackTrace(); // o cualquier otra forma de manejo de errores
            return null;
        }
    }

    private static void updateClient() {

        System.out.print("Enter client ID to update: ");
        String id = scanner.nextLine();

        Client existingClient = ClientRepository.getClient(id);

        if (existingClient == null) {
            System.out.println("Client not found");
            return;
        }

        System.out.print("Enter updated name: ");
        String updatedName = scanner.nextLine();

        System.out.print("Enter updated last name: ");
        String updatedLastName = scanner.nextLine();

        System.out.print("Enter updated birth date (dd/mm/yyyy): ");
        String updatedBirthStr = scanner.nextLine();
        Date updatedBirthDate = parseStringToDate(updatedBirthStr);

        Profile updatedProfile = new Profile(updatedName, updatedLastName, updatedBirthDate);

        existingClient.setProfile(updatedProfile);

        ClientRepository.updateClient(id, existingClient);

        System.out.println("Client updated successfully!");

    }

    private static void deleteClient() {

        System.out.print("Enter client ID to delete: ");
        String id = scanner.nextLine();

        Client client = ClientRepository.getClient(id);

        if (client == null) {
            System.out.println("Client not found");
            return;
        }

        if (!client.getBorrowedBooks().isEmpty()) {
            System.out.println("Cannot delete client because they have borrowed books");
            return;
        }

        ClientRepository.deleteClient(client);

        System.out.println("Client deleted successfully!");
    }

    private static void listClients() {
        List<Client> clients = ClientRepository.getAllClients();
        for (Client client : clients) {
            System.out.println("ID: " + client.getId() + ", Nombre: " + client.getProfile().getName() + ", Apellido: "
                    + client.getProfile().getLastName() + ", Fecha de nacimiento: "
                    + client.getProfile().getBirthdate());
        }
    }

    private static void listClientsWithBooks() {
        List<Client> clientsWithBooks = ClientRepository.getClientsWithBooks();
        for (Client client : clientsWithBooks) {
            System.out.println("Client: " + client.getProfile().getName());
            System.out.println("Borrowed Books:");
            for (Book book : client.getBorrowedBooks()) {
                System.out.println("- " + book.getTitle());
            }
        }
    }
    // Otros métodos menú

    public static void showReportMenu() {
        System.out.println("1. Filter by date range");
        System.out.println("2. Filter by client");
        System.out.println("3. Filter by book");
        System.out.println("0. Back to main menu");

        int option = Integer.parseInt(scanner.nextLine());

        switch (option) {
            case 1:
                showTransactionsByDate();
                break;
            case 2:
                showTransactionsByClient();
                break;
            case 3:
                showTransactionsByBook();
                break;
            case 0:
                showMainMenu();
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private static void showTransactionsByDate() {

        System.out.println("Enter start date (dd/mm/yyyy): ");
        String startDateStr = scanner.nextLine();
        Date startDate = parseStringToDate(startDateStr);

        System.out.println("Enter end date (dd/mm/yyyy):");
        String endDateStr = scanner.nextLine();
        Date endDate = parseStringToDate(endDateStr);

        List<Transaction> transactions = TransactionRepository
                .getTransactionsInRange(startDate, endDate);

        showTransactions(transactions);
    }

    private static void showTransactionsByClient() {

        System.out.print("Enter client ID: ");
        String clientId = scanner.nextLine();

        Client client = ClientRepository.getClient(clientId);

        List<Transaction> transactions = TransactionRepository
                .getTransactionsByClient(client);

        showTransactions(transactions);
    }

    private static void showTransactionsByBook() {

        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        Book book = BookRepository.getBookByIsbn(isbn);

        List<Transaction> transactions = TransactionRepository
                .getTransactionsByBook(book);

        showTransactions(transactions);
    }

    /*
     * 
     * private static void showTransactions(List<Transaction> transactions) {
     * 
     * // Iterar transacciones y mostrar datos relevantes
     * 
     * }
     */
}