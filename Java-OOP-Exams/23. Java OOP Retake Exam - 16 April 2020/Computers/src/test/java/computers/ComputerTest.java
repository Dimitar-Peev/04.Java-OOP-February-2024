package computers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComputerTest {

    private Computer computer;

    @Before
    public void setUp() {
        this.computer = new Computer("Lenovo");
        fillComputerWithParts();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThExWhenNameIsNull() {
        computer = new Computer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThExWhenNameIsEmpty() {
        computer = new Computer("");
    }

    @Test
    public void testConstructorShouldSetCorrectName() {
        Computer computer = new Computer("Acer");
        assertEquals("Acer", computer.getName());
    }

    @Test
    public void testGetPartsShouldReturnCollection() {
        assertNotNull(this.computer.getParts());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetPartsShouldReturnUnmodifiableCollection() {
        this.computer.getParts().add(new Part("Mouse", 35));
    }

    @Test
    public void testGetTotalPriceShouldReturnCorrectValue() {
        assertEquals(710.5, this.computer.getTotalPrice(), 0.0);
    }

    @Test
    public void testGetTotalPriceShouldReturnZeroWhenEmpty() {
        Computer computer = new Computer("Acer");
        double actualTotalPrice = computer.getTotalPrice();
        assertEquals(0.0, actualTotalPrice, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddPartShouldThExWhenPartIsNull() {
        this.computer.addPart(null);
    }

    @Test
    public void testAddPartShouldAddPartIsNotNull() {
        this.computer.addPart(new Part("Mouse", 35));
        assertNotNull(computer.getPart("Mouse"));
    }

    @Test
    public void testRemovePartShouldRemovePart() {
        Part mouse = new Part("Mouse", 35);
        assertFalse(computer.removePart(mouse));

        this.computer.addPart(mouse);
        assertTrue(computer.removePart(mouse));
    }

    @Test
    public void testGetPartShouldReturnFirstMatchPart() {
        Part actual = computer.getPart("Monitor");
        assertNotNull(actual);
    }

    @Test
    public void testGetPartShouldReturnNull() {
        assertNull(computer.getPart("Mouse"));
    }

    private void fillComputerWithParts() {
        Part monitor = new Part();
        monitor.setName("Monitor");
        monitor.setPrice(130);
        this.computer.addPart(monitor);
        this.computer.addPart(new Part("Keyboard", 30));
        this.computer.addPart(new Part("Processor", 550.50));
    }
}