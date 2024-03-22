package UnitTesting_Exercises.p01_Database;

import org.junit.*;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest { 
    private static final Integer[] ELEMENTS = new Integer[]{4, 6, 9, 32, 5};
    private Database database;

    @Before
    public void setUp () throws OperationNotSupportedException {
        database = new Database (ELEMENTS);
    }

    @Test
    public void when_correctElementsArePassed_then_createDatabaseInstance() throws OperationNotSupportedException {
        assertEquals(ELEMENTS.length, database.getElements().length);
        assertArrayEquals(ELEMENTS, database.getElements());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_elementsMoreThan16_then_exceptionThrown() throws OperationNotSupportedException {
        Integer[] elements = new Integer[17];
         database = new Database(elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_elementsLessThan1_then_exceptionThrown() throws OperationNotSupportedException {
        new Database();
    }

    // add
    @Test
    public void when_validElementsPassedToAdd_then_elementsIsAddedOnLast_position() throws OperationNotSupportedException {
        int element = 15;
        database.add(element);
        Integer[] databaseElements = database.getElements();

        assertEquals(ELEMENTS.length + 1, databaseElements.length);
        assertEquals(Integer.valueOf(element), databaseElements[databaseElements.length - 1]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_invalidElementPassedtoAdd_then_exceptionIsThrown() throws OperationNotSupportedException {
        database.add(null);
    }

    // remove()
    @Test
    public void when_remove_then_lastElementIsRemoved() throws OperationNotSupportedException {
        database.remove();
        Integer[] databaseElements = database.getElements();
        assertEquals(ELEMENTS.length - 1, databaseElements.length);
        assertEquals(ELEMENTS[ELEMENTS.length - 2], databaseElements[databaseElements.length - 1]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_elementsIsEmptyAndRemove_then_exceptionIdThrown() throws OperationNotSupportedException {
        database = new Database(new Integer[1]);
        database.remove();
        database.remove();
    }
}
