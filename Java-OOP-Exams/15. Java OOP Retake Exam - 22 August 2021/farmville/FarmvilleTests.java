package farmville;

import org.junit.*;

import static org.junit.Assert.*;

public class FarmvilleTests {
    private static final String NAME = "SoftUni";
    private static final int CAPACITY = 15;
    private static final String ANIMAL_TYPE = "Lion";
    private static final int ENERGY = 100;

    private Farm farm;
    private Animal animal;

    @Before
    public void setUp() {
        this.farm = new Farm(NAME, CAPACITY);
        this.animal = new Animal(ANIMAL_TYPE, ENERGY);
    }

    @Test
    public void testShouldCreateFarmSuccess() {
        assertEquals(NAME, farm.getName());
        assertEquals(CAPACITY, farm.getCapacity());
        assertEquals(0, farm.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testNullName() {
        new Farm(null, CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_negativeCapacity() {
        new Farm(NAME, -1);
    }

    @Test
    public void testAddShouldAddSuccess() {
        farm.add(animal);
        assertEquals(1, farm.getCount());
        animal = new Animal("chicken", 43);
        farm.add(animal);
        assertEquals(2, farm.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowBecauseNoCapacity() {
        farm = new Farm(NAME, 1);
        farm.add(new Animal("Cat", 58));
        farm.add(new Animal("Dog", 99));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowBecauseDuplicateAnimal() {
        farm.add(animal);
        farm.add(animal);
    }

    @Test
    public void testRemove() {
        farm.add(animal);
        assertTrue(farm.remove(ANIMAL_TYPE));
        assertEquals(0, farm.getCount());
    }
}
