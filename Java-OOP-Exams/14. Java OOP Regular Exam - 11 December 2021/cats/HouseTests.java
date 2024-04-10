package cats;

import org.junit.Test;

import static org.junit.Assert.*;

public class HouseTests {

    @Test(expected = IllegalArgumentException.class)
    public void testCreateHouseInvalidCapacity() {
        new House("test_house", -1);
    }

   @Test(expected = NullPointerException.class)
    public void testCreateHouseWithInvalidName() {
        new House(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateHouseWithNameEmpty() {
        new House("  ", 10);
    }

    @Test
    public void testCreateHouse() {
        House house = new House("test_house", 10);
        assertEquals("test_house", house.getName());
        assertEquals(10, house.getCapacity());
    }

    @Test
    public void testAddCat() {
        House house = new House("house", 10);
        Cat silvester = new Cat("Silvester");
        assertEquals(0, house.getCount());
        house.addCat(silvester);
        assertEquals(1, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd_NoCapacity() {
        House house = new House("house", 0);
        Cat silvester = new Cat("Silvester");
        house.addCat(silvester);
    }

    @Test
    public void restRemoveCat() {
        House house = new House("house", 10);
        Cat silvester = new Cat("Silvester");
        house.addCat(silvester);
        assertEquals(1, house.getCount());
        house.removeCat("Silvester");
        assertEquals(0, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void restRemoveNonExistingCat() {
        House house = new House("house", 10);
        house.removeCat("Silvester");
    }

    @Test
    public void testCatForSale() {
        House house = new House("house", 10);
        Cat silvester = new Cat("Silvester"); 
        house.addCat(silvester);
        Cat returnedCat = house.catForSale("Silvester");
        assertFalse(returnedCat.isHungry());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaleNonExistingCat() {
        House house = new House("house", 10);
        house.catForSale("Ivan");
    }

    @Test
    public void testStatistics() {
        House house = new House("house", 10);
        Cat silvester = new Cat("Silvester");
        house.addCat(silvester);
        assertEquals("The cat Silvester is in the house house!", house.statistics());
    }

}