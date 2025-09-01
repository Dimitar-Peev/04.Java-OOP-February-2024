package carTrip;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarTest {
    private static final String DEFAULT_MODEL = "Charger";
    private static final int DEFAULT_CAPACITY = 70;
    private static final int DEFAULT_FUEL_AMOUNT = 60;
    private static final int DEFAULT_FUEL_CONSUMPTION = 10;
    private Car car;

    @Before
    public void setUp() {
        this.car = new Car(DEFAULT_MODEL, DEFAULT_CAPACITY, DEFAULT_FUEL_AMOUNT, DEFAULT_FUEL_CONSUMPTION);
    }

    @Test
    public void getModelNameShouldReturnCorrect() {
        assertEquals(DEFAULT_MODEL, this.car.getModel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetModelThrowsExceptionWhenModelIsNull() {
        this.car.setModel(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetModelThrowsExceptionWhenModelIsEmpty() {
        this.car.setModel("");
    }

    @Test
    public void testSetModelWhenModelIsValid() {
        final String newModel = "Challenger";
        this.car.setModel(newModel);
        assertEquals(newModel, this.car.getModel());
    }

    @Test
    public void getTankCapacityShouldReturnCorrect() {
        assertEquals(DEFAULT_CAPACITY, this.car.getTankCapacity(), 0.00);
    }

    @Test
    public void setTankCapacityShouldReturnCorrect() {
        double newCapacity = 100;
        this.car.setTankCapacity(newCapacity);
        assertEquals(newCapacity, this.car.getTankCapacity(), 0.00);
    }

    @Test
    public void getFuelAmountShouldReturnCorrect() {
        assertEquals(DEFAULT_FUEL_AMOUNT, this.car.getFuelAmount(), 0.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setFuelAmountThrowsExceptionWhenFuelAmountIsGreaterThanTankCapacity() {
        this.car.setFuelAmount(DEFAULT_CAPACITY + 1);
    }

    @Test
    public void getFuelConsumptionShouldReturnCorrect() {
        assertEquals(DEFAULT_FUEL_CONSUMPTION, this.car.getFuelConsumptionPerKm(), 0.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setFuelAmountThrowsExceptionWhenIsNegative() {
        this.car.setFuelConsumptionPerKm(-1);
    }

    @Test
    public void driveShouldReturnCorrectMessageWhenDrive() {
        double distance = 6.00;
        String expected = "Have a nice trip";
        assertEquals(expected, this.car.drive(distance));
    }

    @Test(expected = IllegalStateException.class)
    public void driveThrowsExceptionWhenNotEnoughFuel() {
        double distance = 100.00;
        this.car.drive(distance);
    }

    @Test
    public void driveShouldUseFuel() {
        double distance = 1.00;
        double expected = DEFAULT_FUEL_AMOUNT - DEFAULT_FUEL_CONSUMPTION;
        this.car.drive(distance);
        assertEquals(expected, this.car.getFuelAmount(), 0.00);
    }

    @Test
    public void refuelShouldSetCorrectAmountOfFuel() {
        double amount = 10.00;
        double expected = amount + this.car.getFuelAmount();
        this.car.refuel(amount);
        assertEquals(expected, this.car.getFuelAmount(), 0.00);
    }

    @Test(expected = IllegalStateException.class)
    public void refuelThrowsExceptionWhenFuelAmountIsGreaterTankCapacity() {
        double amount = this.car.getTankCapacity() + 1;
        this.car.refuel(amount);
    }
}