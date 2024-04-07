package stuntClimb;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClimbingTests {

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCapacity() {
        new Climbing("test", -1);
    }
    @Test(expected = NullPointerException.class)
    public void testCreateInvalidName() {
        new Climbing(null, 10);
    }

    @Test
    public void testCreateSuccessfull() {
        Climbing climbing = new Climbing("test", 10);
        assertEquals("test", climbing.getName());
        assertEquals(10, climbing.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNoCapacity() {
        Climbing climbing = new Climbing("test", 0);
        RockClimber peter = new RockClimber("Peter",99);
        climbing.addRockClimber(peter);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddDuplicate() {
        Climbing climbing = new Climbing("test", 0);
        RockClimber peter = new RockClimber("Peter",99);
        climbing.addRockClimber(peter);
        climbing.addRockClimber(peter);
        climbing.addRockClimber(peter);
    }
    @Test
    public void restRemoveExisting() {
        Climbing climbing = new Climbing("test", 10);
        RockClimber peter = new RockClimber("Peter",99);
        climbing.addRockClimber(peter);
        assertEquals(1, climbing.getCount());
        climbing.removeRockClimber("Peter");
        assertEquals(0, climbing.getCount());
    }
    @Test
    public void testRemoveNonExisting() {
        Climbing climbing = new Climbing("test", 10);
        climbing.removeRockClimber("Peter");
        assertEquals(0, climbing.getCount());
    }
    @Test
    public void testCreateGetStrength() {
        RockClimber peter = new RockClimber("Peter",99);
        assertEquals("Peter", peter.getName());
        assertEquals(99, peter.getStrength(),0);
    }

}
