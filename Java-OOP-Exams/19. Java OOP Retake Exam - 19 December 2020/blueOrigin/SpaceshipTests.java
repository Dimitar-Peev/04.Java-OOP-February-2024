package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceshipTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Spaceship
    private Spaceship spaceship;

    @Before
    public void setUp() {
        this.spaceship = new Spaceship("test_name", 5);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameNull() {
        new Spaceship(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameEmpty() {
        new Spaceship(" ", 10);
    }

    @Test
    public void count() {
        assertEquals(0, spaceship.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacity() {
        new Spaceship("Spaceship", -1);
    }

    @Test
    public void testGetAstronaut() {
        spaceship.add(new Astronaut("name_1", 100));
        spaceship.add(new Astronaut("name_2", 200));
        assertEquals(2, spaceship.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTheSameSecondTime() {
        spaceship.add(new Astronaut("name_1", 100));
        spaceship.add(new Astronaut("name_1", 100));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addMoreThanCapacity() {
        Spaceship spaceship = new Spaceship("Spaceship", 1);
        spaceship.add(new Astronaut("name_1", 100));
        spaceship.add(new Astronaut("name_2", 200));
    }
    @Test
    public void tryToRemoveInvalidAstronaut() {
        spaceship.add(new Astronaut("name_1", 100));
        assertFalse(this.spaceship.remove("other"));
    }

    @Test
    public void tryToRemoveValidAstronaut() {
        spaceship.add(new Astronaut("name_1", 100));
        assertTrue(spaceship.remove(new Astronaut("name_1", 100).getName()));
        assertEquals(0, spaceship.getCount());
    }
}
