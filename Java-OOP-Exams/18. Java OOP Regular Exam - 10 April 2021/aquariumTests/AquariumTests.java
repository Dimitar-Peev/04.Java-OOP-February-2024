package aquarium;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AquariumTests { // 6 теста

    @Before
    public void setUp() throws Exception {
    }

    @Test(expected = NullPointerException.class) // 33/100
    public void test_SetName_ShouldFail_when_nameIsNull() {
        new Aquarium(null, 10);
    }

    @Test(expected = NullPointerException.class) // 33/100
    public void test_SetName_ShouldFail_when_nameIsWhiteSpace() {
        new Aquarium("    ", 10);
    }

    @Test                                             // 33/100
    public void test_SetName_ShouldSetCorrectName() {
        Aquarium aquarium = new Aquarium("test", 10);
        assertEquals("test", aquarium.getName());
    }

    @Test(expected = IllegalArgumentException.class) // 50/100
    public void test_SetCapacity_ShouldFail_when_Negative() {
        new Aquarium("test", -1);
    }

    @Test                                            // 50/100
    public void test_SetCapacity_ShouldSetCorrectCapacity() {
        Aquarium aquarium = new Aquarium("test", 10);
        assertEquals(10, aquarium.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class) // 66/100
    public void test_AddFish_ShouldFail_whenMaxCapacityReached() {
        Aquarium aquarium = new Aquarium("test_name", 0);
        aquarium.add(new Fish("test_fish"));
    }

    @Test                                           // 66/100
    public void test_RemoveFish_ShouldFail_whenMaxCapacityReached() {
        Aquarium aquarium = new Aquarium("test_name", 1);
        aquarium.add(new Fish("test_fish"));
        aquarium.remove("test_fish");
        assertEquals(1, aquarium.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)  // 83/100
    public void test_RemoveFish_ShouldFail_whenIsEmpty() {
        Aquarium aquarium = new Aquarium("test", 0);
        aquarium.remove("test_fish");
    }
    @Test(expected = IllegalArgumentException.class)  // 83/100
    public void test_RemoveFish_ShouldFail_whenIsMissing() {
        Aquarium aquarium = new Aquarium("test", 1);
        aquarium.add(new Fish("Tom"));
        aquarium.remove("test_fish");
    }

    @Test
    public void test_AddFish_ShouldIncrease_FishCount() {
        Aquarium aquarium = new Aquarium("test_name", 10);
        aquarium.add(new Fish("test_fish"));
        assertEquals(1, aquarium.getCount());
    }
    @Test
    public void test_RemoveFish_whenIsAvailable_ShouldDecrease_FishCount() {
        Aquarium aquarium = new Aquarium("test_name", 10);
        aquarium.add(new Fish("test_fish"));
        aquarium.remove("test_fish");
        assertEquals(0, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)          // 83/100
    public void test_SellFish_ShouldFail_whenIsMissing() {
        Aquarium aquarium = new Aquarium("test_name", 1);
        aquarium.sellFish("test_fish");
    }

    @Test                                                   // 83/100
    public void test_SellFish_ShouldSetFishAsSold() {
        Aquarium aquarium = new Aquarium("test_name", 1);
        Fish fish = new Fish("test_fish");
        aquarium.add(fish);
        aquarium.sellFish("test_fish");
        assertFalse(fish.isAvailable());
    }

    @Test
    public void getName() {
    }

    @Test
    public void getCapacity() {
    }

    @Test
    public void getCount() {
    }

    @Test
    public void add() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void sellFish() {
    }

    @Test
    public void report() {
    }
}