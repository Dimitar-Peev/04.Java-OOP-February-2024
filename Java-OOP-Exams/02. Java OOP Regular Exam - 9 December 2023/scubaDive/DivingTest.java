package scubaDive;

import org.junit.Test;

import static org.junit.Assert.*;

public class DivingTest {

    @Test
    public void testRemoveExisting() {
        Diving diving = new Diving("test", 10);
        DeepWaterDiver peter = new DeepWaterDiver("Peter",99);
        diving.addDeepWaterDiver(peter);
        assertEquals(1, diving.getCount());
        diving.removeDeepWaterDiver("Peter");
        assertEquals(0, diving.getCount());
    }

    @Test
    public void testCreateSuccessfull() {
        Diving diving = new Diving("test", 10);
        assertEquals("test", diving.getName());
        assertEquals(10, diving.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCapacity() {
        new Diving("test", -1);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateInvalidName() {
        new Diving(null, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNoCapacity() {
        Diving diving = new Diving("test", 0);
        DeepWaterDiver peter = new DeepWaterDiver("Peter",99);
        diving.addDeepWaterDiver(peter);
    }


    @Test
    public void testCreateGetStrength() {
        DeepWaterDiver peter = new DeepWaterDiver("Peter",99);
        assertEquals("Peter", peter.getName());
        assertEquals(99, peter.getOxygen(),0);
    }
}