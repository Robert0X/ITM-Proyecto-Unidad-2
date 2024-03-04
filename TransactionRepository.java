import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionRepository {

    private static List<Transaction> transactions = new ArrayList<>();

    public static List<Transaction> getAllTransactions() {
        return transactions;
    }

    public static void logTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public static Transaction getTransactionById(String id) {
        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(id)) {
                return transaction;
            }
        }
        return null;
    }

    public static List<Transaction> getTransactionsByBook(Book book) {
        List<Transaction> bookTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getBook().equals(book)) {
                bookTransactions.add(transaction);
            }
        }
        return bookTransactions;
    }

    public static List<Transaction> getTransactionsByClient(Client client) {
        List<Transaction> clientTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getClient().equals(client)) {
                clientTransactions.add(transaction);
            }
        }
        return clientTransactions;
    }

    public static List<Transaction> getTransactionsInRange(Date start, Date end) {
        List<Transaction> rangeTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getDate().after(start) &&
                    transaction.getDate().before(end)) {
                rangeTransactions.add(transaction);
            }
        }
        return rangeTransactions;
    }

}