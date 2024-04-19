package restaurant.models.working;

import restaurant.models.client.Client;
import restaurant.models.waiter.Waiter;

import java.util.ArrayDeque;
import java.util.Collection;

public class WorkingImpl implements Working {
    @Override
    public void takingOrders(Client client, Collection<Waiter> waiters) {

        ArrayDeque<String> clientsInRestaurant = new ArrayDeque<>(client.getClientOrders());
        for (Waiter waiter : waiters) {
            while (waiter.canWork() && !clientsInRestaurant.isEmpty()){
                String currentClient = clientsInRestaurant.poll();
                waiter.takenOrders().getOrdersList().add(currentClient);
                clientsInRestaurant.remove(currentClient);
                waiter.work();
            }
        }

    }
}

