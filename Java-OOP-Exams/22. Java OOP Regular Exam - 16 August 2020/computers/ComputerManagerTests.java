package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ComputerManagerTests {

    private ComputerManager computerManager;
    private Computer computer1;
    private Computer computer2;

    @Before
    public void setUp() {
        this.computerManager = new ComputerManager();
        this.computer1 = new Computer("Dell", "A4532", 300);
        this.computer2 = new Computer("Asus", "ROG", 500);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetComputer() {
        computerManager.getComputers().remove(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullAdd() {
        this.computerManager.addComputer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDuplicate() {
        this.computerManager.addComputer(computer1);
        this.computerManager.addComputer(computer1);
    }

    @Test
    public void testAdd() {
        this.computerManager.addComputer(computer1);
        assertEquals(1, this.computerManager.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGet() {
        this.computerManager.getComputer(null, "test_model");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGet2() {
        this.computerManager.getComputer("test_manufacturer", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNonExisting() {
        this.computerManager.getComputer(computer1.getManufacturer(), computer1.getModel());
    }

    @Test
    public void testGetReturnsCorrect() {
        computerManager.addComputer(computer1);
        computerManager.addComputer(computer2);
        Computer returned = computerManager.getComputer(computer1.getManufacturer(), this.computer1.getModel());
        assertNotNull(returned);
    }

    @Test
    public void testGetByManufacturer() {
        computerManager.addComputer(computer1);
        computerManager.addComputer(computer2);
        List<Computer> list = computerManager.getComputersByManufacturer(computer1.getManufacturer());
        assertNotNull(list);
        assertEquals(list.get(0).getManufacturer(), computer1.getManufacturer());
    }

    @Test
    public void testGetByManufacturerWhenEmpty() {
        List<Computer> list = computerManager.getComputersByManufacturer(computer1.getManufacturer());
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }
}