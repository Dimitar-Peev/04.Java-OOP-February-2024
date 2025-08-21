package toyStore;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Map;

import static org.junit.Assert.*;

public class ToyStoryTest {
    private ToyStore toyStore;
    private Toy toy1;
    private Toy toy2;

    @Before
    public void setUp() {
        toyStore = new ToyStore();
        toy1 = new Toy("Lego", "id1");
        toy2 = new Toy("Playmobile", "id2");
    }

    @Test
    public void testConstructorInitializesShelves() {
        Map<String, Toy> shelves = toyStore.getToyShelf();
        assertEquals(7, shelves.size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetToyShelfReturnsUnmodifiableMap() {
        toyStore.getToyShelf().put("Z", toy1);
    }

    @Test
    public void testAddToySuccessfully() throws Exception {
        String result = toyStore.addToy("A", toy1);
        assertEquals(toy1, toyStore.getToyShelf().get("A"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToyToNonExistingShelfThrows() throws Exception {
        toyStore.addToy("Z", toy1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToyToAlreadyTakenShelfThrows() throws Exception {
        toyStore.addToy("A", toy1);
        toyStore.addToy("A", toy2);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddToyAlreadyInShelfThrows() throws Exception {
        toyStore.addToy("A", toy1);
        toyStore.addToy("B", toy1);
    }

    @Test
    public void testRemoveToySuccessfully() throws Exception {
        toyStore.addToy("A", toy1);
        String result = toyStore.removeToy("A", toy1);
        assertEquals("Remove toy:id1 successfully!", result);
        assertNull(toyStore.getToyShelf().get("A"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveToyFromNonExistingShelfThrows() {
        toyStore.removeToy("Z", toy1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveToyDifferentToyInShelfThrows() throws Exception {
        toyStore.addToy("A", toy1);
        toyStore.removeToy("A", toy2);
    }

    @Test
    public void testGetManufacturer(){
        Toy toy = new Toy("Lego", "id1");
        assertEquals("Lego", toy.getManufacturer());
    }
}