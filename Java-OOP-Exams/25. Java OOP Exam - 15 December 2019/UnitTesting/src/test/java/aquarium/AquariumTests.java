package aquarium;

import org.junit.Assert;
import org.junit.Test;

public class AquariumTests {

    @Test
    public void aquariumConstructor_withValidParameters_shouldCreateObject() {
        Aquarium aquarium = new Aquarium("aqua", 10);

        String expectedName = "aqua";
        int expectedCapacity = 10;

        String actualName = aquarium.getName();
        int actualCapacity = aquarium.getCapacity();

        Assert.assertEquals(expectedName, actualName);
        Assert.assertEquals(expectedCapacity, actualCapacity);
    }

    @Test
    public void fishConstructor_withValidParameters_shouldCreateObject() {
        Fish fish = new Fish("fish");

        String expectedName = "fish";
        String actualName = fish.getName();

        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void validateAddFish_withValidParameters_shouldAddFish() {
        Aquarium aquarium = new Aquarium("aqua", 10);
        Fish fish = new Fish("fish");

        aquarium.add(fish);

        Assert.assertEquals(1, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateAddFish_withInvalidParameters_shouldNotAddFish() {
        Aquarium aquarium = new Aquarium("aqua", 1);
        Fish fish = new Fish("fish");
        Fish fish2 = new Fish("fish2");

        aquarium.add(fish);
        aquarium.add(fish2);
    }

    @Test
    public void validateRemoveFish_withValidParameters_shouldRemoveFish() {
        Aquarium aquarium = new Aquarium("aqua", 10);
        Fish fish = new Fish("fish");
        aquarium.add(fish);

        aquarium.remove("fish");

        Assert.assertEquals(0, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateRemoveFish_withInvalidParameters_shouldNotRemoveFish() {
        Aquarium aquarium = new Aquarium("aqua", 10);
        Fish fish = new Fish("fish");
        aquarium.add(fish);

        aquarium.remove("fish2");
    }

    @Test(expected = NullPointerException.class)
    public void validateAquariumName_withNull_shouldThrowException() {
        Aquarium aquarium = new Aquarium(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void validateAquariumName_withEmpty_shouldThrowException() {
        Aquarium aquarium = new Aquarium("   ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateAquariumCapacity_withNegative_shouldThrowException() {
        Aquarium aquarium = new Aquarium("aqua", -1);
    }

    @Test
    public void sellFish_withValidParameters_shouldRemoveFish() {
        Aquarium aquarium = new Aquarium("aqua", 10);
        Fish fish = new Fish("fish");
        aquarium.add(fish);

        Fish actualFish = aquarium.sellFish("fish");

        Assert.assertEquals(fish, actualFish);
        Assert.assertFalse(actualFish.isAvailable());
        Assert.assertEquals(1, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void sellFish_withInvalidParameters_shouldNotRemoveFish() {
        Aquarium aquarium = new Aquarium("aqua", 10);
        Fish fish = new Fish("fish");
        aquarium.add(fish);

        aquarium.sellFish("fish2");
    }

    @Test
    public void report_withValidParameters_shouldReturnReport() {
        Aquarium aquarium = new Aquarium("aqua", 10);
        Fish fish = new Fish("fish");
        aquarium.add(fish);
        Fish fish2 = new Fish("fish2");
        aquarium.add(fish2);

        String expectedReport = "Fish available at aqua: fish, fish2";

        String actualReport = aquarium.report();

        Assert.assertEquals(expectedReport, actualReport);
    }
}

