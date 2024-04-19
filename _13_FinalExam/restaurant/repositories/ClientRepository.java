package restaurant.repositories;

import restaurant.models.client.Client;

import java.util.*;

public class ClientRepository implements Repository<Client> {
    private Map<String, Client> clients;

    public ClientRepository() {
        this.clients = new LinkedHashMap<>();
    }

    @Override
    public Collection<Client> getCollection() {
        return Collections.unmodifiableCollection(this.clients.values());
    }

    @Override
    public void add(Client client) {
        this.clients.putIfAbsent(client.getName(), client);
    }

    @Override
    public boolean remove(Client client) {
        return this.clients.remove(client.getName()) != null;
    }

    @Override
    public Client byName(String name) {
        return this.clients.get(name);
    }
}
