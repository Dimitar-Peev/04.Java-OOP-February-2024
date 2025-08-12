package unitTesting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class RaceEntryTest {
    private RaceEntry raceEntry;
    private UnitMotorcycle unitMotorcycle;
    private UnitRider rider;

    @Before
    public void setUp() {
        raceEntry = new RaceEntry();
        unitMotorcycle = new UnitMotorcycle("Honda", 150, 1000);
        rider = new UnitRider("Dimitar", unitMotorcycle);
    }

    @Test(expected = NullPointerException.class)
    public void testAddRiderNullShouldThrowException() {
        raceEntry.addRider(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddRiderExistingShouldThrowException() {
        raceEntry.addRider(rider);
        raceEntry.addRider(rider);
    }

    @Test
    public void testAddRiderShouldAddRider() {
        String expected = "Rider Dimitar added in race.";
        String actual = raceEntry.addRider(rider);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetMotorcyclesShouldReturnCorrectCount() {
        int expected = 1;
        raceEntry.addRider(rider);
        int actual = raceEntry.getRiders().size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetMotorcyclesShouldReturnCorrectModel() {
        Assert.assertEquals("Honda", rider.getMotorcycle().getModel());
    }

    @Test
    public void testGetMotorcyclesShouldReturnCorrectCubicCentimeters() {
        Assert.assertEquals(1000, rider.getMotorcycle().getCubicCentimeters(), 0.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateAverageHorsePowerWithLessThanTwoParticipantsShouldThrowException() {
        raceEntry.calculateAverageHorsePower();
    }

    @Test
    public void testCalculateAverageHorsePowerWithTwoParticipants() {
        UnitMotorcycle unitMotorcycle = new UnitMotorcycle("Yamaha", 100, 800);
        UnitRider pesho = new UnitRider("Pesho", unitMotorcycle);
        raceEntry.addRider(rider);
        raceEntry.addRider(pesho);
        double averageHorsePower = raceEntry.calculateAverageHorsePower();
        Assert.assertEquals(125, averageHorsePower, 0.00);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetRidersShouldReturnUnmodifiableCollection() {
        Collection<UnitRider> riders = raceEntry.getRiders();
        riders.add(rider);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetRidersClearShouldThrowException() {
        Collection<UnitRider> riders = raceEntry.getRiders();
        riders.clear();
    }
}
