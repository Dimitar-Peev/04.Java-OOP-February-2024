package busyWaiters;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class RestaurantTests {
    @Test
    public void testCreate() {
        Restaurant restaurant = new Restaurant("test_restaurant", 10);
        assertEquals("test_restaurant", restaurant.getName());
        assertEquals(10, restaurant.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateInvalidCapacity() {
        new Restaurant("test_restaurant", -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd_NoCapacity() {
        Restaurant restaurant = new Restaurant("test_restaurant", 0);
        FullTimeWaiter waiter = new FullTimeWaiter("Simona",100);
        restaurant.addFullTimeWaiter(waiter);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateWithNameEmpty() {
        new Restaurant("  ", 10);
    }

    @Test
    public void restRemove() {
        Restaurant restaurant = new Restaurant("test_restaurant", 10);
        FullTimeWaiter waiter = new FullTimeWaiter("Simona",100);
        restaurant.addFullTimeWaiter(waiter);
        assertEquals(1, restaurant.getCount());
        restaurant.removeFullTimeWaiter("Simona");
        assertEquals(0, restaurant.getCount());
    }

    @Test
    public void testGetSuccessfully() {
        Restaurant restaurant = new Restaurant("test_restaurant", 10);
        FullTimeWaiter waiter = new FullTimeWaiter("Anna",100);
        restaurant.addFullTimeWaiter(waiter);
        Collection<FullTimeWaiter> waiters = restaurant.getWaiters();
        Assert.assertEquals(1, restaurant.getCount());
        Assert.assertEquals(1, waiters.size());

    }

    @Test(expected = IllegalArgumentException.class)
    public void addDuplicate() {
        Restaurant restaurant = new Restaurant("test_restaurant", 10);
        FullTimeWaiter waiter = new FullTimeWaiter("Simona",100);
        restaurant.addFullTimeWaiter(waiter);
        restaurant.addFullTimeWaiter(waiter);
    }

    @Test
    public void testGetEfficiency() {
        Restaurant restaurant = new Restaurant("test_restaurant", 10);
        FullTimeWaiter waiter = new FullTimeWaiter("Simona",100);
        restaurant.addFullTimeWaiter(waiter);
        assertEquals(1, restaurant.getCount());
        assertEquals(100, waiter.getEfficiency());
    }
}