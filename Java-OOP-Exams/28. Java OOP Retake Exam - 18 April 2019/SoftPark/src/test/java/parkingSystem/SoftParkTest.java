package parkingSystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SoftParkTest {
    private SoftPark softPark;
    private Car car;

    @Before
    public void setUp() {
        this.softPark = new SoftPark();
        this.car = new Car("Dodge", "CT 1234 ME");
    }

    @Test
    public void testConstructor() {
        Assert.assertNotNull(softPark);
    }

    @Test
    public void testConstructorSize() {
        int actualSize = softPark.getParking().size();
        Assert.assertEquals(12, actualSize);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetParkingShouldReturnUnmodifiableMap() {
        softPark.getParking().clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void parkCarShouldThrowExceptionIfParkingSpotNotExist() {
        softPark.parkCar("Home", car);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parkCarShouldThrowExceptionIfParkingSpotIsTaken() {
        softPark.parkCar("A1", car);
        softPark.parkCar("A1", car);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parkCarShouldThrowExceptionIfCarAlreadyParked() {
        Car testCar = new Car("Test", "Test");

        final String expected = "Car is already parked!";
        String actual = "";

        try {
            this.softPark.parkCar("A1", car);
            this.softPark.parkCar("B1", testCar);
        } catch (IllegalStateException ex) {
            actual = ex.getMessage();
        }

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parkCarShouldReturnCorrectMessageIfCarParkSuccessful() {
        String expected = "Car:CT 1234 ME parked successfully!";
        String actual = softPark.parkCar("A1", car);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parkCarWorkCorrectly() {
        softPark.parkCar("A1", car);

        Assert.assertEquals(car, softPark.getParking().get("A1"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void removeCarShouldThrowExceptionIfParkingSpotNotExist() {
        softPark.removeCar("Home", car);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeCarShouldThrowExceptionIfCarForThatSpotDoesntExists() {
        softPark.parkCar("A1", car);

        Car car1 = new Car("SecondTest", "SecondTest");
        softPark.removeCar("A1", car1);

        Assert.assertNull(softPark.getParking().get("A1"));
    }

    @Test
    public void removeCarShouldRemoveCorrectCar() {
        softPark.parkCar("A1", car);
        softPark.removeCar("A1", car);

        Assert.assertNull(softPark.getParking().get("A1"));
    }

    @Test
    public void removeCarShouldReturnCorrectMessageIfCarRemovedSuccessful() {
        softPark.parkCar("A1", car);

        String expected = "Remove car:CT 1234 ME successfully!";
        String actual = softPark.removeCar("A1", car);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMakeShouldReturnCorrectValue() {
        Assert.assertEquals("Dodge", car.getMake());
    }
}