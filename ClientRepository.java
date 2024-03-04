import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientRepository {

    private static List<Client> clients = new ArrayList<>();

    public static void createClient(Client client) {
        clients.add(client);
    }

    public static Client getClientById(UUID id) {
        for (Client client : clients) {
            if (client.getProfile().getId().equals(id)) {
                return client;
            }
        }
        return null;
    }

    public static Client getClient(String id) {
        for (Client client : clients) {
            if (client.getId().equals(id)) {
                return client;
            }
        }
        return null;
    }

    public static List<Client> getAllClients() {
        return clients;
    }

    public static List<Client> getClientsWithBooks() {
        List<Client> clientsWithBooks = new ArrayList<>();
        for (Client client : clients) {
            if (!client.getBorrowedBooks().isEmpty()) {
                clientsWithBooks.add(client);
            }
        }
        return clientsWithBooks;
    }

    public static void updateClient(String id, Client updatedClient) {
        for (Client client : clients) {
            if (client.getProfile().getId().equals(id)) {
                clients.set(clients.indexOf(client), updatedClient);
                return;
            }
        }
    }

    public static void deleteClient(Client client) {
        if (!client.getBorrowedBooks().isEmpty()) {
            System.out.println("Client can't be deleted because they have borrowed books!");
            return;
        }
        clients.remove(client);
    }

}