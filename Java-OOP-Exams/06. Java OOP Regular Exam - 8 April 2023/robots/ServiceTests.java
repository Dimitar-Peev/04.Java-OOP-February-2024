package robots;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTests {

    @Test(expected = NullPointerException.class)
    public void testSetNameNull() {
        Service service = new Service(null, 10);
    }

    @Test
    public void testGetCapacity() {
        Service service = new Service("service_test", 10);
        assertEquals(10, service.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeCapacity() {
        Service service = new Service("Pesho", -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFullCapacity() {
        Service service = new Service("Pesho", 0);
        Robot robot = new Robot("Dimitar");
        service.add(robot);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveMissing() {
        Service service = new Service("Pesho", 1);
        service.remove("Dimitar");
    }

    @Test
    public void testRobotGetName() {
        Service service = new Service("Pesho", 1);
        Robot robot = new Robot("Dimitar");
        assertEquals("Dimitar", robot.getName());
    }

    @Test
    public void testServiceGetCount() {
        Service service = new Service("Pesho", 1);
        Robot robot = new Robot("Dimitar");
        service.add(robot);
        assertEquals(1, service.getCount());
    }

    @Test
    public void testServiceGetName() {
        Service service = new Service("Pesho", 1);
        assertEquals("Pesho", service.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForSale() {
        Service service = new Service("Pesho", 1);
        service.forSale("Dimitar");
    }

    @Test
    public void test_SellFish_ShouldSetFishAsSold() {
        Service service = new Service("Pesho", 1);
        Robot robot = new Robot("Dimitar");
        service.add(robot);
        service.forSale("Dimitar");
        assertFalse(robot.isReadyForSale());
    }

}
