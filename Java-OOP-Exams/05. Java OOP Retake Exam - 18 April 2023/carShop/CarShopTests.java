package carShop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CarShopTests {

    private CarShop carShop;
    private Car car;

    @Before
    public void setUp() {
        carShop = new CarShop();
        car = new Car("Skoda", 198, 599.45);
    }

    @Test
    public void add() {
        carShop.add(car);
        assertEquals(1, carShop.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void addNull() {
        Car car = null;
        carShop.add(car);
    }


    @Test
    public void getCars() {
        List<Car> cars = carShop.getCars();
        carShop.add(car);
        Assert.assertEquals(1, cars.size());
    }

    @Test
    public void remove() {
        Car car = new Car("Audi", 200, 20000.15);
        Car car2 = new Car("HONDA", 150, 5000);
        carShop.add(car);
        carShop.add(car2);

        Assert.assertEquals(2, carShop.getCount());
        Assert.assertEquals("HONDA", carShop.getCars().get(1).getModel());

        Assert.assertTrue(carShop.remove(car));
        Assert.assertEquals(1, carShop.getCount());
    }

    @Test
    public void findAllCarsWithMaxHorsePower() {
        Car ford = new Car("Focus", 450, 45000);
        Car vw = new Car("Golf", 150, 15000);
        Car audi = new Car("A4", 150, 25000);

        carShop.add(ford);
        carShop.add(vw);
        carShop.add(audi);

        List<Car> expectedCars = List.of(ford);
        List<Car> actualCars = carShop.findAllCarsWithMaxHorsePower(150);

        Assert.assertEquals(expectedCars, actualCars);
    }

    @Test
    public void getTheMostLuxuryCar() {
        Car ford = new Car("Focus", 450, 45000);
        Car vw = new Car("Golf", 150, 15000);
        Car audi = new Car("A4", 150, 25000);

        carShop.add(ford);
        carShop.add(vw);
        carShop.add(audi);

        Car theMostLuxuryCar = carShop.getTheMostLuxuryCar();
        Assert.assertEquals(ford, theMostLuxuryCar);
    }

    @Test
    public void findAllCarByModel() {
        Car ford = new Car("Focus", 450, 45000);
        Car renault = new Car("Clio", 150, 15000);
        Car audi = new Car("A4", 150, 25000);

        carShop.add(ford);
        carShop.add(renault);
        carShop.add(audi);

        List<Car> expectedCars = List.of(ford);
        List<Car> actualCars = carShop.findAllCarByModel("Focus");

        Assert.assertEquals(expectedCars, actualCars);
    }
}