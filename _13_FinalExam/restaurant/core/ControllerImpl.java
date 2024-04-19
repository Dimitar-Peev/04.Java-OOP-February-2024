package restaurant.core;

import restaurant.models.client.Client;
import restaurant.models.client.ClientImpl;
import restaurant.models.waiter.FullTimeWaiter;
import restaurant.models.waiter.HalfTimeWaiter;
import restaurant.models.waiter.Waiter;
import restaurant.models.working.WorkingImpl;
import restaurant.repositories.ClientRepository;
import restaurant.repositories.WaiterRepository;

import java.util.Arrays;
import java.util.Collection;

import static restaurant.common.ConstantMessages.*;
import static restaurant.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private ClientRepository clientRepository;
    private WaiterRepository waiterRepository;
    private int count;

    public ControllerImpl() {
        this.clientRepository = new ClientRepository();
        this.waiterRepository = new WaiterRepository();
        this.count = 0;
    }

    @Override
    public String addWaiter(String type, String waiterName) {
        Waiter waiter = null;

        switch (type) {
            case "FullTimeWaiter":
                waiter = new FullTimeWaiter(waiterName);
                break;
            case "HalfTimeWaiter":
                waiter = new HalfTimeWaiter(waiterName);
                break;
            default:
                throw new IllegalArgumentException(WAITER_INVALID_TYPE);
        }

        waiterRepository.add(waiter);
        return String.format(WAITER_ADDED, type, waiterName);
    }

    @Override
    public String addClient(String clientName, String... orders) {

        Client client = new ClientImpl(clientName);

        client.getClientOrders().addAll(Arrays.asList(orders));

        this.clientRepository.add(client);

        return String.format(CLIENT_ADDED, clientName);
    }

    @Override
    public String removeWaiter(String waiterName) {
        Waiter waiter = this.waiterRepository.byName(waiterName);

        if (null == waiter) {
            throw new IllegalArgumentException(String.format(WAITER_DOES_NOT_EXIST, waiterName));
        }

        this.waiterRepository.remove(waiter);

        return String.format(WAITER_REMOVE, waiterName);

    }

    @Override
    public String removeClient(String clientName) {

        Client client = this.clientRepository.byName(clientName);

        if (null == client) {
            throw new IllegalArgumentException(String.format(CLIENT_DOES_NOT_EXIST, clientName));
        }

        this.clientRepository.remove(client);

        return String.format(CLIENT_REMOVE, clientName);
    }

    @Override
    public String startWorking(String clientName) {
        Collection<Waiter> waiters = this.waiterRepository.getCollection();

        if (waiters.isEmpty()) {
            throw new IllegalArgumentException(THERE_ARE_NO_WAITERS);
        }

        Client client = this.clientRepository.byName(clientName);
        WorkingImpl working = new WorkingImpl();
        working.takingOrders(client, waiters);
        this.count++;

        return String.format(ORDERS_SERVING, clientName);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(FINAL_CLIENTS_COUNT, count));
        sb.append(System.lineSeparator());
        sb.append(FINAL_WAITERS_STATISTICS);

        this.waiterRepository
                .getCollection()
                .forEach(waiter -> {

                    sb.append(System.lineSeparator());
                    sb.append(String.format(FINAL_WAITER_NAME, waiter.getName()));

                    sb.append(System.lineSeparator());
                    sb.append(String.format(FINAL_WAITER_EFFICIENCY, waiter.getEfficiency()));

                    sb.append(System.lineSeparator());

                    Collection<String> ordersList = waiter.takenOrders().getOrdersList();

                    String listOrders = ordersList.isEmpty() ?
                            "None" :
                            String.join(FINAL_WAITER_ORDERS_DELIMITER, ordersList);

                    sb.append(String.format(FINAL_WAITER_ORDERS, listOrders));
                });
        return sb.toString();
    }
}
